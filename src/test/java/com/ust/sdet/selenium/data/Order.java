package com.ust.sdet.selenium.data;
import java.time.LocalDate;
public record Order(
            String sku,
            int quantity,
            long totalPaise,
            String status,
            LocalDate orderedOn,
            boolean refunded
){
}
