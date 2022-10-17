
public class nft {
	protected int nftID;
	protected String name;
	protected String description;
	protected String image;
	protected int owner;
	
	
	//constructors
	public nft() {}
	
	public nft(int nftID, String name, String description, String image, int owner) {
		this.nftID = nftID;
		this.name = name;
		this.description = description;
		this.image = image;
		this.owner = owner;
	}
	//getters and setters
	
	public int getNftID() {
		return nftID;
	}
	public void setNftID(int nftID) {
		this.nftID = nftID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public int getOwner() {
		return owner ;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
}

