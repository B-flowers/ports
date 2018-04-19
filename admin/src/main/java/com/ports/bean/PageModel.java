package com.ports.bean;

import java.util.List;
import java.util.Map;

public class PageModel<E> {
	// 结果集
	private List<E> list;

	// 每页多少条数据
	private int pageSizeNum;

	// 当前页数
	private int gotoPage;

	// 总记录数
	private int resultCount;

	// 总页数
	private int pageCount;

	// begin开始条数
	private int begin;
	// end结束条数
	private int end;
	// 条件
	private Map<String, String> conditions;

	/**
	 * 构造不带查询条件
	 */
//	public PageModel() {
//
//	}

	/**
	 * 构造带查询条件
	 *
	 * @param pageSizeNum
	 * @param resultCount
	 * @param conditions
	 */
	public PageModel(int resultCount, int pageSizeNum,
					 Map<String, String> conditions) {
		this.pageSizeNum = pageSizeNum;
		this.resultCount = resultCount;
		this.conditions = conditions;

	}

	/**
	 * 分页的结果集
	 *
	 * @return
	 */
	public List<E> getList() {
		return list;
	}

	/**
	 * 分页的结果集
	 *
	 * @return
	 */
	public void setList(List<E> list) {
		this.list = list;
	}

	/**
	 * 得到分页的要去往的页数
	 *
	 * @return
	 */
	public int getGotoPage() {
		return gotoPage;
	}

	/**
	 * 分页页数赋值
	 *
	 * @return
	 */
	public void setGotoPage(int gotoPage) {
		pageCount=this.getPageCount();
		if (gotoPage < 1) {
			gotoPage = 1;
		} else if (gotoPage > pageCount) {
			gotoPage = pageCount;
		}
		this.gotoPage = gotoPage;
	}

	/**
	 * 得到分页的总页数
	 *
	 * @return
	 */
	public int getPageCount() {
		if (resultCount % pageSizeNum == 0) {
			pageCount = resultCount / pageSizeNum;
		} else if (resultCount % pageSizeNum > 0) {
			pageCount = resultCount / pageSizeNum + 1;
		}
		return pageCount;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	/**
	 * 得到分页的begin数
	 *
	 * @return
	 */
	public int getBegin() {
		begin = (this.gotoPage - 1) * pageSizeNum + 1;
		// System.out.println("begin--------------"+begin);
		return begin;
	}

	/**
	 * 得到分页的end数
	 *
	 * @return
	 */
	public int getEnd() {
		end = this.gotoPage * pageSizeNum;
		// System.out.println("end--------------"+end);
		return end;
	}

	public Map<String, String> getConditions() {
		return conditions;
	}

	public void setConditions(Map<String, String> conditions) {
		this.conditions = conditions;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getPageSizeNum() {
		return pageSizeNum;
	}

	public void setPageSizeNum(int pageSizeNum) {
		this.pageSizeNum = pageSizeNum;
	}

}
