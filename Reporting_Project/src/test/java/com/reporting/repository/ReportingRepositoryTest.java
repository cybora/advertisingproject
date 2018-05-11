package com.reporting.repository;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.reporting.model.Reporting;
import com.reporting.repository.ReportingMappingBoth;
import com.reporting.repository.ReportingMappingMonth;
import com.reporting.repository.ReportingMappingSite;
import com.reporting.repository.ReportingRepository;
import com.reporting.service.ReportingService;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableAutoConfiguration
@EntityScan(basePackageClasses=Reporting.class)
@ContextConfiguration(classes = {ReportingService.class})
public class ReportingRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private ReportingRepository reportingRepository;
    
    @Test
    public void whenFindByMonth_thenReturnMonth() {
        // given
        Reporting reporting = new Reporting("March", "android", 123l, 321l, 343l, 232l, new BigDecimal(2312.23), new BigDecimal(0.32),
        		new BigDecimal(0.15), new BigDecimal(1231321), new BigDecimal(123123));
        entityManager.persist(reporting);
        entityManager.flush();
     
        // when
        ReportingMappingMonth found = reportingRepository.findByMonth(reporting.getMonth());
        
        String month = found.getMonth();
     
        // then
        assertEquals(month, reporting.getMonth());
    }
    
    @Test
    public void whenFindByMonth_thenReturnRevenue() {
        // given
        Reporting reporting = new Reporting("March", "android", 123l, 321l, 343l, 232l, new BigDecimal(2312.23).setScale(2, RoundingMode.HALF_UP), new BigDecimal(0.32),
        		new BigDecimal(0.15), new BigDecimal(1231321), new BigDecimal(123123));
        entityManager.persist(reporting);
        entityManager.flush();
     
        // when
        ReportingMappingMonth found = reportingRepository.findByMonth(reporting.getMonth());
        
        BigDecimal revenue = found.getRevenue();
     
        // then
        assertEquals(revenue, reporting.getRevenue());
    }
    
    @Test
    public void whenFindBySite_thenReturnSite() {
        // given
        Reporting reporting = new Reporting("March", "android", 123l, 321l, 343l, 232l, new BigDecimal(2312.23).setScale(2, RoundingMode.HALF_UP), new BigDecimal(0.32),
        		new BigDecimal(0.15), new BigDecimal(1231321), new BigDecimal(123123));
        entityManager.persist(reporting);
        entityManager.flush();
     
        // when
        ReportingMappingSite found = reportingRepository.findBySiteIgnoreCase(reporting.getSite());
        
        String site = found.getSite();
     
        // then
        assertEquals(site, reporting.getSite());
    }
    
    @Test
    public void whenFindBySite_thenReturnRequests() {
        // given
        Reporting reporting = new Reporting("March", "android", 123l, 321l, 343l, 232l, new BigDecimal(2312.23).setScale(2, RoundingMode.HALF_UP), new BigDecimal(0.32),
        		new BigDecimal(0.15), new BigDecimal(1231321), new BigDecimal(123123));
        entityManager.persist(reporting);
        entityManager.flush();
     
        // when
        ReportingMappingSite found = reportingRepository.findBySiteIgnoreCase(reporting.getSite());
        
        Long requests = found.getRequests();
     
        // then
        assertEquals(requests, reporting.getRequests());
    }
    
    @Test
    public void whenFindByMonthAndSite_thenReturnCR() {
        // given
        Reporting reporting = new Reporting("March", "android", 123l, 321l, 343l, 232l, new BigDecimal(2312.23).setScale(2, RoundingMode.HALF_UP), new BigDecimal(0.32),
        		new BigDecimal(0.15).setScale(2, RoundingMode.HALF_UP), new BigDecimal(1231321), new BigDecimal(123123));
        entityManager.persist(reporting);
        entityManager.flush();
     
        // when
        ReportingMappingBoth found = reportingRepository.findByMonthAndSiteIgnoreCase(reporting.getMonth(), reporting.getSite());
        
        BigDecimal requests = found.getCR();
     
        // then
        assertEquals(requests, reporting.getCR());
    }
}
