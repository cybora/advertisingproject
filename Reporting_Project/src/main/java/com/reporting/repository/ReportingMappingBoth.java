package com.reporting.repository;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReportingMappingBoth extends ReportingMappingBase implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String month;
	
	public String site;

	public ReportingMappingBoth(String month, String site, Long requests, Long impressions, Long clicks,
			Long conversions, BigDecimal revenue, BigDecimal cTR, BigDecimal cR, BigDecimal fill_rate,
			BigDecimal eCPM) {
		super(requests, impressions, clicks, conversions, revenue, cTR, cR, fill_rate, eCPM);
		
		this.month = month;
		this.site = site;

	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
}
