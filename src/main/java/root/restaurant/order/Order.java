package root.restaurant.order;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private int orderId;
    private Map<String, Integer> itemQuantityMap;
    private int costumerId;
    private Float totalWithoutTax;
    private Float totalWithTax;
    private static final Float saleTaxRatio =0.16f;
    private static final Float serviceTaxRatio =0.20f;



    //no args constructor
    public Order() {

        itemQuantityMap= new HashMap<>();
        totalWithoutTax= 0f;
        totalWithTax= 0f;
    }



    public void addToItemQuantityMap(String itemName, int quantity){

        itemQuantityMap.put(itemName, quantity);

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(int costumerId) {
        this.costumerId = costumerId;
    }

    public Float getTotalWithoutTax() {
        return totalWithoutTax;
    }

    public void setTotalWithoutTax(Float totalWithoutTax) {
        this.totalWithoutTax = totalWithoutTax;
    }

    public Float getTotalWithTax() {
        return totalWithTax;
    }

    public void setTotalWithTax(Float totalWithTax) {
        this.totalWithTax = totalWithTax;
    }

    public static Float getSaleTaxRatio() {
        return saleTaxRatio;
    }

    public static Float getServiceTaxRatio() {
        return serviceTaxRatio;
    }

    public Map<String, Integer> getItemQuantityMap() {
        return itemQuantityMap;
    }

    public void setItemQuantityMap(Map<String, Integer> itemQuantityMap) {
        this.itemQuantityMap = itemQuantityMap;
    }
}
