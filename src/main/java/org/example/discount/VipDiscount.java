package org.example.discount;

import org.example.Order;

public class VipDiscount implements DiscountStrategy {

  private static final double RATE = 0.05;

  @Override
  public double calculateDiscount(Order order) {
    return order.getAmount() * RATE;
  }
}
