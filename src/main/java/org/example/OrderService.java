package org.example;

import java.util.List;
import org.example.commission.CommissionStrategies;
import org.example.commission.CommissionStrategy;
import org.example.discount.DiscountStrategies;
import org.example.discount.DiscountStrategy;
import org.example.receipt.ConsolePrinter;
import org.example.receipt.ReceiptPrinter;
import org.example.repository.InMemoryRepository;
import org.example.repository.OrderRepository;


/**
 Контекст: Тебе достался в наследство код от предыдущего разработчика — сервис обработки заказов интернет-магазина OrderService.
 Он работает, тесты (если бы они были) прошли бы, заказчик доволен... пока не попросил добавить новый тип клиента или способ оплаты.
 Тут выясняется, что каждое такое "простое" изменение требует лезть в один и тот же метод на 80 строк, где всё завязано друг на друга,
 и есть риск что-то сломать в соседней логике.
 Твоя задача: не переписать сервис с нуля, а отрефакторить — сохранив то же поведение (те же скидки, та же комиссия, тот же чек)
 Опирайся на SOLID и паттерны проектирования
Lombok использовать нельзя, все писать руками.
 */
public class OrderService {

    private final OrderValidator validator;
    private final ReceiptPrinter receiptPrinter;
    private final OrderRepository orderRepository;
    private final StrategyRegistry<DiscountStrategy> discountRegistry;
    private final StrategyRegistry<CommissionStrategy> commissionRegistry;

    public OrderService(OrderRepository orderRepository,
        ReceiptPrinter receiptPrinter,
        StrategyRegistry<DiscountStrategy> discountRegistry,
        StrategyRegistry<CommissionStrategy> commissionRegistry) {
        this.orderRepository = orderRepository;
        this.receiptPrinter = receiptPrinter;
        this.discountRegistry = discountRegistry;
        this.commissionRegistry = commissionRegistry;
        this.validator = new OrderValidator();
    }

    public double processOrder(Order order) {
        validator.validate(order);

        double discount = discountRegistry
            .resolve(order.getCustomerType())
            .calculateDiscount(order);

        double commission = commissionRegistry
            .resolve(order.getPaymentType())
            .calculateCommission(order);

        double finalAmount = order.getAmount() - discount + commission;

        receiptPrinter.print(order, discount, commission, finalAmount);
        orderRepository.save(order);

        return finalAmount;
    }

    public static void main(String[] args) {
        OrderService service = new OrderService(new InMemoryRepository(),
            new ConsolePrinter(),
            DiscountStrategies.registry(),
            CommissionStrategies.registry()
        );

        Order order1 = new Order("1", "VIP", "CARD", List.of("Laptop", "Mouse"), 1000.0);
        Order order2 = new Order("2", "REGULAR", "CASH", List.of("Book"), 20.0);

        System.out.println("Final amount 1: " + service.processOrder(order1));
        System.out.println("Final amount 2: " + service.processOrder(order2));
    }
}

