package root.messaging.dao;

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

public class MessageDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private   static String INSERT= "insert into message (reservationId, messageContent, " +
            "submittingTime , messageStatus) values(?,?,?,?)";
    private   static String UPDATE_STATUS= "update  message set messageStatus=?" +
            " where messageId=?";
    private   static  String SELECT_MESSAGE_ID= "select messageId from message" +
            " where reservationId=? and submittingTime=?";
    private static  String SELECT_ID_BY_STATUS= "select messageId from message" +
            " where messageStatus=?";
    private static  String SELECT_CONTENT_BY_ID= "select messageContent from message" +
            " where messageId=?";
    private static  String SELECT_Time_BY_ID= "select submittingTime from message" +
            " where messageId=?";
    private static  String SELECT_RESERVATION_ID= "select reservationId from message" +
            " where messageId=?";



    public int insert (int reservationId, String messageContent,
                       Date submittingTime, String messageStatus ){

       Timestamp timestamp=  new Timestamp(submittingTime.getTime());

        return  jdbcTemplate.update(INSERT, reservationId, messageContent
                            , timestamp, messageStatus);
    }



    public int updateStatus( Integer messageId, String status){

        return  jdbcTemplate.update(UPDATE_STATUS, status, messageId);
    }




    public  int selectMessageId(final Integer reservationId, final Date submittingTime){

        final Timestamp timestamp=  new Timestamp(submittingTime.getTime());

        List<Integer> messageIdList= jdbcTemplate.query(SELECT_MESSAGE_ID


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1,reservationId);
                        preparedStatement.setTimestamp(2,timestamp);
                    }
                }


                ,new RowMapper<Integer>() {

                    @Override
                    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                        Integer messageId=resultSet.getInt("messageId");
                        return messageId;
                    }}

        );

        if (messageIdList.size()==0){
            return -1;
        }

        return messageIdList.get(0);
    }




    public  List<Integer> selectMessageIdByStatus(final String status){

        List<Integer> messageIdList= jdbcTemplate.query(SELECT_ID_BY_STATUS


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1,status);
                    }
                }


                ,new RowMapper<Integer>() {

                    @Override
                    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                        Integer messageId=resultSet.getInt("messageId");
                        return messageId;
                    }}

        );


        return messageIdList;
    }




    public  String selectMessageContent(final Integer messageID){

        List<String> messageContentList= jdbcTemplate.query(SELECT_CONTENT_BY_ID


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1,messageID);
                    }
                }


                ,new RowMapper<String>() {

                    @Override
                    public String mapRow(ResultSet resultSet, int i) throws SQLException {
                        String messageContent=resultSet.getString("messageContent");
                        return messageContent;
                    }}

        );


        if (messageContentList.size()==0){
            return "";
        }

        return messageContentList.get(0);
    }




    public  Date selectSubmittingTime(final Integer messageID){

        List<Date> submittingTimeList= jdbcTemplate.query(SELECT_Time_BY_ID


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1,messageID);
                    }
                }


                ,new RowMapper<Date>() {

                    @Override
                    public Date mapRow(ResultSet resultSet, int i) throws SQLException {
                        Date date=resultSet.getTimestamp("submittingTime");
                        return date;
                    }}

        );


        if (submittingTimeList.size()==0){
            return null;
        }

        return submittingTimeList.get(0);
    }




    public  Integer selectReservationId(final Integer messageID){

        List<Integer> reservationIdList= jdbcTemplate.query(SELECT_RESERVATION_ID


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1,messageID);
                    }
                }


                ,new RowMapper<Integer>() {

                    @Override
                    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                        Integer reservationId=resultSet.getInt("reservationId");
                        return reservationId;
                    }}

        );


        if (reservationIdList.size()==0){
            return -1;
        }

        return reservationIdList.get(0);
    }
}
