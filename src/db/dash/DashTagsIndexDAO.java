package db.dash;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tool.DashDBConn;

public class DashTagsIndexDAO {
	private DashDBConn dbc = new DashDBConn();

	/*
	 * search by userid and paperid
	 */
	public List<DashTagsIndexVO> scan() {
		List<DashTagsIndexVO> ls = new ArrayList<DashTagsIndexVO>();

		ResultSet rs = null;
		String sql = "select * from tagsIndex";
		PreparedStatement pstmt = null;
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					DashTagsIndexVO tagsIndexVO = new DashTagsIndexVO();
					tagsIndexVO.setTid(rs.getInt("tid"));
					tagsIndexVO.setSid(rs.getInt("sid"));
					ls.add(tagsIndexVO);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	public static void testScan() {
		DashTagsIndexDAO tagsIndexDAO = new DashTagsIndexDAO();
		List<DashTagsIndexVO> l = tagsIndexDAO.scan();
		System.out.println(l.size());
		for (DashTagsIndexVO tagsIndexVO : l) {
			System.out.println(tagsIndexVO);
		}
	}

	public static void main(String[] args) {
		testScan();
	}
}
