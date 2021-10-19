package com.mysite.core.models;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = SlingHttpServletRequest.class, adapters = SkillModel.class, resourceType = SkillModel.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

@Exporter(name = "jackson", extensions = "json", selector = "geeks", options = {
		@ExporterOption(name = "SerializationFeature.WRAP_ROOT_VALUE", value = "true"),
		@ExporterOption(name = "MapperFeature.SORT_PROPERTIES_ALPHABETICALLY", value = "true") })

public class SkillModel {
	
	private static final Logger LOG = LoggerFactory.getLogger(SkillModel.class);
	final protected static String RESOURCE_TYPE = "mysite/components/customcomponents/skill";

	@ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
	private String firstname;

	@ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
	private String surname;

	@ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
	private String address;

	@ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
	private String rootpath;

	public String getFirstname() {
		return firstname;
	}

	public String getSurname() {
		return surname;
	}

	public String getAddress() {
		return address;
	}

	public String getRootpath() {
		return rootpath;
	}

}
