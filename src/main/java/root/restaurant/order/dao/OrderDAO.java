package root.restaurant.order.dao;

import root.actors.Costumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class OrderDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String INSERT="insert into RestaurantOrder" +
            "(costumerid,orderTotal,ReservationId, status ) " +
            " values(?,?,?,?)";
    private static final String SELECT_ORDER_ID="select orderid" +
            " from RestaurantOrder where costumerid=? and abs(orderTotal-?)<0.001";



    public int insert(Costumer costumer, Integer reservationId, Float total, String status){

        return jdbcTemplate.update(INSERT, costumer.getId(), total, reservationId, status);
    }



    public  int selectOrderId(final Costumer costumer, final Float total){

        List<Integer> orderIdList= jdbcTemplate.query(SELECT_ORDER_ID


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1,costumer.getId());
                        preparedStatement.setFloat(2,total);
                    }
                }


                ,new RowMapper<Integer>() {

                    @Override
                    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                        Integer orderId=resultSet.getInt("orderid");
                        return orderId;
                    }}

        );


        return orderIdList.get(0);
    }
}

