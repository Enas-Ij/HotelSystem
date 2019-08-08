package root.login.dao;

import root.actors.Costumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.Date;

public class CostumerLoginDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private static final String INSERT="insert into CostumerLogin" +
            "(CostumerId,loginTime ) " +
            " values(?,?)";

    private static final String UPDATE_LOGOUT_TIME="update CostumerLogin set logoutTime=?" +
            "where CostumerId=? and loginTime=?";


    public int insert(Costumer costumer,Date date) {

       Timestamp sqlDate= new Timestamp(date.getTime());

        return jdbcTemplate.update(INSERT,costumer.getId(),sqlDate);
    }


    public int updateLogoutTime(Costumer costumer, Date loginDate, Date logOutDate){

        Timestamp sqlLoginDate= new Timestamp(loginDate.getTime());
        Timestamp sqlLogoutDate= new Timestamp(logOutDate.getTime());

        return jdbcTemplate.update(UPDATE_LOGOUT_TIME, sqlLogoutDate, costumer.getId(), sqlLoginDate);
    }
}
