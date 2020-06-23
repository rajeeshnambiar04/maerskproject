package com.rajeesh.maersk.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class JobDetail {
	 private String name;
	    private String group;
	    private String description;
	    private Class jobClass;
	    private boolean concurrentExectionDisallowed;  
	    private boolean persistJobDataAfterExecution;
	    private boolean durable;
	    private boolean requestsRecovery;

	    private List<JobTrigger> triggers;
}
