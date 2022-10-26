import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
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
@WebServlet("/marketPlaceDAO")
public class marketPlaceDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public marketPlaceDAO(){}
	
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
    
    public List<marketPlace> listMarketPlace() throws SQLException {
        List<marketPlace> listMarketPlace = new ArrayList<marketPlace>();        
        String sql = "SELECT * FROM marketPlace";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	int saleID = resultSet.getInt("saleID");
            Timestamp endDate = resultSet.getTimestamp("endDate");
            int price = resultSet.getInt("price");
            int nftID = resultSet.getInt("nftID");
            marketPlace market = new marketPlace(saleID, endDate, price, nftID);
            listMarketPlace.add(market);
        }        
        resultSet.close();
        disconnect();        
        return listMarketPlace;
    }

    public boolean insert(Date date, int price, int nftID) throws SQLException {
        String sql = "insert into marketPlace(endDate, price, nftID) values (?, ?, ?)";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        
        preparedStatement.setDate(1,date);
        preparedStatement.setInt(2, price);
        preparedStatement.setInt(3, nftID);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
    public marketPlace getmarketPlace() throws SQLException {
    	marketPlace marketplace = null;
        String sql = "SELECT * FROM marketPlace";
         
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);;
         
        if (resultSet.next()) {
        	int saleID = resultSet.getInt("saleID");
            Timestamp endDate = resultSet.getTimestamp("endDate");
            int price = resultSet.getInt("price");
            int nftID = resultSet.getInt("nftID");
             marketplace = new marketPlace(saleID, endDate, price, nftID);
        }
         
        resultSet.close();
        statement.close();
         
        return marketplace;
    }
    public boolean delete(int nftID) throws SQLException {
        String sql = "DELETE FROM marketPlace WHERE nftID = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, nftID);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
    public boolean deleteViaDate( )throws SQLException{
    	 String sql = "DELETE FROM marketPlace WHERE endDate <= SYSDATE()";        
         connect_func();
          
         preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        
         boolean rowDeleted = preparedStatement.executeUpdate() > 0;
         preparedStatement.close();
         return rowDeleted;   
    	
    }
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL1 = {
					        "use testdb; ",
					        "drop table if exists marketPlace; ",
					        ("CREATE TABLE if not exists marketPlace( " +
					        	"saleID INTEGER AUTO_INCREMENT PRIMARY KEY,"+
					        	"endDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"+
					        	"price INTEGER(20),"+
					        	"nftID INTEGER,"+
					        	"FOREIGN KEY (nftID) REFERENCES NFT(nftID));")
					        	
        					};
        String[] TUPLES1 = {("insert into marketPlace(price, nftID)"+
        			"values ('70', '1'),"+
			    		 	"('3243242', '2'),"+
			    		 	"('123434', '3'),"+
			    		 	"('432', '4'),"+
			    		 	"('79', '5'),"+
			    		 	"('234', '6'),"+
			    		 	"('676', '7'),"+
			    		 	"('567', '8'),"+
			    		 	"('43', '9'),"+
			    		 	"('34342', '10');")
			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL1.length; i++)
        	statement.execute(INITIAL1[i]);
        for (int i = 0; i < TUPLES1.length; i++)	
        	statement.execute(TUPLES1[i]);
        disconnect();
    }
}