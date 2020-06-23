package com.rajeesh.maersk.model;

import lombok.Data;

@Data
public class FilterRequest {
    private Boolean active;
    private String zipFilter;

    public FilterRequest() { }

	public Boolean getActive() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getZipFilter() {
		// TODO Auto-generated method stub
		return null;
	}
}
