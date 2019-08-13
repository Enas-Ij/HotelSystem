package root.permission.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import root.permission.PermissionStringMapper;
import root.permission.PermissionType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PermissionEmployeeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PermissionStringMapper permissionStringMapper;

    private static final String SELECT=" select permission from RolePermission where employeeRole=?";



    public List<PermissionType> selectPermissions (final String role){

            List<String> permissionList= jdbcTemplate.query(SELECT


                    , new PreparedStatementSetter() {

                        @Override
                        public void setValues(PreparedStatement preparedStatement) throws SQLException {
                            preparedStatement.setString(1,role);
                        }
                    }


                    ,new RowMapper<String>() {

                        @Override
                        public String mapRow(ResultSet resultSet, int i) throws SQLException {
                            String permission=resultSet.getString("permission");
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




