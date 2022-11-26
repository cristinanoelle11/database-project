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
    //number 6:
    public List<User> diamondHands() throws SQLException {
        List<User> listUser = new ArrayList<User>(); 
        UserDAO user = new UserDAO();
        String sql = null;
        	sql = "SELECT DISTINCT U.userID\r\n"
        			+ "FROM user U, history H, nft N\r\n"
        			+ "WHERE U.userID = H.userID  AND \r\n"
        			+ "H.nftID = N.nftID AND\r\n"
        			+ "H.action = 'bought' AND U.userID NOT IN(SELECT U.userID\r\n"
        			+ "FROM user U, history H \r\n"
        			+ "WHERE U.userID = H.userID  AND \r\n"
        			+ "H.nftID = N.nftID AND\r\n"
        			+ "H.action  = 'sold');";   
       
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
        	sql = "SELECT  U.userID\r\n"
        			+ "FROM user U, history H, nft N\r\n"
        			+ "WHERE U.userID = H.userID  AND \r\n"
        			+ "H.nftID = N.nftID AND\r\n"
        			+ "H.action = 'bought'\r\n"
        			+ "HAVING COUNT(H.action = 'bought') >= 3";
        			   
       
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
        			+ "(SELECT U.userID\r\n"
        			+ "From user U, history H\r\n"
        			+ " WHERE U.userID = H.userID  AND\r\n"
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
