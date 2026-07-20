package com.alsultanseafood.fishsupplychainmanagement.report.service;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


import java.io.ByteArrayOutputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.stereotype.Service;

import com.alsultanseafood
        .fishsupplychainmanagement
        .expense.entity.Expense;
import com.alsultanseafood
        .fishsupplychainmanagement
        .expense.repository.ExpenseRepository;
import com.alsultanseafood
        .fishsupplychainmanagement
        .order.entity.Order;
import com.alsultanseafood
        .fishsupplychainmanagement
        .order.repository.OrderRepository;
import com.alsultanseafood
        .fishsupplychainmanagement
        .procurement.entity.Procurement;
import com.alsultanseafood
        .fishsupplychainmanagement
        .procurement.repository.ProcurementRepository;
import com.alsultanseafood
        .fishsupplychainmanagement
        .report.dto.ReportDto;

@Service
public class ReportServiceImpl
        implements ReportService {

    private final OrderRepository
            orderRepository;

    private final ProcurementRepository
            procurementRepository;

    private final ExpenseRepository
            expenseRepository;

    public ReportServiceImpl(
            OrderRepository orderRepository,
            ProcurementRepository procurementRepository,
            ExpenseRepository expenseRepository) {

        this.orderRepository =
                orderRepository;

        this.procurementRepository =
                procurementRepository;

        this.expenseRepository =
                expenseRepository;
    }

    @Override
    public ReportDto getReport(
            LocalDate startDate,
            LocalDate endDate) {

        LocalDateTime start =
                startDate.atStartOfDay();

        LocalDateTime end =
                endDate.atTime(
                        23,
                        59,
                        59);

        List<Order> orders =
                orderRepository
                        .findByOrderDateBetween(
                                start,
                                end);

        List<Procurement> procurements =
                procurementRepository
                        .findByProcurementDateBetween(
                                startDate,
                                endDate);

        List<Expense> expenses =
                expenseRepository
                        .findByExpenseDateBetween(
                                startDate,
                                endDate);

        double totalSales = 0;
        double totalProcurement = 0;
        double totalExpenses = 0;

        for (Order order : orders) {

            if (order.getOrderStatus()
                    .equals(
                            "DELIVERED")) {

                totalSales +=
                        order.getTotalAmount();
            }
        }

        for (Procurement procurement
                : procurements) {

            totalProcurement +=
                    procurement.getTotalAmount();
        }

        for (Expense expense
                : expenses) {

            totalExpenses +=
                    expense.getAmount();
        }

        double profit =
                totalSales
                        - totalProcurement
                        - totalExpenses;

        ReportDto dto =
                new ReportDto();

        dto.setTotalSales(
                totalSales);

        dto.setTotalProcurement(
                totalProcurement);

        dto.setTotalExpenses(
                totalExpenses);

        dto.setProfit(
                profit);

        return dto;
    }




    @Override
public ResponseEntity<byte[]>
downloadPdf(
        LocalDate startDate,
        LocalDate endDate)
        throws Exception {

    ReportDto report =
            getReport(
                    startDate,
                    endDate);

    ByteArrayOutputStream out =
            new ByteArrayOutputStream();

    Document document =
            new Document();

    PdfWriter.getInstance(
            document,
            out);

    document.open();

    Font title =
            FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD,
                    20);

    Paragraph heading =
            new Paragraph(
                    "ALSULTAN SEAFOOD REPORT",
                    title);

    heading.setAlignment(
            Element.ALIGN_CENTER);

    document.add(
            heading);

    document.add(
            new Paragraph(" "));

    document.add(
            new Paragraph(
                    "Start Date : "
                            + startDate));

    document.add(
            new Paragraph(
                    "End Date : "
                            + endDate));

    document.add(
            new Paragraph(" "));

    document.add(
            new Paragraph(
                    "Total Sales : ₹"
                            + report.getTotalSales()));

    document.add(
            new Paragraph(
                    "Total Procurement : ₹"
                            + report.getTotalProcurement()));

    document.add(
            new Paragraph(
                    "Total Expenses : ₹"
                            + report.getTotalExpenses()));

    document.add(
            new Paragraph(
                    "Profit / Loss : ₹"
                            + report.getProfit()));

    document.close();

    HttpHeaders headers =
            new HttpHeaders();

    headers.add(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=report.pdf");

    return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(
                    MediaType.APPLICATION_PDF)
            .body(
                    out.toByteArray());
}
}
