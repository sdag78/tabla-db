package com.stevengiblin.spring.taleemdb.controllers;

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

import com.stevengiblin.spring.taleemdb.dto.CreateCompositionForm;
import com.stevengiblin.spring.taleemdb.dto.EditCompositionForm;
import com.stevengiblin.spring.taleemdb.entities.Composition;
import com.stevengiblin.spring.taleemdb.entities.Piece;
import com.stevengiblin.spring.taleemdb.services.CompositionService;
import com.stevengiblin.spring.taleemdb.services.PieceService;
import com.stevengiblin.spring.taleemdb.util.MyUtil;
import com.stevengiblin.spring.taleemdb.validators.CreateCompositionFormValidator;
import com.stevengiblin.spring.taleemdb.validators.EditCompositionFormValidator;

@Controller
public class CompositionController {

	private static final Logger logger = LoggerFactory.getLogger(CompositionController.class);
	
	private PieceService pieceService;
	private CompositionService compositionService;
	private EditCompositionFormValidator editCompositionFormValidator;
	private CreateCompositionFormValidator createCompositionFormValidator;
	
	@Autowired
	public CompositionController(
			PieceService pieceService, 
			CompositionService compositionService,
			EditCompositionFormValidator editCompositionFormValidator,
			CreateCompositionFormValidator createCompositionFormValidator
			) {
		this.pieceService = pieceService;
		this.compositionService = compositionService;
		this.editCompositionFormValidator = editCompositionFormValidator;
		this.createCompositionFormValidator = createCompositionFormValidator;
	}
	
	@InitBinder("editCompositionForm")
	protected void initEditCompositionBinder(WebDataBinder binder) {
		binder.setValidator(editCompositionFormValidator);
	}
	
	@InitBinder("createCompositionForm")
	protected void initCreateCompositionBinder(WebDataBinder binder) {
		binder.setValidator(createCompositionFormValidator);
	}
	
	@RequestMapping(value = "/pieces/{piecePid}/createComposition", method = RequestMethod.GET)
	public String createComposition(
			@PathVariable("piecePid") String piecePid,
			Model model) throws Exception {
		
		Piece piece = pieceService.findByPiecePidAndUserId(piecePid, MyUtil.getSessionUserId());
		CreateCompositionForm createCompositionForm = new CreateCompositionForm();
		createCompositionForm.setNumberOfColumns(piece.getNumberOfBeats());
		model.addAttribute(createCompositionForm);
		return "composition-create"; 
	}
	
	/*
	 * receive the form data and add a record to the Compositions table
	 * then redirect user to the page for that composition
	 */
	@RequestMapping(value = "/pieces/{piecePid}/createComposition", method = RequestMethod.POST)
	public String createComposition(
			@PathVariable("piecePid") String piecePid,
			@ModelAttribute("createCompositionForm") @Valid CreateCompositionForm createCompositionForm,
			BindingResult result, 
			RedirectAttributes redirectAttributes,
			HttpServletRequest request) throws ServletException {
		
		if (result.hasErrors()) return "composition-create";
		Piece piece = pieceService.findByPiecePidAndUserId(piecePid, MyUtil.getSessionUserId());
		long pieceId = piece.getPieceId();
		createCompositionForm.setPieceId(pieceId);
		compositionService.createComposition(createCompositionForm);
		MyUtil.flash(redirectAttributes,  "success",  "newCompositionCreated");
		return "redirect:/pieces/"+piecePid;
	}
	
	@RequestMapping(value = "/pieces/{piecePid}/compositions", method = RequestMethod.GET)
	public String listCompositions(@PathVariable("piecePid") String piecePid, Model model) {
		
		Piece piece = pieceService.findByPiecePidAndUserId(piecePid, MyUtil.getSessionUserId());
		long pieceId = piece.getPieceId(); 
		model.addAttribute("piece", piece);
		model.addAttribute("compositions", compositionService.getCompositions(pieceId));
		return "composition-list";
	}
	
