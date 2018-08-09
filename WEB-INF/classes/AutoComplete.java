import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/AutoComplete")

public class AutoComplete extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{
		Utilities utility = new Utilities();

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		String[] queryString;
		String action = "";
		String searchId = "";

		try
		{
			queryString = request.getQueryString().split("&");
			for (int i =0; i < queryString.length; i++)
			{
				if (queryString[i].contains("action"))
				{
					action = queryString[i].split("=")[1];
				}
				if (queryString[i].contains("searchId"))
				{
					searchId = queryString[i].split("=")[1];
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}

		try
		{
			StringBuffer buffer = new StringBuffer();
			boolean names = false;
			if (action.equals("complete"))
			{
				if(!searchId.equals(""))
				{
					AjaxUtility a = new AjaxUtility();
					buffer = a.readdata(searchId);
					if(buffer!=null || !buffer.equals(""))
					{
						names = true;
					}
					if (names)
					{
						response.setContentType("text/xml");
						response.getWriter().write("<products>" + buffer.toString() + "</products>");
						System.out.println(buffer);
					}
				}
			}
		}
		catch  (Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		Utilities utility = new Utilities();

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	}
}
