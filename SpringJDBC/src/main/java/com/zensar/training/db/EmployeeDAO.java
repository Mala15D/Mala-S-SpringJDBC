package com.zensar.training.db;

import java.sql.Connection;
import java.util.List;
import com.zensar.training.bean.Employee;


public interface EmployeeDAO  {
	String INSERT_QRY="insert into TEMPLOYEES(NAME,DOJ,GRADE,BASIC_SALARY,GENDER) values(?,?,?,?,?)";
	String UPDATE_QRY="update TEMPLOYEES set NAME=?,DOJ=?,GRADE=?,BASIC_SALARY=?, GENDER=? where ID=?";
	String DELETE_QRY="delete from TEMPLOYEES where ID=?";
	String FIND_QRY="select * from TEMPLOYEES where ID=?";
	String FIND_ALL_QRY="select * from TEMPLOYEES";

	boolean addEmployee(Connection connection,Employee employee) throws Exception;
	boolean updateEmployee(Connection connection,Employee employee) throws Exception;
	boolean deleteEmployee(Connection connection,Employee employee) throws Exception;
	Employee findEmployee(Connection connection,int empId) throws Exception;
	List<Employee> findAllEmployees(Connection connection) throws Exception;


}
