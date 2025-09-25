package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class GUIDelivery {
    private static NaiveBayes predictTraining;
    private static boolean isModelTrained = false;

    public static void main(String[] args) {
        predictTraining = new NaiveBayes();

        Dataset dataset = new Dataset();
        try {
            dataset.loadFromCSV("isParcelDelivered_dataset.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        JFrame frame = new JFrame("Naive Bayes Classifier with Figures");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800); // Give it a size for the layout
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(5, 2, 10, 10)); 
        String[] locations = {"Urban", "Rural"};
        String[] weatherOptions = {"Clear", "Stormy"};
        String[] sizeOptions = {"Small", "Large"};
        String[] timeOptions = {"Morning", "Evening"};
        
        // We make drop down boxes for each feature
        JComboBox<String> locationBox = new JComboBox<>(locations);
        JComboBox<String> weatherBox = new JComboBox<>(weatherOptions);
        JComboBox<String> sizeBox = new JComboBox<>(sizeOptions);
        JComboBox<String> timeBox = new JComboBox<>(timeOptions);
        
        // Buttons for prediction and Train classifer
        JButton predictButton = new JButton("Predict");
        JButton trainClassifierButton = new JButton("Train Classifier");
        
        
        // Adding it to the application or GUI
        topPanel.add(new JLabel("Delivery Location:"));
        topPanel.add(locationBox);
        topPanel.add(new JLabel("Weather Condition:"));
        topPanel.add(weatherBox);
        topPanel.add(new JLabel("Package Size:"));
        topPanel.add(sizeBox);
        topPanel.add(new JLabel("Delivery Time:"));
        topPanel.add(timeBox);
        topPanel.add(trainClassifierButton);
        topPanel.add(predictButton);
        
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        String[] newLocations = {"Urban", "Rural"};
        String[] newWeatherOptions = {"Clear", "Stormy"};
        String[] newSizeOptions = {"Small", "Large"};
        String[] newTimeOptions = {"Morning", "Evening"};
        String[] newLabelOptions = {"yes", "no"};
        
        // These are dropdowns for adding new Row
        JComboBox<String> newLocationBox = new JComboBox<>(newLocations);
        JComboBox<String> newWeatherBox = new JComboBox<>(newWeatherOptions);
        JComboBox<String> newSizeBox = new JComboBox<>(newSizeOptions);
        JComboBox<String> newTimeBox = new JComboBox<>(newTimeOptions);
        JComboBox<String> newLabelBox = new JComboBox<>(newLabelOptions);

        JButton addButton = new JButton("Add New Data");
        JButton testAccuracyButton = new JButton("Test Accuracy");
        
        // Adding it to the bottom of the GUI
        bottomPanel.add(new JLabel("New Location:"));
        bottomPanel.add(newLocationBox);
        bottomPanel.add(new JLabel("New Weather:"));
        bottomPanel.add(newWeatherBox);
        bottomPanel.add(new JLabel("New Size:"));
        bottomPanel.add(newSizeBox);
        bottomPanel.add(new JLabel("New Time:"));
        bottomPanel.add(newTimeBox);
        bottomPanel.add(new JLabel("New Label (Yes/No):"));
        bottomPanel.add(newLabelBox);
        bottomPanel.add(addButton);
        bottomPanel.add(testAccuracyButton);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.CENTER);

        predictButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String location = (String) locationBox.getSelectedItem();
                String weather = (String) weatherBox.getSelectedItem();
                String size = (String) sizeBox.getSelectedItem();
                String time = (String) timeBox.getSelectedItem();
                
                // Checks if the train classifer button is pressed if not we predict it using our manual hardcoded values
                String prediction;
                if (!isModelTrained) {
                    prediction = predictTraining.staticPredict(location, weather, size, time);
                } else {
                    prediction = predictTraining.predict(location, weather, size, time);
                }
                
                // Displays wether it will be delivered or not 
                if (prediction.equals("yes")) {
                    JOptionPane.showMessageDialog(frame,
                            "Prediction: " + prediction + " it will be delivered",
                            "Prediction Result",
                            JOptionPane.INFORMATION_MESSAGE);
                } 
                else if (prediction.equals("no")) {
                    JOptionPane.showMessageDialog(frame,
                            "Prediction: " + prediction + " it will not be delivered",
                            "Prediction Result",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        // When the user clicks train classifier it trains it using the non hardcoded values 
        trainClassifierButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    predictTraining = new NaiveBayes();
                    predictTraining.training(dataset.getAll());
                    isModelTrained = true;
                    JOptionPane.showMessageDialog(frame, "Classifier trained from dataset successfully!",
                            "Training Complete", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error training classifier.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Button to add a new Row to the 
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get values from dropdowns
                    String newLocation = (String) newLocationBox.getSelectedItem();
                    String newWeather = (String) newWeatherBox.getSelectedItem();
                    String newSize = (String) newSizeBox.getSelectedItem();
                    String newTime = (String) newTimeBox.getSelectedItem();
                    String newLabel = (String) newLabelBox.getSelectedItem();

                    // Creates a new Instance with the newly added row features and label
                    Instance newInstance = new Instance(newLocation, newWeather, newSize, newTime, newLabel);
                    dataset.getAll().add(newInstance);

                    // We append it to our CSV File 
                    FileWriter writer = new FileWriter("isParcelDelivered_dataset.csv", true); 
                    writer.write("\n" + newLocation + "," + newWeather + "," + newSize + "," + newTime + "," + newLabel);
                    writer.close();
                    
                    JOptionPane.showMessageDialog(frame,
                            "New data added & saved to file!\nRow Added: " +
                                    newLocation + " ," + newWeather + " ," + newSize + " ," + newTime + " ," + newLabel,
                            "Data Added",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error adding new data to file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // When the user clicks test accuracy it grabs the return message from testModelAccuracy
        testAccuracyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String resultMessage = testModelAccuracy(dataset);

                JOptionPane.showMessageDialog(frame,
                        resultMessage,
                        "Test Accuracy Result",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.setVisible(true); // Displays the GUI
    }
    
    // This is a method to check the accuracy but we use 75/25 (150 rows and 50 rows for testing) 
    private static String testModelAccuracy(Dataset dataset) {
        try {
            List<Instance> instances = dataset.getAll();

            int trainSize = (int) (instances.size() * 0.75);

            int correctPredictions = 0;
            int totalPredictions = 0;
            
            // Our new Model
            predictTraining = new NaiveBayes();
            
            // The training dataset
            // Gets 150 rows
            List<Instance> trainSet = new ArrayList<>();
            for (int i = 0; i < trainSize; i++) {
                trainSet.add(instances.get(i));
            }
            predictTraining.training(trainSet);
            
            // Gets 50 rows
            List<Instance> testSet = new ArrayList<>();
            for (int i = trainSize; i < instances.size(); i++) {
                testSet.add(instances.get(i));
            }
            // For loop that makes predictions using our predicting method and counts the correct ones
            for (Instance instance : testSet) {
                String predicted = predictTraining.predict(instance.getLocation(), instance.getWeather(),
                        instance.getSize(), instance.getTime());

                if (predicted.equals(instance.getLabel())) {
                    correctPredictions++;
                }
                totalPredictions++;
            }
            
            // Calculates our percentage for us 
            double accuracy = (double) correctPredictions / totalPredictions * 100;

            return "Correct Predictions: " + correctPredictions + "/" + totalPredictions + " (" + accuracy + "%";
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error during accuracy testing.", "Error", JOptionPane.ERROR_MESSAGE);
            return "Error during accuracy testing.";
        }
    }
}
