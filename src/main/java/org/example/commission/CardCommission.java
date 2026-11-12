package org.example.commission;

import org.example.Order;

public class CardCommission implements CommissionStrategy {

  private static final double RATE = 0.02;

  @Override
  public double calculateCommission(Order order) {
    return order.getAmount() * RATE;
  }
}
