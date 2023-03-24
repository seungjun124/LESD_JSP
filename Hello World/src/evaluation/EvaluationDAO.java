package evaluation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class EvaluationDAO {
	
	public int write(EvaluationDTO evaluationDTO) {
		String SQL = "INSERT INTO EVALUATION VALUES(MEMBERINFO.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,0)";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = util.DatabaseUtil.getConnection();
			ps = conn.prepareStatement(SQL);
			ps.setString(1, evaluationDTO.getUserID());
			ps.setString(2, evaluationDTO.getLectureName());
			ps.setString(3, evaluationDTO.getProfessorName());
			ps.setInt(4, evaluationDTO.getLectureYear());
			ps.setString(5, evaluationDTO.getSemesterDivide());
			ps.setString(6, evaluationDTO.getLectureDivide());
			ps.setString(7, evaluationDTO.getEvaluationTitle());
			ps.setString(8, evaluationDTO.getEvaluationContent());
			ps.setString(9, evaluationDTO.getTotalScore());
			ps.setString(10, evaluationDTO.getCreditScore());
			ps.setString(11, evaluationDTO.getComfortableScore());
			ps.setString(12, evaluationDTO.getLectureScore());
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
	
	public ArrayList<EvaluationDTO> getList (String lectureDivide, String searchType, String search, int pageNumber) {
		if (lectureDivide.equals("전체")) {
			lectureDivide = "";
		}
		ArrayList<EvaluationDTO> evaluationList = null;
		String SQL = "";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			if (searchType.equals("최신순")) {
				SQL = "SELECT * FROM EVALUATION WHERE lectureDivide LIKE ? AND CONCAT(lectureName, professorName, evaluationTitle, evaluationContent) LIKE" + "? ORDER BY evaluationID DESC LIMIT" + pageNumber * 5 + "," + pageNumber * 5 + 6;
			} else if (searchType.equals("추천순")) {
				SQL = "SELECT * FROM EVALUATION WHERE lectureDivide LIKE ? AND CONCAT(lectureName, professorName, evaluationTitle, evaluationContent) LIKE" + "? ORDER BY likeCount DESC LIMIT" + pageNumber * 5 + "," + pageNumber * 5 + 6;
			}
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
}
