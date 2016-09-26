package tool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SnippetMonitor {
	public static int getRowNum(String sql) {
		int result = -1;

		SnippetDBConn dbc = new SnippetDBConn();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					int count = rs.getInt(1);
					result = count;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		String[] tables = { "Label", "License", "Highlight", "SidebarItem",
				"Snippet", "SnippetLabels", "Meta", "SyncQueue" };
		for (String table : tables) {
			int rowNum = getRowNum("select count(*) from " + table);
			System.out.println(table + "\t" + rowNum);
		}
	}
}
