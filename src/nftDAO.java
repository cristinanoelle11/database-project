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
    public List<nft> listCertainNFT(String nameSearch) throws SQLException {
        List<nft> certainNFT = new ArrayList<nft>();        
        String sql = "SELECT * FROM NFT WHERE name = '"+nameSearch+"'";  
        
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	int nftId = resultSet.getInt("nftID");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String image = resultSet.getString("image");
            int owner = resultSet.getInt("owner");
             
            nft nftcertain = new nft(nftId, name, description, image, owner);
            certainNFT.add(nftcertain);
        }        
        resultSet.close();
        disconnect();        
        return certainNFT;
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
					        	"name VARCHAR(40),"+
					        	"description VARCHAR(500),"+
					        	"image VARCHAR(4000),"+
					        	"owner INTEGER,"+
					        	"FOREIGN KEY (owner) REFERENCES User(userID)"
					        	+ ");")
					        	
        					};
        String[] TUPLES1 = {("insert into NFT( name, description, image, owner)"+
        			"values ('Grass', 'picture of grass', 'https://images.pexels.com/photos/413195/pexels-photo-413195.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2', '1'), "+
			    		 	"('Pear', 'picture of pear','https://image.shutterstock.com/image-photo/red-apple-isolated-on-white-600w-1727544364.jpg', '3'),"+
			    		 	"('Apple', 'picture of apple','https://image.shutterstock.com/image-photo/red-apple-isolated-on-white-600w-1727544364.jpg', '2'),"+
			    		 	"('The girl', 'picture of a girl reading a book','https://image.shutterstock.com/image-vector/girl-reading-sit-side-book-600w-1687461220.jpg', '4'),"+
			    		 	"('Beach', 'a busy day at the beach', 'https://image.shutterstock.com/image-photo/vibrant-aerial-view-people-beach-600w-1268832001.jpg', '6'),"+
			    		 	"('Bunny', 'a bunny eating a carrot','https://image.shutterstock.com/image-photo/brown-cute-bunny-rabbit-crouched-600w-1657888987.jpg','5'),"+
			    		 	"('The Ying Yang', 'a yin yang symbol with the left side filled with mountains and the left side a rising sun from the ocean','http://pepperdine-graphic.com/wp-content/uploads/2019/12/yinyangONLINE.png', '8'),"+
			    		 	"('The Art of Books', 'an area of a bookstore full of different books','https://bookriot.com/wp-content/uploads/2020/04/bookstore-books-feature-700x375-1-1280x720.jpg','7'),"+
			    		 	"('The Bouquet','a bouquet of white, pink, and purple roses with greenery as well','https://image.shutterstock.com/image-photo/woman-bouquet-beautiful-roses-on-600w-1935156821.jpg','10'),"+
			    		 	"('Girls Day to the Theater','Three girls sitting next to each other with soda and popcorn enjoying a movie', 'https://image1.masterfile.com/getImage/NjExMS0wNjgzNzgxOGVuLjAwMDAwMDAw=AChW$q/6111-06837818en_Masterfile.jpg','9');"
        		
        						)
			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL1.length; i++)
        	statement.execute(INITIAL1[i]);
        for (int i = 0; i < TUPLES1.length; i++)	
        	statement.execute(TUPLES1[i]);
        disconnect();
    }
}
