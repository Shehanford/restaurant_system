package com.mycompany;

import com.mycompany.model.DeliveryOrder;
import com.mycompany.reporting.DeliveryOrderFilesExporter;
import com.mycompany.service.DeliveryOrderService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class DeliveryOrderRestController {

    @Autowired
    DeliveryOrderService deliveryOrderService;

    @Autowired
    DeliveryOrderFilesExporter deliveryOrderFilesExporter;

    @GetMapping("/deliveryOrder/export/csv")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        List<DeliveryOrder> listDeliveryOrders = deliveryOrderService.getAllDeliveryOrders();
        deliveryOrderFilesExporter.exportToCSV(listDeliveryOrders, response);
    }

    @GetMapping("/deliveryOrder/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws IOException {
        List<DeliveryOrder> listDeliveryOrders = deliveryOrderService.getAllDeliveryOrders();
        deliveryOrderFilesExporter.exportToPDF(listDeliveryOrders, response);
    }

}
