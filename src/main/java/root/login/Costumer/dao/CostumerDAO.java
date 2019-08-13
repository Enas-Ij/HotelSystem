package root.login.Costumer.dao;

import root.actors.Costumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CostumerDAO {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT="insert into costumer" +
            "(costumerEmail,costumerFirstName,costumerLastName" +
            ",costumerPhone,costumerPassword)" +
            " values(?,?,?,?,?)";
    private static final String DELETE="delete from costumer where costumerId =?";
    private static final String UPDATE="update costumer set costumerEmail=?" +
            ",costumerFirstName=?,costumerLastName=?,costumerPhone=?,costumerPassword=?" +
            " where costumerId =?" ;
    private  static final String SELECT="select * from costumer where costumerId =?";
    private  static final String SELECTBYEMAil="select * from costumer where costumerEmail =?";
    private  static final String SELECTID="select costumerId from costumer where costumerEmail=?";
    private  static final String SELECTPASSWORD="select costumerPassword from costumer where costumerEmail=?";



    public int insert(Costumer costumer) {

        return jdbcTemplate.update(INSERT,costumer.getEmail(),costumer.getFirstName(),
                costumer.getLastName(),costumer.getPhoneNum(),costumer.getPassword());
    }


    public int remove(String id) {

        return jdbcTemplate.update(DELETE,id) ;
    }



    public int update(Costumer costumer,String id) {

        return jdbcTemplate.update(UPDATE,costumer.getEmail(),costumer.getFirstName(),
                costumer.getLastName(),costumer.getPhoneNum(),costumer.getPassword(),id);
    }



    public Costumer select(final String id) {

       List<Costumer> costumerList= jdbcTemplate.query(SELECT


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1,id);
                    }
                    }


                ,new RowMapper<Costumer>() {

                    @Override
                    public Costumer mapRow(ResultSet resultSet, int i) throws SQLException {
                        Costumer costumer = new Costumer();
                        costumer.setId(resultSet.getInt("costumerId"));
                        costumer.setFirstName(resultSet.getString("costumerFirstName"));
                        costumer.setEmail(resultSet.getString("costumerEmail"));
                        costumer.setLastName(resultSet.getString("costumerLastName"));
                        costumer.setPhoneNum(resultSet.getString("costumerPhone"));
                        costumer.setPassword(resultSet.getString("costumerPassword"));
                        return costumer;
                    }}

                );
       return costumerList.get(0);
    }



//
    public Costumer selectByEmail(final String email) {

        List<Costumer> costumerList= jdbcTemplate.query(SELECTBYEMAil


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1,email);
                    }
                }


                ,new RowMapper<Costumer>() {

                    @Override
                    public Costumer mapRow(ResultSet resultSet, int i) throws SQLException {
                        Costumer costumer = new Costumer();
                        costumer.setId(resultSet.getInt("costumerId"));
                        costumer.setFirstName(resultSet.getString("costumerFirstName"));
                        costumer.setEmail(resultSet.getString("costumerEmail"));
                        costumer.setLastName(resultSet.getString("costumerLastName"));
                        costumer.setPhoneNum(resultSet.getString("costumerPhone"));
                        costumer.setPassword(resultSet.getString("costumerPassword"));
                        return costumer;
                    }}

        );
        return costumerList.get(0);
    }


    //
    public Integer selectIdByEmail(final String email) {

       List<Integer> idList=   jdbcTemplate.query(SELECTID
                , new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1,email);
                    }
                }


                ,new RowMapper<Integer>() {

                    @Override
                    public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                        Integer id=resultSet.getInt("costumerId");
                        return id;
                    }}
        );

       if (idList.size()==0){
          int DOESNTEXIST=-1;
           return DOESNTEXIST;
       }
       return idList.get(0);
    }


    //
    public String selectPasswordByEmail(final String email) {
        List<String> passList=   jdbcTemplate.query(SELECTPASSWORD
                , new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1,email);
                    }
                }


                ,new RowMapper<String>() {

                    @Override
                    public String mapRow(ResultSet resultSet, int i) throws SQLException {
                        String  password=resultSet.getString("costumerPassword");
                        return password;
                    }}
        );

        if (passList.size()==0){
            String DOESNTEXIST="";
            return DOESNTEXIST;
        }
        return passList.get(0);
    }



    public List<Costumer> selectList(String selectCriteria) {
        return null;
    }
}
