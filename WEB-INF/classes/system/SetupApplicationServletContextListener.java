package system;

import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.*;
import java.sql.*;
import java.util.*;
import java.io.IOException;

@WebListener
public class SetupApplicationServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("START");
		ServiceIT serviceIT = new ServiceIT();
			try {
			Connection connection = Config.getConnection();
			Statement s = connection.createStatement();
			String query = "SELECT * FROM service_it;";
			ResultSet rs = s.executeQuery(query);
			while (rs.next()) {
				serviceIT.setMaintenance(rs.getString(1));
				serviceIT.setWebStatus(rs.getString(2));
			}
			s = connection.createStatement();
			query = "SELECT * FROM users;";
			rs = s.executeQuery(query);
			while (rs.next()) {
				User u = new User(rs.getString("username"));
				u.setFirstName(rs.getString("firstname"));
				u.setLastname(rs.getString("lastName"));
				u.setEmail(rs.getString("email"));
				u.setContact(rs.getString("contactNum"));
				serviceIT.addUser(u);
			}
			s = connection.createStatement();
			query = "SELECT * FROM new_requests;";
			rs = s.executeQuery(query);
			while (rs.next()) {
				User user = serviceIT.getUser(rs.getString("userSub"));
				ServiceRequest sr = new ServiceRequest(rs.getString("category"),rs.getString("issue"), rs.getString("title"), rs.getString("userSub"), rs.getString("restart"), rs.getString("location"), user);
				sr.setUUIDString(rs.getString("uuid"));
				sr.setWait(rs.getString("wait"));
				sr.setStatus(rs.getString("status"));
				serviceIT.addRequest(sr);
			}
			s = connection.createStatement();
			query = "SELECT * FROM new_requests_comments;";
			rs = s.executeQuery(query);
			while (rs.next()) {
				for (ServiceRequest req : serviceIT.getNewReq()) {
					if (req.getUUIDstring().equals(rs.getString("uuid"))) {
						req.addComment(rs.getString("comment"));
					}
				}
			}
			s = connection.createStatement();
			query = "SELECT * FROM knowledge_base;";
			rs = s.executeQuery(query);
			while (rs.next()) {
				User user = serviceIT.getUser(rs.getString("userSub"));
				ServiceRequest sr = new ServiceRequest(rs.getString("category"),rs.getString("issue"), rs.getString("title"), rs.getString("userSub"), rs.getString("restart"), rs.getString("location"), user);
				sr.setUUIDString(rs.getString("uuid"));
				sr.setWait(rs.getString("wait"));
				sr.setStatus(rs.getString("status"));
				serviceIT.addKBReq(sr);
			}
			s = connection.createStatement();
			query = "SELECT * FROM knowledge_base_comments;";
			rs = s.executeQuery(query);
			while (rs.next()) {
				for (ServiceRequest req : serviceIT.getKBReq()) {
					if (req.getUUIDstring().equals(rs.getString("uuid"))) {
						req.addComment(rs.getString("comment"));
					}
				}
			}
			rs.close();
			s.close();
			connection.close();
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
		catch (NamingException ne) {
			System.err.println(ne.getMessage());
			System.err.println(ne.getStackTrace());
		}
		servletContextEvent.getServletContext().setAttribute("serviceIT", serviceIT);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		ServiceIT serviceIT = (ServiceIT) servletContextEvent.getServletContext().getAttribute("serviceIT");
		try {
			//connection
			Connection connection = Config.getConnection();
			//Maintenance and Web Status storage
			String statement = "DELETE FROM service_it WHERE TRUE;";
			PreparedStatement s = connection.prepareStatement(statement);
			s.executeUpdate();
			statement = "INSERT INTO service_it VALUES (?,?);";
			s = connection.prepareStatement(statement);
			s.setString(1, serviceIT.getWebStatus());
			s.setString(2, serviceIT.getMaintenance());
			s.executeUpdate();
			//Service requests storage
			statement = "DELETE FROM new_requests_comments WHERE TRUE;";
			s = connection.prepareStatement(statement);
			s.executeUpdate();
			statement = "DELETE FROM new_requests WHERE TRUE;";
			s = connection.prepareStatement(statement);
			s.executeUpdate();
			for (ServiceRequest req : serviceIT.getNewReq()) {
				statement = "INSERT INTO new_requests VALUES (?,?,?,?,?,?,?,?,?);";
				s = connection.prepareStatement(statement);
				s.setString(1, req.getUUIDstring());
				s.setString(2, req.getCat());
				s.setString(3, req.getStatus());
				s.setString(4, req.getIssue());
				s.setString(5, req.getTitle());
				s.setString(6, req.getRestart());
				s.setString(7, req.getLocation());
				s.setString(8, req.getWait());
				s.setString(9, req.getUser());
				s.executeUpdate();
				for (String comment : req.getComments()) {
					statement = "INSERT INTO new_requests_comments VALUES (?,?);";
					s = connection.prepareStatement(statement);
					s.setString(1, req.getUUIDstring());
					s.setString(2, comment);
					s.executeUpdate();
				}
			}
			//Knowledge base storage
			statement = "DELETE FROM knowledge_base_comments WHERE TRUE;";
			s = connection.prepareStatement(statement);
			s.executeUpdate();
			statement = "DELETE FROM knowledge_base WHERE TRUE;";
			s = connection.prepareStatement(statement);
			s.executeUpdate();
			for (ServiceRequest req : serviceIT.getKBReq()) {
				statement = "INSERT INTO knowledge_base VALUES (?,?,?,?,?,?,?,?,?);";
				s = connection.prepareStatement(statement);
				s.setString(1, req.getUUIDstring());
				s.setString(2, req.getCat());
				s.setString(3, req.getStatus());
				s.setString(4, req.getIssue());
				s.setString(5, req.getTitle());
				s.setString(6, req.getRestart());
				s.setString(7, req.getLocation());
				s.setString(8, req.getWait());
				s.setString(9, req.getUser());
				s.executeUpdate();
				for (String comment : req.getComments()) {
					statement = "INSERT INTO knowledge_base_comments VALUES (?,?);";
					s = connection.prepareStatement(statement);
					s.setString(1, req.getUUIDstring());
					s.setString(2, comment);
					s.executeUpdate();
				}
				s.close();
				connection.close();
			}
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
		}
		catch (NamingException ne) {
			System.err.println(ne.getMessage());
			System.err.println(ne.getStackTrace());
		}
		System.out.println("STOP");
		try {
			System.in.read();
		}
		catch (IOException io) {
			System.err.println(io.getMessage());
			System.err.println(io.getStackTrace());
		}
	}
}
