package com.reporting.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table
@JsonInclude(value=Include.NON_NULL)
public class Reporting implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String month;
	
	private String site;
	
	private Long requests;
	
	private Long impressions;
	
	private Long clicks;
	
	private Long conversions;
	
	private BigDecimal revenue;
	
	private BigDecimal CTR;
	
	private BigDecimal CR;
	
	private BigDecimal fill_rate;
	
	private BigDecimal eCPM;
	
	

	public Reporting(String month, String site, Long requests, Long impressions, Long clicks, Long conversions,
			BigDecimal reveneu, BigDecimal cTR, BigDecimal cR, BigDecimal fill_rate, BigDecimal eCPM) {
		this.month = month;
		this.site = site;
		this.requests = requests;
		this.impressions = impressions;
		this.clicks = clicks;
		this.conversions = conversions;
		this.revenue = reveneu;
		CTR = cTR;
		CR = cR;
		this.fill_rate = fill_rate;
		this.eCPM = eCPM;
	}
	
	
	
	public Reporting(String site, Long requests, Long impressions, Long clicks, Long conversions, BigDecimal revenue,
			BigDecimal cTR, BigDecimal cR, BigDecimal fill_rate, BigDecimal eCPM) {
		this.site = site;
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



	public Reporting() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
