import java.sql.Timestamp;
import java.util.Date;

public class History {
	protected int historyID;
	protected int userID;
	protected int nftID;
	protected String details;
	protected String action;
	protected Timestamp date;
	
	//constructor
	public History() {}
	public History(int historyID, int userID, int nftID, String details, String action, Timestamp date) {
		this.historyID = historyID;
		this.userID = userID;
		this.nftID = nftID;
		this.details = details;
		this.action = action;
		this.date = date;
	}
	
	//getters & setters
	public int getHistoryID() {
		return historyID;
	}
	public void setHistoryID(int historyID) {
		this.historyID = historyID;
	}
	public int getNftID() {
		return nftID;
	}
	public void setNftID(int nftID) {
		this.nftID = nftID;
	}
	public int getuserID() {
		return userID;
	}
	public void setuserID(int userID) {
		this.userID = userID;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		 long time = date.getTime();
		 date = new Timestamp(time);
	}
	
}
