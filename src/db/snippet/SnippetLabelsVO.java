package db.snippet;

public class SnippetLabelsVO {
	private String labelKey;
	private String snippetKey;

	@Override
	public String toString() {
		return "SnippetLabelsVO [labelKey=" + labelKey + ", snippetKey="
				+ snippetKey + "]";
	}

	public String getLabelKey() {
		return labelKey;
	}

	public void setLabelKey(String labelKey) {
		this.labelKey = labelKey;
	}

	public String getSnippetKey() {
		return snippetKey;
	}

	public void setSnippetKey(String snippetKey) {
		this.snippetKey = snippetKey;
	}

}
