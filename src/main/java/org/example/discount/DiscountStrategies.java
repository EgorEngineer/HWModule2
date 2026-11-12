package org.example.discount;

import java.util.HashMap;
import java.util.Map;
import org.example.StrategyRegistry;

public final class DiscountStrategies {

  public static final String REGULAR = "REGULAR";
  public static final String VIP = "VIP";
  public static final String PREMIUM = "PREMIUM";
  public static final String ORGANISATION = "ORGANISATION";

  private DiscountStrategies() {
  }

  public static Map<String, DiscountStrategy> defaults() {
    Map<String, DiscountStrategy> strategies = new HashMap<>();
    strategies.put(REGULAR, new RegularDiscount());
    strategies.put(VIP, new VipDiscount());
    strategies.put(PREMIUM, new PremiumDiscount());
    strategies.put(ORGANISATION, new OrganisationDiscount());
    return strategies;
  }

  public static Map<String, DiscountStrategy> extending(
      Map<String, DiscountStrategy> base,
      String customerType,
      DiscountStrategy strategy) {
    Map<String, DiscountStrategy> copy = new HashMap<>(base);
    copy.put(customerType, strategy);
    return copy;
  }

  public static StrategyRegistry<DiscountStrategy> registry() {
    return new StrategyRegistry<>(defaults(), "customer type");
  }
}
