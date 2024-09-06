package com.ecommerce.ecom.controller.admin;

import com.ecommerce.ecom.dto.OrderDTO;
import com.ecommerce.ecom.services.admin.adminOrder.AdminOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequestMapping("/api/admin")
@RestController
@RequiredArgsConstructor
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    @GetMapping("/placedOrders")
    public ResponseEntity<List<OrderDTO>> getAllPlacedOrders() {
        return ResponseEntity.ok(adminOrderService.getAllPlacedOrders());
    }

    @GetMapping("/order/{orderId}/{status}")
    public ResponseEntity<?> changeOrderStatus(@PathVariable Long orderId, @PathVariable String status) {
        OrderDTO orderDTO = adminOrderService.changeOrderStatus(orderId, status);

        if (Objects.isNull(orderId)) return new ResponseEntity<>("Something wen wrong!", HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.OK).body(orderDTO);
    }
}
