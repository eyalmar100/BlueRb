package com.eyal.bluebrn.dtos.requests;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TicketAvailabilityRequest {
	@NotNull
	private String ticketId;

	private String destinationId;

}
