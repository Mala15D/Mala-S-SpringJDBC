package com.zensar.training.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.zensar.training.bean.Employee;
import com.zensar.training.bean.Gender;

@Component
public class EmployeeJdbcImpl implements EmployeeDAO {

	@Override
	public boolean addEmployee(Connection connection, Employee employee) throws Exception {
		PreparedStatement statement = connection.prepareStatement(INSERT_QRY);
		statement.setString(1, employee.getName());

		LocalDate localDate = employee.getHiredDate();
		Date dateOfJoining = DateConversion.convertToSQLDate(localDate);

		statement.setDate(2, dateOfJoining);
		statement.setString(3, employee.getGrade() + "");
		statement.setDouble(4, employee.getBasicSalary());
		statement.setString(5, employee.getGender().toString().charAt(0) + "");

		int count = statement.executeUpdate();
		if (count > 0)
			return true;
		return false;
	}

	@Override
	public boolean updateEmployee(Connection connection, Employee employee) throws Exception {
		PreparedStatement statement = connection.prepareStatement(UPDATE_QRY);
		statement.setString(1, employee.getName());

		LocalDate localDate = employee.getHiredDate();
		Date dateOfJoining = DateConversion.convertToSQLDate(localDate);

		statement.setDate(2, dateOfJoining);
		statement.setString(3, employee.getGrade() + "");
		statement.setDouble(4, employee.getBasicSalary());
		statement.setString(5, employee.getGender().toString().charAt(0) + "");
		statement.setInt(6,employee.getId());
		int count = statement.executeUpdate();
		if (count > 0)
			return true;
		return false;
	}

	@Override
	public boolean deleteEmployee(Connection connection, Employee employee) throws Exception {
		PreparedStatement statement = connection.prepareStatement(DELETE_QRY);
		statement.setInt(1, employee.getId());
		int count = statement.executeUpdate();
		if (count > 0)
			return true;
		return false;
	}

	@Override
	public Employee findEmployee(Connection connection, int empId) throws Exception {
		PreparedStatement statement = connection.prepareStatement(FIND_QRY);
		statement.setInt(1, empId);

		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			Employee employee = new Employee();
			employee.setId(rs.getInt(1));
			employee.setName(rs.getString(2));

			Date date = rs.getDate(3);
			LocalDate doj = DateConversion.convertToLocalDate(date);

			employee.setHiredDate(doj);
			employee.setGrade(rs.getString(4).charAt(0));
			employee.setBasicSalary(rs.getDouble(5));
			char gender = rs.getString(6).charAt(0);
			if (gender == 'M')
				employee.setGender(Gender.MALE);
			if (gender == 'F')
				employee.setGender(Gender.FEMALE);
			return employee;
		}
		return null;
	}

	@Override
	public List<Employee> findAllEmployees(Connection connection) throws Exception {
		List<Employee> employees=new ArrayList<>();
		
		PreparedStatement statement=connection.prepareStatement(FIND_ALL_QRY);
		ResultSet rs=statement.executeQuery();
		while(rs.next()) {
			Employee employee = new Employee();
			employee.setId(rs.getInt(1));
			employee.setName(rs.getString(2));

			Date date = rs.getDate(3);
			LocalDate doj = DateConversion.convertToLocalDate(date);

			employee.setHiredDate(doj);
			employee.setGrade(rs.getString(4).charAt(0));
			employee.setBasicSalary(rs.getDouble(5));
			char gender = rs.getString(6).charAt(0);
			if (gender == 'M')
				employee.setGender(Gender.MALE);
			if (gender == 'F')
				employee.setGender(Gender.FEMALE);
			employees.add(employee);
		}
		return employees;
	}

}
