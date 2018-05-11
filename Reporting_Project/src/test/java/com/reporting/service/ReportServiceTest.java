package com.reporting.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import com.reporting.repository.ReportingMappingMonth;
import com.reporting.repository.ReportingRepository;
import com.reporting.service.ReportingService;

@RunWith(SpringRunner.class)
public class ReportServiceTest {
	
	@TestConfiguration
    static class ReportServiceTestContextConfiguration {
		 @Bean
	        public ReportingService employeeService() {
	            return new ReportingService();
	        }
	}
	
	@Autowired
    private ReportingService reportingService;
 
    @MockBean
    private ReportingRepository reportingRepository;
    
    @Before
    public void setUp() {
    	ReportingMappingMonth mappingMonth = new ReportingMappingMonth("March", 123l, 321l, 343l, 232l, new BigDecimal(2312.23).setScale(2, RoundingMode.HALF_UP), new BigDecimal(0.32),
        		new BigDecimal(0.15).setScale(2, RoundingMode.HALF_UP), new BigDecimal(1231321), new BigDecimal(123123));

     
        Mockito.when(reportingRepository.findByMonth(mappingMonth.getMonth()))
          .thenReturn(mappingMonth);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void whenInValidNumericMonth_MoreThan12_thenValidationShouldThrowException() {
    	String month = "13";
    	reportingService.validateParameters(month, null);
     }
    
    @Test(expected=IllegalArgumentException.class)
    public void whenInValidNumericMonth__LessThan1_thenValidationShouldThrowException() {
    	String month = "0";
    	reportingService.validateParameters(month, null);
     }
    
    @Test(expected=IllegalArgumentException.class)
    public void whenInValidMonthString_thenValidationShouldThrowException() {
    	String month = "abc";
    	reportingService.validateParameters(month, null);
     }
    
    @Test(expected=IllegalArgumentException.class)
    public void whenInValidSite_thenValidationShouldThrowException() {
    	String site = "blackberry";
    	reportingService.validateParameters(null, site);
     }

}
