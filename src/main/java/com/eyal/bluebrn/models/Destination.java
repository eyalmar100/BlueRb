package com.eyal.bluebrn.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Destination {
	private String id;
	private String name;
}