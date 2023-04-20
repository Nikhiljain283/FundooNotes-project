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

import com.googleKeep.googleKeep1.DTO.NoteDTO;
import com.googleKeep.googleKeep1.DTO.ResponseDTO;
import com.googleKeep.googleKeep1.Model.NoteModel;
import com.googleKeep.googleKeep1.Service.INoteService;

@RestController
@RequestMapping("/note")
public class NoteController {

	@Autowired
	INoteService noteService;

	@PostMapping("/note")
	public ResponseEntity<ResponseDTO> createNote(@RequestBody NoteDTO data, @RequestHeader String token) {
		NoteModel response = noteService.addNote(data, token);
		ResponseDTO responseDto = new ResponseDTO("note is added", "response");
		return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseDTO> updateNote(@RequestBody NoteDTO data, @RequestHeader String token,
			@PathVariable int id) {
		NoteModel note = noteService.update(data, token, id);
		ResponseDTO responseDto = new ResponseDTO("note updated", note);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable int id, @RequestHeader String token) {
		String response = noteService.delete(id, token);
		ResponseDTO responseDto = new ResponseDTO("Deletion status : ", response);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@GetMapping("/note/{id}")
	public ResponseEntity<Object> getNote(@PathVariable int id, @RequestHeader String token) {
		NoteModel note = noteService.get(id, token);
		ResponseDTO responseDto = new ResponseDTO("Required note is", note);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);

	}

	@GetMapping("/allNotes")
	public ResponseEntity<ResponseDTO> getNotes(@RequestHeader String token) {
		List<NoteModel> notes = noteService.getAllNotes(token);
		ResponseDTO responseDto = new ResponseDTO("Notes : ", notes);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);

	}

	@PutMapping("/pin/{noteId}")
	public ResponseEntity<ResponseDTO> pinNote(@PathVariable int noteId, @RequestHeader String token) {
		NoteModel note = noteService.doPin(noteId, token);
		ResponseDTO response = new ResponseDTO("Note pin done : ", note);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/trash/{noteId}")
	public ResponseEntity<ResponseDTO> trashNote(@PathVariable int noteId, @RequestHeader String token) {
		NoteModel note = noteService.doTrash(noteId, token);
		ResponseDTO response = new ResponseDTO("Note trash done : ", note);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/archive/{noteId}/{token}")
	public ResponseEntity<ResponseDTO> archiveNote(@PathVariable int noteId, @RequestHeader String token) {
		NoteModel note = noteService.doArchive(noteId, token);
		ResponseDTO response = new ResponseDTO("Note archive done : ", note);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/reminder/{id}/{reminder}")
	public ResponseEntity<ResponseDTO> addReminder(@PathVariable int id, @PathVariable String reminder, @RequestHeader String token ) {
		NoteModel note = noteService.addReminder(id, reminder, token);
		ResponseDTO responseDto = new ResponseDTO("Reminder added successfully ", note);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);

	}

	@DeleteMapping("/reminder/{id}")
	public ResponseEntity<ResponseDTO> deleteReminder(@PathVariable int id, @RequestHeader String token) {
		NoteModel note = noteService.deleteReminder(id, token);
		ResponseDTO responseDto = new ResponseDTO("Remainder deleted successfully", note);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}


}
