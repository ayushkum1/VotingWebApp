package com.app.dao;

import java.sql.SQLException;

import com.app.my_pojos.Voter;

public interface IVoterDAO {

	Voter login(String name, String password) throws SQLException;
	String updateVotingStatus(int voterId) throws SQLException;
	
}
