package tool;

public class SnippetDBConn extends DBConn {
	public String url;

	public SnippetDBConn() {
		super(
				"jdbc:sqlite:/Users/huangwaleking/Library/Application Support/Snippets/Snippets.sqlite");
	}

	public static void main(String[] args) {
		SnippetDBConn db = new SnippetDBConn();
		db.getConnection();
	}
}
