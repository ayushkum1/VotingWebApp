package com.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static com.app.utils.DBUtils.fetchDBConnection;
import com.app.my_pojos.Voter;

public class VoterDAOImpl implements IVoterDAO{

	private Connection cn;
	private PreparedStatement pst;
	
	public VoterDAOImpl() throws SQLException, ClassNotFoundException{
	
		String loginStmt = "select * from my_customers where email=? and password=?";
		
		cn = fetchDBConnection();
		
		pst = cn.prepareStatement(loginStmt);
		
	}

	@Override
	public Voter login(String name, String password) throws SQLException {
		
		Voter voter = null;
		
		pst.setString(1, name);
		pst.setString(2, password);
		
		try(ResultSet rst = pst.executeQuery()){
			if(rst.next()) {
				voter = new Voter(rst.getInt(1), rst.getString(2), rst.getString(3),
							rst.getString(4), rst.getBoolean(5), rst.getString(6));
			}
		}
		
		return voter;
	}

	@Override
	public String updateVotingStatus(int voterId) throws SQLException {
		
		return null;
	}
	
	public void cleanUp() throws SQLException{
		
		if(pst != null) {
			pst.close();
		}
		
		if(cn != null) {
			cn.close();
		}
	}

}
