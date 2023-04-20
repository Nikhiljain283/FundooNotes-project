package com.googleKeep.googleKeep1.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googleKeep.googleKeep1.DTO.NoteDTO;
import com.googleKeep.googleKeep1.Exception.CustomException;
import com.googleKeep.googleKeep1.Model.LabelModel;
import com.googleKeep.googleKeep1.Model.NoteModel;
import com.googleKeep.googleKeep1.Model.UserModel;
import com.googleKeep.googleKeep1.Repository.LabelRepository;
import com.googleKeep.googleKeep1.Repository.NoteRepository;
import com.googleKeep.googleKeep1.Repository.UserRepository;
import com.googleKeep.googleKeep1.Util.Token;

@Service
public class NoteService implements INoteService {

	@Autowired
	NoteRepository noteRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	LabelRepository labelRepo;

	@Autowired
	Token tokenUtil;

	@Override
	public NoteModel addNote(NoteDTO data, String token) {
		int userId = tokenUtil.decodeToken(token);
		UserModel userModel = userRepo.findById(userId).get();
		if (userModel != null) {
			NoteModel note = new NoteModel(data);
			note.setUser(userModel);
			note.getUsers().add(userModel);
			noteRepo.save(note);
			return note;
		} else {
			throw new CustomException("note not saved");
		}
	}

	@Override
	public NoteModel update(NoteDTO data, String token, int id) {
		int userId = tokenUtil.decodeToken(token);
		UserModel user = userRepo.findById(userId).get();
		NoteModel oldNote = noteRepo.findById(id).get();
		if (oldNote != null && userRepo != null) {
			oldNote.setTitle(data.getTitle());
			oldNote.setDescription(data.getDescription());
			noteRepo.save(oldNote);
			return oldNote;

		} else {
			throw new CustomException("Note not updated");
		}
	}
	

	@Override
	public String delete(int id, String token) {
		int userId = tokenUtil.decodeToken(token);
		UserModel user = userRepo.findById(userId).get();
		NoteModel note = noteRepo.findById(id).get();
		if (note != null && user != null) {
			noteRepo.deleteById(id);
			return "successfully deleted";
		}
		throw new CustomException("Not Deleted");
	}

	@Override
	public NoteModel get(int id, String token) {
		int userId = tokenUtil.decodeToken(token);
		UserModel user = userRepo.findById(userId).get();
		if (noteRepo.findById(id).isPresent() && user != null) {
			return noteRepo.findById(id).get();
		} else
			throw new CustomException("note is not present");
	}

	@Override
	public List<NoteModel> getAllNotes(String token) {
		int userId = tokenUtil.decodeToken(token);
		UserModel user = userRepo.findById(userId).get();
		if(user != null) {
		return noteRepo.findAll();
		}else {
			throw new CustomException("note not present");
		}
	}

	@Override
	public NoteModel doPin(int noteId, String token) {
		NoteModel note = noteRepo.findById(noteId).get();
		int userId = tokenUtil.decodeToken(token);
		UserModel user = userRepo.findById(userId).get();
		if (!note.isPinned() && user != null) {
			note.setPinned(true);
			noteRepo.save(note);
			return note;
		} else {
			throw new CustomException("note is already pinned");
		}
	}

	@Override
	public NoteModel doTrash(int noteId, String token) {
		int userId = tokenUtil.decodeToken(token);
		UserModel user = userRepo.findById(userId).get();
		NoteModel note = noteRepo.findById(noteId).get();
		if (!note.isTrashed() && user != null) {
			note.setTrashed(true);
			noteRepo.save(note);
			return note;
		} else {
			throw new CustomException("note is already in thrash");
		}

	}

	@Override
	public NoteModel doArchive(int noteId, String token) {
		int userId = tokenUtil.decodeToken(token);
		UserModel user = userRepo.findById(userId).get();
		NoteModel note = noteRepo.findById(noteId).get();
		if (!note.isArchived() && user != null) {
			note.setArchived(true);
			noteRepo.save(note);
			return note;
		} else {
			throw new CustomException("note is already archived");
		}
	}

	@Override
	public NoteModel addReminder(int noteId, String reminder, String token) {
		int userId = tokenUtil.decodeToken(token);
		UserModel user = userRepo.findById(userId).get();
		NoteModel note = noteRepo.findById(noteId).get();
//		if (note != null && user != null) {
		if (user != null) {
			note.setReminder(reminder);
			noteRepo.save(note);
			return note;
		} else {
			throw new CustomException("remainder is already added");
		}

	}

	@Override
	public NoteModel deleteReminder(int noteId, String token) {
		int userId = tokenUtil.decodeToken(token);
		UserModel user = userRepo.findById(userId).get();
		NoteModel note = noteRepo.findById(noteId).get();
		if (note != null && user != null) {
			note.setReminder(null);
			noteRepo.save(note);
			return note;
		} else {
			throw new CustomException("remainder is already removed");
		}
	}

	@Override
	public NoteModel addLabel(int id, int labelId, String token) {
		int userId = tokenUtil.decodeToken(token);
		UserModel user = userRepo.findById(userId).get();
		NoteModel note = noteRepo.findById(id).get();
		LabelModel label = labelRepo.findById(labelId).get();
		if (note != null && label != null && user != null) {
			note.setLabel(label.getLabelName());
			noteRepo.save(note);
			return note;
		}
		throw new CustomException("label not added");

	}

	@Override
	public NoteModel deleteLabel(int id, int labelId, String token) {
		int userId = tokenUtil.decodeToken(token);
		UserModel user = userRepo.findById(userId).get();
		NoteModel note = noteRepo.findById(id).get();
		LabelModel label = labelRepo.findById(labelId).get();
		if (note != null && label != null && user != null) {
			note.setLabel(null);
			noteRepo.save(note);
			return note;
		}
		return null;
	}

}
