package org.example.repository;

import java.util.ArrayList;
import java.util.List;
import org.example.Order;

public class InMemoryRepository implements OrderRepository{

  private final List<Order> orders = new ArrayList<>();

  @Override
  public void save(Order order){
    orders.add(order);
  }
}
