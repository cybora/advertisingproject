package com.reporting.repository;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReportingMappingSite extends ReportingMappingBase implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String site;

	public ReportingMappingSite(String site, Long requests, Long impressions, Long clicks,
			Long conversions, BigDecimal revenue, BigDecimal cTR, BigDecimal cR, BigDecimal fill_rate,
			BigDecimal eCPM) {
		super(requests, impressions, clicks, conversions, revenue, cTR, cR, fill_rate, eCPM);
		this.site = site;

	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
}
