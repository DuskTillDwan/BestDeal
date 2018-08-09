import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TVList")

public class TVList extends HttpServlet {

	/* Television Page Displays all the Television and their Information in Best Deal */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");


		/* Checks the Tablets type whether it is microsft or Samsung or sceptre */

		HashMap<String, Television> hm = new HashMap<String, Television>();
		String searchId = request.getParameter("searchId");
		
		if(CategoryName==null && searchId == null)
		{
			hm.putAll(SaxParserDataStore.televisions);
			name = "";
		}
		else if (searchId != null)
		{
			for(Map.Entry<String, Television> entry : SaxParserDataStore.televisions.entrySet())
			{
				if(entry.getValue().getId().equals(searchId))
				{
					hm.put(entry.getValue().getId(), entry.getValue());
					name = "";
				}
			}
		}
		else
		{
		   if(CategoryName.equals("lg"))
		   {
			 for(Map.Entry<String,Television> entry : SaxParserDataStore.televisions.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("LG"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "LG";
		   }
		   else if(CategoryName.equals("samsung"))
		    {
			for(Map.Entry<String,Television> entry : SaxParserDataStore.televisions.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Samsung"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "Samsung";
			}
			else if(CategoryName.equals("sceptre"))
			{
				for(Map.Entry<String,Television> entry : SaxParserDataStore.televisions.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Sceptre"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Sceptre";
			}
		}
		
		/* Header, Left Navigation Bar are Printed.

		All the tv and tv information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Televisions</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Television> entry : hm.entrySet())
		{
			Television television = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+television.getName()+"</h3>");
			pw.print("<strong>$"+television.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/tvs/"+television.getImage()+"' alt='' /></li>");
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='television'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='televisions'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='televisions'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}		
		pw.print("</table></div></div></div>");		
		utility.printHtml("Footer.html");
		
	}
}
