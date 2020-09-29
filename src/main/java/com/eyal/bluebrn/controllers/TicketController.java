package com.eyal.bluebrn.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyal.bluebrn.dtos.requests.TicketAvailabilityRequest;
import com.eyal.bluebrn.dtos.responses.TicketAvailabilityResponse;
import com.eyal.bluebrn.services.TicketService;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@PostMapping(value = "/availability", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TicketAvailabilityResponse> checkTicketAvailability(
			@RequestBody @Valid TicketAvailabilityRequest ticketAvailabilityRequest) {
		return ResponseEntity.ok(this.ticketService.checkTicketAvailability(ticketAvailabilityRequest));
	}
}
