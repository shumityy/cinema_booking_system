package payment;

import payment.interfaces.Payment;

public class CardPayment implements Payment {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Card");
    }
}
