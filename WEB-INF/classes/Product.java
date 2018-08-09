public class Product
{
	private String productId;
	private String productName;

	public Product()
	{

	}

	public Product(String productId, String productName)
	{
		this.productId = productId;
		this.productName = productName;
	}

	public String getName(){
		return productName;
	}

	public String getId(){
		return productId;
	}
}