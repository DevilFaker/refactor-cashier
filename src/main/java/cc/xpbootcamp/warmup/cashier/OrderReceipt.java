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

        StringBuilder output = new StringBuilder();

        // print headers
        output.append("=====老王超市，值得信赖=====\n\n");

        LocalDate dateTime=order.getDate();

        Integer week = dateTime.get(WeekFields.of(DayOfWeek.of(1), 1).dayOfWeek());

        output.append(dateTime+" "+"星期"+week+"\n\n");

        getCustomerInfo(output);

        // prints lineItems
        double totSalesTx = 0d;
        double tot = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            output.append(lineItem.getDescription()+", ");
            output.append(lineItem.getPrice()+" x ");
            output.append(lineItem.getQuantity());
            output.append('\t');
            output.append('\n');

            // calculate sales tax @ rate of 10%
            double salesTax = lineItem.totalAmount() * .10;
            totSalesTx += salesTax;

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            tot += lineItem.totalAmount() + salesTax;
        }

        output.append("------------------------------------\n");

        // prints the state tax
        output.append("税额: ").append(totSalesTx).append('\n');

        if(week==3){
            double account=tot*.02;
            tot=tot*.98;
            output.append("折扣: ").append(account).append('\n');
        }

        // print total amount
        output.append("总价: ").append(tot);

        return output.toString();
    }

    private void getCustomerInfo(StringBuilder output) {
        if(order.getCustomerAddress()!=null){
            output.append(order.getCustomerAddress());
        }
        if(order.getCustomerName()!=null){
            output.append(order.getCustomerName());
        }
    }
}