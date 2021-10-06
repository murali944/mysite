package com.mysite.core.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ContainerExporter;
import com.adobe.cq.export.json.ExporterConstants;

/*@Model(adaptables = Resource.class)*/
@Model(adaptables = Resource.class, adapters = { MultiLineModel.class, ComponentExporter.class,
		ContainerExporter.class }, resourceType = MultiLineModel.RESOURCE_TYPE)
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class MultiLineModel {
	private static final Logger LOG = LoggerFactory.getLogger(MultiLineModel.class);

	public final static String RESOURCE_TYPE = "mysite/components/customcomponents/multiline";
	@SlingObject
	private Resource componentResource;
	/*
	 * @ScriptVariable private Resource componentResource;
	 */
	@ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
	private String title;

	@ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
	private List<String> beverages;

	public String getTitle() {
		return title;
	}

	public List<String> getBeverages() {
		beverages = new ArrayList<String>();
		beverages.add("Tea");
		beverages.add("Coffe");
		beverages.add("Boost");
		return beverages;
	}

	public List<Map<String, String>> getOrdersList() {
		List<Map<String, String>> orderList = new ArrayList<Map<String, String>>();
		if (componentResource == null) {
			LOG.info("component resource obj is null");
		}
		try {
			LOG.info("Resource Name -->" + componentResource.getName());
			LOG.info("Resource Name -->" + componentResource.getPath());
			if (componentResource != null) {
				Resource ordersResource = componentResource.getChild("orders");
				for (Resource eachResource : ordersResource.getChildren()) {
					Map<String, String> itemMap = new HashMap<String, String>();
					String drink = eachResource.getValueMap().get("drink", String.class);
					String food = eachResource.getValueMap().get("food", String.class);
					itemMap.put("drink", drink);
					itemMap.put("food", food);
					orderList.add(itemMap);
				}
			}
		} catch (Exception e) {
			LOG.info("\n ERROR while getting Order Details " + e.getMessage());
		}
		return orderList;

	}

	@PostConstruct
	protected void init() {
		LOG.debug("Init method of " + this.getClass());
	}

}
