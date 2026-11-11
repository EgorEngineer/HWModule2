package org.example.discount;

import org.example.Order;

public class OrganisationDiscount implements DiscountStrategy{

  private final double RATE = 0.15;

  @Override
  public double calculateDiscount(Order order) {
    return order.getAmount() * RATE;
  }
}
