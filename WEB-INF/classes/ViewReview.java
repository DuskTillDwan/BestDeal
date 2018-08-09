import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@WebServlet("/ViewReview")
@SuppressWarnings("serial")

public class ViewReview extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");

		String name = request.getParameter("name");

		ArrayList<Review> reviewsOfProduct = MongoDBDataStoreUtilities.selectReviewByProductName(name);

		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'> Reviews for " + name + "</a>");
		pw.print("</h2><div class='entry'><br><br><br>");

		for (int i = 0; i < reviewsOfProduct.size(); i++)
		{
			Review review = reviewsOfProduct.get(i);

			String productName = review.getProductName();
			String productType = review.getProductType();
			String productPrice = review.getProductPrice();
			String retailerName = review.getRetailerName();
			String retailerZip = review.getRetailerZip();
			String retailerCity = review.getRetailerCity();
			String retailerState = review.getRetailerState();
			String productOnSale = review.getProductOnSale();
			String manufacturerName = review.getManufacturerName();
			String manufacturerRebate = review.getManufacturerRebate();
			String userName = review.getUserName();
			String userAge = review.getUserAge();
			String userGender = review.getUserGender();
			String userOccupation = review.getUserOccupation();
			String reviewRating = review.getReviewRating();
			String reviewDate = review.getReviewDate();
			String reviewText = review.getReviewText();

			int reviewNumber = i+1;
			pw.print("<h2>Review #" + reviewNumber + "</h2>");
			pw.print("<table class='gridtable'>");

			pw.print("<tr>");
			pw.print("<td> Prodcut Name: </td>");
			pw.print("<td>" + productName + "</td>");

			pw.print("<tr>");
			pw.print("<td> Product Type </td>");
			pw.print("<td>" + productType + "</td>");

			pw.print("<tr>");
			pw.print("<td>Price: </td>");
			pw.print("<td>" + productPrice + "</td>");

			pw.print("<tr>");
			pw.print("<td>Retailer Name:</td>");
			pw.print("<td>" + retailerName + "</td>");

			pw.print("<tr>");
			pw.print("<td> Retailer Zip: </td>");
			pw.print("<td>" + retailerZip + "</td>");

			pw.print("<tr>");
			pw.print("<td> Retailer City: </td>");
			pw.print("<td>" + retailerCity + "</td>");

			pw.print("<tr>");
			pw.print("<td> Retailer State: </td>");
			pw.print("<td>" + retailerState + "</td>");

			pw.print("<tr>");
			pw.print("<td> Product On Sale: </td>");
			pw.print("<td>" + productOnSale + "</td>");

			pw.print("<tr>");
			pw.print("<td> Manufacturer Name: </td>");
			pw.print("<td>" + manufacturerName + "</td>");

			pw.print("<tr>");
			pw.print("<td> Manufacturer Rebate: </td>");
			pw.print("<td>" + manufacturerRebate + "</td>");

			pw.print("<tr>");
			pw.print("<td> User: </td>");
			pw.print("<td>" + userName + "</td>");

			pw.print("<tr>");
			pw.print("<td> User Age: </td>");
			pw.print("<td>" + userAge + "</td>");

			pw.print("<tr>");
			pw.print("<td> Gender: </td>");
			pw.print("<td>" + userGender + "</td>");

			pw.print("<tr>");
			pw.print("<td> Occupation: </td>");
			pw.print("<td>" + userOccupation + "</td>");


			pw.print("<tr>");
			pw.print("<td> Review Date: </td>");
			pw.print("<td>" + reviewDate + "</td>");

			pw.print("<tr>");
			pw.print("<td> Review: </td>");
			pw.print("<td>" + reviewText + "</td>");

			pw.print("<tr>");
			pw.print("<td> Reviewer: </td>");
			pw.print("<td>" + userName + "</td>");

			pw.print("</table>");
			pw.print("<br><br>");
		}
		pw.print("</h2></div></div></div>");
		utility.printHtml("Footer.html");

		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");

		String name = request.getParameter("productName");

		ArrayList<Review> reviewsOfProduct = MongoDBDataStoreUtilities.selectReviewByProductName(name);

		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'> Reviews for " + name + "</a>");
		pw.print("</h2><div class='entry'><br><br><br>");

		for (int i = 0; i < reviewsOfProduct.size(); i++)
		{
			Review review = reviewsOfProduct.get(i);

			String productName = review.getProductName();
			String userName = review.getUserName();
			String productType = review.getProductType();
			String reviewRating = review.getReviewRating();
			String reviewDate = review.getReviewDate();
			String reviewText = review.getReviewText();

			int reviewNumber = i+1;
			pw.print("<h2>Review #" + reviewNumber + "</h2>");
			pw.print("<table class='gridtable'>");

			pw.print("<tr>");
			pw.print("<td> Review Rating (out of 5): </td>");
			pw.print("<td>" + reviewRating + "</td>");

			pw.print("<tr>");
			pw.print("<td> Review Date: </td>");
			pw.print("<td>" + reviewDate + "</td>");

			pw.print("<tr>");
			pw.print("<td> Review: </td>");
			pw.print("<td>" + reviewText + "</td>");

			pw.print("<tr>");
			pw.print("<td> Reviewer: </td>");
			pw.print("<td>" + userName + "</td>");

			pw.print("</table>");
			pw.print("<br><br>");
		}
		pw.print("</h2></div></div></div>");
		utility.printHtml("Footer.html");
	}
}
