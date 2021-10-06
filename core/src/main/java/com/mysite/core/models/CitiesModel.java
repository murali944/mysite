package com.mysite.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;

@Model(adaptables = SlingHttpServletRequest.class, adapters = CitiesModel.class, resourceType = CitiesModel.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

@Exporter(name = "jackson", extensions = "json", selector = "geeks", options = {
		@ExporterOption(name = "SerializationFeature.WRAP_ROOT_VALUE", value = "true"),
		@ExporterOption(name = "MapperFeature.SORT_PROPERTIES_ALPHABETICALLY", value = "true") })
public class CitiesModel {
	private static final Logger LOG = LoggerFactory.getLogger(CitiesModel.class);
	final protected static String RESOURCE_TYPE = "mysite/components/customcomponents/cities";

	@Inject
	Resource resource;

	@ScriptVariable
	Page currentPage;

	@ValueMapValue
	private List<String> cities;

	public List<String> getCities() {
		if (cities != null) {
			return new ArrayList<String>(cities);
		} else {
			return Collections.emptyList();
		}
	}

	public List<Map<String, String>> getTownsMap() {
		List<Map<String, String>> townsList = new ArrayList<Map<String, String>>();
		try {
			Resource componentResource = resource.getChild("towns");
			if (componentResource != null) {
				for (Resource eachTownRes : componentResource.getChildren()) {
					Map<String, String> townMap = new HashMap<String, String>();
					String townName = eachTownRes.getValueMap().get("name", String.class);
					String headName = eachTownRes.getValueMap().get("population", String.class);
					townMap.put("name", townName);
					townMap.put("population", headName);
					townsList.add(townMap);
					LOG.info("Town details-->"+townMap.get("name") +"---"+townMap.get("population"));
				}
			}
		} catch (Exception e) {
			LOG.info("error occured while getting town details-->"+e.getMessage());
		}
		return townsList;
	}
	
	  public List<Map<String, String>> getBookDetailsWithMap() {
	        List<Map<String, String>> bookDetailsMap=new ArrayList<>();
	        try {
	            Resource bookDetail=resource.getChild("bookdetailswithmap");
	            if(bookDetail!=null){
	                for (Resource book : bookDetail.getChildren()) {
	                    Map<String,String> bookMap=new HashMap<>();
	                    bookMap.put("bookname",book.getValueMap().get("bookname",String.class));
	                    bookMap.put("booksubject",book.getValueMap().get("booksubject",String.class));
	                    bookMap.put("publishyear",book.getValueMap().get("publishyear",String.class));
	                    bookDetailsMap.add(bookMap);
	                }
	            }
	        }catch (Exception e){
	            LOG.info("\n ERROR while getting Book Details {} ",e.getMessage());
	        }
	        return bookDetailsMap;
	    }


}
