package com.rajeesh.maersk.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class JobInformation {
	 private String version;
	    private String schedulerName;
	    private String instanceId;

	    private Class threadPoolClass;
	    private int numberOfThreads;

	    private Class schedulerClass;
	    private boolean isClustered;

	    private Class jobStoreClass;
	    private long numberOfJobsExecuted;

	    private Date startTime;
	    private boolean inStandbyMode;

	    private List<String> simpleJobDetail;

	    public String getSchedulerProductName() {
	        return "Quartz Scheduler (spring-boot-starter-quartz)";
	    }
}
