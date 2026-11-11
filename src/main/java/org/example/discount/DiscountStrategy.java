package org.example.discount;

import org.example.Order;

public interface DiscountStrategy {
  double calculateDiscount(Order order);
}
