package project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Dataset {
	private List<Instance> instances;
	
	// Creating the array where our data is going to be held in
	public Dataset()
	{
		instances = new ArrayList<>();
	}

	// Method to read CSV file, 'String Path' being our file path to CSV file.
	public void loadFromCSV(String Path) throws FileNotFoundException, IOException
	{	
		// Empty instances in case data that we don't want is in here
		instances.clear();
		
		// Opens our file using file reader and wraps it with BufferedReader so we can read it line by line
		try (BufferedReader br = new BufferedReader(new FileReader(Path)))
		{	
			
			String Rline; // Variable to hold each line as the file is read
			boolean firstline = true; // Variable to check for first line
			
			
			while((Rline = br.readLine()) != null) // Reads a line from the file into RLINE
			{	
				// Checker to see if its the first line to skip the header of the CSV file. 
				if (firstline) {
					firstline = false;
					continue;
				}
				
				String[] valuesOfRows = Rline.split(","); // .split splits the line that is read into strings wherever a comma is there
				if (valuesOfRows.length == 5) // Checks if we have the 5 values we need (deliveryLocation, weatherCondition, packageSize, deliveryTime, etc..)
				{
					// Extracts each column from the valuesOfRows array using .trim to remove whitespace's 
					String deliveryLocation = valuesOfRows[0].trim();
					String weatherCondition = valuesOfRows[1].trim();
					String packageSize = valuesOfRows[2].trim();
					String deliveryTime = valuesOfRows[3].trim();
					String label = valuesOfRows[4].trim();
					
					// Creates a new Instance object with the 5 values of data
					Instance instance = new Instance(deliveryLocation, weatherCondition, packageSize, deliveryTime, label);
					instances.add(instance); // Adds the values to the instances list. 
				}
				
					
			}
		}
		
		
	}
	
	// Getter for all instances list.
    public List<Instance> getAll() {
        return instances;
    }
}

	

