package utility;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.Order;
import dto.Engineer;
import dto.Partner;
import dto.Engineering;
import dto.Service;
import dto.City;

public class JsonTransformer {
	
	private static String data = null;
	static Gson gson = new Gson();
	 
	public static String OrderTransformer(ArrayList<Order> list)
	{				
		data = gson.toJson(list);
		return data;	
	}
		
	public static String StringObjektumTransformer(String list)
	{		
		data = gson.toJson(list);
		return data;
	}
		
	public static String EngineerTransformer(ArrayList<Engineer> list)
	{		
		data = gson.toJson(list);
		return data;
	}
		
	public static String PartnerTransformer(ArrayList<Partner> list)
	{		
		data = gson.toJson(list);
		return data;
	}
		
	public static String CityTransformer(ArrayList<City> list)
	{		
		data = gson.toJson(list);
		return data;
	}
	
	public static String EngineeringTransformer(ArrayList<Engineering> list)
	{		
		data = gson.toJson(list);
		return data;
	}
		
	public static String ServiceTransformer(ArrayList<Service> list)
	{		
		data = gson.toJson(list);
		return data;
	}
		
	// Create a new Gson object that could parse all passed in elements
	public Gson gson(){		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd");
		Gson gson = gsonBuilder.create();
		return gson;
	}
}
