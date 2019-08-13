package root.login.Employee.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import root.actors.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT="insert into employee " +
            "(employeeEmail, employeeFirstName, employeeLastName" +
            ",employeePhone, employeePassword, salary, employeeRole)" +
            " values(?,?,?,?,?)";
    private static final String UPDATE="update employee set employeeEmail=?" +
            ",employeeFirstName=?,employeeLastName=?,employeePhone=?,employeePassword=?" +
            ",salary=?, employeeRole=? where employeeId =?" ;
    private  static final String SELECT="select * from employee where employeeId =?";
    private  static final String SELECTBYEMAil="select * from employee where employeeEmail =?";
    private  static final String SELECTID="select employeeId from employee where employeeEmail=?";
    private  static final String SELECTPASSWORD="select employeePassword from employee where employeeEmail=?";



    public int insert(Employee employee) {

        return jdbcTemplate.update(INSERT,employee.getEmail(),employee.getFirstName(),
                employee.getLastName(),employee.getPhoneNum(),employee.getPassword());
    }



    public int update(Employee employee,String id) {

        return jdbcTemplate.update(UPDATE,employee.getEmail(),employee.getFirstName(),
                employee.getLastName(),employee.getPhoneNum(),employee.getPassword(),id);
    }



    public Employee select(final String id) {

        List<Employee> costumerList= jdbcTemplate.query(SELECT


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1,id);
                    }
                }


                ,new RowMapper<Employee>() {

                    @Override
                    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
                      Employee employee = new Employee();
                        employee.setId(resultSet.getInt("employeeId"));
                        employee.setFirstName(resultSet.getString("employeeFirstName"));
                        employee.setEmail(resultSet.getString("employeeEmail"));
                        employee.setLastName(resultSet.getString("employeeLastName"));
                        employee.setPhoneNum(resultSet.getString("employeePhone"));
                        employee.setPassword(resultSet.getString("employeePassword"));
                        employee.setRole(resultSet.getString("employeeRole"));
                        employee.setSalary(resultSet.getFloat("salary"));
                        return employee;
                    }}

        );
        return costumerList.get(0);
    }



    //
    public Employee selectByEmail(final String email) {

        List<Employee> costumerList= jdbcTemplate.query(SELECTBYEMAil


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setString(1,email);
                    }
                }


                ,new RowMapper<Employee>() {

                    @Override
                    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
                        Employee employee = new Employee();
                        employee.setId(resultSet.getInt("employeeId"));
                        employee.setFirstName(resultSet.getString("employeeFirstName"));
                        employee.setEmail(resultSet.getString("employeeEmail"));
                        employee.setLastName(resultSet.getString("employeeLastName"));
                        employee.setPhoneNum(resultSet.getString("employeePhone"));
                        employee.setPassword(resultSet.getString("employeePassword"));
                        employee.setRole(resultSet.getString("employeeRole"));
                        employee.setSalary(resultSet.getFloat("salary"));
                        return employee;
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
                        Integer id=resultSet.getInt("employeeId");
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
                        String  password=resultSet.getString("employeePassword");
                        return password;
                    }}
        );

        if (passList.size()==0){
            String DOESNTEXIST="";
            return DOESNTEXIST;
        }
        return passList.get(0);
    }

}
