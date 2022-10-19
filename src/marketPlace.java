import java.sql.Timestamp;

public class marketPlace {
	protected int saleID;
	protected Timestamp endDate;
	protected int price;
	
	//constructors
		public marketPlace() {}
		
		public marketPlace(int saleID, Timestamp endDate, int price) {
			this.saleID = saleID;
			this.endDate = endDate;
			this.price = price;
		}
		//getters and setters
		
		public int getSaleID() {
			return saleID;
		}
		public void setSaleID(int saleID) {
			this.saleID = saleID;
		}
		public Timestamp getEndDate() {
			return endDate;
		}
		public void setEndDate(Timestamp endDate) {
			 long time = endDate.getTime();
			 endDate = new Timestamp(time);
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		
	
}
