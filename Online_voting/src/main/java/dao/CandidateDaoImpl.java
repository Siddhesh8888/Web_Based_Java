package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static utils.DBUtils.*;

import pojo.Candidate;
import utils.DBUtils;
public class CandidateDaoImpl implements CandidateDao {

	private Connection cn;
	private PreparedStatement pst1,pst2;
	
	
	public CandidateDaoImpl() throws SQLException {
		super();
		cn = openConnection();
		pst1 = cn.prepareStatement("select * from candidates");
		pst2 = cn.prepareStatement("update candidates set votes=votes+1 where id=?");
	}


	public List<Candidate> getAllCandidate() throws SQLException {
		
		List<Candidate> candidateList = new ArrayList<Candidate>();
		
		try(ResultSet rst = pst1.executeQuery())
		{
			while(rst.next())
			{
				candidateList.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
			}
			return candidateList;
		}
	}


	@Override
	public String addVotes(int candidateId) throws SQLException {
		
		pst2.setInt(1, candidateId);
		
		int result = pst2.executeUpdate();
		if(result==1)
			return "votes added successfully!!!";
		
		return "votes not get added!!!";
	}

}
