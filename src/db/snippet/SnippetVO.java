package db.snippet;

import java.util.Date;

public class SnippetVO {
	private String key;// sid
	private String name;// title
	private String sourceCode;// body
	private Date dateAdded = new Date();
	private Date dateModified = new Date();
	private String highlightKey = "YLQzq5E6u0udia6A9zCrHw";// Plain Text in
															// Highlight table

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	@Override
	public String toString() {
		return "SnippetVO [key=" + key + ", name=" + name + ", sourceCode="
				+ sourceCode + ", dateAdded=" + dateAdded + ", highlightKey="
				+ highlightKey + "]";
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getHighlightKey() {
		return highlightKey;
	}

	public void setHighlightKey(String highlightKey) {
		this.highlightKey = highlightKey;
	}
}
