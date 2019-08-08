package root.restaurant;

import root.restaurant.menu.dao.MenuDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {

    @Autowired
    MenuDAO menuDAO;
    private List<String> itemName;
    //itemPrice< Key:itemName, value:itemPrice>
    private Map<String, Float> itemPrice= new HashMap<>();
    //itemSection< Key:itemName, value:itemSection>
    private Map<String, String> itemSection= new HashMap<>();
    private int initCount = 0;


    //Checks if menu bean has been initialized before
    public void initializeMenu() {

        if (initCount == 0) {
            initCount++;
            menuInitializer();
        }
        return;
    }

    //Initialize the maps values from the DB
    private void menuInitializer() {

        itemName= menuDAO.selectItemName();
        fillItemPrice();
        fillItemSection();
    }

    //Fill the HashMap itemPrice< Key:itemName, value:itemPrice> from the DB
    private void fillItemPrice(){

        for (String item : itemName) {

            float price= menuDAO.selectPrice(item);
            itemPrice.put( item, price);

        }

    }

    //Fill the HashMap itemSection< Key:itemName, value:itemSection> from the DB
    private void fillItemSection(){

        for (String item : itemName) {

            String section= menuDAO.selectSection(item);
            itemSection.put( item, section);

        }

    }

    public List<String> getItemName() {
        return itemName;
    }

    public Map<String, Float> getItemPrice() {
        return itemPrice;
    }

    public Map<String, String> getItemSection() {
        return itemSection;
    }
}
