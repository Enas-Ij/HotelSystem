package root.permission.dao;

import root.permission.PermissionStringMapper;
import root.permission.PermissionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PermissionCostumerDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PermissionStringMapper permissionStringMapper;

    private static final String INSERT="insert into CostumerPermission" +
            "(CostumerId,Permission ) values(?,?)";
    private static final String DELETE="delete from CostumerPermission" +
            " where costumerId =? and Permission=?";
    private  static final String SELECT="select Permission from CostumerPermission" +
            " where costumerId =?";


    public int insert(int id,PermissionType permissionType) {
        System.out.println("in  Permission DAO");

       String permission= permissionStringMapper.PremissionToString(permissionType);

         return jdbcTemplate.update(INSERT,id,permission);
    }



    /*REMOVES A single USER PERMISSIONS*/
    public int removePermission(int id,PermissionType permissionType) {

       String permission= permissionStringMapper.PremissionToString(permissionType);

       return jdbcTemplate.update(DELETE,id,permission);
    }


    public List<PermissionType> selectList(final int id) {

        List<String> permissionList= jdbcTemplate.query(SELECT


                , new PreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setInt(1,id);
                    }
                }


                ,new RowMapper<String>() {

                    @Override
                    public String mapRow(ResultSet resultSet, int i) throws SQLException {
                        String permission=resultSet.getString("Permission");
                        return permission;
                    }}

        );

        //Transfer String Permissions to PermissionType
        List<PermissionType> permissionTypeList=new ArrayList<>();
        for (String permission: permissionList) {
            permissionTypeList.add(permissionStringMapper.StringToPremission(permission));
        }

        return permissionTypeList;
    }
}
