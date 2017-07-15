package Shopping.model.vo;

import java.sql.Timestamp;

public class Word {
	private int LeaveWordID;
	private int UserID;
	private Timestamp LeaveDate;
	private String LeaveWord;
	private int CommodityID;
	private String Reply;
	private int Status;
	public int getLeaveWordID() {
		return LeaveWordID;
	}
	public void setLeaveWordID(int leaveWordID) {
		LeaveWordID = leaveWordID;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public Timestamp getLeaveDate() {
		return LeaveDate;
	}
	public void setLeaveDate(Timestamp leaveDate) {
		LeaveDate = leaveDate;
	}
	public String getLeaveWord() {
		return LeaveWord;
	}
	public void setLeaveWord(String leaveWord) {
		LeaveWord = leaveWord;
	}
	public int getCommodityID() {
		return CommodityID;
	}
	public void setCommodityID(int commodityID) {
		CommodityID = commodityID;
	}
	public String getReply() {
		return Reply;
	}
	public void setReply(String reply) {
		Reply = reply;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	
}
