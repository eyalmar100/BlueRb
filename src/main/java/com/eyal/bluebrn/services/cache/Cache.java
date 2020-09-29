package com.eyal.bluebrn.services.cache;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cache {
	private Object id;
	private Object data;
	private Long activeTill;
}
