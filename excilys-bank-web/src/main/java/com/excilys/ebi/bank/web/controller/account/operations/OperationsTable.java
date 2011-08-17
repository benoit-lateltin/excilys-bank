package com.excilys.ebi.bank.web.controller.account.operations;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

public class OperationsTable {

	private int startIndex;
	private int endIndex;
	private int pageIndex;
	private int number;
	private int numberOfElements;
	private int size;
	private long totalElements;
	private int totalPages;
	private boolean hasContent;
	private boolean hasNextPage;
	private boolean hasPreviousPage;
	private boolean isFirstPage;
	private boolean isLastPage;
	private List<OperationsLine> lines = newArrayList();
	private List<String> emptyLines = newArrayList();

	public int getStartIndex() {
		return startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public int getNumber() {
		return number;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public int getSize() {
		return size;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public boolean isHasContent() {
		return hasContent;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public boolean isFirstPage() {
		return isFirstPage;
	}

	public boolean isLastPage() {
		return isLastPage;
	}

	public List<OperationsLine> getLines() {
		return lines;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public void setHasContent(boolean hasContent) {
		this.hasContent = hasContent;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}

	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public void setLines(List<OperationsLine> lines) {
		this.lines = lines;
	}

	public List<String> getEmptyLines() {
		return emptyLines;
	}

	public void setEmptyLines(List<String> emptyLines) {
		this.emptyLines = emptyLines;
	}
}
