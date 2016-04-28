package com.runtech.web.ui;


public class Pager{	
	private Integer pageIndex = 1;
	private Integer pageSize = 10;
	private Integer totalSize = 0;

	public Pager(Integer pageIndexValue, Integer pageSizeValue, Integer totalSizeValue) {
		if(pageIndexValue!=null){
			this.pageIndex = pageIndexValue;
		}
		if(pageSizeValue!=null){
			this.pageSize = pageSizeValue;
		}
		if(totalSizeValue!=null){
			this.totalSize = totalSizeValue;
		}
	}
	
	public Pager() {
	}

	public Integer getStartNumber() {
		return this.pageSize.intValue() * (this.pageIndex.intValue()-1);
	}
	public int getPageIndex() {
		return this.pageIndex;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public int getTotalPages() {
		return (int)Math.ceil((double)totalSize / (double)pageSize);
	}

	public long getTotalSize() {
		return this.totalSize;
	}

	public boolean hasNextPage() {
		return this.pageIndex<this.getTotalPages();
	}
	public boolean hasPreviousPage() {
		return (this.pageIndex>1);
	}

	public boolean isEmpty() {
		return this.totalSize==0;
	}

	public String toString() {
		String result = "<ul class='pager'>";
		int startCount = 1;
		if(this.pageIndex>5){
			startCount = this.pageIndex - 5;
		}
		int endCount = startCount +9;
		int totalPages = this.getTotalPages();
		if(totalPages<=endCount){
			endCount = totalPages - 1;
		}
		if(this.hasPreviousPage()){
			result += "<li class='prev'><a href='?pageIndex="+(this.pageIndex-1)+"'>prev</a></li>";
		}
		for(int i = startCount;i <=endCount; i++){
			if(this.pageIndex != i){
				result += "<li class='index'><a href='?pageIndex="+i+"'>"+i+"</a></li>";
			}else{
				result += "<li class='onIndex'>"+i+"</li>";
			}
		}
		result += "...";
		if(this.pageIndex!=totalPages){
			result += "<li class='index'><a href='?pageIndex="+totalPages+"'>"+totalPages+"</a></li>";
		}else{
			result += "<li class='onIndex'>"+totalPages+"</li>";
		}
		if(this.hasNextPage()){
			result += "<li class='next'><a href='?pageIndex="+(this.pageIndex+1)+"'>next</a></li>";
		}
		result += "</ul>";
		return result;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalSize(Integer integer) {
		this.totalSize = integer;
	}
	
	
}
