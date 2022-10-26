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
        	case"/buy":
        		buy(request,response);
        		break;
        	case "/login":
        		login(request,response);
        		break;
        	case "/mintNFT":
        		mintNFT(request,response);
        		break;
        	case "/register":
        		register(request, response);
        		break;
        	case "/search":
        		search(request,response);
        		break;
        	case "/transfer":
        		transfer(request, response);
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
	    
	    private void transfer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	// Find user to transfer to
	    	String tranferToEmail = request.getParameter("email");
	    	String nftName = request.getParameter("name");
	    	System.out.println("transferNFT started: 00000000000000000000000000000000000");

	    	// Create instance for holder and receiver
		 	user NftHolder = userDAO.getUser(currentUser);
		 	user NftReciever = userDAO.getUser(tranferToEmail);
		 	
	    	//get NFT to transfer
		 	nft certainNFT = nftDAO.getNFT(nftName);
	    	//Update NFT
		 	if (NftHolder.getuserID() == certainNFT.getOwner()) {
		 		nftDAO.update2(NftReciever.getuserID(), nftName);
		 	}
	        
	        System.out.println("transferNFT finished: 111111111111111111111111111111111111");
	    }
	    
	    private void buy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	//int newOwner = Integer.parseInt(request.getParameter("owner"));
	    	System.out.println("buyNFT started: 00000000000000000000000000000000000");
	    	marketPlace listings = marketPlaceDAO.getmarketPlace();
	    	user users = userDAO.getUser(currentUser);
	    	if(users.wallet < listings.price) {
	    		System.out.println("wallet is less then the price of nft");
		   		 request.setAttribute("errorOne","wallet is less then the price of nft");
		   		// request.getRequestDispatcher("register.jsp").forward(request, response);
	    	}else {
	    	//in this case returns 2
	    	int newWallet;
	    	newWallet = users.wallet - listings.price;
    			System.out.println("new Owner is = " + newWallet); // should return 2
    		userDAO.updateWallet(users.userID, newWallet);
	    	int newOwner = users.userID;
	    	nft thenft = nftDAO.getNFT();
	    	//should return 1
	    	int currentOwner = thenft.owner;
	    		System.out.println("the old owner is = " + currentOwner); // returns 1
	    	currentOwner = newOwner;	    	
	    	nftDAO.update(currentOwner,thenft.owner );
	    		System.out.println("new Owner is = " + newOwner); // should return 2
	    	//delete from marketplace
	    	marketPlaceDAO.delete(thenft.owner);
	    	 
	    	
	    	System.out.println("buyNFT finished: 111111111111111111111111111111111111");
	    	}
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
			 	 user users = userDAO.getUser(currentUser);
			 	 request.setAttribute("currentU", users);
			 	 int owner = users.userID;
			 	 List<nft> usersNFTS = nftDAO.listUsersNFTs(owner);
			     request.setAttribute("usersNFTS", usersNFTS);  
				 RequestDispatcher dispatcher = request.getRequestDispatcher("activitypage.jsp");       
			     dispatcher.forward(request, response);
			     System.out.println("Login Successful! Redirecting");
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
	    
	    //Function to mintNFT in servlet
private void mintNFT(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	
	    	String name = request.getParameter("name");
	   	 	String description = request.getParameter("description");
	   	 	String image = request.getParameter("image");
	   	 
	   	 	//Functions to check NFT validity in nftDAO
	   	 	if (!nftDAO.checkNftName(name)) {
	   	 		if (!nftDAO.checkImage(image)) {
		   	 		System.out.println("Minting Successful! Added to database");
		   	 	nft nfts = new nft(name,description, image);
		   	 		nftDAO.insertNFT(nfts);
		   	 		response.sendRedirect("activitypage.jsp");
	   	 		}
		   	 	else {
		   	 		System.out.println("imageURL taken, please enter new imageURL");
		    		 request.setAttribute("errorOne","Minting failed: imageURL taken, please enter a new imageURL.");
		    		 request.getRequestDispatcher("register.jsp").forward(request, response);
		   	 	}
	   	 	}
	   	 	else {
	   	 		System.out.println("NFT name already taken");
	   		 request.setAttribute("errorTwo","Minting failed: NFT name is already taken.");
	   		 request.getRequestDispatcher("mint.jsp").forward(request, response);
	   	 	}
	    }    
	    
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
        		response.sendRedirect("login.jsp");
        	}
	
	    

	     
        
	    
	
	    
	    
	    
}
	        
	        
	    
	        
	        
	        
	    


