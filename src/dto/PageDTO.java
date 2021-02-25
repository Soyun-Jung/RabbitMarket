package dto;

public class PageDTO {

	private int nowPage;
	private int cntPage;
	private int startPage;
	private int endPage;
	private int total;
	private int cntPerPage;
	private int lastPage;
	private int start;
	private int end;
	
	public PageDTO() {
		
	}
	public void calcPage(int total, int cntPage, int cntPerPage) {
		this.setCntPage(cntPage);
		this.setCntPerPage(cntPerPage);
		this.setTotal(total);
		this.calcLastPage(this.getTotal(), this.getCntPerPage());
		this.calcStartEndPage(this.getNowPage(), this.getCntPage());
		this.calcStartEnd(this.getNowPage(), this.getCntPerPage());
	}
	private void calcLastPage(int total, int cntPerPage) {
		// TODO Auto-generated method stub
		this.setLastPage((int)Math.ceil((double)total / (double)cntPerPage));
	}
	private void calcStartEndPage(int nowPage, int cntPage) {
		// TODO Auto-generated method stub
		this.setEndPage((int)Math.ceil((double)nowPage / (double)cntPage) * cntPage);
		if(this.getEndPage()>this.getLastPage()) {
			this.setEndPage(this.getLastPage());
		}
		this.setStartPage(this.getEndPage() - cntPage + 1);
		if(this.getStartPage()<1) {
			this.setStartPage(1);
		}
	}
	private void calcStartEnd(int nowPage, int cntPerPage) {
		// TODO Auto-generated method stub
		this.setEnd(nowPage * cntPerPage);
		this.setStart(this.getEnd() - cntPerPage + 1);
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getCntPage() {
		return cntPage;
	}
	public void setCntPage(int cntPage) {
		this.cntPage = cntPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCntPerPage() {
		return cntPerPage;
	}
	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
}
