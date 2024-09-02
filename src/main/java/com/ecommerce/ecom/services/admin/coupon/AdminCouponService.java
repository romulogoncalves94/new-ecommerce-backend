package com.ecommerce.ecom.services.admin.coupon;

import com.ecommerce.ecom.entity.Coupon;

import java.util.List;

public interface AdminCouponService {

    Coupon createCoupon(Coupon coupon);
    List<Coupon> getAllCoupons();

}
