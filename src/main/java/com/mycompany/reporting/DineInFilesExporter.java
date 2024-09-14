package com.mycompany.reporting;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.mycompany.model.DineInReservation;
import jakarta.servlet.http.HttpServletResponse;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@SuppressWarnings("ALL")
public class DineInFilesExporter {

    public void setResponseHeader(HttpServletResponse response, String contentType, String extension, String prefix) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timeStamp = dateFormat.format(new Date());
        String fileName = prefix + timeStamp + extension;
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
    }

    public void exportToCSV(List<DineInReservation> listDineInReservation, HttpServletResponse response) throws IOException {
        setResponseHeader(response, "text/csv", ".csv", "DineInReservation_List_");
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"ID", "Reservation Date", "Reservation Start Time", "Reservation End Time", "Number of Customers", "Table Number", "Locations"};
        String[] fieldMapping = {"id", "reservationDate", "startTime",  "endTime","numberOfCustomers", "tableNumber","location"};
        csvWriter.writeHeader(csvHeader);
        for (DineInReservation dineInReservation : listDineInReservation) {
            csvWriter.write(dineInReservation, fieldMapping);
        }
        csvWriter.close();
    }

    public void exportToPDF(List<DineInReservation> dineInReservations, HttpServletResponse response) throws DocumentException, IOException {
        setResponseHeader(response, "application/pdf", ".pdf", "DineInReservation_Details_");
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.BLACK);
        Paragraph para = new Paragraph("Dine In Reservations Details", font);
        para.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(para);
        PdfPTable table = new PdfPTable(7); // Number of columns
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);
        writeHeader(table);
        writeData(table, dineInReservations);
        document.add(table);
        document.close();
    }

    private void writeHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.DARK_GRAY);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.WHITE);
        String[] headers = {"ID", "Reservation Date", "Reservation Start Time", "Reservation End Time", "Number of Customers", "Table Number", "Locations"};
        for (String header : headers) {
            cell.setPhrase(new Phrase(header, font));
            table.addCell(cell);
        }
    }

    private void writeData(PdfPTable table, List<DineInReservation> dineInReservationList) {
        for (DineInReservation dineInReservation : dineInReservationList) {
            table.addCell(new PdfPCell(new Phrase(String.valueOf(dineInReservation.getId()))));
            table.addCell(new PdfPCell(new Phrase(dineInReservation.getReservationDate().toString())));
            table.addCell(new PdfPCell(new Phrase(dineInReservation.getStartTime())));
            table.addCell(new PdfPCell(new Phrase(dineInReservation.getEndTime())));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(dineInReservation.getNumberOfCustomers()))));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(dineInReservation.getTableNumber())))); // Corrected to convert Integer to String
            table.addCell(new PdfPCell(new Phrase(dineInReservation.getLocation().getName())));
        }
    }

}
