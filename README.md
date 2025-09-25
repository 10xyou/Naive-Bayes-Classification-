# Naive-Bayes-Classification-

# Delivery Predictor - Naive Bayes Classifier (Java GUI)

**Video Demo:** [Watch here](https://streamable.com/ba401l)  

## Project Overview
This Java application is a machine learning module that predicts whether a parcel will be delivered based on certain features. The predictor uses a **Naive Bayes Classifier** and includes a GUI for interaction.  

### Features
The predictor is trained on the following input values:  
- **Delivery Location:** Urban, Rural  
- **Weather Condition:** Clear, Stormy  
- **Package Size:** Small, Large  
- **Delivery Time:** Morning, Evening  

The application supports multiple levels of functionality:  
1. **Level 1:** Hardcoded manual predictions  
2. **Level 2:** Dynamic training from a CSV dataset and predictions  
3. **Level 3:** Adding new rows to the dataset and retraining  
4. **Level 4:** Accuracy testing using a 75/25 data split (150 training rows, 50 test rows)  

---

## Dataset
The dataset is stored in a CSV file:  
**`isParcelDelivered_dataset.csv`**  

### Features
- `deliveryLocation`  
- `weatherCondition`  
- `packageSize`  
- `deliveryTime`  

### Label
- `yes` or `no` (Was the parcel delivered?)  

### Frequency Table
| Delivery Location | Weather | Size  | Time    | Yes Count | No Count |
|-----------------|--------|-------|---------|----------|---------|
| Urban           | Stormy | Small | Evening | 0        | 14      |
| Urban           | Stormy | Small | Morning | 10       | 2       |
| Urban           | Stormy | Large | Evening | 0        | 12      |
| Urban           | Stormy | Large | Morning | 7        | 5       |
| Urban           | Clear  | Small | Evening | 12       | 0       |
| Urban           | Clear  | Small | Morning | 13       | 0       |
| Urban           | Clear  | Large | Evening | 12       | 0       |
| Urban           | Clear  | Large | Morning | 12       | 0       |
| Rural           | Stormy | Small | Evening | 0        | 12      |
| Rural           | Stormy | Small | Morning | 11       | 1       |
| Rural           | Stormy | Large | Evening | 0        | 12      |
| Rural           | Stormy | Large | Morning | 5        | 7       |
| Rural           | Clear  | Small | Evening | 14       | 0       |
| Rural           | Clear  | Small | Morning | 5        | 7       |
| Rural           | Clear  | Large | Evening | 6        | 7       |
| Rural           | Clear  | Large | Morning | 14       | 0       |

---

## Class List
- **GUIDelivery.java:** Main GUI class using Swing components. Allows prediction, training, new data entry, and testing accuracy.  
- **NaiveBayes.java:** Implements training and predicting. Supports static (hardcoded) and dynamic training from CSV.  
- **Dataset.java:** Reads the CSV file and stores data in a manageable list.  
- **Instance.java:** Provides getters and setters for dataset instances.  

---

## Functionality Implemented ✅
| Feature | Description |
|---------|-------------|
| Level 1 | Manual frequency table and hardcoded prediction logic |
| Level 2 | Predicts by training the model dynamically from CSV |
| Level 3 | Add new rows to dataset via GUI and save to CSV |
| Level 4 | Calculates accuracy using 150 training rows and 50 test rows |

---

## Future Improvements 💡
- Improve GUI aesthetics and layout (color themes, panels)  
- Display probability values for predictions  
- Enhance training and prediction accuracy  

---

## How to Run
1. Save `isParcelDelivered_dataset.csv` in your project directory.  
2. Right-click `GUIDelivery.java` -> Run as -> Java Application.  
