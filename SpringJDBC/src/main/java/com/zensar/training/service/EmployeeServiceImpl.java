package com.zensar.training.service;

import java.sql.Connection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zensar.training.bean.Employee;
import com.zensar.training.db.DBConnectionFactory;
import com.zensar.training.db.EmployeeDAO;

@Component
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDAO dao;

	public EmployeeDAO getDao() {
		return dao;
	}

	public void setDao(EmployeeDAO dao) {
		this.dao = dao;
	}

	@Override
	public boolean addEmployee(Employee employee) throws Exception {
		boolean result=false;
		Connection connection=DBConnectionFactory.createConnection();
		connection.setAutoCommit(false);
		result=dao.addEmployee(connection, employee);
		if(result==true) {
			connection.commit();
			return result;
		}
		else 
			connection.rollback();
		return false;

	}

	@Override
	public boolean updateEmployee(Employee employee) throws Exception {
		boolean result=false;
		Connection connection=DBConnectionFactory.createConnection();
		connection.setAutoCommit(result);
		//EmployeeDAO dao=new EmployeeJdbcImpl();
		result=dao.updateEmployee(connection, employee);
		if(result==true) {
			connection.commit();
			return result;
		}
		else 
			connection.rollback();
		return false;

	}

	@Override
	public boolean deleteEmployee(Employee employee) throws Exception {
		boolean result=false;
		Connection connection=DBConnectionFactory.createConnection();
		//EmployeeDAO dao=new EmployeeJdbcImpl();
		result=dao.deleteEmployee(connection, employee);
		return result;
	}

	@Override
	public Employee findEmployee(int id) throws Exception {
		Connection connection=DBConnectionFactory.createConnection();
		Employee employee=dao.findEmployee(connection, id);
		return employee;
	}

	@Override
	public List<Employee> findAlEmployees() throws Exception {
		Connection connection=DBConnectionFactory.createConnection();
		return dao.findAllEmployees(connection);
	}

}
