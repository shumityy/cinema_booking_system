package payment;

import payment.interfaces.Payment;

public class PaymentFactory {
    public static Payment getPayment(String type) {
        return switch (type) {
            case "CARD" -> new CardPayment();
            case "UPI" -> new UPIPayment();
            default -> throw new IllegalArgumentException("Invalid payment type");
        };
    }

}
