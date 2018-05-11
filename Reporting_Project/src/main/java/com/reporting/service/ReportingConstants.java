package com.reporting.service;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReportingConstants {

	static final String SEPARATOR = ",";
	static final String DIR_SEPARATOR = "/";
	static final String FILE_DETAIL_SEPARATOR = "_";

	static final int SITE_INDEX = 0;
	static final int REQUESTS_INDEX = 1;
	static final int IMPRESSIONS_INDEX = 2;
	static final int CLICKS_INDEX = 3;
	static final int CONVERSIONS_INDEX = 4;
	static final int REVENUE_INDEX = 5;
	static final int MONTH_INDEX = 6;
	
	static final List<String> months = Collections.unmodifiableList(Arrays.asList(" ", "January", "February",
											"March", "April", "May",
											"June", "July", "August",
											"September", "October", "November", "December",
											"jan", "feb", "mar", "apr", "jun", "jul", "aug",
											"sep", "oct", "nov", "dec"));
	
	static final List<String> sites = Collections.unmodifiableList(
		    Arrays.asList("desktop_web", "mobile_web", "android", "ios"));

	static final Map<String, String> monthMap = Stream.of(
	            new SimpleEntry<>("jan", "January"),
	            new SimpleEntry<>("feb", "February"),
	            new SimpleEntry<>("march", "March"),
	            new SimpleEntry<>("april", "April"),
	            new SimpleEntry<>("may", "May"),
	            new SimpleEntry<>("june", "June"),
	            new SimpleEntry<>("july", "July"),
	            new SimpleEntry<>("aug", "August"),
	            new SimpleEntry<>("sep", "September"),
	            new SimpleEntry<>("oct", "October"),
	            new SimpleEntry<>("nov", "November"),
	            new SimpleEntry<>("dec", "December"))
	            .collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));

}
