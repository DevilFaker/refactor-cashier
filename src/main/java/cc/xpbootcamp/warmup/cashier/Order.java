package cc.xpbootcamp.warmup.cashier;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Order {
    String customerName;
    String address;
    List<LineItem> lineItemList;
    LocalDate date;

    public Order(String customerName, String address, LocalDate dateTime, List<LineItem> lineItemList) {
        this.customerName = customerName;
        this.address = address;
        this.lineItemList = lineItemList;
        this.date=dateTime;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<LineItem> getLineItemList() {
        return lineItemList;
    }

    public void setLineItemList(List<LineItem> lineItemList) {
        this.lineItemList = lineItemList;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return address;
    }

    public List<LineItem> getLineItems() {
        return lineItemList;
    }
}
