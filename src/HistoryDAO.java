import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/historyDAO")
public class HistoryDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public HistoryDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false&user=john&password=pass1234");
            System.out.println(connect);
        }
    }
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
  //connect to the database 
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/userdb?"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    
    public List<History> listAllHistory() throws SQLException {
        List<History> listHistory = new ArrayList<History>();        
        String sql = "SELECT * FROM History";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	int historyID = resultSet.getInt("historyID");
        	 int userID = resultSet.getInt("userID");
        	int nftID = resultSet.getInt("nftID");
            String details = resultSet.getString("details");
            String action = resultSet.getString("action");
            Timestamp date = resultSet.getTimestamp("date");
             
            History historys = new History(historyID, userID, nftID, details, action, date);
            listHistory.add(historys);
        }        
        resultSet.close();
        disconnect();        
        return listHistory;
    }
    public void insertExpired(User users, Nft nfts)throws SQLException{
    	connect_func();
    	
	    	String sql1 = "insert into History(userID, nftID, details, action, date) values (?, ?, ?,?, NOW())";
	  		preparedStatement = (PreparedStatement) connect.prepareStatement(sql1);
	  			preparedStatement.setInt(1,users.userID);
	  			preparedStatement.setInt(2, nfts.nftID);
	  			preparedStatement.setString(3, nfts.name +" (nft id: "+nfts.nftID+ ") has left the marketplace");
	  			preparedStatement.setString(4, "expired");
	  		
	  		preparedStatement.executeUpdate();
	        preparedStatement.close();
   
    }
    public void insertTransfer(User NftHolder, User NftReciever, Nft certainNFT) throws SQLException {
        connect_func();         
		String sql = "insert into History(userID, nftID, details, action, date) values (?, ?, ?, ?, NOW())";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setInt(1,NftHolder.userID);
			preparedStatement.setInt(2, certainNFT.nftID);
			preparedStatement.setString(3, NftHolder.firstName + " transfered the NFT '" + certainNFT.name + " to "+ NftReciever.firstName);
			preparedStatement.setString(4, "transfered");
		
		preparedStatement.executeUpdate();
        preparedStatement.close();
        
        String sql1 = "insert into History(userID, nftID, details, action, date) values (?, ?, ?, ?, NOW())";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setInt(1,NftReciever.userID);
			preparedStatement.setInt(2, certainNFT.nftID);
			preparedStatement.setString(3, NftReciever.firstName + " recieved the NFT '" + certainNFT.name + "' ("+ certainNFT.nftID +") from "+ NftHolder.firstName );
			preparedStatement.setString(4, "recieved");
			
		preparedStatement.executeUpdate();
        preparedStatement.close();
        
    }
    public void insertBought(User buyer, User seller, Nft certainNFT) throws SQLException {
        connect_func();         
		String sql = "insert into History(userID, nftID, details, action, date) values (?, ?, ?, ?, NOW())";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setInt(1,buyer.userID);
			preparedStatement.setInt(2, certainNFT.nftID);
			preparedStatement.setString(3, buyer.firstName + " bought the NFT '" + certainNFT.name +"'(nft id:"+ certainNFT.nftID +") from "+ seller.firstName);
			preparedStatement.setString(4, "bought");
		
		preparedStatement.executeUpdate();
        preparedStatement.close();
        
        String sql1 = "insert into History(userID, nftID, details, action, date) values (?, ?, ?, ?, NOW())";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setInt(1,seller.userID);
			preparedStatement.setInt(2, certainNFT.nftID);
			preparedStatement.setString(3, seller.firstName + " sold the NFT '" + certainNFT.name + "' (nft id:"+ certainNFT.nftID +") from "+ buyer.firstName );
			preparedStatement.setString(4, "recieved");
			
		preparedStatement.executeUpdate();
        preparedStatement.close();
        
    }
    public void cancelSell(User seller, Nft certainNFT) throws SQLException {
        connect_func();         
		String sql = "insert into History(userID, nftID, details, action, date) values (?, ?, ?, ?, NOW())";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setInt(1,seller.userID);
			preparedStatement.setInt(2, certainNFT.nftID);
			preparedStatement.setString(3, seller.firstName + " removed the NFT '" + certainNFT.name +"'(nft id:"+ certainNFT.nftID +") from the marketplace");
			preparedStatement.setString(4, "removed");
		
		preparedStatement.executeUpdate();
        preparedStatement.close();
        
    }
    public void insertMint(User users,  Nft nfts) throws SQLException {
        connect_func();         
		String sql = "insert into History(userID, nftID, details, action, date) values (?, ?, ?, ?, NOW())";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setInt(1,users.userID);
			preparedStatement.setInt(2, nfts.nftID);
			preparedStatement.setString(3, users.firstName + " created the NFT '" + nfts.name + "' ("+ nfts.nftID +")");
			preparedStatement.setString(4, "created");
		
		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public void insertUser(User users) throws SQLException {
        connect_func();         
		String sql = "insert into History(userID, details, action, date) values (?, ?, ?, NOW())";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setInt(1,users.userID);
			preparedStatement.setString(2, "user created");
			preparedStatement.setString(3, "created");
		
		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
   
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL1 = {
					        "use testdb; ",
					        "drop table if exists History; ",
					        ("CREATE TABLE if not exists History( " +
					        	"historyID INTEGER AUTO_INCREMENT PRIMARY KEY,"+
					        	"userID INTEGER,"+
					        	"nftID INTEGER,"+
					        	"details VARCHAR(2000),"+
					        	"action VARCHAR(50),"+
					        	"date TIMESTAMP);")
					        	
        					};
        String[] TUPLES1 = {("insert into History( userID, nftID, details, action, date )"+
        			"values ('1','1','user created','create', NOW()),"+
        					"('2','3','user created','create', NOW()),"+
        					"('3','2','user created','create', NOW()),"+
        					"('4','4','user created','create', NOW()),"+
        					"('5','6','user created','create', NOW()),"+
        					"('6','5','user created','create', NOW()),"+
        					"('7','8','user created','create', NOW()),"+
        					"('8','7','user created','create', NOW()),"+
        					"('9','10','user created','create', NOW()),"+
			    		 	"('10','9','user created', 'create', NOW());")
			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL1.length; i++)
        	statement.execute(INITIAL1[i]);
        for (int i = 0; i < TUPLES1.length; i++)	
        	statement.execute(TUPLES1[i]);
        disconnect();
    }
    
}
