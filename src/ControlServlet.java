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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;

public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO = new UserDAO();
	private NftDAO nftDAO = new NftDAO();
	private HistoryDAO historyDAO = new HistoryDAO();
	private MarketPlaceDAO marketPlaceDAO = new MarketPlaceDAO();
	private CommonDAO commonDAO = new CommonDAO();
	private String currentUser;
	private HttpSession session = null;

	public ControlServlet() {
	}

	public void init() {
		userDAO = new UserDAO();
		currentUser = "";
		nftDAO = new NftDAO();
		historyDAO = new HistoryDAO();
		marketPlaceDAO = new MarketPlaceDAO();
		commonDAO = new CommonDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);

		try {
			switch (action) {
			case "/buy":
				buy(request, response);
				break;
			case "/activity":
				activity(request, response);
				break;
			case "/cancel":
				cancel(request, response);
				break;
			case "/login":
				login(request, response);
				break;
			case "/mintNFT":
				mintNFT(request, response);
				break;
			case "/register":
				register(request, response);
				break;
			case "/search":
				search(request, response);
				break;
			case "/searchNFT":
				searchNFT(request, response);
				break;
			case "/displayNFT":
				displayNFT(request, response);
				break;
			case "/placeInMarket":
				placeInMarket(request, response);
				break;
			case "/sell":
				sell(request, response);
				break;
			case "/diamondHands":
				diamond(request, response);
				break;
			case "/paperHands":
				paper(request, response);
				break;
			case "/goodBuyers":
				goodBuyers(request, response);
				break;
			case "/inactiveUsers":
				inactiveUsers(request, response);
				break;
			case "/userStats":
				userStats(request, response);
				break;
			case "/mint":
				mint(request, response);
				break;
			case "/transfer":
				transfer(request, response);
				break;
			case "/transferSetUp":
				transferSetUp(request, response);
				break;
			case "/initialize":
				userDAO.init();
				nftDAO.init();
				historyDAO.init();
				marketPlaceDAO.init();
				System.out.println("Database successfully initialized!");
				rootPage(request, response, "");
				break;
			case "/root":
				rootPage(request, response, "");
				break;
			case "/searchUsers":
				searchUsers(request, response);
				break;
			case "/displayUser":
				displayUser(request, response);
				break;
			case "/listMarketPlace":
				listMarketPlace(request, response);
				break;
			case "/logout":
				logout(request, response);
				break;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	private void diamond(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		System.out.println("diamondHands started: 00000000000000000000000000000000000");
		List<User> diamondHands = commonDAO.diamondHands();
		request.setAttribute("diamondHands", diamondHands);
		request.getRequestDispatcher("statistics.jsp").forward(request, response);

		System.out.println("diamondHands finished: 111111111111111111111111111111111111");
	}
	private void paper(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
		System.out.println("paperHands started: 00000000000000000000000000000000000");
		List<User> paperHands = commonDAO.paperHands();
		request.setAttribute("paperHands", paperHands);
		request.getRequestDispatcher("statistics.jsp").forward(request, response);

		System.out.println("paperHands finished: 111111111111111111111111111111111111");
	}
	private void goodBuyers(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
		System.out.println("goodBuyers started: 00000000000000000000000000000000000");
			List<User> goodBuyers = commonDAO.goodBuyers();
			request.setAttribute("goodBuyers", goodBuyers);
			request.getRequestDispatcher("statistics.jsp").forward(request, response);
		
		System.out.println("goodBuyers finished: 111111111111111111111111111111111111");
	}
	private void userStats(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
		System.out.println("userStats started: 00000000000000000000000000000000000");
		String name = request.getParameter("name");
		List<User> closeUser = userDAO.listAllUsers(name);
		if (closeUser.isEmpty()) {
			request.setAttribute("errorMessage", "No similar user, please try again:");
			request.getRequestDispatcher("statistics.jsp").forward(request, response);
		}
		User currentU = closeUser.stream().findFirst().get();
		int user = currentU.userID;

		List<User> userStats = commonDAO.userStats(user);
		request.setAttribute("currentU", currentU);
		request.setAttribute("userStats", userStats);
		request.getRequestDispatcher("statistics.jsp").forward(request, response);
		System.out.println("userStats finished: 111111111111111111111111111111111111");
	}

	private void inactiveUsers(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
		System.out.println("inactiveUsers started: 00000000000000000000000000000000000");
		List<User> inactiveUsers = commonDAO.inactiveUsers();
		request.setAttribute("inactiveUsers", inactiveUsers);
		request.getRequestDispatcher("statistics.jsp").forward(request, response);
		System.out.println("inactiveUsers finished: 111111111111111111111111111111111111");
	}
	private void sell(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
		System.out.println("sell started: 00000000000000000000000000000000000");

		List<Nft> listNFT = nftDAO.listAllNFTS();
		request.setAttribute("listNFT", listNFT);

		List<MarketPlace> listMarketPlace = marketPlaceDAO.listMarketPlace();
		request.setAttribute("listMarketPlace", listMarketPlace);

		List<History> listHistory = historyDAO.listAllHistory();
		request.setAttribute("listHistory", listHistory);

		User user = userDAO.getUser(currentUser);
		request.setAttribute("currentUser", user);
		request.getRequestDispatcher("ListNFT.jsp").forward(request, response);
		System.out.println("sell finished: 111111111111111111111111111111111111");
	}

	private void mint(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ServletException {
		System.out.println("mint started: 00000000000000000000000000000000000");
		List<Nft> listNFT = nftDAO.listAllNFTS();
		request.setAttribute("listNFT", listNFT);

		List<MarketPlace> listMarketPlace = marketPlaceDAO.listMarketPlace();
		request.setAttribute("listMarketPlace", listMarketPlace);

		List<History> listHistory = historyDAO.listAllHistory();
		request.setAttribute("listHistory", listHistory);

		User user = userDAO.getUser(currentUser);
		request.setAttribute("currentUser", user);
		List<History> result = new ArrayList<History>();
		listHistory.stream().forEach(i -> {
			if (i.getAction().equals("mint")) {
				result.add(i);
			}
		});
		request.setAttribute("result", result);
		request.setAttribute("messageOne", "NFT's you have previously created:");
		request.getRequestDispatcher("mint.jsp").forward(request, response);
		System.out.println("sell finished: 111111111111111111111111111111111111");
	}

	private void searchNFT(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		System.out.println("searchNFT started: 00000000000000000000000000000000000");

		List<Nft> listNFT = nftDAO.listAllNFTS();
		request.setAttribute("listNFT", listNFT);

		List<MarketPlace> listMarketPlace = marketPlaceDAO.listMarketPlace();
		request.setAttribute("listMarketPlace", listMarketPlace);

		List<History> listHistory = historyDAO.listAllHistory();
		request.setAttribute("listHistory", listHistory);

		User user = userDAO.getUser(currentUser);
		request.setAttribute("currentUser", user);
		List<History> result = new ArrayList<History>();

		listHistory.stream().forEach(i -> {
			if ((user.userID == i.userID) && i.getAction().equals("bought")) {
				result.add(i);

			}
		});
		request.setAttribute("result", result);
		request.getRequestDispatcher("search.jsp").forward(request, response);
		System.out.println("searchNFT finished: 111111111111111111111111111111111111");
	}

	
	  private void listMarketPlace(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException { 
		  System.out.println("listMarketPlace started: 00000000000000000000000000000000000");
			  List<MarketPlace> listMarketPlace = marketPlaceDAO.listMarketPlace();
			  request.setAttribute("listMarketPlace", listMarketPlace); 
			  request.getRequestDispatcher("marketPlaceList.jsp").forward(request, response);
		  System.out.println("listMarket finished: 111111111111111111111111111111111111"); 
	  }
	private void rootPage(HttpServletRequest request, HttpServletResponse response, String view)throws ServletException, IOException, SQLException {
		System.out.println("root view");
		request.setAttribute("listUser", userDAO.listAllUsers());
		request.setAttribute("listNFT", nftDAO.listAllNFTS());
		request.setAttribute("listMarketPlace", marketPlaceDAO.listMarketPlace());
		request.setAttribute("listHistory", historyDAO.listAllHistory());
		request.getRequestDispatcher("rootView.jsp").forward(request, response);
	}
	private void search(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		String name = request.getParameter("name");
		name = name.toLowerCase();
		System.out.println("search started: 00000000000000000000000000000000000");
		List<Nft> nfts = nftDAO.nftsOnMarketPlace(name);
		if (nfts.isEmpty()) {
			request.setAttribute("errorOne", "There is no similar NFT, please try again");
		}
		request.setAttribute("nfts", nfts);
		request.setAttribute("messageOne", "NFT you may be looking for:");
		request.getRequestDispatcher("/searchNFT").forward(request, response);

		System.out.println("search finished: 111111111111111111111111111111111111");
	}
	private void displayNFT(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		String name = request.getParameter("name");
		System.out.println("displayNFT started: 00000000000000000000000000000000000");
		List<Nft> certainNFT = nftDAO.listCertainNFT(name);
		request.setAttribute("certainNFT", certainNFT);
		User user = userDAO.getUser(currentUser);
		request.setAttribute("currentUser", user);
		request.getRequestDispatcher("NFT.jsp").forward(request, response);
		System.out.println("displayNFT finished: 111111111111111111111111111111111111");
	}

	private void displayUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		System.out.println("displayUSer started: 111111111111111111111111111111111111");
		String email = request.getParameter("name");
		User users = userDAO.getUser(email);
		String enteredEmail = users.email;
		if (enteredEmail.equals(currentUser)) {
			request.getRequestDispatcher("/activity").forward(request, response);
		} else {
			request.setAttribute("currentU", users);
			int owner = users.userID;
			List<Nft> usersNFTS = nftDAO.listUsersNFTs(owner);
			request.setAttribute("usersNFTS", usersNFTS);
			request.getRequestDispatcher("userDisplay.jsp").forward(request, response);
		}
		System.out.println("displayUser finished: 111111111111111111111111111111111111");
	}

	private void searchUsers(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		String email = request.getParameter("email");
		System.out.println("searchUser started: 00000000000000000000000000000000000");
		List<User> users = userDAO.listAllUsers(email);
		if (users.isEmpty()) {
			request.setAttribute("errorOne", "There is no similar user, please try again");
		}
		request.setAttribute("users", users);
		request.setAttribute("messageOne", "Users you may be looking for:");
		request.getRequestDispatcher("searchUsers.jsp").forward(request, response);

		System.out.println("searchUser finished: 111111111111111111111111111111111111");
	}

	private void placeInMarket(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException, ParseException {
		System.out.println("listNFT started: 00000000000000000000000000000000000");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String date = request.getParameter("date");
		User currentU = userDAO.getUser(currentUser);
		Nft enteredNFT = nftDAO.getNFTbyName(name);
		request.setAttribute("enteredNFT", enteredNFT);
		if (enteredNFT == null) {
			request.setAttribute("messageNotEqual", "Make sure you are entering the EXACT name of the nft, try again");
			request.getRequestDispatcher("/sell").forward(request, response);
		}
		if (enteredNFT.owner != currentU.userID) {
			request.setAttribute("messageNotEqual", "You cannot list a NFT that you dont own, try again");
			request.getRequestDispatcher("/sell").forward(request, response);
		}
		//int enterednftID = enteredNFT.nftID;
		List<MarketPlace> listings = marketPlaceDAO.listMarketPlace();
		List<History> listHistory = historyDAO.listAllHistory();
		request.setAttribute("listHistory", listHistory);
		User user = userDAO.getUser(currentUser);
		request.setAttribute("currentUser", user);
		List<History> result = new ArrayList<History>();
		listHistory.stream().forEach(i -> {if (i.getAction().equals("mint")) {result.add(i);}	});
		request.setAttribute("result", result);
		boolean ans = listings.stream().filter(o -> o.getnftID() == enteredNFT.nftID).findFirst().isPresent();
		if (ans) {
			request.setAttribute("errorOne", "this product is already listed in the marketplace, try again");
			request.getRequestDispatcher("ListNFT.jsp").forward(request, response);
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			java.util.Date dateStr = null;
			try {
				 dateStr = formatter.parse(date);
			}catch(ParseException ex) {
				request.setAttribute("errorDate", "ERROR: Please enter the date in the format: MM/DD/YYYY");
				request.getRequestDispatcher("/sell").forward(request, response);
				//request.getRequestDispatcher("ListNFT.jsp").forward(request, response);
			}
			java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
			Nft thenft = nftDAO.getNFTbyName(name);
			marketPlaceDAO.insert(dateDB, Integer.parseInt(price), thenft.nftID);
			List<Nft> listNFT = nftDAO.listAllNFTS();
			request.setAttribute("listNFT", listNFT);
			request.getRequestDispatcher("/listMarketPlace").forward(request, response);
			System.out.println("listNFT finished: 00000000000000000000000000000000000");
		}
	}

	private void transferSetUp(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		User users = userDAO.getUser(currentUser);
		request.setAttribute("currentU", users);
		int owner = users.userID;
		List<Nft> usersNFTS = nftDAO.listUsersNFTs(owner);
		request.setAttribute("usersNFTS", usersNFTS);
		request.getRequestDispatcher("transfer.jsp").forward(request, response);
	}

	private void transfer(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		// Find user to transfer to
		String tranferToEmail = request.getParameter("email");
		String nftName = request.getParameter("name");
		System.out.println("transferNFT started: 00000000000000000000000000000000000");
		// Create instance for holder and receiver
		User NftHolder = userDAO.getUser(currentUser);
		int owner = NftHolder.userID;
		List<Nft> usersNFTS = nftDAO.listUsersNFTs(owner);
		request.setAttribute("usersNFTS", usersNFTS);
		User NftReciever = userDAO.getUser(tranferToEmail);
		// get NFT to transfer
		Nft certainNFT = nftDAO.getNFT(nftName);
		if (certainNFT == null) {
			request.setAttribute("errorMessage", "Sorry, we cannot find that user, try again.");
			request.getRequestDispatcher("transfer.jsp").forward(request, response);
		} else {
			// Update NFT
			if (NftHolder.userID == certainNFT.owner) {
				nftDAO.update2(NftReciever.getuserID(), nftName);
				historyDAO.insertTransfer(NftHolder, NftReciever, certainNFT);
			}
			request.setAttribute("noNFTStr", "You currently own no NFTs.");
			request.getRequestDispatcher("/activity").forward(request, response);
			System.out.println("transferNFT finished: 111111111111111111111111111111111111");
		}
	}

	private void buy(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		// int newOwner = Integer.parseInt(request.getParameter("owner"));
		String nftID = request.getParameter("nftID");
		String name = request.getParameter("name");
		System.out.println("buyNFT started: 00000000000000000000000000000000000");
		MarketPlace listing = marketPlaceDAO.getmarketPlace(nftID);
		User user = userDAO.getUser(currentUser);
		if (user.wallet < listing.price) {
			System.out.println("wallet is less then the price of nft");
			request.setAttribute("poorError", "You don't have enough money to buy that NFT, try to buy another one");
			request.getRequestDispatcher("/search").forward(request, response);
			// request.getRequestDispatcher("register.jsp").forward(request, response);
		} else {
			// SUBTRACT TOTAL AND UPDATE
			int newWallet = user.wallet - listing.price;
			userDAO.updateWallet(user.userID, newWallet);
			int newOwner = user.userID;
			Nft certainNFT = nftDAO.getNFTbyName(name);
			String NFTname = certainNFT.name;
			System.out.println("nft name:" + certainNFT.name);
			
			// UPDATE NFT OWNER
			int currentOwner = certainNFT.owner;
			System.out.println("Current owner: " + currentOwner);
			
			User oldOwner = userDAO.getUser(currentOwner);
			int oldOwnerWallet = oldOwner.wallet + listing.price;
			userDAO.updateWallet(currentOwner, oldOwnerWallet);
			// currentOwner = newOwner;
			System.out.println("new owner: " + newOwner);
			System.out.println("Current owner: " + certainNFT.owner);
			nftDAO.update(NFTname, newOwner, currentOwner);
			// delete from marketplace
			marketPlaceDAO.delete(certainNFT.nftID);
			User userUpdate = userDAO.getUser(currentUser);
			request.setAttribute("currentU", userUpdate);
			
			List<Nft> usersNFTS = nftDAO.listUsersNFTs(user.userID);
			request.setAttribute("usersNFTS", usersNFTS);
			request.setAttribute("noNFTStr", "You currently own no NFTs.");
			historyDAO.insertBought(user, oldOwner, certainNFT);
			request.getRequestDispatcher("/activity").forward(request, response);
			System.out.println("buyNFT finished: 111111111111111111111111111111111111");
		}
	}

	protected void cancel(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		System.out.println("cancelNFT started: 111111111111111111111111111111111111");
		String nftID = request.getParameter("nftID");
		String name = request.getParameter("name");
		Nft certainNFT = nftDAO.getNFTbyName(name);
		int currentOwner = certainNFT.owner;
		User Owner = userDAO.getUser(currentOwner);
		marketPlaceDAO.delete(certainNFT.owner);
		historyDAO.cancelSell(Owner, certainNFT);
		request.getRequestDispatcher("/activity").forward(request, response);
		System.out.println("cancelNFT finished: 111111111111111111111111111111111111");
	}

	protected void login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if (email.equals("root") && password.equals("pass1234")) {
			System.out.println("Login Successful! Redirecting to root");
			session = request.getSession();
			session.setAttribute("username", email);
			rootPage(request, response, "");
		} else if (userDAO.isValid(email, password)) {
			currentUser = email;
			User users = userDAO.getUser(currentUser);
			request.setAttribute("currentU", users);
			int owner = users.userID;
			List<Nft> usersNFTS = nftDAO.listUsersNFTs(owner);
			request.setAttribute("usersNFTS", usersNFTS);
			ArrayList<Integer> deletedNFTS = marketPlaceDAO.deleteViaDate();
			for (Integer nftid : deletedNFTS) {
				Nft thenft = nftDAO.getNFT(nftid);
				historyDAO.insertExpired(users, thenft);
			}
			request.getRequestDispatcher("/activity").forward(request, response);
			System.out.println("Login Successful! Redirecting");
		} else {
			request.setAttribute("loginStr", "Login Failed: Please check your credentials.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	protected void activity(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		User users = userDAO.getUser(currentUser);
		request.setAttribute("currentU", users);
		int owner = users.userID;
		List<Nft> usersNFTS = nftDAO.listUsersNFTs(owner);
		request.setAttribute("usersNFTS", usersNFTS);
		List<Nft> listNFT = nftDAO.listAllNFTS();
		request.setAttribute("listNFT", listNFT);
		List<MarketPlace> listMarketPlace = marketPlaceDAO.listMarketPlace();
		request.setAttribute("listMarketPlace", listMarketPlace);

		request.getRequestDispatcher("activitypage.jsp").forward(request, response);
		System.out.println("activity Successful! Redirecting");
	}

	private void register(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		String age = request.getParameter("age");
		String confirm = request.getParameter("confirmation");
		int number = Integer.parseInt(age);
		if (number <= 18) {
			request.setAttribute("errorThree", "Registration failed: You do not meet the age restrictions");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		if (password.equals(confirm)) {
			if (!userDAO.checkEmail(email)) {
				System.out.println("Registration Successful! Added to database");
				User users = new User(email, firstName, lastName, password, age, 100);
				userDAO.insert(users);
				historyDAO.insertUser(users);
				response.sendRedirect("login.jsp");
			} else {
				System.out.println("Username taken, please enter new username");
				request.setAttribute("errorOne", "Registration failed: Username taken, please enter a new username.");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
		} else {
			System.out.println("Password and Password Confirmation do not match");
			request.setAttribute("errorTwo", "Registration failed: Password and Password Confirmation do not match.");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}

	// Function to mintNFT in servlet
	private void mintNFT(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String image = request.getParameter("image");
		User users = userDAO.getUser(currentUser);
		int owner = users.userID;
		// Functions to check NFT validity in nftDAO
		if (!nftDAO.checkNftName(name)) {
			if (!nftDAO.checkImage(image)) {
				System.out.println("Minting Successful! Added to database");
				Nft nfts = new Nft(name, description, image, owner);
				nftDAO.insertNFT(nfts);
				// have to call getnft to make sure we get the automatic nftid to pass into history
				Nft newNFT = nftDAO.getNFTbyName(name);
				historyDAO.insertMint(users, newNFT);
				request.getRequestDispatcher("/activity").forward(request, response);
			} else {
				System.out.println("imageURL taken, please enter new imageURL");
				request.setAttribute("errorOne", "Minting failed: imageURL taken, please enter a new imageURL.");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
		} else {
			System.out.println("NFT name already taken");
			request.setAttribute("errorTwo", "Minting failed: NFT name is already taken.");
			request.getRequestDispatcher("mint.jsp").forward(request, response);
		}
	}
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		currentUser = "";
		response.sendRedirect("login.jsp");
	}
}
}