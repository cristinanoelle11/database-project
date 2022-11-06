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
@WebServlet("/userDAO")
public class UserDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public UserDAO(){}	
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
    public boolean database_login(String email, String password) throws SQLException{
    	try {
    		connect_func("root","pass1234");
    		String sql = "select * from user where email = ?";
    		preparedStatement = connect.prepareStatement(sql);
    		preparedStatement.setString(1, email);
    		ResultSet rs = preparedStatement.executeQuery();
    		return rs.next();
    	}
    	catch(SQLException e) {
    		System.out.println("failed login");
    		return false;
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
    public List<User> listAllUsers() throws SQLException {
        List<User> listUser = new ArrayList<User>();        
        String sql = "SELECT * FROM User";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
        	int userID = resultSet.getInt("userID");
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String age = resultSet.getString("age");
            int wallet = resultSet.getInt("wallet"); 
            User users = new User(userID,email,firstName, lastName, password, age, wallet);
            listUser.add(users);
        }        
        resultSet.close();
        disconnect();        
        return listUser;
    }  
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    public void insert(User users) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into User(email, firstName, lastName, password, age,wallet) values (?, ?, ?, ?, ?, ?)";
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, users.getEmail());
			preparedStatement.setString(2, users.getFirstName());
			preparedStatement.setString(3, users.getLastName());
			preparedStatement.setString(4, users.getPassword());
			preparedStatement.setString(5, users.getAge());	
			preparedStatement.setInt(6, users.getWallet());
		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public boolean delete(String email) throws SQLException {
        String sql = "DELETE FROM User WHERE email = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);  
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     public boolean updateWallet(int userID, int wallet) throws SQLException{
    	 String sql = "update user set wallet= ? where userID = ?";
         connect_func();
         preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
         preparedStatement.setInt(1, wallet);
         preparedStatement.setInt(2, userID);
         boolean rowUpdated = preparedStatement.executeUpdate()>0;
         preparedStatement.close();
         return rowUpdated;
     }
    public boolean update(User users) throws SQLException {
        String sql = "update User set userID =?,firstName=?, lastName =?,password = ?,age=?, wallet=? where email = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);     
        preparedStatement.setString(1, users.getEmail());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());
		preparedStatement.setString(4, users.getPassword());
		preparedStatement.setString(5, users.getAge());		
		preparedStatement.setInt(6, users.getWallet());		
     
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }  
    public User getUser(String email) throws SQLException {
    	User user = null;
        String sql = "SELECT * FROM User WHERE email = ?";
        // Handle error where user is not in database
        connect_func();       
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);         
        ResultSet resultSet = preparedStatement.executeQuery();        
        if (resultSet.next()) {
        	int userID = resultSet.getInt("userID");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String age = resultSet.getString("age");
            int wallet = resultSet.getInt("wallet");
            user = new User(userID, email, firstName, lastName, password, age, wallet);
        }        
        resultSet.close();
        preparedStatement.close();         
        return user;
    }
    public User getUser(int userID) throws SQLException {
    	User user = null;
        String sql = "SELECT * FROM User WHERE userID = ?";
        // Handle error where user is not in database
        connect_func();        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, userID);         
        ResultSet resultSet = preparedStatement.executeQuery();        
        if (resultSet.next()) {
        	String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String age = resultSet.getString("age");
            int wallet = resultSet.getInt("wallet");
            user = new User(userID, email, firstName, lastName, password, age, wallet);
        }        
        resultSet.close();
        preparedStatement.close();         
        return user;
    }
    public User getUserByName(String name) throws SQLException {
    	User user = null;
        String sql = "SELECT * FROM User WHERE name = ?";
        connect_func();  
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
        	int userID = resultSet.getInt("userID");
        	String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("password");
            String age = resultSet.getString("age");
            int wallet = resultSet.getInt("wallet");
            user = new User(userID, email, firstName, lastName, password, age, wallet);
        } 
        resultSet.close();
        statement.close();  
        return user;
    }
    public boolean checkEmail(String email) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE email = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	        
        if (resultSet.next()) {
        	checks = true;
        }
        System.out.println(checks);
    	return checks;
    }   
    public boolean checkPassword(String password) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE password = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	        
        if (resultSet.next()) {
        	checks = true;
        }        
        System.out.println(checks);
       	return checks;
    }    
    public boolean isValid(String email, String password) throws SQLException
    {
    	String sql = "SELECT * FROM User";
    	connect_func();
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);   	
    	resultSet.last();    	
    	int setSize = resultSet.getRow();
    	resultSet.beforeFirst();    	
    	for(int i = 0; i < setSize; i++){
    		resultSet.next();
    		if(resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
    			return true;
    		}		
    	}
    	return false;
    }
  
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = {"drop database if exists testdb; ",
					        "create database testdb; ",
					        "use testdb; ",
					        "drop table if exists User; ",
					        ("CREATE TABLE if not exists User( " +
					        	"userID INTEGER AUTO_INCREMENT PRIMARY KEY,"+
					            "email VARCHAR(50) NOT NULL, " + 
					            "firstName VARCHAR(10) NOT NULL, " +
					            "lastName VARCHAR(10) NOT NULL, " +
					            "password VARCHAR(20) NOT NULL, " +
					            "age VARCHAR(5) NOT NULL, " +
					            "wallet INTEGER DEFAULT 100);")
        					};
        String[] TUPLES = {("insert into User(email, firstName, lastName, password, age, wallet)"+
        			"values ('cristina@gmail.com', 'Cristina ', 'Powers', 'cristina1234', '27', '100'),"+
			    		 	"('logan@gmail.com', 'Logan', 'Barnes','logan123', '20', '100'),"+
			    	 	 	"('sara@gmail.com', 'Sara', 'Lawson','sara1234', '19', '100'),"+
			    		 	"('joe@gmail.com', 'Joe', 'Brady','joe1234', '22','100'),"+
			    		 	"('katie@gmail.com', 'Katie', 'Moore','katie1234', '15','100'),"+
			    		 	"('amelia@gmail.com', 'Amelia', 'Phillips','amelia1234', '24', '100'),"+
			    			"('sophie@gmail.com', 'Sophie', 'Pierce','sophie1234', '35', '100'),"+
			    			"('chris@gmail.com', 'Chris', 'Francis','chris1234', '26','100'),"+
			    			"('mike@gmail.com', 'Mike', 'Smith','mike1234', '17', '100'),"+
			    			"('jeannette@gmail.com', 'Jeannette ', 'Stone','jeannette1234', '22','100'),"+
			    			"('root', 'default', 'default','pass1234', '0', '100');")
			    			};        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        disconnect();
    }
}