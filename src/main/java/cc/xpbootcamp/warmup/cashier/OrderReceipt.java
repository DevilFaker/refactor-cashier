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

        Integer week = getHeaderAndWeekInfo(output);

        getCustomerInfo(output);

        double totSalesTx = 0d;
        double tot = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            output.append(lineItem.getDescription()+", ");
            output.append(lineItem.getPrice()+" x ");
            output.append(lineItem.getQuantity());
            output.append('\t');
            output.append('\n');

            double salesTax = lineItem.totalAmount() * .10;
            totSalesTx += salesTax;

            tot += lineItem.totalAmount() + salesTax;
        }

        printTaxAndTotal(output, week, totSalesTx, tot);

        return output.toString();
    }

    private Integer getHeaderAndWeekInfo(StringBuilder output) {
        output.append("=====老王超市，值得信赖=====\n\n");

        LocalDate dateTime=order.getDate();

        Integer week = dateTime.get(WeekFields.of(DayOfWeek.of(1), 1).dayOfWeek());

        output.append(dateTime+" "+"星期"+week+"\n\n");
        return week;
    }

    private void printTaxAndTotal(StringBuilder output, Integer week, double totSalesTx, double tot) {
        output.append("------------------------------------\n");

        output.append("税额: ").append(totSalesTx).append('\n');

        tot = getDiscountInfo(output, week, tot);

        output.append("总价: ").append(tot);
    }

    private double getDiscountInfo(StringBuilder output, Integer week, double tot) {
        if(week==3){
            double account=tot*.02;
            tot=tot*.98;
            output.append("折扣: ").append(account).append('\n');
        }
        return tot;
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