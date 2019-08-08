package root.reservation.room.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_Numbers=
            "Select RoomNumber from room";
    private static final String SELECT_Kind=
            "Select RoomKind from room where RoomNumber=?";


    public List<String> selectRoomsNumbers(){

        List<String> roomsNums= jdbcTemplate.query(SELECT_Numbers

                ,new RowMapper<String>() {

                    @Override
                    public String mapRow(ResultSet resultSet, int i) throws SQLException {
                        String roomNum=resultSet.getString("RoomNumber");
                        return roomNum;
                    }}

        );

        return roomsNums;
    }


    public  String selectKind(final String roomNumber){

        List<String> kindList= jdbcTemplate.query(SELECT_Kind


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1,roomNumber);
                    }
                }


                ,new RowMapper<String>() {

                    @Override
                    public String mapRow(ResultSet resultSet, int i) throws SQLException {
                        String roomKind=resultSet.getString("RoomKind");
                        return roomKind;
                    }}

        );


        return kindList.get(0);
    }
}
