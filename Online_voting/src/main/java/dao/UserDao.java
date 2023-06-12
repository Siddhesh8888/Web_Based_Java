package dao;

import java.sql.Date;
import java.sql.SQLException;

import pojo.User;

public interface UserDao {
	
	User authenticateUser(String email,String password) throws SQLException;
	String voterAdd(String fname,String lname,String email,String password,Date dob) throws SQLException;
	String updateVoterStatus(int id) throws SQLException;
}
