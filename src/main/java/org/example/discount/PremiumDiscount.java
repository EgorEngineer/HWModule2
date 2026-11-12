package org.example.discount;

import org.example.Order;

public class PremiumDiscount implements DiscountStrategy {

  private static final double RATE = 0.10;

  @Override
  public double calculateDiscount(Order order) {
    return order.getAmount() * RATE;
  }
}