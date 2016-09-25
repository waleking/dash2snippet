package tool;

public class DashDBConn extends DBConn {
	public String url;

	public DashDBConn() {
		super(
				"jdbc:sqlite:/Volumes/MacintoshHD/Users/huangwaleking/Library/Application Support/Dash/library.dash");
	}

	public static void main(String[] args) {
		DashDBConn db = new DashDBConn();
		db.getConnection();
	}
}
