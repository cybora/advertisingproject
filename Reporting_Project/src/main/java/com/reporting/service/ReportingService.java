package com.reporting.service;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reporting.model.Reporting;
import com.reporting.repository.ReportingMappingBoth;
import com.reporting.repository.ReportingMappingMonth;
import com.reporting.repository.ReportingMappingSite;
import com.reporting.repository.ReportingRepository;
import com.reporting.service.ReportingConstants;

@Service
public class ReportingService {
	
	@Autowired
	private ReportingRepository reportingRepository;

	private final String PATH = "src/main/resources/csv";

	/**
	 * read the content CSV files
	 */
	public void readRecords() {

		List<String> fileList = listReportFiles();
		
		fileList.forEach(value -> System.out.println(value));
		
		for (int i = 0; i < fileList.size(); i++) {
			String fullFilePath = fileList.get(i);
			
			try (Stream<String> lines = Files.lines(Paths.get(fullFilePath))) {
				List<List<String>> values = lines.skip(1).map(line -> new ArrayList<String>(Arrays.asList(line.split(ReportingConstants.SEPARATOR))))
						.collect(Collectors.toList());

				String[] pathDetails = fullFilePath.split("/");
				String[] fileDetails = pathDetails[pathDetails.length - 1].split("_");
				String month = fileDetails[1];
				
				for (int j = 0; j < values.size(); j++) {
					values.get(j).add(ReportingConstants.MONTH_INDEX, month);
				}

				saveRecords(values);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return list of CSV files in the given path.
	 */
	public List<String> listReportFiles() {
		List<String> fileList = new ArrayList<String>();

		File[] files = new File(PATH).listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith("csv");
			}
		});

		for (File file : files) {
			if (file.isFile()) {
				fileList.add(PATH + "/" + file.getName());
			}
		}

		return fileList;
	}

	/**
	 * @param values that read from the CSV files
	 * making the calculations and saving the results into DB
	 */
	public void saveRecords(List<List<String>> values) {
		for (int i = 0; i < values.size(); i++) {
			String month = values.get(i).get(ReportingConstants.MONTH_INDEX).trim();
			int monthNo = Integer.parseInt(month);
			month = ReportingConstants.months.get(monthNo);
			
			String site = values.get(i).get(ReportingConstants.SITE_INDEX).trim();
			
			Long requests = Long.valueOf(values.get(i).get(ReportingConstants.REQUESTS_INDEX).trim());
						
			Long impressions = Long.valueOf(values.get(i).get(ReportingConstants.IMPRESSIONS_INDEX).trim());
			
			Long clicks = Long.valueOf(values.get(i).get(ReportingConstants.CLICKS_INDEX).trim());
			
			Long conversions = Long.valueOf(values.get(i).get(ReportingConstants.CONVERSIONS_INDEX).trim());
			
			BigDecimal revenue = new BigDecimal(values.get(i).get(ReportingConstants.REVENUE_INDEX).trim());
			
			// calculations for the appropriate metrics according to the given formulas.
			
			BigDecimal cTR = new BigDecimal(((double)clicks / impressions) * 100);
			
			BigDecimal cR = new BigDecimal(((double)conversions / impressions) * 100);
			
			BigDecimal fill_rate = new BigDecimal(((double)impressions / requests) * 100);
			
			BigDecimal eCPM = (revenue.multiply(new BigDecimal(1000))).divide(new BigDecimal(impressions), 2, RoundingMode.HALF_UP);
			
			Reporting reporting = new Reporting(month, site, requests, impressions,
					clicks, conversions, revenue, cTR, cR, fill_rate, eCPM);
			
			reportingRepository.save(reporting);
		}
	}
	
