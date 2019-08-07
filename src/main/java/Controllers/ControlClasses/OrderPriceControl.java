package Controllers.ControlClasses;

import Restaurant.Menu;
import Restaurant.Order;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class OrderPriceControl {

    @Autowired
    Menu menu;

    public String orderPrice(HttpServletRequest request){

        Map<String,String> parameters= request.getParameterMap();
        Float priceWithoutTax=calculatePriceWithoutTax(parameters, request);
        Float priceWithTax=calculatePriceWithTax(parameters,request);
        Float tax=priceWithTax-priceWithoutTax;

        String responseString= "Price: "+ priceWithoutTax+" JOD<br>Tax: "+ tax+
                " JOD<br>Total: "+priceWithTax+ " JOD";

       return responseString;
    }


    /** calculate Price Without  Tax: finds the names of the menu items names from
     *   the parameterMap that is submitted with the request,  gets the  quantity
     *   ordered from each item from request, then calculate  the price Without Tax**/
    private Float calculatePriceWithoutTax
            ( Map<String,String> parameters, HttpServletRequest request){

        Float priceWithoutTax=0f;
        for (String item: parameters.keySet()) {

            int itemQuantity= Integer.parseInt(request.getParameter(item));
            priceWithoutTax+=itemQuantity*menu.getItemPrice().get(item);

        }

        return priceWithoutTax;
    }



    /** calculate Price Without  Tax: finds the names of the menu items names from
     *   the parameterMap that is submitted with the request,  gets the  quantity
     *   ordered from each item from request, then calculate the price Without Tax
     *   and add the tax amount to it **/
    private Float calculatePriceWithTax
            ( Map<String,String> parameters, HttpServletRequest request){

        Float priceWithoutTax=0f;
        for (String item: parameters.keySet()) {

            int itemQuantity= Integer.parseInt(request.getParameter(item));
            priceWithoutTax+= itemQuantity*menu.getItemPrice().get(item);

        }
        Float priceWithTax= priceWithoutTax+ Order.getSaleTaxRatio()*priceWithoutTax+
                Order.getServiceTaxRatio()*priceWithoutTax;

        return priceWithTax;
    }
}
