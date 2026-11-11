package org.example.commission;

import org.example.Order;

public class TransferCommission implements CommissionStrategy {

  private static final double RATE = 0.01;

  @Override
  public double calculateCommission(Order order) {
    return order.getAmount() * RATE;
  }
}
