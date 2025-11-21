# CEBWebApp
campus-event-booking
Campus Event Booking System built using Java Servlet, JSP, and MySQL. Implements full MVC architecture with CRUD operations for Categories and Events, user login system, DAO + Service layers, backend validation, and MySQL database integration. Runs on Tomcat 9 with JSP/JSTL frontend and clean UI.

Layer	Technology
Frontend (Views)	-JSP, HTML, CSS, JSTL
Backend (Controller)	-Java Servlets
Business & DAO Layer	-Java, Interfaces, OOP
Database	-MySQL 8.0
Architecture	-MVC (Model–View–Controller)
Build & Deploy	-Apache Tomcat 9.0, Eclipse IDE

How to Run
1.Import into Eclipse File → Import → Dynamic Web Project → Select project
2.Configure Tomcat Servers → Add New Server → Tomcat v9
3.Datacase Setup CREATE DATABASE ceb; Add the provided tables + sample data. Update DB credentials in: src/main/resources/db.properties
4.Run the app Run on Server → Tomcat visit : http://localhost:8080/CEBWebApp/home
Admin login: Email: admin@uni.lk Password: admin123 
User login: Email: user@uni.lk Password: user123

