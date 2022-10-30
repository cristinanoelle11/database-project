public class User 
{
		protected int userID;
		protected String password;
	 	protected String email;
	    protected String firstName;
	    protected String lastName;
	    protected String age;
	    protected int wallet;
	    
	 
	    //constructors
	    
	    public User(String email) 
	    {
	        this.email = email;
	    }
	    public User(String email, String firstName, String lastName, String password,String age, int wallet) 
	    {
	    	this.email = email;
	    	this.firstName = firstName;
	    	this.lastName = lastName;
	    	this.password = password;
	        this.age = age;
	        this.wallet = wallet; 
	    }
	    
	    public User(int userID, String email,String firstName, String lastName, String password,String age, int wallet)
	    {
	    	this(email,firstName,lastName,password,age,wallet);
	    	this.userID = userID;
	    	
	    }
	   
	    
	   //getter and setter methods
	    public String getEmail() {
	        return email;
	    }
	    public void setEmail(String email) {
	        this.email = email;
	    }
	    public int getuserID() {
	    	return userID;
	    }
	    public void setuserID(int userID) {
	    	this.userID = userID;
	    }
	    public String getFirstName() {
	        return firstName;
	    }
	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }
	    
	    public String getLastName() {
	        return lastName;
	    }
	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }
	    
	    public String getPassword() {
	        return password;
	    }
	    public void setPassword(String password) {
	        this.password = password;
	    }
	  
	    public String getAge() {
	    	return age;
	    }
	    public void setAge(String age) {
	    	this.age = age;
	    }
	    public int getWallet() {
	    	return wallet;
	    }
	    public void setWallet(int wallet) {
	    	this.wallet = wallet;
	    }
	    
	   
	}