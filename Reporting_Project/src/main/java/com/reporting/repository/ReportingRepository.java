package com.reporting.repository;

import org.springframework.stereotype.Repository;

import com.reporting.model.Reporting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ReportingRepository extends JpaRepository<Reporting, Long>{
	
	/**
	 * @param month
	 * @param site
	 * @return ReportingMappingBoth object having the fields including month and site columns.
	 */
	@Query("select new com.reporting.repository.ReportingMappingBoth(a.month, a.site,"
			+ " a.requests, a.impressions, a.clicks, a.conversions,"
			+ "a.revenue, a.CTR, a.CR, a.fill_rate, a.eCPM) from Reporting a "
			+ "where LOWER(:month) like LOWER(a.month) and LOWER(:site) like LOWER(a.site)")
	ReportingMappingBoth findByMonthAndSiteIgnoreCase(@Param("month")String month, @Param("site")String site);
	
	/**
	 * @param month
	 * @return ReportingMappingMonth object having the aggregated fields including month but excluding site columns.
	 */
	@Query("select new com.reporting.repository.ReportingMappingMonth(a.month, sum(a.requests),"
			+ " sum(a.impressions),"
			+ " sum(a.clicks), sum(a.conversions),"
			+ " sum(a.revenue), sum(a.CTR), sum(a.CR), sum(a.fill_rate),"
			+ " sum(a.eCPM)) from Reporting a group by a.month "
			+ "having LOWER(:month) like LOWER(a.month)")
	ReportingMappingMonth findByMonth(@Param("month") String month);
	
	/**
	 * @param site
	 * @return ReportingMappingSite object having the aggregated fields including site but excluding month columns.
	 */
	@Query("select new com.reporting.repository.ReportingMappingSite(a.site, sum(a.requests), "
			+ "sum(a.impressions),"
			+ " sum(a.clicks), sum(a.conversions),"
			+ "sum(a.revenue), sum(a.CTR), sum(a.CR), sum(a.fill_rate),"
			+ " sum(a.eCPM)) from Reporting a group by a.site "
			+ "having LOWER(:site) like LOWER(a.site)")
	ReportingMappingSite findBySiteIgnoreCase(@Param("site")String site);
}
