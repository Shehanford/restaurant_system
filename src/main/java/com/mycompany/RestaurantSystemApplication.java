package com.mycompany;

import com.mycompany.reporting.DeliveryOrderFilesExporter;
import com.mycompany.reporting.DineInFilesExporter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestaurantSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantSystemApplication.class, args);
    }

  @Bean
    public DeliveryOrderFilesExporter deliveryOrderFilesExporter(){
        return new DeliveryOrderFilesExporter();
    }

    @Bean
    public DineInFilesExporter dineInFilesExporter(){
        return new DineInFilesExporter();
    }
}

