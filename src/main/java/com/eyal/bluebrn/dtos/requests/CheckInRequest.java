package com.eyal.bluebrn.dtos.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckInRequest {
	
	
	
	 
	@NotNull(message = "destinationId is required")
	@Size(min = 2, max = 14, message = "First name must be atleast 2 char len and 14 char max")
	private String destinationId;

	@NotNull( message = "baggageId could not be bull")
	private String baggageId;
}
