package db.dash;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tool.DashDBConn;

public class SnippetsDAO {
	private DashDBConn dbc = new DashDBConn();

	/*
	 * search by userid and paperid
	 */
	public List<SnippetsVO> scan() {
		List<SnippetsVO> ls = new ArrayList<SnippetsVO>();

		ResultSet rs = null;
		String sql = "select * from snippets";
		PreparedStatement pstmt = null;
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					SnippetsVO snippetsVO = new SnippetsVO();
					snippetsVO.setSid(rs.getInt("sid"));
					snippetsVO.setTitle(rs.getString("title"));
					snippetsVO.setBody(rs.getString("body"));
					ls.add(snippetsVO);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	public static void testScan() {
		SnippetsDAO snippetsDAO = new SnippetsDAO();
		List<SnippetsVO> l = snippetsDAO.scan();
		System.out.println(l.size());
		for (SnippetsVO snippetsVO : l) {
			System.out.println(snippetsVO);
		}
	}

	public static void main(String[] args) {
		testScan();
	}
}
