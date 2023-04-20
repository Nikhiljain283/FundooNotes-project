package com.googleKeep.googleKeep1.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoteDTO {
	private String title;
	private String description;
	private int userId;

	
}
