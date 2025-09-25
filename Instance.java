package project;


public class Instance {
	
	private String deliveryLocation;
	private String weatherCondition;
	private String packageSize;
	private String deliveryTime;
	private String label;

	public Instance(String deliveryLocation, String weatherCondition, String packageSize, String deliveryTime, String label )
	{
		setLocation(deliveryLocation);
		setWeather(weatherCondition);
		setSize(packageSize);
		setTime(deliveryTime);
		setLabel(label);
		
	}
	
	// Setter for location
	public void setLocation(String deliveryLocation)
	{
		this.deliveryLocation = deliveryLocation;
	}
	
	// Getter for Location
	public String getLocation()
	{
		return deliveryLocation;
	}
	
	// Setter for weather
	public void setWeather(String weatherCondition)
	{
		this.weatherCondition = weatherCondition;
		
	}
	
	//Getter for weather
	public String getWeather()
	{
		return weatherCondition;
	}
	// Setter for size
	public void setSize(String packageSize)
	{
		this.packageSize = packageSize;
	}
	
	// Getter for size
	public String getSize()
	{
		return packageSize;
	}
	
	// Setter for time
	public void setTime(String deliveryTime)
	{
		this.deliveryTime = deliveryTime;
	}
	// getter for time
	public String getTime()
	{
		return deliveryTime;
	}
	
	// setter for label
	public void setLabel(String label)
	{
		this.label = label;
		
	}
	
	// getter for label
	public String getLabel()
	{
		return label;
	}
	
	// method to display 
	public String toString() {
        return "Delivery Location: " + getLocation() + ", Weather: " + getWeather() + ", Package Size: " + getSize() + ", Delivery Time: " + getTime() + ", Label: " + getLabel();
    }


	
		
}




