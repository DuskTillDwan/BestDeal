import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/WriteReview")
@SuppressWarnings("serial")


public class WriteReview extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		displayWriteReview(request, response);
	}

	protected void displayWriteReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to write a review");
			response.sendRedirect("Login");
			return;
		}

		String productName = request.getParameter("name");
		String productType = request.getParameter("type");
		String price = request.getParameter("price");
		String productOnSale = request.getParameter("discount");
		String manufacturerName = request.getParameter("maker");

		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");

		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 20px;'>Review Item</a>");
		pw.print("</h2><div class='entry'><table id ='bestseller'>");

		pw.print("</td><td>");
		pw.print("<li><form method='post' action='WriteReview'>");
		pw.print("<table><tr></tr><tr></tr>");
		pw.print("<tr><td>");
		pw.print("Product Name:</td>");
		pw.print("<td><input type='text' name='productName' value='" + productName + "'>");
		pw.print("</td></tr>");

		pw.print("<tr><td>");
		pw.print("Product Type:</td>");
		pw.print("<td><input type='text' name='productType' value='" + productType + "'>" );
		pw.print("</td></tr>");

		pw.print("<tr><td>");
		pw.print("Price:</td>");
		pw.print("<td><input type='text' name='productPrice' value='" + price + "'>" );
		pw.print("</td></tr>");

		pw.print("<tr><td>");
		pw.print("Product on Sale:</td>");
		pw.print("<td><input type='text' name='productOnSale' value='Yes'>" );
		pw.print("</td></tr>");

		pw.print("<tr><td>");
		pw.print("Retailer Name:</td>");
		pw.print("<td><input type='text' name='retailerName' value='BestDeal'>" );
		pw.print("</td></tr>");

		pw.print("<tr><td>");
		pw.print("Zip:</td>");
		pw.print("<td><input type='text' name='retailerZip' value='60616'>" );
		pw.print("</td></tr>");

		pw.print("<tr><td>");
		pw.print("Retailer City:</td>");
		pw.print("<td><input type='text' name='retailerCity' value='Chicago'>" );
		pw.print("</td></tr>");

		pw.print("<tr><td>");
		pw.print("Retailer State:</td>");
		pw.print("<td><input type='text' name='retailerState' value='IL'>" );
		pw.print("</td></tr>");

	//	pw.print("<tr><td>");
	//	pw.print("On Sale:</td>");
	//	pw.print("<td><input type='text' name='productOnSale' value='Yes'>" );
	//	pw.print("</td></tr>");

		pw.print("<tr><td>");
		pw.print("Manufacturer:</td>");
		pw.print("<td><input type='text' name='manufacturerName' value = '" + manufacturerName + "'>" );
		pw.print("</td></tr>");

		pw.print("<tr><td>");
		pw.print("Manufacturer Rebate:</td>");
		pw.print("<td><input type='text' name='manufacturerRebate' value = 'Yes'>" );
		pw.print("</td></tr>");


		pw.print("<tr><td>");
		pw.print("Username:</td>");
		pw.print("<td><input type='text' name='userName'>" );
		pw.print("</td></tr>");

		pw.print("<tr><td>");
		pw.print("User Age:</td>");
		pw.print("<td><input type='text' name='userAge'>" );
		pw.print("</td></tr>");

		pw.print("<tr><td>");
		pw.print("User Gender:</td>");
		pw.print("<td><input type='text' name='userGender'>" );
		pw.print("</td></tr>");

		pw.print("<tr><td>");
		pw.print("Occupation:</td>");
		pw.print("<td><input type='text' name='userOccupation'>" );
		pw.print("</td></tr>");

		pw.print("<tr><td>");
		pw.print("Rating (1 to 5):</td>");
		pw.print("<td><input type='text' name='reviewRating'>" );
		pw.print("</td></tr>");

		pw.print("<tr><td>");
		pw.print("Date:</td>");
		pw.print("<td><input type='text' name='reviewDate'>" );
		pw.print("</td></tr>");

		pw.print("<tr><td>");
		pw.print("Review:</td>");
		pw.print("<td><input type='text' name='reviewText'>" );
		pw.print("</td></tr></table>");


		pw.print("<input type='submit' name='Submit Review' value='WriteReview' class='btnbuy'>");

		//utility.printHtml("Footer.html");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		displayWriteReview(request, response);

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		Utilities utility = new Utilities(request, pw);

		String productName = request.getParameter("productName");
		String productType = request.getParameter("productType");
		String productPrice = request.getParameter("productPrice");
		String retailerName = request.getParameter("retailerName");
		String retailerZip = request.getParameter("retailerZip");
		String retailerCity = request.getParameter("retailerCity");
		String retailerState = request.getParameter("retailerState");
		String productOnSale = request.getParameter("productOnSale");
		String manufacturerName = request.getParameter("manufacturerName");
		String manufacturerRebate = request.getParameter("manufacturerRebate");
		String userName = request.getParameter("userName");
		String userAge = request.getParameter("userAge");
		String userGender = request.getParameter("userGender");
		String userOccupation = request.getParameter("userOccupation");
		String reviewRating = request.getParameter("reviewRating");
		String reviewDate = request.getParameter("reviewDate");
		String reviewText = request.getParameter("reviewText");

		MongoDBDataStoreUtilities.insertReview(productName, productType, productPrice, retailerName, retailerZip, retailerCity, retailerState, productOnSale, manufacturerName, manufacturerRebate, userName, userAge, userGender, userOccupation, reviewRating, reviewDate, reviewText);

		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 20px;'> Review Item Stored for: " + productName + "</a>");
		pw.print("</h2><div class='entry'><table id ='bestseller'>");
		pw.print("</table></div></div></div>");
	}
}