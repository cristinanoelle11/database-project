import java.sql.Timestamp;

public class marketPlace {
	protected int saleID;
	protected Timestamp endDate;
	protected int price;
	protected int nftID;
	protected String name;
	//constructors
		public marketPlace() {}
		
		public marketPlace(int saleID, Timestamp endDate, int price,int nftID) {
			this.saleID = saleID;
			this.endDate = endDate;
			this.price = price;
			this.nftID = nftID;
		}
		public marketPlace(int saleID, Timestamp endDate, int price,int nftID, String name) {
			this.saleID = saleID;
			this.endDate = endDate;
			this.price = price;
			this.nftID = nftID;
			this.name = name;
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
		public int getnftID() {
			return nftID;
		}
		public void setnftID(int nftID) {
			this.nftID = nftID;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
}
