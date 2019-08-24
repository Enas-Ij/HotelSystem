package root.restaurant.order.dao;


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
            "(costumerid,orderTotal,ReservationId,orderTime, orderStatus ) " +
            " values(?,?,?,?,?)";
    private static final String UPDATE_STATUS="update  RestaurantOrder set " +
            " orderStatus=? , deliveryTime=? where orderId=?";
    private static final String SELECT_ORDER_ID="select orderId" +
            " from RestaurantOrder where costumerid=? and abs(orderTotal-?)<0.001";
    private static final String SELECT_ORDER_ID_BY_STATUS="select orderId" +
            " from RestaurantOrder where orderStatus=?";
    private static final String SELECT_RESERVATION_ID ="select ReservationId" +
            " from RestaurantOrder where orderId=?";
    private static final String SELECT_PRICE= "select orderTotal from  RestaurantOrder " +
            "where reservationID=?";

    public int insert(Integer costumerId, Integer reservationId, Float total
            , Date orderTime, String status){

        Timestamp timestamp=  new Timestamp(orderTime.getTime());
        return jdbcTemplate.update(INSERT,costumerId, total, reservationId, timestamp, status);
    }


    public int updateStatus(Integer orderId, String status, Date deliveryTime){

        Timestamp timestamp=  new Timestamp(deliveryTime.getTime());
        return jdbcTemplate.update(UPDATE_STATUS, status, deliveryTime, orderId);
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
                        Integer orderId=resultSet.getInt("orderId");
                        return orderId;
                    }}

        );

        if (orderIdList.size()==0){
            return -1;
        }

        return orderIdList.get(0);
    }


    public  List<Integer> selectOrderIdByStatus(final String status){

        List<Integer> orderIdList= jdbcTemplate.query(SELECT_ORDER_ID_BY_STATUS


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1,status);

                    }
                }


                ,new RowMapper<Integer>() {

                    @Override
                    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                        Integer orderId=resultSet.getInt("orderId");
                        return orderId;
                    }}

        );


        return orderIdList;
    }

    public  int selectReservationId(final Integer orderId){

        List<Integer> reservationIdList= jdbcTemplate.query(SELECT_RESERVATION_ID


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1,orderId);
                    }
                }


                ,new RowMapper<Integer>() {

                    @Override
                    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                        Integer reservationId=resultSet.getInt("ReservationId");
                        return reservationId;
                    }}

        );

        if (reservationIdList.size()==0){
            return -1;
        }

        return reservationIdList.get(0);
    }



    public  List<Float> selectOrderPrice(final Integer reservationId){

        List<Float> orderPrices= jdbcTemplate.query(SELECT_PRICE


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1,reservationId);
                    }
                }


                ,new RowMapper<Float>() {

                    @Override
                    public Float mapRow(ResultSet resultSet, int i) throws SQLException {
                        Float orderPrice=resultSet.getFloat("orderTotal");
                        return orderPrice;
                    }}

        );



        return orderPrices;
    }
}

