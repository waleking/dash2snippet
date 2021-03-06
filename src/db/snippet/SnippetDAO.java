package db.snippet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import tool.SnippetDBConn;
import tool.SnippetMappingFunction;

public class SnippetDAO {
	private SnippetDBConn dbc = new SnippetDBConn();

	/**
	 * insert snippetVO into Snippet table
	 * 
	 * @param snippetVO
	 * @return
	 */
	public int insert(SnippetVO snippetVO) {
		if (snippetVO.getKey().length() != 22) {
			System.err.println("key's length is not 22");
			return -1;
		}
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
					.getTime() / 1000));
			pstmt.setTimestamp(5, new Timestamp(snippetVO.getDateModified()
					.getTime() / 1000));
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

	public void insertBatchly(List<SnippetVO> l) {
		PreparedStatement pstmt = null;
		try {
			dbc.getConnection().setAutoCommit(false);
			for (int i = 0; i < l.size(); i++) {
				SnippetVO snippetVO = l.get(i);
				if (snippetVO.getKey().length() != 22) {
					System.err.println("key's length is not 22");
				}

				String sql = "INSERT INTO Snippet(key,name,sourceCode,dateAdded,dateModified,highlightKey) "
						+ "VALUES (?,?,?,?,?,?)";
				// 下面是针对数据库的具体操作
				pstmt = dbc.getConnection().prepareStatement(sql);
				pstmt.setString(1, snippetVO.getKey());
				pstmt.setString(2, snippetVO.getName());
				pstmt.setString(3, snippetVO.getSourceCode());
				pstmt.setTimestamp(4, new Timestamp(snippetVO.getDateAdded()
						.getTime() / 1000));
				pstmt.setTimestamp(5, new Timestamp(snippetVO.getDateModified()
						.getTime() / 1000));
				pstmt.setString(6, snippetVO.getHighlightKey());
				pstmt.executeUpdate();
			}
			//批量更新
			dbc.getConnection().commit();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		snippetVO.setKey(SnippetMappingFunction.turnIntToString22(22));
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
		testDelete();
		testInsert();
		testScan();
	}
}
