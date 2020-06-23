package com.rajeesh.maersk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rajeesh.maersk.model.JobResponse.ResponseType;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class JobResponse {
	  private ResponseType type;
	    private String name;
	    private String group;
	    private boolean result;
	    private String status;

	    public enum ResponseType {
	        DELETE
	    }
}
