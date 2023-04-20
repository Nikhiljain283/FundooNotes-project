package com.googleKeep.googleKeep1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.googleKeep.googleKeep1.DTO.LabelDTO;
import com.googleKeep.googleKeep1.DTO.ResponseDTO;
import com.googleKeep.googleKeep1.Model.LabelModel;
import com.googleKeep.googleKeep1.Service.ILabelService;

@RequestMapping("/label")
@RestController
public class LabelController {

	@Autowired
	ILabelService labelService;

	@PostMapping("/createlabel")
	public ResponseEntity<ResponseDTO> createLabel(@RequestBody LabelDTO labelDto, @RequestHeader String token) {
		LabelModel label = labelService.createLabel(labelDto, token);
		ResponseDTO responseDto = new ResponseDTO("Label created successfully", label);
		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
	}

	@PutMapping("/{labelId}")
	public ResponseEntity<ResponseDTO> updateLabel(@PathVariable int labelId, @RequestBody LabelDTO labelDto,
			@RequestHeader String token) {
		LabelModel label = labelService.updateLabel(labelId, labelDto, token);
		ResponseDTO responseDto = new ResponseDTO("Label updated successfully", label);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@DeleteMapping("/{labelId}")
	public ResponseEntity<ResponseDTO> deleteLabel(@PathVariable int labelId, @RequestHeader String token) {
		String label = labelService.deleteLabel(labelId, token);
		ResponseDTO responseDto = new ResponseDTO("Label deleted successfully ", label);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@GetMapping("/{labelId}")
	public ResponseEntity<ResponseDTO> getLabelById(@PathVariable int labelId, @RequestHeader String token) {
		LabelModel label = labelService.getLabelById(labelId, token);
		ResponseDTO responseDto = new ResponseDTO("Required label is ", label);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);

	}

	@GetMapping("/labels")
	public ResponseEntity<ResponseDTO> getAllLabels(@RequestHeader String token) {
		List<LabelModel> label = labelService.getAllLabels(token);
		ResponseDTO responseDto = new ResponseDTO("Required label is ", label);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);

	}

}
