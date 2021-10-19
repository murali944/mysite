package com.mysite.core.models;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = SlingHttpServletRequest.class, adapters = RealMFDataModel.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class RealMFDataModel {
	private static final Logger LOG = LoggerFactory.getLogger(RealMFDataModel.class);

	@ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
	private String publishdate;

	@ChildResource
	private List<MfDataModel> detailsList;

	public String getPublishdate() {
		return publishdate;
	}

	public List<MfDataModel> getDetailsList() {
		return detailsList;
	}

}
