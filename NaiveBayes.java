package project;

import java.util.*;


public class NaiveBayes {
    // Label counts
    int yesCount = 0;
    int noCount = 0;

    // Feature counts for each label
    // Location
    int urban_yes = 0;
    int urban_no = 0;
    int rural_yes = 0;
    int rural_no = 0;

    // Weather
    int clear_yes = 0;
    int clear_no = 0;
    int stormy_yes = 0;
    int stormy_no = 0;

    // Package size
    int small_yes = 0;
    int small_no = 0;
    int large_yes = 0;
    int large_no = 0;

    // Time
    int morning_yes = 0;
    int morning_no = 0;
    int evening_yes = 0;
    int evening_no = 0;

    int trainedAmount = 0;
    
    // Manually hardcoding my data to predict, every calculation is different
    public String staticPredict(String location, String weather, String size, String time) {
        int staticYes = 121;
        int staticNo = 79;
        double yesScore = (double) staticYes / (staticYes + staticNo);
        double noScore = (double) staticNo / (staticYes + staticNo);

        if (location.equals("Urban") && weather.equals("Stormy") && size.equals("Small") && time.equals("Evening")) {
            yesScore = yesScore * (0 + 1.0) / (staticYes + 2.0);
            noScore = noScore * (14 + 1.0) / (staticNo + 2.0);
        } 
        else if (location.equals("Urban") && weather.equals("Stormy") && size.equals("Small") && time.equals("Morning")) {
            yesScore = yesScore *  (10 + 1.0) / (staticYes + 2.0);
            noScore = noScore * (2 + 1.0) / (staticNo + 2.0);
        } 
        else if (location.equals("Urban") && weather.equals("Stormy") && size.equals("Large") && time.equals("Evening")) {
            yesScore = yesScore * (0 + 1.0) / (staticYes + 2.0);
            noScore = noScore * (12 + 1.0) / (staticNo + 2.0);
        } 
        else if (location.equals("Urban") && weather.equals("Stormy") && size.equals("Large") && time.equals("Morning")) {
            yesScore = yesScore * (7 + 1.0) / (staticYes + 2.0);
            noScore = noScore * (5 + 1.0) / (staticNo + 2.0);
        } 
        else if (location.equals("Urban") && weather.equals("Clear") && size.equals("Small") && time.equals("Evening")) {
            yesScore = yesScore * (12 + 1.0) / (staticYes + 2.0);
            noScore = noScore * (0 + 1.0) / (staticNo + 2.0);
        } 
        else if (location.equals("Urban") && weather.equals("Clear") && size.equals("Small") && time.equals("Morning")) {
            yesScore = yesScore * (13 + 1.0) / (staticYes + 2.0);
            noScore = noScore * (0 + 1.0) / (staticNo + 2.0);
        } 
        else if (location.equals("Urban") && weather.equals("Clear") && size.equals("Large") && time.equals("Evening")) {
            yesScore = yesScore * (12 + 1.0) / (staticYes + 2.0);
            noScore = noScore * (0 + 1.0) / (staticNo + 2.0);
        } 
        else if (location.equals("Urban") && weather.equals("Clear") && size.equals("Large") && time.equals("Morning")) {
            yesScore = yesScore * (12 + 1.0) / (staticYes + 2.0);
            noScore = noScore * (0 + 1.0) / (staticNo + 2.0);
        } 
        else if (location.equals("Rural") && weather.equals("Stormy") && size.equals("Small") && time.equals("Evening")) {
            yesScore = yesScore * (0 + 1.0) / (staticYes + 2.0);
            noScore = noScore * (12 + 1.0) / (staticNo + 2.0);
        } 
        else if (location.equals("Rural") && weather.equals("Stormy") && size.equals("Small") && time.equals("Morning")) {
            yesScore = yesScore * (11 + 1.0) / (staticYes + 2.0);
            noScore = noScore * (1 + 1.0) / (staticNo + 2.0);
        } 
        else if (location.equals("Rural") && weather.equals("Stormy") && size.equals("Large") && time.equals("Evening")) {
            yesScore = yesScore * (0 + 1.0) / (staticYes + 2.0);
            noScore = yesScore * (12 + 1.0) / (staticNo + 2.0);
        } 
        else if (location.equals("Rural") && weather.equals("Stormy") && size.equals("Large") && time.equals("Morning")) {
            yesScore = yesScore * (4 + 1.0) / (staticYes + 2.0);
            noScore = noScore * (4 + 1.0) / (staticNo + 2.0);
        } 
        else if (location.equals("Rural") && weather.equals("Clear") && size.equals("Small") && time.equals("Evening")) {
            yesScore = yesScore * (8 + 1.0) / (staticYes + 2.0);
            noScore = noScore * (0 + 1.0) / (staticNo + 2.0);
        }
        else if (location.equals("Rural") && weather.equals("Clear") && size.equals("Small") && time.equals("Morning")) {
            yesScore = yesScore * (7 + 1.0) / (staticYes + 2.0);
            noScore = noScore * (6 + 1.0) / (staticNo + 2.0);
        } 
        else if (location.equals("Rural") && weather.equals("Clear") && size.equals("Large") && time.equals("Evening")) {
            yesScore = yesScore * (6 + 1.0) / (staticYes + 2.0);
            noScore = noScore * (6 + 1.0) / (staticNo + 2.0);
        } 
        else if (location.equals("Rural") && weather.equals("Clear") && size.equals("Large") && time.equals("Morning")) {
            yesScore = yesScore * (13 + 1.0) / (staticYes + 2.0);
            noScore = noScore * (1 + 1.0) / (staticNo + 2.0);
        }

        return (yesScore > noScore) ? "yes" : "no";
    }


