package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DatabaseUtil; 

public class UserDAO {
	
	public int login(String userID, String userPasswrod) {
		String SQL = "SELECT userPasswrod FROM USERS WHERE userID = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = util.DatabaseUtil.getConnection();
			ps = conn.prepareStatement(SQL);
			rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(userPasswrod)) {
					return 1; //로그인 성공
				} else {
					return 0; //로그인 실패
				}
			}
			return -1; // 아이디 없음
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (conn != null) {conn.close();}} catch (Exception e) {e.printStackTrace();};
			try {if (ps != null) {ps.close();}} catch (Exception e) {e.printStackTrace();};
			try {if (rs != null) {rs.close();}} catch (Exception e) {e.printStackTrace();};
		}
		return -2; // 디비 오류
	}
	
	public int join(UserDTO user) {
		String SQL = "INSERT INTO USERS VALUES(?,?,?,?,0)";
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
			try {if (conn != null) {conn.close();}} catch (Exception e) {e.printStackTrace();};
			try {if (ps != null) {ps.close();}} catch (Exception e) {e.printStackTrace();};
			try {if (rs != null) {rs.close();}} catch (Exception e) {e.printStackTrace();};
		}
		return 0; //디비 오류
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
			try {if (conn != null) {conn.close();}} catch (Exception e) {e.printStackTrace();};
			try {if (ps != null) {ps.close();}} catch (Exception e) {e.printStackTrace();};
			try {if (rs != null) {rs.close();}} catch (Exception e) {e.printStackTrace();};
		}
		return ""; // 디비 오류
	}
	
	public boolean getEmailChecked(String userID) {
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
			try {if(conn != null){conn.close();}} catch (Exception e) {e.printStackTrace();};
			try {if(ps != null){ps.close();}} catch (Exception e) {e.printStackTrace();};
			try {if(rs != null){rs.close();}} catch (Exception e) {e.printStackTrace();};
		}
		return false; // 디비 오류
	}
	
	public boolean setEmailChecked(String userID) {
		String SQL = "UPDATE USERS SET userEmailChecked WHERE userID = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DatabaseUtil.getConnection();
			ps = conn.prepareStatement(SQL);
			ps.setString(1, userID);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
