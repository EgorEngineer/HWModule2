package org.example.commission;

import org.example.Order;

public class CryptoCommission implements CommissionStrategy{

  private final double RATE = 0.001;

  @Override
  public double calculateCommission(Order order) {
    return order.getAmount() * RATE;
  }
}
