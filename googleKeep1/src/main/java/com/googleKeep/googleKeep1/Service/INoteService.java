package com.googleKeep.googleKeep1.Service;

import java.util.List;

import com.googleKeep.googleKeep1.DTO.NoteDTO;
import com.googleKeep.googleKeep1.Model.NoteModel;

public interface INoteService {

	NoteModel addNote(NoteDTO data, String token);

	NoteModel update(NoteDTO data, String token, int id);

	String delete(int id, String token);

	NoteModel get(int id, String token);

	List<NoteModel> getAllNotes(String token);

	NoteModel doPin(int noteId, String token);

	NoteModel doTrash(int noteId, String token);

	NoteModel doArchive(int noteId, String token);

	NoteModel addReminder(int id, String reminder, String token);

	NoteModel deleteReminder(int id, String token);

	NoteModel addLabel(int id, int labelId, String token);

	NoteModel deleteLabel(int id, int labelId, String token);

	

}
