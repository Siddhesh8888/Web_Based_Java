package dao;

import java.sql.SQLException;
import java.util.List;

import pojo.Candidate;

public interface CandidateDao {
	
	List<Candidate> getAllCandidate() throws SQLException;
	String addVotes(int candidateId) throws SQLException;

}
