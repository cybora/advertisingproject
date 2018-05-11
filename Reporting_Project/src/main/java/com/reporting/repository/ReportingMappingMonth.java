package com.reporting.repository;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReportingMappingMonth extends ReportingMappingBase implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String month;

	public ReportingMappingMonth(String month, Long requests, Long impressions, Long clicks,
			Long conversions, BigDecimal revenue, BigDecimal cTR, BigDecimal cR, BigDecimal fill_rate,
			BigDecimal eCPM) {
		super(requests, impressions, clicks, conversions, revenue, cTR, cR, fill_rate, eCPM);
		this.month = month;

	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}
