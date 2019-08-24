package root.reservation.dao;

import root.actors.Costumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String INSERT=" insert into roomReservation " +
            "(costumerId,RoomNumber,fromDate,toDate)" +
            " values(?,?,?,?)";
    private static final String UPDATE_TO_DATE=" update roomReservation set toDate=? where" +
            " ReservationId=?";

    private static final String CANCEL="delete from roomReservation" +
            "where costumerId =? and rooRoomNumber=? and fromDate=? and toDate=?";

    private static final String SLCT_AVLBL_ROOMS="select DISTINCT RoomNumber from roomReservation " +
            "where RoomNumber not in (select DISTINCT RoomNumber from roomReservation where" +
                    "((fromDate>=? and fromDate<=?) or" +
                    " (toDate>=? and toDate<=?)))";

    private static final String SLCT_CSTMR_RSRVTION=
            "select DISTINCT ReservationId from roomReservation where costumerId=? and toDate>=?";
    private static final String SLCT_RSRVTION_ROOM=
            "select DISTINCT RoomNumber from roomReservation where ReservationId=?";
    private static final String SLCT_TODATE=
            "select DISTINCT toDate from roomReservation where ReservationId=?";
    private static final String SLCT_FROMDATE=
            "select DISTINCT fromDate from roomReservation where ReservationId=?";
    private static final String SLCT_CSTMER_CURRENT_ROOMS="select DISTINCT ReservationId from roomReservation " +
            "where costumerId=? and fromDate<=? and toDate>=?";
    private static final String SLCT_CSTMER_ID="select DISTINCT costumerId from roomReservation " +
            "where ReservationId=?";

    //insert new reservation to the db
    public int insert(Costumer costumer, String roomNum, Date fromDate, Date toDate){

        java.sql.Date sqlFromDate= new java.sql.Date(fromDate.getTime());
        java.sql.Date sqlToDate= new java.sql.Date(toDate.getTime());

        return jdbcTemplate.update(INSERT, costumer.getId(),roomNum, sqlFromDate, sqlToDate);
    }


    //update reservation toDate
    public int updateToDate(Integer reservationId, Date toDate){

        java.sql.Date sqlToDate= new java.sql.Date(toDate.getTime());

        return jdbcTemplate.update(UPDATE_TO_DATE, sqlToDate, reservationId);
    }



    //delete reservation to the db
    public int cancel(Costumer costumer, String roomNum, Date fromDate, Date toDate){

        java.sql.Date sqlFromDate= new java.sql.Date(fromDate.getTime());
        java.sql.Date sqlToDate= new java.sql.Date(toDate.getTime());

        return jdbcTemplate.update(CANCEL, costumer.getId(),roomNum, sqlFromDate, sqlToDate);
    }


    //return a list of the available room numbers (that are not reserved) on certain dates
    public List<String> selectAvailableRooms(final Date fromDate, Date toDate){

        final java.sql.Date sqlFromDate= new java.sql.Date(fromDate.getTime());
        final java.sql.Date sqlToDate= new java.sql.Date(toDate.getTime());

        List<String> rooms= jdbcTemplate.query(SLCT_AVLBL_ROOMS


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setDate(1,sqlFromDate);
                        preparedStatement.setDate(2,sqlToDate);
                        preparedStatement.setDate(3,sqlFromDate);
                        preparedStatement.setDate(4,sqlToDate);
                    }
                }


                ,new RowMapper<String>() {

                    @Override
                    public String mapRow(ResultSet resultSet, int i) throws SQLException {
                        String room=resultSet.getString("RoomNumber");
                        return room;
                    }}

        );

        return rooms;
    }



    //return a list of current and future reservations of the costumer
    public List<Integer> selectReservationId(final Integer costumerId){


        Date date= new Date();
        final java.sql.Date sqlToday= new java.sql.Date(date.getTime());

        List<Integer> reservations= jdbcTemplate.query(SLCT_CSTMR_RSRVTION


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1,costumerId);
                        preparedStatement.setDate(2,sqlToday);

                    }
                }


                ,new RowMapper<Integer>() {

                    @Override
                    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                        Integer reservationId=resultSet.getInt("ReservationId");
                        return reservationId;
                    }}

        );



        return reservations;
    }



    //return a map of current and future reservations of the costumer and the room number for each reservation
    public Map<Integer,String> selectReservedRooms(final Integer costumerId){


        Map<Integer,String> reservationIdRoomMap= new HashMap<>();
        final List<Integer> reservations= selectReservationId(costumerId) ;

        for (final Integer reservation : reservations ) {

            reservationIdRoomMap.put(reservation, selectReservationRoomNum(reservation));
        }




        return reservationIdRoomMap;
    }



    //return room number for each reservation
    public String selectReservationRoomNum(final Integer reservationId){


            List<String> roomList=  jdbcTemplate.query(SLCT_RSRVTION_ROOM


                    , new PreparedStatementSetter() {

                        @Override
                        public void setValues(PreparedStatement preparedStatement) throws SQLException {
                            preparedStatement.setInt(1,reservationId);

                        }
                    }


                    ,new RowMapper<String>() {

                        @Override
                        public String mapRow(ResultSet resultSet, int i) throws SQLException {
                            String roomNumber=resultSet.getString("RoomNumber");
                            return roomNumber ;
                        }}

            );

            if (roomList.size()==0){
                return "";}

            return roomList.get(0);

    }




    // return the date in which the reservation starts
    public Date selectFromDate(final Integer reservationId){


         List<java.sql.Date> fromDateList= jdbcTemplate.query(SLCT_FROMDATE


                    , new PreparedStatementSetter() {

                        @Override
                        public void setValues(PreparedStatement preparedStatement) throws SQLException {
                            preparedStatement.setInt(1, reservationId);

                        }
                    }


                    ,new RowMapper<java.sql.Date>() {

                        @Override
                        public java.sql.Date mapRow(ResultSet resultSet, int i) throws SQLException {
                            java.sql.Date fromDate=resultSet.getDate("fromDate");
                            return fromDate ;
                        }}

            );


         Date fromDate= new Date(fromDateList.get(0).getTime());

        return fromDate;
    }




    // return the date in which the reservation ends
    public Date selectToDate(final Integer reservationId){


        List<java.sql.Date> toDateList= jdbcTemplate.query(SLCT_TODATE


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1, reservationId);

                    }
                }


                ,new RowMapper<java.sql.Date>() {

                    @Override
                    public java.sql.Date mapRow(ResultSet resultSet, int i) throws SQLException {
                        java.sql.Date toDate=resultSet.getDate("toDate");
                        return toDate ;
                    }}

        );


        Date toDate= new Date(toDateList.get(0).getTime());

        return toDate;
    }


    //return a list of current reservations of the costumer
    public List<Integer> selectCurrentReservationId(final Integer costumerId){


        Date date= new Date();
        final java.sql.Date sqlToday= new java.sql.Date(date.getTime());

        List<Integer> reservations= jdbcTemplate.query(SLCT_CSTMER_CURRENT_ROOMS


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1,costumerId);
                        preparedStatement.setDate(2,sqlToday);
                        preparedStatement.setDate(3,sqlToday);


                    }
                }


                ,new RowMapper<Integer>() {

                    @Override
                    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                        Integer reservationId=resultSet.getInt("ReservationId");
                        return reservationId;
                    }}

        );



        return reservations;
    }





    public Integer selectCostumerId(final Integer reservationId){



        List<Integer> costumerIdList= jdbcTemplate.query(SLCT_CSTMER_ID


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1,reservationId);



                    }
                }


                ,new RowMapper<Integer>() {

                    @Override
                    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                        Integer costumerId=resultSet.getInt("costumerId");
                        return costumerId;
                    }}

        );


        if (costumerIdList.size()==0){
            return -1;
        }
        return costumerIdList.get(0);
    }
}