	/**
	 * @param month
	 * @param site
	 * @return Reporting object for the handler to return to the API caller
	 */
	public Reporting createResponse(String month, String site) {
		Reporting reporting = null;
		
		/* Month conversions for numeric and shortened form */
		if (month != null) {
			if (isNumeric(month)) { // numeric 
				int monthNo = Integer.parseInt(month);
				month = ReportingConstants.months.get(monthNo);
			}
			
			if (month.length() == 3) { // shortened form
				month = ReportingConstants.monthMap.get(month);
			}
		}

		/* conversion of incoming underscore to space for site*/
		site = site != null ? site.replace("_", " ") : null;
		
		
		// response creation according to the parameters
		if (month != null && site != null) { // query for month and site
			ReportingMappingBoth mapList = reportingRepository.findByMonthAndSiteIgnoreCase(month, site);
			reporting = createResponseForBothParam(mapList);
		} else if (month != null && site == null) { // query aggregation for month
			ReportingMappingMonth mapList = reportingRepository.findByMonth(month);
			reporting = createResponseForMonthParam(mapList);
		} else if (month == null && site != null) { // query aggregation for site
			ReportingMappingSite mapList = reportingRepository.findBySiteIgnoreCase(site);
			reporting = createResponseForSiteParam(mapList);
		}
		
		return reporting;
	}

	/**
	 * @param map that has mapped according to the given parameters while API called.
	 * @return Reporting object having the fields including month and site columns.
	 */
	public Reporting createResponseForBothParam(ReportingMappingBoth map) {
			Reporting reporting = new Reporting(map.getMonth(),
					map.getSite(), map.getRequests(), map.getImpressions(),
					map.getClicks(), map.getConversions(), map.getRevenue(),
					map.getCTR(), map.getCR(), map.getFill_rate(), 
					map.geteCPM());
		
		return reporting;
	}
	
	/**
	 * @param map that has mapped according to the given parameters while API called.
	 * @return Reporting object having the fields , but site as null
	 */
	public Reporting createResponseForMonthParam(ReportingMappingMonth map) {
		
			Reporting reporting = new Reporting(map.getMonth(),
					null, map.getRequests(), map.getImpressions(),
					map.getClicks(), map.getConversions(), map.getRevenue(),
					map.getCTR(), map.getCR(), map.getFill_rate(), 
					map.geteCPM());
		
		return reporting;
	}
	
	/**
	 * @param map that has mapped according to the given parameters while API called.
	 * @return Reporting object having the fields , but month as null
	 */
	public Reporting createResponseForSiteParam(ReportingMappingSite map) {
		
			Reporting reporting = new Reporting(null,
					map.getSite(), map.getRequests(), map.getImpressions(),
					map.getClicks(), map.getConversions(), map.getRevenue(),
					map.getCTR(), map.getCR(), map.getFill_rate(), 
					map.geteCPM());
		
		return reporting;
	}
	
	/**
	 * @param month
	 * @param site
	 * parameter validation service for the parameters
	 */
	public void validateParameters(String month, String site) {
		
		// at least one parameter should be given
		if (month == null && site == null) {
			throw new IllegalArgumentException("You need to supply at least one parameter ( month or site )");
		}
		
		if (month != null) { // check month parameter if given
			if (isNumeric(month)) {
				int monthNo = Integer.parseInt(month);
				if (monthNo < 1 || monthNo > 12) { // month numeric value should be between 1-12
					throw new IllegalArgumentException("The numerical value for month should be between 1-12");
				}
			} else {
				if (!containsCaseInsensitive(month, ReportingConstants.months)) {
					throw new IllegalArgumentException("The month string value should be defined by its full name or by the first three letter");
				}
			}
		}
		
		if (site != null) { // check site parameter if given

			if (!ReportingConstants.sites.contains(site)) { // check if the site value is as we expected
				throw new IllegalArgumentException(
						"Site value should be one of this: desktop_web , mobile_web, android, iOS");
			}

		}
	}
	

	/**
	 * @param str , month numeric value as string
	 * @return true if the month parameter is numeric , otherwise false
	 */
	public boolean isNumeric(String str) {
		return str.matches("\\d+");
	}
	
	/**
	 * @param str
	 * @param list
	 * @return true if the given month string value is valid ( full month name or the first 3 letters ) , otherwise false
	 */
	public boolean containsCaseInsensitive(String str, List<String> list){
        return list.stream().anyMatch(el -> el.equalsIgnoreCase(str));
    }

}