	@RequestMapping(value = "/pieces/{piecePid}/compositions/{compositionPid}", method = RequestMethod.GET)
	public String showComposition(
			@PathVariable("piecePid") String piecePid,
			@PathVariable("compositionPid") String compositionPid, 
			Model model) {
		
		Piece piece = pieceService.findByPiecePidAndUserId(piecePid, MyUtil.getSessionUserId());
		long pieceId = piece.getPieceId();
		model.addAttribute("piece", piece);
		model.addAttribute("composition", compositionService.findByPieceIdAndCompositionPid(pieceId, compositionPid));
		return "composition-show"; 
	}
	
	@RequestMapping(value = "/pieces/{piecePid}/compositions/{compositionPid}/edit", method = RequestMethod.GET)
	public String editComposition(
			@PathVariable("piecePid") String piecePid,
			@PathVariable("compositionPid") String compositionPid, 
			Model model) {
		
		Piece piece = pieceService.findByPiecePidAndUserId(piecePid, MyUtil.getSessionUserId());
		long pieceId = piece.getPieceId();
		int numberOfColumns = piece.getNumberOfBeats();
		Composition composition = compositionService.findByPieceIdAndCompositionPid(pieceId, compositionPid);
		EditCompositionForm ecf = new EditCompositionForm(composition, numberOfColumns);
		model.addAttribute(ecf);
		return "composition-edit"; 
	}
	
	@RequestMapping(value = "/pieces/{piecePid}/compositions/{compositionPid}/edit", method = RequestMethod.POST)
	public String editComposition(
			@PathVariable("piecePid") String piecePid,
			@PathVariable("compositionPid") String compositionPid,
			@ModelAttribute("editCompositionForm") @Valid EditCompositionForm editCompositionForm,
			BindingResult result, 
			RedirectAttributes redirectAttributes,
			HttpServletRequest request) throws ServletException {
		
		if (result.hasErrors()) {
			logger.info("Validation failure in /editComposition POST method");
			return "composition-edit";
		}
		/* next four lines replace IDs that get nullified in the model between the GET and the POST... wierd */
		Piece piece = pieceService.findByPiecePidAndUserId(piecePid, MyUtil.getSessionUserId());
		long pieceId = piece.getPieceId();
		Composition composition = compositionService.findByPieceIdAndCompositionPid(pieceId, compositionPid);
		long compositionId = composition.getCompositionId();
		
		editCompositionForm.setPieceId(pieceId);
		editCompositionForm.setCompositionId(compositionId);
		compositionService.editComposition(editCompositionForm); // could pass the pids in from the URL
		MyUtil.flash(redirectAttributes,  "success",  "editedCompositionWasSaved");
		return "redirect:/pieces/"+piecePid;
	}

	@RequestMapping(value = "/pieces/{piecePid}/compositions/{compositionPid}/delete", method = RequestMethod.GET)
	public String deleteComposition(
			@PathVariable("piecePid") String piecePid,
			@PathVariable("compositionPid") String compositionPid, 
			Model model) {
		
		Piece piece = pieceService.findByPiecePidAndUserId(piecePid, MyUtil.getSessionUserId());
		Composition composition = compositionService.findByPieceIdAndCompositionPid(piece.getPieceId(), compositionPid);
		model.addAttribute(piece);
		model.addAttribute(composition);
		return "composition-delete-confirm";
	}
	
	@RequestMapping(value = "/pieces/{piecePid}/compositions/{compositionPid}/deleteConfirm", method = RequestMethod.GET)
	public String deleteCompositionConfirm(
			@PathVariable("piecePid") String piecePid,
			@PathVariable("compositionPid") String compositionPid, 
			RedirectAttributes redirectAttributes,
			Model model) {
		 
		long pieceId = pieceService.getPieceIdFromPiecePid(piecePid);
		compositionService.deleteByPieceIdAndCompositionPid(pieceId, compositionPid);
		MyUtil.flash(redirectAttributes,  "success",  "compositionWasDeleted");
		return "redirect:/pieces/"+piecePid;
	}
	
}
