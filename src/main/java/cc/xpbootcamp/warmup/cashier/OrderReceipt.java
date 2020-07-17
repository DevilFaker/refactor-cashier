package cc.xpbootcamp.warmup.cashier;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 *
 */
public class OrderReceipt {

    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {

        StringBuilder receipt = new StringBuilder();

        int weekInfo = getHeaderAndWeekInfo(receipt);

        getCustomerInfo(receipt);

        double totalTax = 0d;
        double totalPrice = 0d;
        
        for (LineItem lineItem : order.getLineItems()) {
            receipt.append(lineItem.getDescription()+", ");
            receipt.append(lineItem.getPrice()+" x ");
            receipt.append(lineItem.getQuantity());
            receipt.append('\t');
            receipt.append('\n');
            double tax = lineItem.totalAmount() * .10;
            totalTax += tax;
            totalPrice += lineItem.totalAmount() + tax;
        }

        printTaxAndTotal(receipt, weekInfo, totalTax, totalPrice);

        return receipt.toString();
    }

    private int getHeaderAndWeekInfo(StringBuilder receipt) {
        receipt.append("=====老王超市，值得信赖=====\n\n");

        LocalDate dateTime=order.getDate();

        int week = dateTime.get(WeekFields.of(DayOfWeek.of(1), 1).dayOfWeek());

        receipt.append(dateTime+" "+"星期"+week+"\n\n");

        return week;
    }

    private void printTaxAndTotal(StringBuilder receipt, Integer week, double totSalesTx, double totalPrice) {
        receipt.append("------------------------------------\n");

        receipt.append("税额: ").append(totSalesTx).append('\n');

        totalPrice = getDiscountInfo(receipt, week, totalPrice);

        receipt.append("总价: ").append(totalPrice);
    }

    private double getDiscountInfo(StringBuilder receipt, Integer week, double totalPrice) {
        if(week==3){
            double account=totalPrice*.02;
            totalPrice=totalPrice*.98;
            receipt.append("折扣: ").append(account).append('\n');
        }
        return totalPrice;
    }

    private void getCustomerInfo(StringBuilder receipt) {
        if(order.getCustomerAddress()!=null){
            receipt.append(order.getCustomerAddress());
        }
        if(order.getCustomerName()!=null){
            receipt.append(order.getCustomerName());
        }
    }
}