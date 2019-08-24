package root.restaurant.order.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderItemsDAO {


    @Autowired
    JdbcTemplate jdbcTemplate;


    private static final String INSERT="insert into OrderItems" +
            "(orderId,ItemName, quantity ) " +
            " values(?,?,?)";

    private static final String SELECT_ITEMS = "select ItemName from OrderItems where" +
            " orderId=?";
    private static final String SELECT_QUANTITY = "select quantity from OrderItems where" +
            " orderId=? and ItemName=?";


    public int insert(int orderId, Map<String, Integer> itemQuantityMap){

        int affectedRows=0;

        for (String item: itemQuantityMap.keySet()  ) {
            if (itemQuantityMap.get(item)==0){
                continue;
            }

          affectedRows+=  jdbcTemplate.update(INSERT, orderId, item,
                  itemQuantityMap.get(item));
        }

        return  affectedRows;
    }



    public List<String> selectOrderItems(final int orderId){

        List<String> itemList= jdbcTemplate.query(SELECT_ITEMS


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1, orderId);
                    }
                }


                ,new RowMapper<String>() {

                    @Override
                    public String mapRow(ResultSet resultSet, int i) throws SQLException {
                        String item =resultSet.getString("ItemName");
                        return item;
                    }}

        );


        return itemList;
    }


    public Map<String, Integer> selectOrderItemsQuantity(final int orderId){

        List<String> itemList= selectOrderItems(orderId);
        Map<String, Integer> orderItemsQuantity= new HashMap<>();

        for (final String item : itemList) {
            List<Integer> itemQuantity= jdbcTemplate.query(SELECT_QUANTITY


                    , new PreparedStatementSetter() {

                        @Override
                        public void setValues(PreparedStatement preparedStatement) throws SQLException {
                            preparedStatement.setInt(1, orderId);
                            preparedStatement.setString(2, item);
                        }
                    }


                    ,new RowMapper<Integer>() {

                        @Override
                        public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                            Integer quantity =resultSet.getInt("quantity");
                            return quantity;
                        }}

            );

            orderItemsQuantity.put(item, itemQuantity.get(0));
        }

        return orderItemsQuantity;
    }

    }




