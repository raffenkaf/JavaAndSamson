package com.samson.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.samson.model.UserProfile;

public class UserProfileDAOImpl implements UserProfileDAO{
	
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	public UserProfileDAOImpl(DataSource dataSource) {
		jdbcTemplate.setDataSource(dataSource);
	}
	
	@Override
	public void addUser(UserProfile addUser) {
        String sql = "INSERT INTO UserProfiles (username, name, password, sessionId)"
                + " VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, addUser.getUserName(), addUser.getName(), 
    		addUser.getPassword(), addUser.getSessionId());
        
        sql = "INSERT INTO UserRoles (username, role) VALUES (?, ?)";
        jdbcTemplate.update(sql, addUser.getUserName(), "user");
	}

	@Override
	public void deleteUserBySessionId(String sessionId) {
	    
		String sql = "SELECT * FROM UserProfiles WHERE sessionId=?";
	    UserProfile userProfile = (UserProfile)jdbcTemplate.queryForObject(sql, new Object[]{sessionId}, 
	    		new RowMapper<UserProfile>() {
	    	
	    	@Override
	    	public UserProfile mapRow(ResultSet rs, int rowNum)
	    			throws SQLException {
	    		UserProfile userProfile = new UserProfile();
	    		
	    		userProfile.setUserName(rs.getString("username"));
	    		userProfile.setName(rs.getString("name"));
	    		userProfile.setPassword(rs.getString("password"));
	    		userProfile.setSessionId(rs.getString("sessionId"));
	    		
	    		return userProfile;
	    		
	    	}
	    });
		
	    sql = "DELETE FROM UserProfiles WHERE sessionId=?";
	    jdbcTemplate.update(sql, sessionId);
	    
	    sql = "DELETE FROM UserRoles WHERE username=?";
	    jdbcTemplate.update(sql, userProfile.getUserName());
	}

	@Override
	public void deleteUserByUserName(String username) {
		String sql;
		
		sql = "DELETE FROM UserProfiles WHERE username=?";
	    jdbcTemplate.update(sql, username);
	    
	    sql = "DELETE FROM UserRoles WHERE username=?";
	    jdbcTemplate.update(sql, username);
	}

	@Override
	public UserProfile getUserByUserName(String username) {
	    String sql = "SELECT * FROM UserProfiles WHERE username=?";
	    return (UserProfile)jdbcTemplate.queryForObject(sql, new Object[]{username}, 
	    		new RowMapper<UserProfile>() {
	    	
	    	@Override
	    	public UserProfile mapRow(ResultSet rs, int rowNum)
	    			throws SQLException {
	    		UserProfile userProfile = new UserProfile();
	    		
	    		userProfile.setUserName(rs.getString("username"));
	    		userProfile.setName(rs.getString("name"));
	    		userProfile.setPassword(rs.getString("password"));
	    		userProfile.setSessionId(rs.getString("sessionId"));
	    		
	    		return userProfile;
	    		
	    	}
	    });
	    
	}

	@Override
	public void deleteSession(String sessionId) {
		String sql = "UPDATE UserProfiles "
				+ "SET sessionId = null WHERE sessionId = ?";
		jdbcTemplate.update(sql, sessionId);
	}

	@Override
	public List<UserProfile> getAllUsers() {
	    String sql = "SELECT * FROM UserProfiles";
	    List<UserProfile> listUserProfiles = jdbcTemplate.query(sql, new RowMapper<UserProfile>() {
	    	@Override
	    	public UserProfile mapRow(ResultSet rs, int rowNum)
	    			throws SQLException {
	    		UserProfile userProfile = new UserProfile();
	    		
	    		userProfile.setUserName(rs.getString("username"));
	    		userProfile.setName(rs.getString("name"));
	    		userProfile.setPassword(rs.getString("password"));
	    		userProfile.setSessionId(rs.getString("sessionId"));
	    		
	    		return userProfile;
	    	}
	    });
		return listUserProfiles;
	}

	@Override
	public void addSessionId(String userName, String sessionId) {
		String sql = "update UserProfiles set sessionId=? WHERE username=?";
		jdbcTemplate.update(sql, sessionId, userName);
	}
}
