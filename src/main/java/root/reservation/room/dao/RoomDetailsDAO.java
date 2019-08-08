package root.reservation.room.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomDetailsDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SELECT_Kinds=
            "Select RoomKind from RoomDetails";
    private static final String SELECT_PRICE=
            "Select price from RoomDetails where RoomKind=?";
    private static final String SELECT_DETAIL=
            "Select details from RoomDetails where RoomKind=?";


    public  List<String> selectRoomKinds(){

        List<String> roomKinds= jdbcTemplate.query(SELECT_Kinds

                ,new RowMapper<String>() {

                    @Override
                    public String mapRow(ResultSet resultSet, int i) throws SQLException {
                        String roomKind=resultSet.getString("RoomKind");
                        return roomKind;
                    }}

        );

        return roomKinds;
    }


    public  String selectDetail(final String roomKind){

        List<String> detailsList= jdbcTemplate.query(SELECT_DETAIL


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1,roomKind);
                    }
                }


                ,new RowMapper<String>() {

                    @Override
                    public String mapRow(ResultSet resultSet, int i) throws SQLException {
                        String details=resultSet.getString("details");
                        return details;
                    }}

        );


        return detailsList.get(0);
    }

    public  Float selectPrice(final String roomKind){

        List<Float> priceList= jdbcTemplate.query(SELECT_PRICE


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1,roomKind);
                    }
                }


                ,new RowMapper<Float>() {

                    @Override
                    public Float mapRow(ResultSet resultSet, int i) throws SQLException {
                        Float price=resultSet.getFloat("Price");
                        return price;
                    }}

        );


        return priceList.get(0);
    }


}
