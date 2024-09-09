package com.ecommerce.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnalyticsResponseDTO {

    private Long placed;
    private Long shipped;
    private Long delivered;
    private Long currentMonthOrders;
    private Long previousMonthOrders;
    private Long currentMonthEarnings;
    private Long previousMonthEarnings;

}
