package com.googleKeep.googleKeep1.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googleKeep.googleKeep1.DTO.LabelDTO;
import com.googleKeep.googleKeep1.Exception.CustomException;
import com.googleKeep.googleKeep1.Model.LabelModel;
import com.googleKeep.googleKeep1.Model.NoteModel;
import com.googleKeep.googleKeep1.Model.UserModel;
import com.googleKeep.googleKeep1.Repository.LabelRepository;
import com.googleKeep.googleKeep1.Repository.NoteRepository;
import com.googleKeep.googleKeep1.Repository.UserRepository;
import com.googleKeep.googleKeep1.Util.Token;

@Service
public class LabelService implements ILabelService {

	@Autowired
	LabelRepository labelRepo;

	@Autowired
	Token tokenUtil;

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	NoteRepository noteRepo;

	@Override
	public LabelModel createLabel(LabelDTO labelDto, String token) {
		int userId = tokenUtil.decodeToken(token);
		UserModel user = userRepo.findById(userId).get();
		Optional<NoteModel> note = noteRepo.findById(labelDto.getNoteId());
		if (user != null ) {
			LabelModel label = new LabelModel(labelDto);
			label.setNote(note.get());
			label.getUsers().add(user);
			labelRepo.save(label);
			return label;
		} else {
			throw new CustomException("label is not added");
		}
	}

	@Override
	public LabelModel updateLabel(int labelId, LabelDTO labelDto, String token) {
		int userId = tokenUtil.decodeToken(token);
		UserModel user = userRepo.findById(userId).get();
		LabelModel label = labelRepo.findById(labelId).get();

		if (user != null) {
			label.setLabelName(labelDto.getLabelName());
			labelRepo.save(label);
			return label;
		} else {
			throw new CustomException("user or label do not exist");
		}
	}

	@Override
	public String deleteLabel(int labelId, String token) {
		int userId = tokenUtil.decodeToken(token);
		UserModel user = userRepo.findById(userId).get();
		LabelModel label = labelRepo.findById(labelId).get();
		if (label != null && user != null) {
			labelRepo.deleteById(labelId);
			return "label is deleted";
		} else {
			throw new CustomException("label not deleted");
		}
	}

	@Override
	public LabelModel getLabelById(int labelId, String token) {
		int userId = tokenUtil.decodeToken(token);
		UserModel user = userRepo.findById(userId).get();
		LabelModel model = labelRepo.findById(labelId).get();
		if (model != null && user != null) {
			return model;
		} else {
			throw new CustomException("label is not present");
		}

	}

	@Override
	public List<LabelModel> getAllLabels(String token) {
		int userId = tokenUtil.decodeToken(token);
		UserModel user = userRepo.findById(userId).get();
		if(user != null) {
		List<LabelModel> model = labelRepo.findAll();
		return model;
		}else {
			throw new CustomException("Either user or label not present");
		}
	}

}
