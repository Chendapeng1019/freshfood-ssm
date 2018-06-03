package mo;

public class SearchMo {
  
	   private int currentPage;
	   private int pageSize;
	   private String search;
	   private String selectsort;
	   
	   private int page;
	   
	   
	    
    public int getPage() {
    	page=(this.getCurrentPage()-1)*this.getPageSize();
		return page;
	}

	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getSelectsort() {
		return selectsort;
	}
	public void setSelectsort(String selectsort) {
		this.selectsort = selectsort;
	}
	
}
