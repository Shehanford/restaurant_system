package com.mycompany.reporting;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.mycompany.model.DeliveryOrder;
import jakarta.servlet.http.HttpServletResponse;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DeliveryOrderFilesExporter {

    public void setResponseHeader(HttpServletResponse response, String contentType, String extension, String prefix) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timeStamp = dateFormat.format(new Date());
        String fileName = prefix + timeStamp + extension;
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
    }

    public void exportToCSV(List<DeliveryOrder> listDeliveryOrders, HttpServletResponse response) throws IOException {
        setResponseHeader(response, "text/csv", ".csv", "DeliveryOrder_List_");
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"ID", "Delivery Address", "Order Time", "Total Amount", "Phone Number", "Transaction ID"};
        String[] fieldMapping = {"id", "deliveryAddress", "orderTime", "totalAmount", "phoneNumber","transactionId"};
        csvWriter.writeHeader(csvHeader);
        for (DeliveryOrder order : listDeliveryOrders) {
            csvWriter.write(order, fieldMapping);
        }
        csvWriter.close();
    }

    public void exportToPDF(List<DeliveryOrder> listDeliveryOrders, HttpServletResponse response) throws DocumentException, IOException {
        setResponseHeader(response, "application/pdf", ".pdf", "DeliveryOrder_Details_");
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.BLACK);
        Paragraph para = new Paragraph("Delivery Order Details", font);
        para.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(para);
        PdfPTable table = new PdfPTable(6); // Number of columns
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);
        writeHeader(table);
        writeData(table, listDeliveryOrders);
        document.add(table);
        document.close();
    }

    private void writeHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.DARK_GRAY);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.WHITE);
        String[] headers = {"ID", "Delivery Address", "Order Time", "Total Amount", "Phone Number","Transaction ID"};
        for (String header : headers) {
            cell.setPhrase(new Phrase(header, font));
            table.addCell(cell);
        }
    }

    private void writeData(PdfPTable table, List<DeliveryOrder> listDeliveryOrders) {
        for (DeliveryOrder order : listDeliveryOrders) {
            table.addCell(String.valueOf(order.getId()));
            table.addCell(order.getDeliveryAddress());
            table.addCell(order.getOrderTime().toString());
            table.addCell(order.getTotalAmount().toString());
            table.addCell(order.getPhoneNumber());
            table.addCell(order.getTransactionId());
        }
    }
}
