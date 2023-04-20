package com.googleKeep.googleKeep1.Model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.googleKeep.googleKeep1.DTO.LabelDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class LabelModel {

	@Id
	@GeneratedValue
	@Column(name = "labelId")
	private int labelId;
	private String labelName;
	private LocalDateTime createdDate=LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserModel user;
	
	@ManyToOne
	@JoinColumn(name = "note_id")
	private NoteModel note;
	
	 @ManyToMany
	 @JoinTable(name = "label_note", joinColumns = {@JoinColumn(name = "label_id")},
	 inverseJoinColumns = { @JoinColumn(name = "note_id")})
	 private Set<UserModel> users = new HashSet<>();
	
	
	public LabelModel(LabelDTO labelDto) {
		this.labelName=labelDto.getLabelName();
		
	}
}
