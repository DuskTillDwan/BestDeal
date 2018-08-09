import com.mongodb.*;
import java.util.HashMap;
import java.util.ArrayList;

public class MongoDBDataStoreUtilities{


	static DBCollection myReviews;

	public static void getConnection(){
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);

		DB db = mongo.getDB("CustomerReviews");
		myReviews = db.getCollection("myReviews");
	}


	public static HashMap<String, ArrayList<Review>> selectReview()
	{
		getConnection();

		HashMap<String, ArrayList<Review>> reviewHashmap = new HashMap<String, ArrayList<Review>>();
		DBCursor cursor = myReviews.find();
		
		while (cursor.hasNext()){
			
			BasicDBObject obj = (BasicDBObject)cursor.next();
			
			if(!reviewHashmap.containsKey(obj.getString("productName"))){
				ArrayList<Review> arr = new ArrayList<Review>();
				reviewHashmap.put(obj.getString("productName"), arr);
			}
			
			ArrayList<Review> listReview = reviewHashmap.get(obj.getString("productName"));
			Review review = new Review(obj.getString("productName"), obj.getString("productType"), obj.getString("productPrice"), obj.getString("retailerName"), obj.getString("retailerZip"), obj.getString("retailerCity"), obj.getString("retailerState"), obj.getString("productOnSale"), obj.getString("manufacturerName"), obj.getString("manufacturerRebate"), obj.getString("userName"), obj.getString("userAge"), obj.getString("userGender"), obj.getString("userOccupation"), obj.getString("reviewRating"), obj.getString("reviewDate"), obj.getString("reviewText"));
			listReview.add(review);
		}
		return reviewHashmap;
	}

	public static void insertReview(String productname,String producttype,String productprice,String retailername,String retailerzip,String retailercity,String retailerstate,String productonsale,String manufacturername,String manufacturerrebate,String username,String userage,String usergender, String useroccupation,String reviewrating, String reviewdate,String reviewtext){
		getConnection();
		BasicDBObject doc = new BasicDBObject("title", "myReviews").append("productName", productname).append("productType", producttype).append("productPrice", productprice).append("retailerName", retailername).append("retailerZip", retailerzip).append("retailerCity", retailercity).append("retailerState", retailerstate).append("productOnSale", productonsale).append("manufacturerName", manufacturername).append("manufacturerRebate", manufacturerrebate).append("userName", username).append("userAge", userage).append("userGender", usergender).append("userOccupation", useroccupation).append("reviewRating", reviewrating).append("reviewdDate", reviewdate).append("reviewText", reviewtext);
		myReviews.insert(doc);
	}
//insertReview(productName, productType, productPrice, retailerName, retailerZip, retailerCity, retailerState, productOnSale, manufacturerName, manufacturerRebate, userName, userAge, userGender, userOccupation, reviewRating, reviewDate, reviewText)
	public static ArrayList<Review> selectReviewByZip()
	{
		getConnection();

		BasicDBObject queryObject = new BasicDBObject();
		queryObject.put("reviewRating", new BasicDBObject("$gt", "1").append("$lt", "6"));
		DBCursor cursor = myReviews.find(queryObject);

		ArrayList<Review> listReview = new ArrayList<Review>();
		int count = 0;
		while (cursor.hasNext()){
			if (count == 5){
				break;
			} else {
				count++;
			}
			BasicDBObject obj = (BasicDBObject)cursor.next();
			Review review = new Review(obj.getString("productName"), obj.getString("productType"),obj.getString("productPrice"),obj.getString("retailerName"),obj.getString("retailerZip"),obj.getString("retailerCity"),obj.getString("retailerState"),obj.getString("productOnSale"),obj.getString("manufacturerName"),obj.getString("manufacturerRebate"),obj.getString("userName"),obj.getString("userAge"),obj.getString("userGender"), obj.getString("userOccupation"), obj.getString("reviewRating"), obj.getString("reviewDate"), obj.getString("reviewText"));
			listReview.add(review);

		}
		return listReview;
	}

	public static ArrayList<String> selectTopFive()
	{
		getConnection();

		BasicDBObject queryObject = new BasicDBObject();
		queryObject.put("reviewRating", new BasicDBObject("$gt", "1").append("$lt", "6"));
		DBCursor cursor = myReviews.find(queryObject);

		ArrayList<String> listReview = new ArrayList<String>();
		HashMap<String, Review> map = new HashMap<String, Review>();

		while (cursor.hasNext()){
			
			BasicDBObject obj = (BasicDBObject)cursor.next();
			Review review = new Review(obj.getString("productName"), obj.getString("productType"),obj.getString("productPrice"),obj.getString("retailerName"),obj.getString("retailerZip"),obj.getString("retailerCity"),obj.getString("retailerState"),obj.getString("productOnSale"),obj.getString("manufacturerName"),obj.getString("manufacturerRebate"),obj.getString("userName"),obj.getString("userAge"),obj.getString("userGender"), obj.getString("userOccupation"), obj.getString("reviewRating"), obj.getString("reviewDate"), obj.getString("reviewText"));
			if (!listReview.contains(obj.getString("productName")))
			{
				listReview.add(obj.getString("productName"));
			}

		}

		return listReview;

	}

	public static ArrayList<Review> selectReviewByProductName(String productName)
	{
		getConnection();

		BasicDBObject pNameQuery = new BasicDBObject();
		pNameQuery.put("productName", productName);

		DBCursor cursor = myReviews.find(pNameQuery);
		ArrayList<Review> listReview = new ArrayList<Review>();

		while (cursor.hasNext())
		{
			BasicDBObject obj = (BasicDBObject)cursor.next();
			Review review = new Review(obj.getString("productName"), obj.getString("productType"),obj.getString("productPrice"),obj.getString("retailerName"),obj.getString("retailerZip"),obj.getString("retailerCity"),obj.getString("retailerState"),obj.getString("productOnSale"),obj.getString("manufacturerName"),obj.getString("manufacturerRebate"),obj.getString("userName"),obj.getString("userAge"),obj.getString("userGender"), obj.getString("userOccupation"), obj.getString("reviewRating"), obj.getString("reviewDate"), obj.getString("reviewText"));
			listReview.add(review);
		}
		return listReview;
	}
}