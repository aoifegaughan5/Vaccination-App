import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class VaccinationApp {

	public static void main(String[] args) {
	    // List to store the patients' data using Table 1
		// Sample patients data
	    List<Patient> patients = new ArrayList<>();
	    patients.add(new Patient("Olivia", "Walsh", "Female", 70, "Birmingham", "05/07/2024"));
	    patients.add(new Patient("Andrew", "Smith", "Male", 48, "Birmingham", "05/07/2024"));
	    patients.add(new Patient("Emily", "Taylor", "Female", 21, "Nottingham", "06/07/2024"));
	    patients.add(new Patient("Sophie", "Evans", "Female", 38, "Nottingham", "13/07/2024"));
	    patients.add(new Patient("Isla", "Jones", "Female", 23, "York", "01/07/2024"));
	    patients.add(new Patient("Harry", "Brown", "Male", 82, "York", "15/07/2024"));
	    patients.add(new Patient("Oscar", "Roberts", "Male", 46, "Manchester", "10/07/2024"));
	    patients.add(new Patient("George", "Davies", "Male", 45, "Manchester", "11/07/2024"));

	    // Vaccination types using Table 2
	    // Vaccination data provided to us
	    List<VaccinationType> vaccinations = new ArrayList<>();
	    vaccinations.add(new VaccinationType("Vaccination 1", "train the immune system", 51, Integer.MAX_VALUE));
	    vaccinations.add(new VaccinationType("Vaccination 2", "produce a specific viral protein", 41, 50));
	    vaccinations.add(new VaccinationType("Vaccination 3", "builds immune memory", 20, 40));
	    
        Scanner scanner = new Scanner(System.in);

        // Menu-driven interface that allows the programme to interact with the user
        while (true) {
            System.out.println("\n ***** Vaccine Record Portal ***** ");
            System.out.println("1. Input Patients Data");
            System.out.println("2. Input Vaccination Types");
            System.out.println("3. Display Patients in Birmingham");
            System.out.println("4. Display Female Patients");
            System.out.println("5. Sort Patients by Date");
            System.out.println("6. Sort Patients by Age (oldest first)");
            System.out.println("7. Search Patients by City");
            System.out.println("8. Search Patients with Vaccination Type 2 and Sort by Age");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline (avoids issues when reading)
         // This handles the user's menu choice input and calls the corresponding methods
            switch (choice) {
                case 1:
                    inputPatientsData(patients, scanner);
                    break;
                case 2:
                    inputVaccinationTypes(vaccinations, scanner);
                    break;
                case 3:
                    displayBirminghamPatients(patients, vaccinations);
                    break;
                case 4:
                    displayFemalePatients(patients, vaccinations);
                    break;
                case 5:
                    sortByDate(patients);
                    break;
                case 6:
                    sortByAge(patients);
                    break;
                case 7:
                    searchByCity(patients, vaccinations, scanner);
                    break;
                case 8:
                    searchAndSortVaccinationType2(patients, vaccinations);
                    break;
                case 9:
                    System.out.println("We are now exiting program.");
                    scanner.close(); // Close the scanner
                    return; // Closing the loop in order to end the program
                default:
                    System.out.println("Invalid choice. Can you please try again.");
            }
        }
    }

 
    // Method to input patients data
    // Adds multiple patients entered by the user
    public static void inputPatientsData(List<Patient> patients, Scanner scanner) {
        System.out.print("Enter the number of patients: ");
        int numPatients = scanner.nextInt();
        scanner.nextLine(); // New Line

        // For loop to add additional/multiple patients which is based on the users input
        for (int i = 0; i < numPatients; i++) {
            System.out.println("Enter patient details:");

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Surname: ");
            String surname = scanner.nextLine();

            System.out.print("Gender: ");
            String gender = scanner.nextLine();

            System.out.print("Age: ");
            // Ensure the the user enter's a valid integer
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid age.");
                scanner.next(); // For the invalid input
            }
            int age = scanner.nextInt();
            scanner.nextLine(); // New Line character

            System.out.print("City: ");
            String city = scanner.nextLine();

            System.out.print("Date (DD/MM/YYYY): ");
            String date = scanner.nextLine();

            // Add the new patient to the list
            patients.add(new Patient(name, surname, gender, age, city, date));
            System.out.println("Patient added successfully.");
        }
    }

    // Method to input the Vaccination types
    // Adds multiple vaccination types entered by the user
    public static void inputVaccinationTypes(List<VaccinationType> vaccinations, Scanner scanner) {
        System.out.print("Enter the number of vaccination types: ");
        int numVaccinations = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        // For loop for inputting the vaccination details
        for (int i = 0; i < numVaccinations; i++) {
            System.out.println("Enter vaccination type details:");

            System.out.print("Vaccination Type: ");
            String vaccinationType = scanner.nextLine();

            System.out.print("Treatment: ");
            String treatment = scanner.nextLine();

            int minAge;
            int maxAge;

            // Loop that validates the minimum and maximum age
            while (true) {
                System.out.print("Minimum Age: ");
                minAge = scanner.nextInt();

                System.out.print("Maximum Age: ");
                maxAge = scanner.nextInt();
                scanner.nextLine(); // New Line character
                // Check if min age is not greater than max age
                if (minAge <= maxAge) {
                    break; // If valid, exit the loop
                } else {
                    System.out.println("Minimum Age cannot be greater than Maximum Age. Please try again.");
                }
            }
            // Adding vaccination type to the list
            vaccinations.add(new VaccinationType(vaccinationType, treatment, minAge, maxAge));
            System.out.println("Vaccination type added successfully.");
        }
    }


    // Method to display patients in Birmingham with vaccination type and treatment
    public static void displayBirminghamPatients(List<Patient> patients, List<VaccinationType> vaccinations) {
        System.out.println("\nPatients from Birmingham:");
        for (Patient p : patients) {
            if (p.getCity().equalsIgnoreCase("Birmingham")) {
                String vaccinationType = getVaccinationTypeForAge(p.getAge(), vaccinations);
                String treatment = getTreatmentForVaccinationType(vaccinationType, vaccinations);
                System.out.println(p.getName() + " " + p.getSurname() + " (" + vaccinationType + ") - " + treatment);
            }
        }
    }

  
 // Method to display the female patients with vaccination type
    public static void displayFemalePatients(List<Patient> patients, List<VaccinationType> vaccinations) {
        if (patients.isEmpty()) {
            System.out.println("\nNo patients available. Please enter patient data first.");
            return;
        }
        
        System.out.println("\nFemale Patients:");
        boolean hasFemalePatients = false;
        // Find female patients in patient list
        for (Patient p : patients) {
            if (p.getGender().equalsIgnoreCase("Female")) {
                String vaccinationType = getVaccinationTypeForAge(p.getAge(), vaccinations);
                System.out.println(p.getName() + " " + p.getSurname() + " (" + vaccinationType + ")");
                hasFemalePatients = true;
            }
        }

        if (!hasFemalePatients) {
            System.out.println("No female patients found.");
        }
    }


    // Method to sort patients by date with earlier dates listed first
    public static void sortByDate(List<Patient> patients) {
        patients.sort(Comparator.comparing(Patient::getDate));
        System.out.println("\nPatients sorted by date:");
        for (Patient p : patients) {
            System.out.println(p.getName() + " " + p.getSurname() + " (" + p.getDate() + ")");
        }
    }

    // Method to sort patients by age and display the minimum and maximum age
    public static void sortByAge(List<Patient> patients) {
        patients.sort(Comparator.comparingInt(Patient::getAge).reversed());
        System.out.println("\nPatients sorted by age (oldest first):");
        for (Patient p : patients) {
            System.out.println(p.getName() + " " + p.getSurname() + " (" + p.getAge() + ")");
        }
        // Display the oldest and youngest patients
        Patient oldest = patients.get(0);
        Patient youngest = patients.get(patients.size() - 1);
        System.out.println("Oldest patient: " + oldest.getName() + " " + oldest.getSurname() + " (" + oldest.getAge() + ")");
        System.out.println("Youngest patient: " + youngest.getName() + " " + youngest.getSurname() + " (" + youngest.getAge() + ")");
    }

    // Method to search for the patient by city and display vaccination details
    public static void searchByCity(List<Patient> patients, List<VaccinationType> vaccinations, Scanner scanner) {
        System.out.print("Enter the City: ");
        String city = scanner.nextLine();

        System.out.println("Patients in " + city + ":");
        for (Patient p : patients) {
            if (p.getCity().equalsIgnoreCase(city)) {
                String vaccinationType = getVaccinationTypeForAge(p.getAge(), vaccinations);
                String treatment = getTreatmentForVaccinationType(vaccinationType, vaccinations);
                System.out.println(p.getName() + " " + p.getSurname() + " " + p.getAge() + " " + vaccinationType + " " + treatment);
            }
        }
    }

    // Method to search for patients treated with Vaccination Type 2 and sort by age in descending order
    public static void searchAndSortVaccinationType2(List<Patient> patients, List<VaccinationType> vaccinations) {
        System.out.println("\nPatients treated with Vaccination Type 2 sorted by age (oldest first):");
        List<Patient> filteredPatients = new ArrayList<>();
       // Filter the patients treated with Vaccination type 2
        for (Patient p : patients) {
            String vaccinationType = getVaccinationTypeForAge(p.getAge(), vaccinations);
            if (vaccinationType.equals("Vaccination 2")) {
                filteredPatients.add(p);
            }
        }

        // Sort patients by age in descending order
        filteredPatients.sort(Comparator.comparingInt(Patient::getAge).reversed());

        // Display sorted patients
        for (Patient p : filteredPatients) {
            System.out.println(p.getName() + " " + p.getSurname() + " " + p.getGender() + " " + p.getAge() + " " + p.getCity() + " " + p.getDate());
        }
    }

    // Method to get vaccination type based on the patient's age
    public static String getVaccinationTypeForAge(int age, List<VaccinationType> vaccinations) {
        for (VaccinationType v : vaccinations) {
            if (v.isEligible(age)) {
                return v.getVaccinationType();
            }
        }
        return "No matching vaccination type found";
    }

    // Method to get treatment based on vaccination type
    public static String getTreatmentForVaccinationType(String vaccinationType, List<VaccinationType> vaccinations) {
        for (VaccinationType v : vaccinations) {
            if (v.getVaccinationType().equals(vaccinationType)) {
                return v.getTreatment();
            }
        }
        return "No matching treatment found";
    }
}
