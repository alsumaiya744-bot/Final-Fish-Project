package com.alsultanseafood.fishsupplychainmanagement.report.controller;



import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.alsultanseafood
        .fishsupplychainmanagement
        .report.dto.ReportDto;
import com.alsultanseafood
        .fishsupplychainmanagement
        .report.service.ReportService;

@RestController
@RequestMapping("/api/reports")

public class ReportController {

    private final ReportService
            reportService;

    public ReportController(
            ReportService reportService) {

        this.reportService =
                reportService;
    }

    @GetMapping
    public ReportDto getReport(

            @RequestParam
            LocalDate startDate,

            @RequestParam
            LocalDate endDate) {

        return reportService
                .getReport(
                        startDate,
                        endDate);
    }


    @GetMapping("/pdf")
public ResponseEntity<byte[]>
downloadPdf(

        @RequestParam
        LocalDate startDate,

        @RequestParam
        LocalDate endDate)
        throws Exception {

    return reportService
            .downloadPdf(
                    startDate,
                    endDate);
}
}