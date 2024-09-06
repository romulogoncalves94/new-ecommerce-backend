package com.ecommerce.ecom.services.admin.adminOrder;

import com.ecommerce.ecom.dto.OrderDTO;

import java.util.List;

public interface AdminOrderService {

    List<OrderDTO> getAllPlacedOrders();

}
