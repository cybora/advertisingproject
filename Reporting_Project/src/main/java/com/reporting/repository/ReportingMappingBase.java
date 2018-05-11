package com.reporting.repository;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReportingMappingBase implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Long requests;
	
	public Long impressions;
	
	public Long clicks;
	
	public Long conversions;
	
	public BigDecimal revenue;
	
	public BigDecimal CTR;
	
	public BigDecimal CR;
	
	public BigDecimal fill_rate;
	
	public BigDecimal eCPM;

	public ReportingMappingBase(Long requests, Long impressions, Long clicks,
			Long conversions, BigDecimal revenue, BigDecimal cTR, BigDecimal cR, BigDecimal fill_rate,
			BigDecimal eCPM) {
		this.requests = requests;
		this.impressions = impressions;
		this.clicks = clicks;
		this.conversions = conversions;
		this.revenue = revenue;
		CTR = cTR;
		CR = cR;
		this.fill_rate = fill_rate;
		this.eCPM = eCPM;
	}

	public Long getRequests() {
		return requests;
	}

	public void setRequests(Long requests) {
		this.requests = requests;
	}

	public Long getImpressions() {
		return impressions;
	}

	public void setImpressions(Long impressions) {
		this.impressions = impressions;
	}

	public Long getClicks() {
		return clicks;
	}

	public void setClicks(Long clicks) {
		this.clicks = clicks;
	}

	public Long getConversions() {
		return conversions;
	}

	public void setConversions(Long conversions) {
		this.conversions = conversions;
	}

	public BigDecimal getRevenue() {
		return revenue;
	}

	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}

	public BigDecimal getCTR() {
		return CTR;
	}

	public void setCTR(BigDecimal cTR) {
		CTR = cTR;
	}

	public BigDecimal getCR() {
		return CR;
	}

	public void setCR(BigDecimal cR) {
		CR = cR;
	}

	public BigDecimal getFill_rate() {
		return fill_rate;
	}

	public void setFill_rate(BigDecimal fill_rate) {
		this.fill_rate = fill_rate;
	}

	public BigDecimal geteCPM() {
		return eCPM;
	}

	public void seteCPM(BigDecimal eCPM) {
		this.eCPM = eCPM;
	}
	
}

