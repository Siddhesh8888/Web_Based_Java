package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pojo.User;

import static utils.DBUtils.*;

public class UserDaoImpl implements UserDao{
	
	private Connection cn;
	private PreparedStatement pst1,pst2,pst3;
	public UserDaoImpl() throws SQLException {
		cn = openConnection();
		pst1 = cn.prepareStatement("select * from users where email=? and password=?");
		pst2 = cn.prepareStatement("update users set status=1 where id=?");
		pst3 = cn.prepareStatement("insert into users values(default,?,?,?,?,?,?,?)");
	}
	
	@Override
	public User authenticateUser(String email, String password) throws SQLException {
		pst1.setString(1, email);
		pst1.setString(2, password);
		
		try(ResultSet rst = pst1.executeQuery()){
			if(rst!=null)
			{
				if(rst.next())
				{
					// id | first_name | last_name | email             | password | dob        | status | role
					return new User(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getDate(6),rst.getBoolean(7),rst.getString(8));
				}
			}
		}
		return null;
	}
	
	public void cleanup() throws SQLException {
		if(pst1!=null || pst2!=null || pst3!=null)
		{
			pst1.close();
			pst2.close();
			pst3.close();
		}
		closeConnection();
		System.out.println("userDao cleaned up!!!");
	}

	@Override
	public String updateVoterStatus(int id) throws SQLException {
		pst2.setInt(1, id);
		
		int result = pst2.executeUpdate();
		
		if(result == 1)
		{
			return "Voting status updated successfully!!!";
		}
		return "Voting status not updated!!";
	}

	@Override
	public String voterAdd(String fname, String lname, String email, String password, Date dob) throws SQLException {
		pst3.setString(1, fname);
		pst3.setString(2, lname);
		pst3.setString(3, email);
		pst3.setString(4, password);
		pst3.setDate(5, dob);
		pst3.setBoolean(6, false);
		pst3.setString(7, "voter");
		
		
		int rowCount = pst3.executeUpdate();
		
		if(rowCount == 1)
			return "voter added successfully!!!";
		
		return "voter not added!!";
	}

}
