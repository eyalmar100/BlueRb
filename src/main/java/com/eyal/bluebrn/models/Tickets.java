package com.eyal.bluebrn.models;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tickets {
	private List<Ticket> tickets;
}
