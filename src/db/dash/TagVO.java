package db.dash;

/**
 * map to the table tags
 * @author huangwaleking
 *
 */
public class TagVO {
	private int tid;
	private String tag;
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "TagVO [tid=" + tid + ", tag=" + tag + "]";
	}
}
