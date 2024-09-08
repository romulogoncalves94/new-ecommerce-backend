package com.ecommerce.ecom.services.customer.cart;

import com.ecommerce.ecom.dto.*;
import com.ecommerce.ecom.entity.*;
import com.ecommerce.ecom.enums.OrderStatus;
import com.ecommerce.ecom.exceptions.ValidationException;
import com.ecommerce.ecom.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CouponRepository couponRepository;

    public ResponseEntity<?> addProductToCart(AddProductInCartDTO inCarDTO) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(inCarDTO.getUserId(), OrderStatus.Pending);
        Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId
                (inCarDTO.getProductId(), activeOrder.getId(), inCarDTO.getUserId());

        if (optionalCartItems.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } else {
            Optional<Product> optionalProduct = productRepository.findById(inCarDTO.getProductId());
            Optional<User> optionalUser = userRepository.findById(inCarDTO.getUserId());

            if (optionalProduct.isPresent() && optionalUser.isPresent()) {
                CartItems cartItems = new CartItems();
                cartItems.setProduct(optionalProduct.get());
                cartItems.setPrice(optionalProduct.get().getPrice());
                cartItems.setQuantity(1L);
                cartItems.setUser(optionalUser.get());
                cartItems.setOrder(activeOrder);

                CartItems updateCart = cartItemsRepository.save(cartItems);

                activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cartItems.getPrice());
                activeOrder.setAmount(activeOrder.getAmount() + cartItems.getPrice());
                activeOrder.getCartItems().add(cartItems);
                orderRepository.save(activeOrder);

                return ResponseEntity.status(HttpStatus.CREATED).body(cartItems);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
            }
        }
    }

    public OrderDTO getCartByUserId(Long userId) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
        List<CartItemsDTO> cartItemsDTO = activeOrder.getCartItems().stream().map(CartItems::getCartItemsDTO).toList();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setAmount(activeOrder.getAmount());
        orderDTO.setId(activeOrder.getId());
        orderDTO.setOrderStatus(activeOrder.getOrderStatus());
        orderDTO.setDiscount(activeOrder.getDiscount());
        orderDTO.setTotalAmount(activeOrder.getTotalAmount());
        orderDTO.setCartItems(cartItemsDTO);

        if (activeOrder.getCoupon() != null) {
            orderDTO.setCouponName(activeOrder.getCoupon().getName());
        }

        return orderDTO;
    }

    public OrderDTO applyCoupon(Long userId, String code) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
        Coupon coupon = couponRepository.findByCode(code).orElseThrow(() -> new ValidationException("Coupon not found!"));

        if (couponExpired(coupon)) {
            throw new ValidationException("Coupon has expired!");
        }

        double discountAmount = ((coupon.getDiscount() / 100.0) * activeOrder.getTotalAmount());
        double netAmount = activeOrder.getTotalAmount() - discountAmount;

        activeOrder.setAmount((long) netAmount);
        activeOrder.setDiscount((long) discountAmount);
        activeOrder.setCoupon(coupon);

        orderRepository.save(activeOrder);
        return activeOrder.getOrderDto();
    }

    private boolean couponExpired(Coupon coupon) {
        Date currentDate = new Date();
        Date expirationDate = coupon.getExpirationDate();

        return expirationDate != null && currentDate.after(expirationDate);
    }

    public OrderDTO increaseProductQuantity(AddProductInCartDTO product) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(product.getUserId(), OrderStatus.Pending);
        Optional<Product> optionalProduct = productRepository.findById(product.getProductId());

        Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId(product.getProductId(), activeOrder.getId(), product.getUserId());

        if (optionalProduct.isPresent() && optionalCartItems.isPresent()) {
            CartItems cartItems = optionalCartItems.get();
            Product product1 = optionalProduct.get();

            activeOrder.setAmount(activeOrder.getAmount() + product1.getPrice());
            activeOrder.setTotalAmount(activeOrder.getTotalAmount() + product1.getPrice());

            cartItems.setQuantity(cartItems.getQuantity() + 1);

            if (activeOrder.getCoupon() != null) {
                double discountAmount = ((activeOrder.getCoupon().getDiscount() / 100.0) * activeOrder.getTotalAmount());
                double netAmount = activeOrder.getTotalAmount() - discountAmount;

                activeOrder.setAmount((long) netAmount);
                activeOrder.setDiscount((long) discountAmount);
            }

            cartItemsRepository.save(cartItems);
            orderRepository.save(activeOrder);
            return activeOrder.getOrderDto();
        }

        return null;
    }

    public OrderDTO decreaseProductQuantity(AddProductInCartDTO product) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(product.getUserId(), OrderStatus.Pending);
        Optional<Product> optionalProduct = productRepository.findById(product.getProductId());

        Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId(product.getProductId(), activeOrder.getId(), product.getUserId());

        if (optionalProduct.isPresent() && optionalCartItems.isPresent()) {
            CartItems cartItems = optionalCartItems.get();
            Product product1 = optionalProduct.get();

            activeOrder.setAmount(activeOrder.getAmount() - product1.getPrice());
            activeOrder.setTotalAmount(activeOrder.getTotalAmount() - product1.getPrice());

            cartItems.setQuantity(cartItems.getQuantity() - 1);

            if (activeOrder.getCoupon() != null) {
                double discountAmount = ((activeOrder.getCoupon().getDiscount() / 100.0) * activeOrder.getTotalAmount());
                double netAmount = activeOrder.getTotalAmount() - discountAmount;

                activeOrder.setAmount((long) netAmount);
                activeOrder.setDiscount((long) discountAmount);
            }

            cartItemsRepository.save(cartItems);
            orderRepository.save(activeOrder);
            return activeOrder.getOrderDto();
        }

        return null;
    }

    public OrderDTO placeOrder(PlaceOrderDTO placeOrderDTO) {
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(placeOrderDTO.getUserId(), OrderStatus.Pending);
        Optional<User> optionalUser = userRepository.findById(placeOrderDTO.getUserId());

        if (optionalUser.isPresent()) {
            if (Objects.nonNull(activeOrder)) {
                activeOrder.setOrderDescription(placeOrderDTO.getOrderDescription());
                activeOrder.setAddress(placeOrderDTO.getAddress());
                activeOrder.setDate(new Date());
                activeOrder.setOrderStatus(OrderStatus.Placed);
                activeOrder.setTrackingId(UUID.randomUUID());
                orderRepository.save(activeOrder);
            } else {
                Order newOrder = new Order();
                newOrder.setAmount(0L);
                newOrder.setTotalAmount(0L);
                newOrder.setDiscount(0L);
                newOrder.setUser(optionalUser.get());
                newOrder.setOrderStatus(OrderStatus.Pending);
                newOrder.setTrackingId(UUID.randomUUID());
                orderRepository.save(newOrder);
            }
        }
        return activeOrder != null ? activeOrder.getOrderDto() : null;
    }

    public List<OrderDTO> getMyPlacedOrders(Long userId) {
        return orderRepository.findByUserIdAndOrderStatusIn(userId, List.of(OrderStatus.Placed, OrderStatus.Shipped, OrderStatus.Delivered))
                .stream()
                .map(Order::getOrderDto)
                .toList();
    }

    public OrderDTO searchOrderByTrackingID(UUID trackingId) {
        Optional<Order> optionalOrder = orderRepository.findByTrackingId(trackingId);
        return optionalOrder.map(Order::getOrderDto).orElse(null);
    }

}
