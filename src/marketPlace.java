
public class marketPlace {
	protected int saleID;
	protected String endDate;
	protected int price;
	
	//constructors
		public marketPlace() {}
		
		public marketPlace(int saleID, String endDate, int price) {
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
		public String getEndDate() {
			return endDate;
		}
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		
	
}
