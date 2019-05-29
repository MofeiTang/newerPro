package com.newer.supervision.domain;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable{
	    //当前页码
		private int currentPage;
		
		//每页显示的记录数
		private int pageSize;
		
		//总记录数
		private int rownum;
		
		//该页显示的数据
		private List<?> data;
		
		//总共多少页
		private int pageNum;
		//查询数据库起始行数
		private int startLine;

		public int getStartLine() {
			return startLine;
		}

		public void setStartLine(int startLine) {
			this.startLine = startLine;
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

		public int getRownum() {
			return rownum;
		}

		public void setRownum(int rownum) {
			this.rownum = rownum;
		}

		public List<?> getData() {
			return data;
		}

		public void setData(List<?> data) {
			this.data = data;
		}

		public int getPageNum() {
			return pageNum;
		}

		public void setPageNum(int pageNum) {
			this.pageNum = pageNum;
		}
		
		
		
}
