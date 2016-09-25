package db.dash;

/**
 * map to snippets table
 * @author huangwaleking
 *
 */
public class SnippetsVO {
	private int sid;
	private String title;
	private String body;

	@Override
	public String toString() {
		return "SnippetsVO [sid=" + sid + ", title=" + title + ", body=" + body
				+ "]";
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
