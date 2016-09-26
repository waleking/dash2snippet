package db.snippet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import tool.SnippetDBConn;

public class SnippetDAO {
	private SnippetDBConn dbc = new SnippetDBConn();

	/**
	 * insert snippetVO into Snippet table
	 * 
	 * @param snippetVO
	 * @return
	 */
	public int insert(SnippetVO snippetVO) {
		int toReturn = 0;
		String sql = "INSERT INTO Snippet(key,name,sourceCode,dateAdded,dateModified,highlightKey) "
				+ "VALUES (?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		// 连接数据库
		SnippetDBConn dbc = new SnippetDBConn();
		// 下面是针对数据库的具体操作
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, snippetVO.getKey());
			pstmt.setString(2, snippetVO.getName());
			pstmt.setString(3, snippetVO.getSourceCode());
			pstmt.setTimestamp(4, new Timestamp(snippetVO.getDateAdded()
					.getTime()));
			pstmt.setTimestamp(5, new Timestamp(snippetVO.getDateModified()
					.getTime()));
			pstmt.setString(6, snippetVO.getHighlightKey());
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
		String sql = "delete from snippet";
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
	 */
	public List<SnippetVO> scan() {
		List<SnippetVO> ls = new ArrayList<SnippetVO>();

		ResultSet rs = null;
		String sql = "select * from snippet";
		PreparedStatement pstmt = null;
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					SnippetVO snippetVO = new SnippetVO();
					snippetVO.setKey(rs.getString("key"));
					snippetVO.setName(rs.getString("name"));
					snippetVO.setSourceCode(rs.getString("SourceCode"));
					snippetVO.setDateAdded(rs.getDate("DateAdded"));
					snippetVO.setHighlightKey(rs.getString("HighlightKey"));
					ls.add(snippetVO);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	public static void testScan() {
		SnippetDAO snippetDAO = new SnippetDAO();
		List<SnippetVO> l = snippetDAO.scan();
		System.out.println(l.size());
		for (SnippetVO snippetVO : l) {
			System.out.println(snippetVO);
		}
	}

	public static void testInsert() {
		SnippetDAO snippetDAO = new SnippetDAO();
		SnippetVO snippetVO = new SnippetVO();
		snippetVO.setKey("4");
		snippetVO.setName("add to spark");
		snippetVO.setSourceCode("add to spark");
		snippetDAO.insert(snippetVO);
	}

	public static void testDelete() {
		SnippetDAO labelDAO = new SnippetDAO();
		labelDAO.deleteAll();
	}

	public static void main(String[] args) {
		// testScan();
		// testInsert();
		testDelete();
		testScan();
	}
}
