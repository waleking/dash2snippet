package db.snippet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tool.SnippetDBConn;
import tool.SnippetMappingFunction;

public class SnippetLabelsDAO {
	private SnippetDBConn dbc = new SnippetDBConn();

	/**
	 * 
	 */
	public int insert(SnippetVO snippetVO, LabelVO labelVO) {
		if (snippetVO.getKey().length() != 22) {
			System.err.println("snippet key's length is not 22");
			return -1;
		}
		if (labelVO.getKey().length() != 22) {
			System.err.println("label key's length is not 22");
			return -1;
		}
		int toReturn = 0;
		String sql = "INSERT INTO SnippetLabels(LabelKey,SnippetKey) "
				+ "VALUES (?,?)";
		PreparedStatement pstmt = null;
		// 下面是针对数据库的具体操作
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, snippetVO.getKey());
			pstmt.setString(2, labelVO.getKey());
			// 进行数据库更新操作
			toReturn = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// System.exit(-1);
		}
		return toReturn;
	}

	/**
	 * 
	 */
	public int insert(SnippetLabelsVO snippetLabelsVO) {
		if (snippetLabelsVO.getSnippetKey().length() != 22) {
			System.err.println("snippet key's length is not 22");
			return -1;
		}
		if (snippetLabelsVO.getLabelKey().length() != 22) {
			System.err.println("label key's length is not 22");
			return -1;
		}
		int toReturn = 0;
		String sql = "INSERT INTO SnippetLabels(LabelKey,SnippetKey) "
				+ "VALUES (?,?)";
		PreparedStatement pstmt = null;
		// 连接数据库
		SnippetDBConn dbc = new SnippetDBConn();
		// 下面是针对数据库的具体操作
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			pstmt.setString(1, snippetLabelsVO.getLabelKey());
			pstmt.setString(2, snippetLabelsVO.getSnippetKey());
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
		String sql = "delete from snippetlabels";
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
	public List<SnippetLabelsVO> scan() {
		List<SnippetLabelsVO> ls = new ArrayList<SnippetLabelsVO>();

		ResultSet rs = null;
		String sql = "select * from snippetlabels";
		PreparedStatement pstmt = null;
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					SnippetLabelsVO snippetLabelsVO = new SnippetLabelsVO();
					snippetLabelsVO.setSnippetKey(rs.getString("SnippetKey"));
					snippetLabelsVO.setLabelKey(rs.getString("LabelKey"));
					ls.add(snippetLabelsVO);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	public static void testScan() {
		SnippetLabelsDAO snippetDAO = new SnippetLabelsDAO();
		List<SnippetLabelsVO> l = snippetDAO.scan();
		System.out.println(l.size());
		for (SnippetLabelsVO snippetLabelsVO : l) {
			System.out.println(snippetLabelsVO);
		}
	}

	public static void testInsert() {
		SnippetLabelsDAO snippetLabelsDAO = new SnippetLabelsDAO();
		SnippetLabelsVO snippetLabelsVO = new SnippetLabelsVO();
		snippetLabelsVO.setSnippetKey(SnippetMappingFunction
				.turnIntToString22(22));
		snippetLabelsVO
				.setLabelKey(SnippetMappingFunction.turnIntToString22(3));
		snippetLabelsDAO.insert(snippetLabelsVO);
	}

	public static void testDelete() {
		SnippetLabelsDAO snippetLabelsDAO = new SnippetLabelsDAO();
		snippetLabelsDAO.deleteAll();
	}

	public static void main(String[] args) {
		// testScan();
		testInsert();
		// testDelete();
		testScan();
	}
}
