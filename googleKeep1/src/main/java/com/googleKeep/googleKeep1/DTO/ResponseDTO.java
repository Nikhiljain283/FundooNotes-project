package com.googleKeep.googleKeep1.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDTO {

	private String message;
	private Object dataObject;

	public ResponseDTO(String message, Object response) {
		this.message = message;
		this.dataObject = response;
	}

	public ResponseDTO(String message, String msg) {
		this.message = message;
		this.dataObject = msg;
	}
}
