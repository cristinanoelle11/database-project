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
@WebServlet("/CommonDAO")
public class CommonDAO {
public CommonDAO(){}
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

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
    //number 1:
    public List<User> bigCreators() throws SQLException {
    	List<User> listUser = new ArrayList<User>();
    	UserDAO user = new UserDAO();
    	String sql = null;
    	sql = "WITH results as (\r\n"
    			+ "SELECT H.userID ,COUNT(H.userID) AS `value_occurrence` \r\n"
    			+ "FROM  history H\r\n"
    			+ "WHERE  H.action = 'mint'\r\n"
    			+ "GROUP BY H.userID\r\n"
    			+ "ORDER BY `value_occurrence` DESC\r\n"
    			+ "),\r\n"
    			+ "mvalue as (\r\n"
    			+ "   select max(value_occurrence) as mCnt from results\r\n"
    			+ ")\r\n"
    			+ "select * from results where value_occurrence = (select mCnt from mvalue);\r\n"
    			+ ";";
	
		 connect_func();
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	       // preparedStatement.setInt(1, userID); 
	      //  preparedStatement.setInt(2, userID); 
	       
	        statement = (Statement) connect.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);
	        while (resultSet.next()) {
	        	int userID = resultSet.getInt("userID");
	            listUser.add(user.getUser(userID));
	        }        
	        resultSet.close();
	        disconnect();        
	        return listUser;
	    }  
    
    //number 2:
    public List<User> bigSellers() throws SQLException {
    	List<User> listUser = new ArrayList<User>();
    	UserDAO user = new UserDAO();    	
    	String sql = null;
    		sql = "WITH results as (\r\n"
    				+ "SELECT H.userID ,COUNT(H.userID) AS `value_occurrence` \r\n"
    				+ "FROM  history H\r\n"
    				+ "WHERE  H.action = 'sold'\r\n"
    				+ "GROUP BY H.userID\r\n"
    				+ "ORDER BY `value_occurrence` DESC\r\n"
    				+ "),\r\n"
    				+ "mvalue as (\r\n"
    				+ "   select max(value_occurrence) as mCnt from results\r\n"
    				+ ")\r\n"
    				+ "select * from results where value_occurrence = (select mCnt from mvalue);\r\n"
    				+ ";";
    		 connect_func();
    	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	       // preparedStatement.setInt(1, userID); 
    	      //  preparedStatement.setInt(2, userID); 
    	       
    	        statement = (Statement) connect.createStatement();
    	        ResultSet resultSet = statement.executeQuery(sql);
    	        while (resultSet.next()) {
    	        	int userID = resultSet.getInt("userID");
    	            listUser.add(user.getUser(userID));
    	        }        
    	        resultSet.close();
    	        disconnect();        
    	        return listUser;
    	    }  

    
    //number 3:
    public List<User> bigBuyers() throws SQLException {
    	List<User> listUser = new ArrayList<User>();
    	UserDAO user = new UserDAO();    	
    	String sql = null;
    	sql = "WITH results as (\r\n"
    			+ "SELECT H.userID ,COUNT(H.userID) AS `value_occurrence` \r\n"
    			+ "FROM  history H\r\n"
    			+ "WHERE  H.action = 'bought'\r\n"
    			+ "GROUP BY H.userID\r\n"
    			+ "ORDER BY `value_occurrence` DESC\r\n"
    			+ "),\r\n"
    			+ "mvalue as (\r\n"
    			+ "   select max(value_occurrence) as mCnt from results\r\n"
    			+ ")\r\n"
    			+ "select * from results where value_occurrence = (select mCnt from mvalue);\r\n"
    			+ ";";
	
		 connect_func();
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	       // preparedStatement.setInt(1, userID); 
	      //  preparedStatement.setInt(2, userID); 
	       
	        statement = (Statement) connect.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);
	        while (resultSet.next()) {
	        	int userID = resultSet.getInt("userID");
	            listUser.add(user.getUser(userID));
	        }        
	        resultSet.close();
	        disconnect();        
	        return listUser;
	    }  
    //number 4:
    public List<Nft> hottestNfts() throws SQLException {
    	List<Nft> listNft = new ArrayList<Nft>();
    	NftDAO nft = new NftDAO();
    	String sql = null;
    		sql = "WITH results as (\r\n"
    				+ "select nftID, COUNT(userID) AS `value_occurrence` \r\n"
    				+ " from history \r\n"
    				+ " where action IN ('bought','gifted', 'mint', 'create')\r\n"
    				+ " GROUP BY nftID\r\n"
    				+ "),\r\n"
    				+ "mvalue as (\r\n"
    				+ "   select max(value_occurrence) as mCnt from results\r\n"
    				+ ")\r\n"
    				+ "select * from results where value_occurrence = (select mCnt from mvalue);\r\n"
    				+ ";";
    		
    		connect_func();
	        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	       // preparedStatement.setInt(1, userID); 
	      //  preparedStatement.setInt(2, userID); 
	       
	        statement = (Statement) connect.createStatement();
	        ResultSet resultSet = statement.executeQuery(sql);
	        while (resultSet.next()) {
	        	int nftID = resultSet.getInt("nftId");
	        	listNft.add(nft.getNFT(nftID));
	        }        
	        resultSet.close();
	        disconnect();        
	        return listNft;
	    }  
    //number 5:
    public List<Nft> commonNFTs(int user1, int user2) throws SQLException {
    	List<Nft> listNft = new ArrayList<Nft>();
    	NftDAO nft = new NftDAO();
    	String sql = null;
    		sql = "With commonnfts AS (\r\n"
    				+ "select H.nftID, GROUP_CONCAT(H.userID) userID\r\n"
    				+ "from history H \r\n"
    				+ "WHERE \r\n"
    				+ "	(H.userID =" + user1 + " OR\r\n"
    				+ "	H.userID =" + user2 + ") AND \r\n"
    				+ "	H.action IN ('bought','gifted', 'mint', 'create')\r\n"
    				+ "    GROUP BY H.nftID)\r\n"
    				+ "    Select * from commonnfts where userID LIKE '%,%';";
    		
    	connect_func();
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	while (resultSet.next()) {
    		int nftID = resultSet.getInt("nftId");
        	listNft.add(nft.getNFT(nftID));
    	}
    	resultSet.close();
    	disconnect();
    	return listNft;
    }

    //number 6:
    public List<User> diamondHands() throws SQLException {
        List<User> listUser = new ArrayList<User>(); 
        UserDAO user = new UserDAO();
        String sql = null;
        	sql = "SELECT DISTINCT H.userID\r\n"
        			+ "FROM  history H WHERE\r\n"
        			+ "H.action = 'bought' AND H.userID NOT IN(SELECT H.userID\r\n"
        			+ "FROM history H \r\n"
        			+ "WHERE H.action  = 'sold');";   
       
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
        	int userID = resultSet.getInt("userID");
            listUser.add(user.getUser(userID));
        }        
        resultSet.close();
        disconnect();        
        return listUser;
    }  
  //number 7:
    public List<User> paperHands() throws SQLException {
        List<User> listUser = new ArrayList<User>(); 
        UserDAO user = new UserDAO();
        String sql = null;
        	sql = "SELECT distinct userID FROM history WHERE action IN('bought') AND userID IN \r\n"
        			+ "	(SELECT userID FROM user WHERE userID NOT IN(\r\n"
        			+ "		SELECT distinct owner FROM nft));\r\n";
        			   
       
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
        	int userID = resultSet.getInt("userID");
            listUser.add(user.getUser(userID));
        }        
        resultSet.close();
        disconnect();        
        return listUser;
    }  
    //number 8:
    public List<User> goodBuyers() throws SQLException {
        List<User> listUser = new ArrayList<User>(); 
        UserDAO user = new UserDAO();
        String sql = null;

        	sql = "SELECT  H.userID\r\n"
        			+ "FROM  history H\r\n"
        			+ "WHERE H.action = 'bought'\r\n"
        			+ "GROUP BY H.userID, H.action \r\n"
        			+ "HAVING COUNT(*) >= 3;";

        			   
       
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
        	int userID = resultSet.getInt("userID");
            listUser.add(user.getUser(userID));
        }        
        resultSet.close();
        disconnect();        
        return listUser;
    }  
    //number 9:
    public List<User> inactiveUsers() throws SQLException {
        List<User> listUser = new ArrayList<User>(); 
        UserDAO user = new UserDAO();
        String sql = null;
        	sql = "SELECT userID \r\n"
        			+ "From User \r\n"
        			+ "WHERE userID NOT IN\r\n"
        			+ "(SELECT H.userID\r\n"
        			+ "From  history H WHERE\r\n"
        			+ " (H.action = 'bought' OR\r\n"
        			+ " H.action = 'sold' OR\r\n"
        			+ " H.action = 'mint' OR\r\n"
        			+ " H.action = 'transfered'));";
        			   
       
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
        	int userID = resultSet.getInt("userID");
            listUser.add(user.getUser(userID));
        }        
        resultSet.close();
        disconnect();        
        return listUser;
    }  
    
    //number 10:
    public List<User> userStats(int userID) throws SQLException {
        List<User> listUser = new ArrayList<User>(); 
      //  UserDAO user = new UserDAO();
      //  UserDAO currentUser = new UserDAO();
       // int userID = currentUser.getUser(name).userID;
        
        String sql = null;
        	sql = "SELECT U.userID, H.action, COUNT(*) FROM user U, history H WHERE U.userID = H.userID AND U.userID = "+ userID +" AND H.action IN ('bought','sold', 'transfered')  \r\n"
        			+ "GROUP BY\r\n"
        			+ "	U.userID, H.action\r\n"
        			+ "UNION\r\n"
        			+ "Select owner, 'NFTS currently owned', COUNT(*) FROM nft WHERE owner ="+ userID +" \r\n"
        			+ "	GROUP BY owner;";
        			   
       
        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
       // preparedStatement.setInt(1, userID); 
      //  preparedStatement.setInt(2, userID); 
       
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
        	userID = resultSet.getInt("userID");
        	String action = resultSet.getString("action");
        	int count = resultSet.getInt(3); 
            User users = new User(userID, action, count);
            listUser.add(users);
        }        
        resultSet.close();
        disconnect();        
        return listUser;
    }  
}
