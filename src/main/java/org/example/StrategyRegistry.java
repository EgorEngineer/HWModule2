package org.example;

import java.util.HashMap;
import java.util.Map;

public class StrategyRegistry <T>{
  private final Map<String, T> strategies;
  private final String keyDescription;

  public StrategyRegistry(Map<String, T> strategies, String keyDescription) {
    this.strategies = new HashMap<>(strategies);
    this.keyDescription = keyDescription;
  }

  public T resolve(String key) {
    T strategy = strategies.get(key);
    if (strategy == null) {
      throw new RuntimeException("Unknown " + keyDescription + ": " + key);
    }

    return strategy;
  }
}
