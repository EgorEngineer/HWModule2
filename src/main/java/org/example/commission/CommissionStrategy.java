package org.example.commission;

import org.example.Order;

public interface CommissionStrategy {
   double calculateCommission(Order order);
}
