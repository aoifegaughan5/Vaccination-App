// Class for the patient with attributes such as name, surname, gender, age
public class Patient {
	// Attributes to store patients details
    private String name; // Storing the patient's first name
    private String surname; // Storing the patient's surname
    private String gender; // Storing the patient's gender
    private int age; // Storing the patient's age
    private String city; // Storing the patient's city
    private String date; // Storing the date patient's appoinment

    // Constructor to initialize the patient object with the attributes that we have been provided
    public Patient(String name, String surname, String gender, int age, String city, String date) {
        this.name = name; // Assigning name
        this.surname = surname; // Assigning surname
        this.gender = gender;// Assigning gender
        this.age = age; // Assigning age
        this.city = city;// Assigning city
        this.date = date; // Assigning date
    }

    // Getters and setters to access and also modify the attributes
    
    // Getter for name to returns the patient's name
    public String getName() {
        return name;
    }
    // Setter method to update patient's name
    public void setName(String name) {
        this.name = name;
    }
    // Getter for surname to return the patient's surname
    public String getSurname() {
        return surname;
    }
    // Setter method to update the patient's surname
    public void setSurname(String surname) {
        this.surname = surname;
    }
    // Getter for gender
    public String getGender() {
        return gender;
    }
    // Setter method to updated gender
    public void setGender(String gender) {
        this.gender = gender;
    }
    // Getter for age
    public int getAge() {
        return age;
    }
    // Setter to update age
    public void setAge(int age) {
        this.age = age;
    }
    // Getter for city
    public String getCity() {
        return city;
    }
    // Setter to update city
    public void setCity(String city) {
        this.city = city;
    }
    // Getter for date
    public String getDate() {
        return date;
    }
    // Setter to update date
    public void setDate(String date) {
        this.date = date;
    }
}
   

