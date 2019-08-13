package root.login.Employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import root.actors.Employee;
import root.login.Employee.dao.EmployeeDao;
import root.permission.dao.PermissionEmployeeDao;

public class EmployeeLoginService {

        @Autowired
        EmployeeDao employeeDao;
        @Autowired
        PermissionEmployeeDao permissionEmployeeDao;



        public Employee login(String email, String password){


            //Bring the costumer Info from the dataBase
            Employee employee=employeeDao.selectByEmail(email);
            employee.setPermissions(permissionEmployeeDao.selectPermissions(employee.getRole()));

            return employee;
        }


    }

