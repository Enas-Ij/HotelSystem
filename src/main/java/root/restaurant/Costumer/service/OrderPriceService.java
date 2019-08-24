package root.restaurant.Costumer.service;

import root.restaurant.menu.Menu;
import root.restaurant.order.Order;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class OrderPriceService {

    @Autowired
    Menu menu;

    public String orderPrice(HttpServletRequest request){

        Float priceWithoutTax=calculatePriceWithoutTax( request);
        Float priceWithTax=calculatePriceWithTax(request);
        Float tax=priceWithTax-priceWithoutTax;

        String responseString= "Price: "+ priceWithoutTax+" JOD<br>Tax: "+ tax+
                " JOD<br>Total: "+priceWithTax+ " JOD";

       return responseString;
    }


    /** calculate Price Without  Tax: gets the  quantity
     *   ordered from each menu item from request, then calculate  the price Without Tax**/
    private Float calculatePriceWithoutTax
            ( HttpServletRequest request){

        Float priceWithoutTax=0f;
        for (String item:menu.getItemName()) {

            int itemQuantity= Integer.parseInt(request.getParameter(item));
            priceWithoutTax+=itemQuantity*menu.getItemPrice().get(item);

        }

        return priceWithoutTax;
    }



    /** calculate Price Without  Tax:  gets the  quantity
     *   ordered from each menu item , then calculate the price Without Tax
     *   and add the tax amount to it **/
    private Float calculatePriceWithTax
            ( HttpServletRequest request){

        Float priceWithoutTax=0f;
        for (String item: menu.getItemName()) {

            int itemQuantity= Integer.parseInt(request.getParameter(item));
            priceWithoutTax+= itemQuantity*menu.getItemPrice().get(item);

        }
        Float priceWithTax= priceWithoutTax+ Order.getSaleTaxRatio()*priceWithoutTax+
                Order.getServiceTaxRatio()*priceWithoutTax;

        return priceWithTax;
    }
}
