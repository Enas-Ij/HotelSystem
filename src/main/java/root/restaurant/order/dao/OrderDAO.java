package root.restaurant.order.dao;

import root.actors.Costumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


public class OrderDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String INSERT="insert into RestaurantOrder" +
            "(costumerid,orderTotal,ReservationId,orderTime, status ) " +
            " values(?,?,?,?,?)";
    private static final String SELECT_ORDER_ID="select orderid" +
            " from RestaurantOrder where costumerid=? and abs(orderTotal-?)<0.001";



    public int insert(Integer costumerId, Integer reservationId, Float total
            , Date orderTime, String status){

        Timestamp timestamp=  new Timestamp(orderTime.getTime());
        return jdbcTemplate.update(INSERT,costumerId, total, reservationId, timestamp, status);
    }



    public  int selectOrderId(final Integer costumerId, final Float total){

        List<Integer> orderIdList= jdbcTemplate.query(SELECT_ORDER_ID


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1,costumerId);
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

