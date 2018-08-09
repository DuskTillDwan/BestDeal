import com.mysql.jdbc.Driver;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


public class MySqlDataStoreUtilities
{
	private String username;
	private String password;
	private String dbURL;

	public MySqlDataStoreUtilities(String username, String password, String dbURL){
		this.username = username;
		this.password = password;
		this.dbURL = "jdbc:mysql://localhost:3306/BestDealDB?useSSL=false";
	}


	public MySqlDataStoreUtilities(){
		this.username = "root";
		this.password = "iluvcheese";
		this.dbURL = "jdbc:mysql://localhost:3306/BestDealDB?useSSL=false";
	}

	public Connection getDBConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(dbURL, username, password);
			return conn;
		}
		catch(SQLException e)
		{
			for (Throwable t:e)
			{
				t.printStackTrace();
			}
		}
		catch (ClassNotFoundException notfound)
		{
			notfound.printStackTrace();
		}
		return null;
	}


	public String selectUserPassword(User user)
	{
		try 
		{
			Connection conn = getDBConnection();

			String findUser = "SELECT Password FROM Accounts WHERE Username = ?;";
			PreparedStatement ps = conn.prepareStatement(findUser);
			ps.setString(1, user.getName());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				String password = rs.getString(1);
				return password;
			}
			else{
				return "Could not find password";
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public boolean insertUser(User user)
	{
		boolean completed = true;
		try
		{
			Connection conn = getDBConnection();

			String doesUserExist = "SELECT UserName FROM Accounts WHERE Username = ?;";
			PreparedStatement ps = conn.prepareStatement(doesUserExist);
			ps.setString(1, user.getName());
			ResultSet rs = ps.executeQuery();

			if (rs.next())
			{
				//user already exists in DB
				
				completed = false;
				rs.close();
				return completed;
			} 
			else if (!rs.next())
			{
				System.out.println("Got to else statement");
				ps.close();
				rs.close();
				String addUser = "INSERT INTO Accounts(UserName, Password, Usertype) VALUES(?,?,?);";
				PreparedStatement pst = conn.prepareStatement(addUser);
				pst.setString(1, user.getName());
				pst.setString(2, user.getPassword());
				pst.setString(3, user.getUsertype());
				completed = pst.execute();
				System.out.println("Made it passed execute");
			}
		conn.close();
		}
		catch (Exception e)
		{
			completed = false;
			e.getMessage();
			e.printStackTrace();
		}
		return completed;
	}

	public HashMap<String, User> getUsers()
	{
		HashMap<String, User> users = new HashMap<String, User>();

		try
		{
			Connection conn = getDBConnection();

			String doesUserExist = "SELECT * FROM 'BestDealDB'.'Accounts';";

			Statement ps = conn.createStatement();
			ResultSet rs = ps.executeQuery(doesUserExist);

			while (rs.next())
			{
				User user = new User(rs.getString("UserName"), rs.getString("Password"), rs.getString("Usertype"));
				users.put(rs.getString("UserName"), user);
			}
			conn.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return users;
	}

	public boolean addOrderPayment(OrderPayment orderPayment)
	{
		boolean successful = true;
		try
		{
			Connection conn = getDBConnection();

			String addOrder = "INSERT IGNORE INTO CustomerOrders(OrderId, userName, orderName, orderPrice, userAddress, creditCard) VALUES (?,?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(addOrder);
			ps.setInt(1, orderPayment.getOrderId());
			ps.setString(2, orderPayment.getUserName());
			ps.setString(3, orderPayment.getOrderName());
			ps.setDouble(4, orderPayment.getOrderPrice());
			ps.setString(5, orderPayment.getUserAddress());
			ps.setString(6, orderPayment.getCreditCardNo());
			ps.execute();
			conn.close();

		}
		catch (Exception e)
		{
			e.printStackTrace();
			successful = false;
		}
		return successful;
	}

	public boolean deleteOrderPayment(OrderPayment orderPayment)
	{
		boolean successful = true;
		try
		{
			Connection conn = getDBConnection();

			String deleteOrder = "DELETE FROM CustomerOrders WHERE OrderId = ? and userName = ? and orderName = ?;";
			PreparedStatement ps = conn.prepareStatement(deleteOrder);
			ps.setInt(1, orderPayment.getOrderId());
			ps.setString(2, orderPayment.getUserName());
			ps.setString(3, orderPayment.getOrderName());
			ps.execute();
			conn.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			successful = false;
		}
		return successful;
	}

}
