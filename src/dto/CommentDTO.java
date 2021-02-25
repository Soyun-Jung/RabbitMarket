package dto;

public class CommentDTO {
	private int cm_bdnum;
	private String cm_bdmbid;
	private String cm_mbid;
	private String cm_content;
	private String cm_date;
	private int cm_state;
	
	public int getCm_bdnum() {
		return cm_bdnum;
	}
	public void setCm_bdnum(int cm_bdnum) {
		this.cm_bdnum = cm_bdnum;
	}
	public String getCm_content() {
		return cm_content;
	}
	public void setCm_content(String cm_content) {
		this.cm_content = cm_content;
	}
	public String getCm_date() {
		return cm_date;
	}
	public void setCm_date(String cm_date) {
		this.cm_date = cm_date;
	}

	public int getCm_state() {
		return cm_state;
	}

	public void setCm_state(int cm_state) {
		this.cm_state = cm_state;
	}
	public String getCm_bdmbid() {
		return cm_bdmbid;
	}
	public void setCm_bdmbid(String cm_bdmbid) {
		this.cm_bdmbid = cm_bdmbid;
	}
	public String getCm_mbid() {
		return cm_mbid;
	}
	public void setCm_mbid(String cm_mbid) {
		this.cm_mbid = cm_mbid;
	}
}
