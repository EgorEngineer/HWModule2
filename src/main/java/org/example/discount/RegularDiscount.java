package org.example.discount;

import org.example.Order;

public class RegularDiscount implements DiscountStrategy {

  @Override
  public double calculateDiscount(Order order) {
    return 0.0;
  }
}
