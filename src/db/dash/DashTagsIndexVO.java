package db.dash;


/**
 * map to tagsIndex table
 * @author huangwaleking
 *
 */
public class DashTagsIndexVO {
	public int tid;
	public int sid;
	@Override
	public String toString() {
		return "TagsIndexVO [tid=" + tid + ", sid=" + sid + "]";
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	
}
