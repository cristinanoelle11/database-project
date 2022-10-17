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
public class historyDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public historyDAO(){}
	
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
    
    public List<history> listAllHistory() throws SQLException {
        List<history> listHistory = new ArrayList<history>();        
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
             
            history historys = new history(historyID, userID, nftID, details, action, date);
            listHistory.add(historys);
        }        
        resultSet.close();
        disconnect();        
        return listHistory;
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
					        	"date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP);")
					        	
        					};
        String[] TUPLES1 = {("insert into History( details, action )"+
        			"values ('user creat','creation'), "+
			    		 	"('user add', 'addition');")
			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL1.length; i++)
        	statement.execute(INITIAL1[i]);
        for (int i = 0; i < TUPLES1.length; i++)	
        	statement.execute(TUPLES1[i]);
        disconnect();
    }
    
}
