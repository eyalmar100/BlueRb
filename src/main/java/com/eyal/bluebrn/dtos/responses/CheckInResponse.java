package com.eyal.bluebrn.dtos.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckInResponse {
	private boolean checkedIn;
}
