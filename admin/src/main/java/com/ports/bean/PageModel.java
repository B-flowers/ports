package com.ports.bean;

import java.util.List;
import java.util.Map;

public class PageModel<E> {
	// �����
	private List<E> list;

	// ÿҳ����������
	private int pageSizeNum;

	// ��ǰҳ��
	private int gotoPage;

	// �ܼ�¼��
	private int resultCount;

	// ��ҳ��
	private int pageCount;

	// begin��ʼ����
	private int begin;
	// end��������
	private int end;
	// ����
	private Map<String, String> conditions;

	/**
	 * ���첻����ѯ����
	 */
//	public PageModel() {
//
//	}

	/**
	 * �������ѯ����
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
	 * ��ҳ�Ľ����
	 *
	 * @return
	 */
	public List<E> getList() {
		return list;
	}

	/**
	 * ��ҳ�Ľ����
	 *
	 * @return
	 */
	public void setList(List<E> list) {
		this.list = list;
	}

	/**
	 * �õ���ҳ��Ҫȥ����ҳ��
	 *
	 * @return
	 */
	public int getGotoPage() {
		return gotoPage;
	}

	/**
	 * ��ҳҳ����ֵ
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
	 * �õ���ҳ����ҳ��
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
	 * �õ���ҳ��begin��
	 *
	 * @return
	 */
	public int getBegin() {
		begin = (this.gotoPage - 1) * pageSizeNum + 1;
		// System.out.println("begin--------------"+begin);
		return begin;
	}

	/**
	 * �õ���ҳ��end��
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
