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
@WebServlet("/nftDAO")
public class nftDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public nftDAO(){}
	
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
    
    public List<nft> listAllNFTS() throws SQLException {
        List<nft> listNFT = new ArrayList<nft>();        
        String sql = "SELECT * FROM NFT";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	int nftId = resultSet.getInt("nftID");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String image = resultSet.getString("image");
            int owner = resultSet.getInt("owner");
             
            nft nfts = new nft(nftId, name, description, image, owner);
            listNFT.add(nfts);
        }        
        resultSet.close();
        disconnect();        
        return listNFT;
    }
    
    
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL1 = {
					        "use testdb; ",
					        "drop table if exists NFT; ",
					        ("CREATE TABLE if not exists NFT( " +
					        	"nftID INTEGER AUTO_INCREMENT PRIMARY KEY,"+
					        	"name VARCHAR(10),"+
					        	"description VARCHAR(50),"+
					        	"image VARCHAR(4000),"+
					        	"owner INTEGER);")
					        	
        					};
        String[] TUPLES1 = {("insert into NFT( name, description, image)"+
        			"values ( 'Grass ', 'picture of grass', 'https://images.pexels.com/photos/413195/pexels-photo-413195.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2'), "+
			    		 	"('pear', 'picture of apple','https://image.shutterstock.com/image-photo/red-apple-isolated-on-white-600w-1727544364.jpg'),"+
			    		 	"('apple', 'picture of apple','https://image.shutterstock.com/image-photo/red-apple-isolated-on-white-600w-1727544364.jpg'),"+
			    		 	"('apple', 'picture of apple','https://image.shutterstock.com/image-photo/red-apple-isolated-on-white-600w-1727544364.jpg');")
			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL1.length; i++)
        	statement.execute(INITIAL1[i]);
        for (int i = 0; i < TUPLES1.length; i++)	
        	statement.execute(TUPLES1[i]);
        disconnect();
    }
}