    public void training(List<Instance> data) {
        for (Instance instance : data) {
        	// Storing each feature and label 
            String location = instance.getLocation();
            String weather = instance.getWeather();
            String packageSize = instance.getSize();
            String time = instance.getTime();
            String label = instance.getLabel(); 
            
            // Checks just for Yes through each feature
            if (label.equals("yes")) {
                yesCount++;
                if (location.equals("Urban"))
                    urban_yes++;
                else if (location.equals("Rural"))
                    rural_yes++;

                if (weather.equals("Clear"))
                    clear_yes++;
                else if (weather.equals("Stormy"))
                    stormy_yes++;

                if (packageSize.equals("Small"))
                    small_yes++;
                else if (packageSize.equals("Large"))
                    large_yes++;

                if (time.equals("Morning"))
                    morning_yes++;
                else if (time.equals("Evening"))
                    evening_yes++;
            } 
            
            // Checks just for no through each feature
            else if (label.equals("no")) {
                noCount++;
                if (location.equals("Urban"))
                    urban_no++;
                else if (location.equals("Rural"))
                    rural_no++;

                if (weather.equals("Clear"))
                    clear_no++;
                else if (weather.equals("Stormy"))
                    stormy_no++;

                if (packageSize.equals("Small"))
                    small_no++;
                else if (packageSize.equals("Large"))
                    large_no++;

                if (time.equals("Morning"))
                    morning_no++;
                else if (time.equals("Evening"))
                    evening_no++;
            }

            trainedAmount++; // keeps track of the trained instances 
        }
    }

    public String predict(String location, String weather, String packageSize, String time) 
    {
    	// We pass our attributes to predict it 
        double pYes = (double) yesCount / trainedAmount;
        double pNo = (double) noCount / trainedAmount;

        // Starting with the probabilities of Yes and No
        double yesScore = pYes;
        double noScore = pNo;

        // Location
        // Checks if Urban is more common in Yes or No 
        if (location.equalsIgnoreCase("Urban")) 
        {
        	// Need this called Laplace smoothing otherwise my data gets messed up and predicts unaccurate predictions
        	// Without this it predicted for best outcome (Urban, Clear, Small, Morning) a No.
        	// + 1 for we seen it once
        	// + 2 because every section has 2 sections (Clear or Stormy) or whatever on the section
            yesScore = yesScore* (urban_yes + 1.0) / (yesCount + 2.0); 
            noScore = noScore * (urban_no + 1.0) / (noCount + 2.0);
        } 
        // Checks if Rural is more common in Yes or No
        else if (location.equalsIgnoreCase("Rural")) 
        {
            yesScore = yesScore* (rural_yes + 1.0) / (yesCount + 2.0);
            noScore = noScore* (rural_no + 1.0) / (noCount + 2.0);
        }

        // Weather
        // Checks if Clear is more common in yes or no 
        if (weather.equalsIgnoreCase("Clear")) 
        {
            yesScore = yesScore * (clear_yes + 1.0) / (yesCount + 2.0);
            noScore = noScore * (clear_no + 1.0) / (noCount + 2.0);
        } 
        // Checks if Stormy is more common in yes or no
        else if (weather.equalsIgnoreCase("Stormy")) 
        {
            yesScore = yesScore * (stormy_yes + 1.0) / (yesCount + 2.0);
            noScore = noScore * (stormy_no + 1.0) / (noCount + 2.0);
        }

        // Package Size
        // Checks if Small is more common in yes or no 
        if (packageSize.equalsIgnoreCase("Small")) 
        {
            yesScore = yesScore * (small_yes + 1.0) / (yesCount + 2.0);
            noScore = noScore * (small_no + 1.0) / (noCount + 2.0);
        } 
        // Checks if Large is more common in yes or no 
        else if (packageSize.equalsIgnoreCase("Large")) {
            yesScore = yesScore * (large_yes + 1.0) / (yesCount + 2.0);
            noScore = noScore * (large_no + 1.0) / (noCount + 2.0);
        }

        // Time
        // Checks if Morning is more common in yes or no
        if (time.equalsIgnoreCase("Morning")) 
        {
            yesScore = yesScore * (morning_yes + 1.0) / (yesCount + 2.0);
            noScore = noScore * (morning_no + 1.0) / (noCount + 2.0);
        } 
        // Checks if Evening is more common in yes or no
        else if (time.equalsIgnoreCase("Evening")) 
        {
            yesScore = yesScore* (evening_yes + 1.0) / (yesCount + 2.0);
            noScore = noScore* (evening_no + 1.0) / (noCount + 2.0);
        }
        
        // After all the calculations of finding out which one is more common it sees
        // is yes or no more common (is the probability of Yes happening higher than No) if so then the prediction is Yes
        return (yesScore > noScore) ? "yes" : "no";
        
 
    }
    
    
  }
