package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	
	public int login(String userID, String userPassword) {
		String SQL = "SELECT userPasswrod FROM USERS WHERE userID = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = util.DatabaseUtil.getConnection();
			ps = conn.prepareStatement(SQL);
			ps.setString(1, userID);
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(userPassword)) {
					return 1;
				} else {
					return 0;
				}
			}
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn != null){conn.close();}} catch(Exception e) {e.printStackTrace();}
			try {if(ps != null){ps.close();}} catch(Exception e) {e.printStackTrace();}
			try {if(rs != null){rs.close();}} catch(Exception e) {e.printStackTrace();}
		}
		return -2;
	}
	
	public int join(UserDTO user) {
		String SQL = "INSERT INTO USERS VALUES(?,?,?,?,'0')";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = util.DatabaseUtil.getConnection();
			ps = conn.prepareStatement(SQL);
			ps.setString(1, user.getUserID());
			ps.setString(2, user.getUserPassword());
			ps.setString(3, user.getUserEmail());
			ps.setString(4, user.getUserEmailHash());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn != null){conn.close();}} catch(Exception e) {e.printStackTrace();}
			try {if(ps != null){ps.close();}} catch(Exception e) {e.printStackTrace();}
			try {if(rs != null){rs.close();}} catch(Exception e) {e.printStackTrace();}
		}
		return -1;
	}
	
	public String getUserEmail(String userID) {
		String SQL = "SELECT userEmail FROM USERS WHERE userID = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = util.DatabaseUtil.getConnection();
			ps = conn.prepareStatement(SQL);
			ps.setString(1, userID);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn != null){conn.close();}} catch(Exception e) {e.printStackTrace();}
			try {if(ps != null){ps.close();}} catch(Exception e) {e.printStackTrace();}
			try {if(rs != null){rs.close();}} catch(Exception e) {e.printStackTrace();}
		}
		return null;	
	}
	
	public boolean getUserEmailChecked(String userID) {
		String SQL = "SELECT userEmailChecked FROM USERS WHERE userID = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = util.DatabaseUtil.getConnection();
			ps = conn.prepareStatement(SQL);
			ps.setString(1, userID);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getBoolean(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn != null){conn.close();}} catch(Exception e) {e.printStackTrace();}
			try {if(ps != null){ps.close();}} catch(Exception e) {e.printStackTrace();}
			try {if(rs != null){rs.close();}} catch(Exception e) {e.printStackTrace();}
		}
		return false;	
	}
	
	public boolean setUserEmailChecked(String userID) {
		String SQL = "UPDATE USERS SET userEmailChecked = 1 WHERE userID = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = util.DatabaseUtil.getConnection();
			ps = conn.prepareStatement(SQL);
			ps.setString(1, userID);
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(conn != null){conn.close();}} catch(Exception e) {e.printStackTrace();}
			try {if(ps != null){ps.close();}} catch(Exception e) {e.printStackTrace();}
			try {if(rs != null){rs.close();}} catch(Exception e) {e.printStackTrace();}
		}
		return false;	
	}
	
}
