package com.eyal.bluebrn.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
	
	@NotEmpty(message = "id is required")
	private String id;
	
	@NotEmpty(message = "destinationId is required")
	@Size(min = 2, max = 14, message = "destinationId must be atleast 2 char len and 14 char max")
	private String destinationId;
	
	@NotEmpty(message = "ticketId is required")
	private String ticketId;
	
	private Baggage baggage;
}