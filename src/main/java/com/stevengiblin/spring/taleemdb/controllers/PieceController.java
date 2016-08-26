package com.stevengiblin.spring.taleemdb.controllers;

import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stevengiblin.spring.taleemdb.dto.CreatePieceForm;
import com.stevengiblin.spring.taleemdb.dto.EditPieceForm;
import com.stevengiblin.spring.taleemdb.entities.Composition;
import com.stevengiblin.spring.taleemdb.entities.Piece;
import com.stevengiblin.spring.taleemdb.services.CompositionService;
import com.stevengiblin.spring.taleemdb.services.PieceService;
import com.stevengiblin.spring.taleemdb.util.MyUtil;
import com.stevengiblin.spring.taleemdb.validators.CreatePieceFormValidator;
import com.stevengiblin.spring.taleemdb.validators.EditPieceFormValidator;

@Controller
public class PieceController {

	private static final Logger logger = LoggerFactory.getLogger(PieceController.class);
	
	private PieceService pieceService;
	private CompositionService compositionService;
	private CreatePieceFormValidator createPieceFormValidator;
	private EditPieceFormValidator editPieceFormValidator;
	
	@Autowired
	public PieceController(
			PieceService pieceService, 
			CreatePieceFormValidator createPieceFormValidator,
			CompositionService compositionService,
			EditPieceFormValidator editPieceFormValidator) {
		this.pieceService = pieceService;
		this.compositionService = compositionService;
		this.createPieceFormValidator = createPieceFormValidator;
		this.editPieceFormValidator = editPieceFormValidator;
	}
	
	@InitBinder("createPieceForm")
	protected void initCreatePieceBinder(WebDataBinder binder) {
		binder.setValidator(createPieceFormValidator);
	}
	
	@InitBinder("editPieceForm")
	protected void initEditPieceBinder(WebDataBinder binder) {
		binder.setValidator(editPieceFormValidator);
	}
	
	@RequestMapping(value = "/createPiece", method = RequestMethod.GET)
	public String createPiece(Model model) {
		model.addAttribute(new CreatePieceForm());
		return "piece-create"; 
	}
	
	@RequestMapping(value = "/createPiece", method = RequestMethod.POST)
	public String createPiece(
			@ModelAttribute("createPieceForm") @Valid CreatePieceForm createPieceForm,
			BindingResult result, 
			RedirectAttributes redirectAttributes,
			HttpServletRequest request) throws ServletException {
		
		long piecePid = pieceService.createPiece(createPieceForm);
		MyUtil.flash(redirectAttributes,  "success",  "newPieceCreated");
		return "redirect:/pieces/"+piecePid;
	}
	
	@RequestMapping(value = "/pieces", method = RequestMethod.GET)
	public String listPieces(Model model) {
		List<Piece> pieces = pieceService.getPieces();
		pieces.sort(new Comparator<Piece>() {

			@Override
			public int compare(Piece o1, Piece o2) {
				return o1.getPaltaTableNumber().compareTo(o2.getPaltaTableNumber());
			}
			
		});
		model.addAttribute("userPieces", pieces);
		return "piece-list";
	}
	
	@RequestMapping(value = "/pieces/{piecePid}", method = RequestMethod.GET)
	public String showPiece(@PathVariable("piecePid") String piecePid, Model model) {
		
		Piece piece = pieceService.findByPiecePidAndUserId(piecePid, MyUtil.getSessionUserId());
		List<Composition> composition = compositionService.findByPieceId(piece.getPieceId());
		model.addAttribute("piece", piece);
		model.addAttribute("compositions", composition);
		return "piece-show"; 
	}
	
	@RequestMapping(value = "/pieces/{piecePid}/edit", method = RequestMethod.GET)
	public String editPiece(
			@PathVariable("piecePid") String piecePid,
			Model model 
			) {
		
		logger.info("In /editPiece GET method");
		Piece piece = pieceService.findByPiecePidAndUserId(piecePid, MyUtil.getSessionUserId());
		EditPieceForm editPieceForm = new EditPieceForm(piece);
		editPieceForm.setPieceId(piece.getPieceId());
		model.addAttribute(editPieceForm);
		return "piece-edit"; 
	}
	
	@RequestMapping(value = "/pieces/{piecePid}/edit", method = RequestMethod.POST)
	public String editPiece(
			@PathVariable("piecePid") String piecePid,
			@ModelAttribute("editPieceForm") @Valid EditPieceForm editPieceForm,
			BindingResult result, 
			RedirectAttributes redirectAttributes,
			HttpServletRequest request) throws ServletException {
		
		if (result.hasErrors()) return "/piece-edit"; 
		Piece piece = pieceService.findByPiecePidAndUserId(piecePid, MyUtil.getSessionUserId());
		if (!MyUtil.checkUserIdValid(piece.getUserId())) {
			MyUtil.flash(redirectAttributes,  "error",  "userAccessViolation");
			return "redirect:/";
		}
		pieceService.editPiece(piecePid, editPieceForm);
		MyUtil.flash(redirectAttributes,  "success",  "editedPieceWasSaved");
		return "redirect:/pieces/"+piecePid;
	}
	
}
