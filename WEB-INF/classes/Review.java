import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Review")

/* 
	laptop class contains class variables userName,productType,reviewRating,retailer,reviewDate,reviewText.

	laptop class has a constructor with Arguments userName,productType,reviewRating,retailer,reviewDate,reviewText.
	  
Review review = new Review(obj.getString("productName"), obj.getString("userName"), obj.getString("productType"), obj.getString("reviewRating"), obj.getString("reviewDate"), obj.getString("reviewText"));


	laptop class contains getters and setters for userName,productType,reviewRating,retailer,reviewDate,reviewText.

*/

public class Review extends HttpServlet{
	private String productName;
	private String productType;
	private String productPrice;
	private String retailerName;
	private String retailerZip;
	private String retailerCity;
	private String retailerState;
	private String productOnSale;
	private String manufacturerName;
	private String manufacturerRebate;
	private String userName;
	private String userAge;
	private String userGender;
	private String userOccupation;
	private String reviewRating;
	private String reviewDate;
	private String reviewText;
	
	public Review(String productName, String productType, String productPrice, String retailerName, String retailerZip, String retailerCity, String retailerState, String productOnSale, String manufacturerName, String manufacturerRebate, String userName, String userAge, String userGender, String userOccupation, String reviewRating, String reviewDate,String reviewText){
		this.productName=productName;
		this.productType=productType;
		this.productPrice=productPrice;
		this.retailerName=retailerName;
		this.retailerZip=retailerZip;
		this.retailerCity=retailerCity;
		this.retailerState=retailerState;
		this.productOnSale=productOnSale;
		this.manufacturerName=manufacturerName;
		this.manufacturerRebate=manufacturerRebate;
		this.userName=userName;
		this.userAge=userAge;
		this.userGender=userGender;
		this.userOccupation=userOccupation;
		this.reviewRating=reviewRating;
		this.reviewDate=reviewDate;
		this.reviewText = reviewText;
	}
	
	public Review(){
//1		
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
		
//2
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}

//3
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

//4
	public String getRetailerName() {
		return retailerName;
	}
	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}

//5
	public String getRetailerZip() {
		return retailerZip;
	}
	public void setRetailerZip(String retailerZip) {
		this.retailerZip = retailerZip;
	}

//6
	public String getRetailerCity() {
		return retailerCity;
	}
	public void setRetailerCity(String retailerCity) {
		this.retailerCity = retailerCity;
	}

//7
	public String getRetailerState() {
		return retailerState;
	}
	public void setRetailerState(String retailerState) {
		this.retailerState = retailerState;
	}

//8
	public String getProductOnSale() {
		return productOnSale;
	}
	public void setProductOnSale(String productOnSale) {
		this.productOnSale = productOnSale;
	}

//9
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

//10
	public String getManufacturerRebate() {
		return manufacturerRebate;
	}
	public void setManufacturerRebate(String manufacturerRebate) {
		this.manufacturerRebate = manufacturerRebate;
	}

//11
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

//12
	public String getUserAge() {
		return userAge;
	}
	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

//13
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

//14
	public String getUserOccupation() {
		return userOccupation;
	}
	public void setUserOccupation(String userOccupation) {
		this.userOccupation = userOccupation;
	}

//15
	public String getReviewRating() {
		return reviewDate;
	}

	public void setReviewRating(String reviewDate) {
		this.reviewDate = reviewDate;
	}

//16
	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

//17
	public String getReviewText() { 
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	
}
