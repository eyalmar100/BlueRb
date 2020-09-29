package com.eyal.bluebrn.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Baggage {
	private String id;
	private String weight;
}