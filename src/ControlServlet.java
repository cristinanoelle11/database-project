import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private userDAO userDAO = new userDAO();	    
	    private nftDAO nftDAO = new nftDAO();

	    private historyDAO historyDAO = new historyDAO();

	    private marketPlaceDAO marketPlaceDAO = new marketPlaceDAO();

	    private String currentUser;
	    private HttpSession session=null;
	    
	    public ControlServlet()
	    {
	    	
	    }
	    
	    public void init()
	    {
	    	userDAO = new userDAO();
	    	currentUser= "";
	    	nftDAO = new nftDAO();
	    	historyDAO = new historyDAO();
	    	marketPlaceDAO = new marketPlaceDAO();
	    
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getServletPath();
	        System.out.println(action);
	    
	    try {
        	switch(action) {  
        	case "/login":
        		login(request,response);
        		break;
        	case "/register":
        		register(request, response);
        		break;
        	case "/search":
        		search(request,response);
        		break;
        	case "/initialize":
        		userDAO.init();
        		nftDAO.init();
        		historyDAO.init();
        		marketPlaceDAO.init();
        		System.out.println("Database successfully initialized!");
        		rootPage(request,response,"");
        		break;
        	case "/root":
        		rootPage(request,response, "");
        		break;
        	case "/logout":
        		logout(request,response);
        		break;
        	 case "/list": 
                 System.out.println("The action is: list");
                 listUser(request, response);           	
                 break;
	    	}
	    }
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
	    }
        	
	    private void listUser(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listUser started: 00000000000000000000000000000000000");

	     
	        List<user> listUser = userDAO.listAllUsers();
	        request.setAttribute("listUser", listUser);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listPeople finished: 111111111111111111111111111111111111");
	    }
	    
	    private void listNFT(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listNFT started: 00000000000000000000000000000000000");
	        
	        List<nft> listNFT = nftDAO.listAllNFTS();
	        request.setAttribute("listNFT", listNFT);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("NFTList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listNFT finished: 111111111111111111111111111111111111");

	    }     
	    
	    private void listHistory(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listHistory started: 00000000000000000000000000000000000");
	        
	        List<history> listHistory = historyDAO.listAllHistory();
	        request.setAttribute("listHistory", listHistory);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("HistoryList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listHistory finished: 111111111111111111111111111111111111");
	    }   

	         
	    
	    private void listMarketPlace(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listMarketPlace started: 00000000000000000000000000000000000");
	        
	        List<marketPlace> listMarketPlace = marketPlaceDAO.listMarketPlace();
	        request.setAttribute("listMarketPlace", listMarketPlace);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("marketPlaceList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listMarket finished: 111111111111111111111111111111111111");
	    }        
	    

	    private void rootPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("root view");
			request.setAttribute("listUser", userDAO.listAllUsers());
			request.setAttribute("listNFT", nftDAO.listAllNFTS());
			request.setAttribute("listMarketPlace", marketPlaceDAO.listMarketPlace());
			request.setAttribute("listHistory", historyDAO.listAllHistory());
	    	request.getRequestDispatcher("rootView.jsp").forward(request, response);
	    }
	    
	    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String name = request.getParameter("name");
	    	System.out.println("searchNFT started: 00000000000000000000000000000000000");

	        List<nft> certainNFT = nftDAO.listCertainNFT(name);
	        request.setAttribute("certainNFT", certainNFT);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("NFT.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("searchNFT finished: 111111111111111111111111111111111111");
	    	
	    }
	    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	 String email = request.getParameter("email");
	    	 String password = request.getParameter("password");
	    	 
	    	 if (email.equals("root") && password.equals("pass1234")) {
				 System.out.println("Login Successful! Redirecting to root");
				 session = request.getSession();
				 session.setAttribute("username", email);
				 rootPage(request, response, "");
	    	 }
	    	 else if(userDAO.isValid(email, password)) 
	    	 {
			 	 
			 	 currentUser = email;
				 System.out.println("Login Successful! Redirecting");
				 request.getRequestDispatcher("activitypage.jsp").forward(request, response);
			 			 			 			 
	    	 }
	    	 else {
	    		 request.setAttribute("loginStr","Login Failed: Please check your credentials.");
	    		 request.getRequestDispatcher("login.jsp").forward(request, response);
	    	 }
	    }
	           
	    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	
	    	String email = request.getParameter("email");
	   	 	String firstName = request.getParameter("firstName");
	   	 	String lastName = request.getParameter("lastName");
	   	 	String password = request.getParameter("password");
	   	 	String age = request.getParameter("age");
	   	 	String confirm = request.getParameter("confirmation");
	   	 	
	   	 	if (password.equals(confirm)) {
	   	 		if (!userDAO.checkEmail(email)) {
		   	 		System.out.println("Registration Successful! Added to database");
		   	 	user users = new user(email,firstName, lastName, password, age, 100);
		   	 		userDAO.insert(users);
		   	 		response.sendRedirect("login.jsp");
	   	 		}
		   	 	else {
		   	 		System.out.println("Username taken, please enter new username");
		    		 request.setAttribute("errorOne","Registration failed: Username taken, please enter a new username.");
		    		 request.getRequestDispatcher("register.jsp").forward(request, response);
		   	 	}
	   	 	}
	   	 	else {
	   	 		System.out.println("Password and Password Confirmation do not match");
	   		 request.setAttribute("errorTwo","Registration failed: Password and Password Confirmation do not match.");
	   		 request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 	}
	    }    
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
        		response.sendRedirect("login.jsp");
        	}
	
	    

	     
        
	    
	
	    
	    
	    
}
	        
	        
	    
	        
	        
	        
	    


