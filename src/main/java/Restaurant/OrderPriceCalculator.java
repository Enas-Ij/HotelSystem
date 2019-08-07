package Restaurant;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class OrderPriceCalculator {

    @Autowired
    private Menu menu;



    //calculate total bill without including tax
    public Float calculateTotalWithoutTax(Map<String, Integer> itemQuantityMap){

        if (itemQuantityMap.keySet().size()==0){
            return 0f;
        }

        Float total=0f;
        for (String item: itemQuantityMap.keySet()) {

            int quantity=itemQuantityMap.get(item);
            float price= menu.getItemPrice().get(item);
            total+=price*quantity;
        }
        Float totalWithoutTax=total;

        return totalWithoutTax;
    }



    //calculate total bill including both sales and service taxes
    public Float calculateTotalWithTax( Map<String, Integer> itemQuantityMap){

        Float totalWithoutTax=calculateTotalWithoutTax( itemQuantityMap);
        Float totalWithTax=totalWithoutTax+ totalWithoutTax* Order.getSaleTaxRatio()
                + totalWithoutTax* Order.getServiceTaxRatio();

        return totalWithTax;
    }
}
