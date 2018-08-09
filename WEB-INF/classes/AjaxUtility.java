import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

@SuppressWarnings({"serial", "unchecked"})

public class AjaxUtility
{

	public static HashMap<String, Product> getData()
	{
		Utilities utility = new Utilities();
		HashMap<String, Product> hm = new HashMap<String, Product>();

		try
		{
			HashMap<String, Tablet> tablets = utility.getTablets();
			HashMap<String, Television> televisions = utility.getTelevisions();
			HashMap<String, Smartphone> smartphones = utility.getSmartphones();
			HashMap<String, Laptop> laptops = utility.getLaptops();

			for(Map.Entry<String, Tablet> entry : tablets.entrySet()){
				Product p = new Product(entry.getValue().getId(), entry.getValue().getName());
				hm.put(entry.getValue().getId(), p);
			}
			for(Map.Entry<String, Television> entry : televisions.entrySet()){
				Product p = new Product(entry.getValue().getId(), entry.getValue().getName());
				hm.put(entry.getValue().getId(), p);
			}
			for(Map.Entry<String, Smartphone> entry : smartphones.entrySet()){
				Product p = new Product(entry.getValue().getId(), entry.getValue().getName());
				hm.put(entry.getValue().getId(), p);
			}
			for(Map.Entry<String, Laptop> entry : laptops.entrySet()){
				Product p = new Product(entry.getValue().getId(), entry.getValue().getName());
				hm.put(entry.getValue().getId(), p);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return hm;
	}

	public StringBuffer readdata(String searchId)
	{
		StringBuffer buffer = new StringBuffer();
		HashMap<String, Product> data;
		data = getData();
		Iterator it = data.entrySet().iterator();
		while (it.hasNext())
		{
			Map.Entry pi = (Map.Entry)it.next();
			Product p = (Product)pi.getValue();
			if(p.getName().toLowerCase().contains(searchId.replace("%20", " ").toLowerCase()))
			{
				buffer.append("<product>");
				buffer.append("<id>" + p.getId() + "</id>");
				buffer.append("<productName>" + p.getName() + "</productName>");
				buffer.append("</product>");
			}
		}
		return buffer;
	}
}