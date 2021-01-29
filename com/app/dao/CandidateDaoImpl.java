package com.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.my_pojos.Candidate;
import com.app.my_pojos.Voter;

import static com.app.utils.DBUtils.fetchDBConnection;

public class CandidateDaoImpl implements ICandidateDAO{

	private Connection cn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rst;
	
	public CandidateDaoImpl() throws SQLException, ClassNotFoundException{
	
		String listCandidateStmt = "select * from candidates";
		String incrementVoteStmt = "update table candidates set votes = votes + 1 where id=?";
		
		cn = fetchDBConnection();
		st = cn.createStatement();
		rst = st.executeQuery(listCandidateStmt);
		pst = cn.prepareStatement(incrementVoteStmt);
		
	}
	
	@Override
	public List<Candidate> listCandidates() throws SQLException {

		List<Candidate> candidates = new ArrayList<>();
		
		while(rst.next()) {
			candidates.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
		}
		
		return candidates;
	}

	@Override
	public String incrementVotes(int candidateId) throws SQLException {
		
		pst.setInt(1, candidateId);
		
		if(pst.executeUpdate() == 0) {
			return "Vote Not Added";
		}
		
		return "Voted successfully";
	}

}
