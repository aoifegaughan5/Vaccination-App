
import java.util.Scanner;

// VaccinationType class is the type of vaccination with multiple properties such as type, treatment, max and min ages
public class VaccinationType {
	// Attributes to store the vaccination type
    private String vaccinationType; // Stores name
    private String treatment; // Stores treatment type
    private int minAge; // Stores minimum age
    private int maxAge; // // Stores maximum age

    
    // Shared scanner instance (in order to avoid multiple scanners)
    private static final Scanner scanner = new Scanner(System.in);
    
    // Constructor to load the vaccination object with the provided details
    public VaccinationType(String vaccinationType, String treatment, int minAge, int maxAge) { 
    	// checking that the min age is not greater than max age
    if (minAge > maxAge) {
        throw new IllegalArgumentException("Minimum Age cannot be greater than the Maximum Age.");
    }
    // Assigning the types/treatments/ages to the instances variables
        this.vaccinationType = vaccinationType; 
        this.treatment = treatment;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    // Getters for attributes
    
    // Getter for vaccination type to return the name
    public String getVaccinationType() {
        return vaccinationType;
    }
    // Getter for Treatment to get description
    public String getTreatment() {
        return treatment;
    }
    // Method to check eligibility, will return true is age falls within min and max range
    public boolean isEligible(int age) {
        return age >= minAge && age <= maxAge;
    }
    
    
    // Setters to allow for modification
    
    //Setter method for vaccinationtype, to allow for updates
    public void setVaccinationType(String vaccinationType) {
        this.vaccinationType = vaccinationType;
    }
    // Setter method to allow for updated treatment description
    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    // Setter method for min age
    public void setMinAge(int minAge) {
    	// checking in the min age is valid
        while (minAge > this.maxAge) {
            System.out.println("Minimum Age cannot be greater than Maximum Age. Please try again.");
            System.out.print("Enter the Minimum Age: ");
            minAge = scanner.nextInt();
        }
        this.minAge = minAge;
    }
    // Setter method for max age

    public void setMaxAge(int maxAge) {
    	// Checking if the max age is valid
        while (maxAge < this.minAge) {
            System.out.println("Maximum Age cannot be less than Minimum Age. Please try again.");
            System.out.print("Enter the Maximum Age: ");
            maxAge = scanner.nextInt();
        }
        this.maxAge = maxAge;
    }

}

