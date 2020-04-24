package com.boardPrograms.web.board.model;

import java.util.Date;

public class BoardVO {
	
	@DBtable(columnName = "boardNumber")
	public int boardNumber;
	@DBtable(columnName = "boardCate")
	public String boardCate;
	public String boardTitle;
	public String boardContent;
	public String boardTag;
	public int viewCount;
	public String boardWriter;
	public Date boardDate;
	public Date editDate;
	
	public int getBoardNumber() {
		return boardNumber;
	}
	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}
	public void setBoardCate(String boardCate) {
		this.boardCate = boardCate;
	}
	public String getBoardCate() {
		return boardCate;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public void setBoardTag(String boardTag) {
		this.boardTag = boardTag;
	}
	public String getBoardTag() {
		return boardTag;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
	public Date getEditDate() {
		return editDate;
	}
	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}
	
	/*
	@Override
	public String toString() {
		return "BoardVO [boardNumber=" + boardNumber + ", boardCate=" + boardCate + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardTag=" + boardTag + ", viewCount=" + viewCount + ", boardWriter=" + boardWriter + ", boardDate=" + boardDate + ", editDate="
				+ editDate + "]";
	}
	*/
	
}
