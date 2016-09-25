package db.dash;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tool.DashDBConn;

public class TagDAO {
	private DashDBConn dbc = new DashDBConn();

	/*
	 * search by userid and paperid
	 */
	public List<TagVO> scan() {
		List<TagVO> ls = new ArrayList<TagVO>();

		ResultSet rs = null;
		String sql = "select * from tags";
		PreparedStatement pstmt = null;
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					TagVO tagVO = new TagVO();
					tagVO.setTag(rs.getString("tag"));
					tagVO.setTid(rs.getInt("tid"));
					ls.add(tagVO);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
		/*
		 * List<TagVO> ls = new ArrayList<TagVO>();
		 * 
		 * ResultSet rs = null; String sql =
		 * "select * from comment where userId=? and paperId=?";
		 * PreparedStatement pstmt = null; try { pstmt =
		 * dbc.getConnection().prepareStatement(sql); pstmt.setInt(1, userid);
		 * pstmt.setInt(2, paperid); rs = pstmt.executeQuery(); if (rs != null)
		 * { while (rs.next()) { CommentVO commentVO = new CommentVO();
		 * commentVO.setPaperid(paperid); commentVO.setUserId(userid);
		 * commentVO.setComment(rs.getString("comment")); ls.add(commentVO); } }
		 * } catch (SQLException e) { e.printStackTrace(); } return ls;
		 */
	}

	public static void testScan() {
		TagDAO tagDAO = new TagDAO();
		List<TagVO> l = tagDAO.scan();
		System.out.println(l.size());
		for (TagVO tagVO : l) {
			System.out.println(tagVO);
		}
	}

	public static void main(String[] args) {
		testScan();
	}
}
