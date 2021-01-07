package com.kh.board.model.vo;

import java.sql.Date;

public class Reply {
	private int replyId;
	private String replyContent;
	private int refBoardId;
	private String replyWriter; // 조회시 : 작성자 이름,  댓글작성시 : 회원번호로 쓰일 것!!
	private Date createDate;
	private Date modifyDate;
	private String status;
	
	public Reply() {
		
	}
	
	

	public Reply(int replyId, String replyContent, String replyWriter, Date createDate) {
		super();
		this.replyId = replyId;
		this.replyContent = replyContent;
		this.replyWriter = replyWriter;
		this.createDate = createDate;
	}



	public Reply(int replyId, String replyContent, int refBoardId, String replyWriter, Date createDate, Date modifyDate,
			String status) {
		super();
		this.replyId = replyId;
		this.replyContent = replyContent;
		this.refBoardId = refBoardId;
		this.replyWriter = replyWriter;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.status = status;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public int getRefBoardId() {
		return refBoardId;
	}

	public void setRefBoardId(int refBoardId) {
		this.refBoardId = refBoardId;
	}

	public String getReplyWriter() {
		return replyWriter;
	}

	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Reply [replyId=" + replyId + ", replyContent=" + replyContent + ", refBoardId=" + refBoardId
				+ ", replyWriter=" + replyWriter + ", createDate=" + createDate + ", modifyDate=" + modifyDate
				+ ", status=" + status + "]";
	}
	
	
}
