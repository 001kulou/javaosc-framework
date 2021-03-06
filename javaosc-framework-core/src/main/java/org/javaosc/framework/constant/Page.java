package org.javaosc.framework.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @description
 * @author Dylan Tao
 * @date 2014-09-09
 * Copyright 2014 Javaosc Team. All Rights Reserved.
 */

public class Page<T> {
	
	private static final int defaultPageNo = 1;
	
	protected int pageNo;
	protected int pageSize; //suggest this set value in action
	protected long totalCount = 0;
	
	protected boolean autoCount = true;
	protected long lastQueryTime;
	
	protected List<T> result =  new ArrayList<T>();
	
	public Page() {}

	public Page(int pageSize, int pageNo) {
		this.pageSize = pageSize;
		if (pageNo > 0) {
			this.pageNo = pageNo;
		} else {
			this.pageNo = defaultPageNo;
		}
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageNo(int pageNo) {
		if (pageNo < 1) {
			this.pageNo = defaultPageNo;
		}else if(pageNo > this.getTotalPage()){
			this.pageNo = this.getTotalPage();
		} 
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}
	
	public List<T> getResult() {
		return result;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		if (totalCount < 0) {
			return 0;
		}else{
			int count = (int)((totalCount + pageSize - 1) / pageSize);
			return count;
		}
	}

	public boolean isAutoCount() {
		return autoCount;
	}

	public void setAutoCount(boolean autoCount) {
		this.autoCount = autoCount;
	}

	public void setLastQueryTime(long lastQueryTime) {
		this.lastQueryTime = lastQueryTime;
	}
	
	public long getLastQueryTime() {
		return lastQueryTime;
	}
}
