package com.alsultanseafood.fishsupplychainmanagement.report.service;





import java.time.LocalDate;

import org.springframework.http.ResponseEntity;

import com.alsultanseafood
        .fishsupplychainmanagement
        .report.dto.ReportDto;

public interface ReportService {

    ReportDto getReport(
            LocalDate startDate,
            LocalDate endDate);


            ResponseEntity<byte[]>
downloadPdf(
        LocalDate startDate,
        LocalDate endDate)
        throws Exception;
}
