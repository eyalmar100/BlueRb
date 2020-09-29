package com.eyal.bluebrn.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ticket {
	private String id;
	private Destination destination;
	private boolean available;
}