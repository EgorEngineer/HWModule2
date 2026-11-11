package org.example.commission;

import java.util.HashMap;
import java.util.Map;
import org.example.StrategyRegistry;

public final class CommissionStrategies {

  public static final String CARD = "CARD";
  public static final String CASH = "CASH";
  public static final String TRANSFER = "TRANSFER";
  public static final String CRYPTO = "CRYPTO";

  private CommissionStrategies() {
  }

  public static Map<String, CommissionStrategy> defaults() {
    Map<String, CommissionStrategy> strategies = new HashMap<>();
    strategies.put(CARD, new CardCommission());
    strategies.put(CASH, new CashCommission());
    strategies.put(TRANSFER, new TransferCommission());
    strategies.put(CRYPTO, new CryptoCommission());
    return strategies;
  }

  public static Map<String, CommissionStrategy> extending(
      Map<String, CommissionStrategy> base,
      String paymentType,
      CommissionStrategy strategy
  ) {
    Map<String, CommissionStrategy> copy = new HashMap<>(base);
    copy.put(paymentType, strategy);
    return copy;
  }

  public static StrategyRegistry<CommissionStrategy> registry() {
    return new StrategyRegistry<>(defaults(), "payment type");
  }
}
