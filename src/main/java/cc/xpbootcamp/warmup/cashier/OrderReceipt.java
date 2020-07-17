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

    private static final String HEADER = "=====老王超市，值得信赖=====";
    private static final String DOUBLE_LINE_BREAK = "\n\n";
    private static final double DISCOUNT = .98;
    private static final double ITEM_TAX = .10;
    private static final double INIT_VALUE = 0d;

    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {

        StringBuilder receipt = new StringBuilder();

        int weekInfo = getHeaderAndWeekInfo(receipt);

        getCustomerInfo(receipt);

        double totalTax = INIT_VALUE;
        double totalPrice = INIT_VALUE;
        
        for (LineItem lineItem : order.getLineItems()) {
             receipt.append(lineItem.getDescription()+", ")
                    .append(lineItem.getPrice()+" x ")
                    .append(lineItem.getQuantity())
                    .append('\t')
                    .append('\n');
            double tax = lineItem.totalAmount() * ITEM_TAX;
            totalTax += tax;
            totalPrice += lineItem.totalAmount() + tax;
        }

        printTaxAndTotal(receipt, weekInfo, totalTax, totalPrice);

        return receipt.toString();
    }

    private int getHeaderAndWeekInfo(StringBuilder receipt) {
        receipt.append(HEADER + DOUBLE_LINE_BREAK);

        LocalDate dateTime=order.getDate();

        int week = dateTime.get(WeekFields.of(DayOfWeek.of(1), 1).dayOfWeek());

        receipt.append(dateTime+" "+"星期"+week+ DOUBLE_LINE_BREAK);

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
            double account=totalPrice*(1- DISCOUNT);
            totalPrice=totalPrice* DISCOUNT;
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