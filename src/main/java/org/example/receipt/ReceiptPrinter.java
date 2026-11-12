package org.example.receipt;

import org.example.Order;

public interface ReceiptPrinter {

  void print(Order order, double discount, double commission, double finalAmount);
}