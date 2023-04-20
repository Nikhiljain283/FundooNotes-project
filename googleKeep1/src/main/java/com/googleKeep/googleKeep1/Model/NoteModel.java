package com.googleKeep.googleKeep1.Model;

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

import com.googleKeep.googleKeep1.DTO.NoteDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class NoteModel {

	@Id
	@GeneratedValue
	@Column(name = "noteId")
	private int noteId;
	private String title;
	private String description;

	private boolean isPinned;
	private boolean isArchived;
	private boolean isTrashed;
	
	 private String reminder;
	 private String label;
	 
	 @ManyToOne
	 @JoinColumn(name = "user_id")
	 private UserModel user;
	 
	 @ManyToMany
	 @JoinTable(name = "note_user", joinColumns = {@JoinColumn(name = "note_id")},
	 inverseJoinColumns = { @JoinColumn(name = "user_id")})
	 private Set<UserModel> users = new HashSet<>();

	public NoteModel(NoteDTO noteDto) {
		this.title = noteDto.getTitle();
		this.description = noteDto.getDescription();
	

	}

}
