package com.googleKeep.googleKeep1.Service;

import java.util.List;

import com.googleKeep.googleKeep1.DTO.LabelDTO;
import com.googleKeep.googleKeep1.Model.LabelModel;

public interface ILabelService {

	LabelModel createLabel(LabelDTO labelDto, String token);

	LabelModel updateLabel(int labelId, LabelDTO labelDto, String token);

	String deleteLabel(int labelId, String token);

	LabelModel getLabelById(int labelId, String token);

	List<LabelModel> getAllLabels(String token);

}
