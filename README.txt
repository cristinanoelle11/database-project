GROUP MEMBERS:
	Cristina Powers gv6830 AND Logarn Barnes gw4822

CONTRIBUTIONS:
	So far each person has done an equal part of participation for this project. We have met for 2 hours after 12 classes
	while also working on it on our own time.
	
SETUP:
	1) Download and install Eclipse IDE for Enterprise Java developers: Eclipse IDE for Enterprise Java Developers
 	(Links to an external site.). In Windows, the zip file is unzipped into C:\Program Files\ and then run the eclipse 
 	app under C:\Program Files\eclipse.
 
 	2) Download and install Tomcat 9 from:https://tomcat.apache.org/download-90.cgi(Links to an external site.)
 	 In Windows, the zip file is unzipped into C:\Users\yourUsername\tomcat.
 	 
 	3) Open Eclipse, and go to Menu item Window -> Show View -> Servers. The new bar of Servers then shows up at 
 	 the bottom of the interface. Click the link in this bar. Select Apache, and add the tomcat 9.0 from where you 
 	 stored it in step 2.
 	 
 	4) Set up a database name testdb, open the MySQL workbench, create the tables displayed below, and put some data by running the 
 	 following command in the MySQL Workbench Query window. 
 	 
 	show databases;
	use testdb;
	
	drop table if exists User; 
	CREATE TABLE if not exists User( 
	userID INTEGER AUTO_INCREMENT PRIMARY KEY,
	    email VARCHAR(50) NOT NULL, 
	    firstName VARCHAR(10) NOT NULL, 
	    lastName VARCHAR(10) NOT NULL, 
	    password VARCHAR(20) NOT NULL, 
	    age VARCHAR(5) NOT NULL, 
	    wallet INTEGER DEFAULT 100);
	insert into User(email, firstName, lastName, password, age, wallet)
	values ('susie@gmail.com', 'Susie ', 'Guzman', 'susie1234', '27','100'),
		   ('sophie@gmail.com', 'Sophie', 'Pierce','sophie1234', '15','100'),
		   ('root', 'default', 'default','pass1234', '00', '100');
	select * from User;  
	
	drop table if exists NFT;          
	CREATE TABLE if not exists NFT( 
		nftID INTEGER AUTO_INCREMENT,
		name VARCHAR(10),
		description VARCHAR(50),
		image VARCHAR(4000),
		owner INTEGER,
		PRIMARY KEY (nftID),
		FOREIGN KEY (owner) REFERENCES User(userID) );
	insert into NFT( name, description, image )
	values ('Adam ', 'description of adam', 'https://www.ADAM.com'),
	       ( 'Sophie', 'description of sophie','https://www.SOPHIE.com');
	select * from NFT;
	
	drop table if exists History;
	CREATE TABLE if not exists History( 
		historyID INTEGER AUTO_INCREMENT PRIMARY KEY,
		userID INTEGER,
		nftID INTEGER,
		details VARCHAR(2000),
		action VARCHAR(50),
		date TIMESTAMP);
	insert into History( userID, nftID, details, action, date )
	values('1', '1' 'user created','creation', NOW()), 
		  ('2', '3', 'user created','creation', NOW());
	select * from History;
	
	drop table if exists marketPlace;          
	CREATE TABLE if not exists marketPlace( 
		saleID INTEGER AUTO_INCREMENT PRIMARY KEY,
		endDate VARCHAR(10),
		price INTEGER(20),
		nftID INTEGER,
		FOREIGN KEY (NFT) REFERENCES NFT(NFTID));
	insert into marketPlace(endDate, price)
	values ('01/12/21','12324'),
		   ('04/07/22','34342');
	select * from marketPlace;
	
	
	5) Download the code for Exercise 5 from: https://github.com/cristinanoelle11/database-project/tree/master to an external site.
	 by clicking the Clone and download button. Unzip the file and place the database folder inside the
	 C:\Users\yourUsername folder. Then import it into Eclipse as "Projects from Folder or Archive." 
	  
	6) Update the Project Facets and Java Run Time environment
	   Project -> Properties -> Java -> (your java version)
	   Project -> Properties -> Java Compiler -> Java Build Path -> JRE System Library
	   Update to (your java version)
 	 	
 	 7) Then change the code in the java source code for the user ID and password on your server.  
 	 At this moment, I assume you have already finished the previous exercises because the table in your local 
 	 server is needed for the following.
 	 
 	 8)Expand Project Explorer -> WebContent -> register.jsp on the left of your software. Select 
 	 Run as -> Run on the server and then register a user and then perform a login, login as root user,
 	 and initialize the user table.
