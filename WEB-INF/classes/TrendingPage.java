import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@WebServlet("/Trending")
@SuppressWarnings("serial")

public class TrendingPage extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);

		ArrayList<String> topFiveReviews = MongoDBDataStoreUtilities.selectTopFive();
		ArrayList<Review> topFiveZipReviews = MongoDBDataStoreUtilities.selectReviewByZip();

		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");

		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 20px;'> Trending...</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");

		pw.print("<h2>Top 5 Most Liked products</h2>");

		pw.print("<table class='gridtable'>");
		pw.print("<tr><td>Product Name</td></tr>");

		for(int i = 0; i < topFiveReviews.size(); i++)
		{
			String review = topFiveReviews.get(i);
			pw.print("<tr><td>" + review + "</td></tr>");
		}
		pw.print("</table>");
		pw.print("<h2>Top 5 Most Bought Products by Zipcode</h2>");

		pw.print("<table class='gridtable'>");
		pw.print("<tr><td>Product Name</td></tr>");

		for(int i = 0; i < topFiveZipReviews.size(); i++)
		{
			Review review = topFiveZipReviews.get(i);
			pw.print("<tr><td>" + review.getRetailerZip() + "</td></tr>");
		}


		pw.print("</table>");
	
		utility.printHtml("Footer.html");
	}
}