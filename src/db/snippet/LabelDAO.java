package db.snippet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tool.SnippetDBConn;

public class LabelDAO {
	private SnippetDBConn dbc = new SnippetDBConn();

	/**
	 * insert labelVO into label table
	 * 
	 * @param commentVO
	 * @return
	 */
	public int insert(LabelVO labelVO) {
		int toReturn = 0;
		String sql = "INSERT INTO label(name,key) " + "VALUES (?,?)";
		PreparedStatement pstmt = null;
		// 连接数据库
		SnippetDBConn dbc = new SnippetDBConn();
		// 下面是针对数据库的具体操作
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, labelVO.getName());
			pstmt.setString(2, labelVO.getKey());
			// 进行数据库更新操作
			toReturn = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// System.exit(-1);
		} finally {
			// 关闭数据库连接
			dbc.close();
		}
		return toReturn;
	}

	public int deleteAll() {
		int toReturn = 0;
		String sql = "delete from label";
		PreparedStatement pstmt = null;
		// 连接数据库
		SnippetDBConn dbc = new SnippetDBConn();
		// 下面是针对数据库的具体操作
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			// 进行数据库更新操作
			toReturn = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// System.exit(-1);
		} finally {
			// 关闭数据库连接
			dbc.close();
		}
		return toReturn;
	}

	/*
	 * search by userid and paperid
	 */
	public List<LabelVO> scan() {
		List<LabelVO> ls = new ArrayList<LabelVO>();

		ResultSet rs = null;
		String sql = "select * from label";
		PreparedStatement pstmt = null;
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					LabelVO tagVO = new LabelVO();
					tagVO.setName(rs.getString("name"));
					tagVO.setKey(rs.getString("key"));
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
		LabelDAO labelDAO = new LabelDAO();
		List<LabelVO> l = labelDAO.scan();
		System.out.println(l.size());
		for (LabelVO labelVO : l) {
			System.out.println(labelVO);
		}
	}

	public static void testInsert() {
		LabelDAO labelDAO = new LabelDAO();
		LabelVO labelVO = new LabelVO();
		labelVO.setKey("3");
		labelVO.setName("hadoop");
		labelDAO.insert(labelVO);
	}

	public static void testDelete() {
		LabelDAO labelDAO = new LabelDAO();
		labelDAO.deleteAll();
	}

	public static void main(String[] args) {
		// testScan();
		// testInsert();
		// testDelete();
		testScan();
	}
}
