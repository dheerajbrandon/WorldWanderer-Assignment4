// Name: Dheeraj Bhardwaj
// Student ID: s4187790
// SEF: Assignment 4


package Flight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FlightSearch {
    private String departureDate;
    private String departureAirportCode;
    private boolean emergencyRowSeating;
    private String returnDate;
    private String destinationAirportCode;
    private String seatingClass;
    private int adultPassengerCount;
    private int childPassengerCount;
    private int infantPassengerCount;

    public boolean runFlightSearch(String departureDate, String departureAirportCode, boolean emergencyRowSeating,
                                   String returnDate, String destinationAirportCode, String seatingClass,
                                   int adultPassengerCount, int childPassengerCount, int infantPassengerCount) {
        // Validate condition 1: Passenger total
        int totalPassengers = adultPassengerCount + childPassengerCount + infantPassengerCount;
        if (totalPassengers < 1 || totalPassengers > 9) return false;

        // Validate date formats (Condition 6 & 7)
        if (!validateDateFormat(departureDate) || !validateDateFormat(returnDate)) return false;
        Date depDate = parseDate(departureDate);
        Date retDate = parseDate(returnDate);
        if (depDate == null || retDate == null || retDate.before(depDate) || depDate.before(new Date())) return false;

        // Validate seating class (Condition 9)
        String[] validClasses = {"economy", "premium economy", "business", "first"};
        boolean validClass = false;
        for (String s : validClasses) {
            if (s.equals(seatingClass)) validClass = true;
        }
        if (!validClass) return false;

        // Condition 10: Only economy can have emergency row
        if (emergencyRowSeating && !seatingClass.equals("economy")) return false;

        // Condition 11: Valid airports
        String[] airports = {"syd", "mel", "lax", "cdg", "del", "pvg", "doh"};
        if (!isValidAirport(departureAirportCode, airports) || !isValidAirport(destinationAirportCode, airports)) return false;
        if (departureAirportCode.equals(destinationAirportCode)) return false;

        // Condition 2: Children cannot be in emergency row or first class
        if (childPassengerCount > 0 && (emergencyRowSeating || seatingClass.equals("first"))) return false;

        // Condition 3: Infants cannot be in emergency row or business class
        if (infantPassengerCount > 0 && (emergencyRowSeating || seatingClass.equals("business"))) return false;

        // Condition 4: 2 children per adult
        if (childPassengerCount > adultPassengerCount * 2) return false;

        // Condition 5: 1 infant per adult
        if (infantPassengerCount > adultPassengerCount) return false;

        // If all valid, initialize attributes
        this.departureDate = departureDate;
        this.departureAirportCode = departureAirportCode;
        this.emergencyRowSeating = emergencyRowSeating;
        this.returnDate = returnDate;
        this.destinationAirportCode = destinationAirportCode;
        this.seatingClass = seatingClass;
        this.adultPassengerCount = adultPassengerCount;
        this.childPassengerCount = childPassengerCount;
        this.infantPassengerCount = infantPassengerCount;

        return true;
    }

    private boolean validateDateFormat(String date) {
        return date.matches("\\d{2}/\\d{2}/\\d{4}");
    }

    private Date parseDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try { return sdf.parse(dateStr); }
        catch (ParseException e) { return null; }
    }

    private boolean isValidAirport(String code, String[] valid) {
        for (String v : valid) if (v.equals(code)) return true;
        return false;
    }


        public static void main(String[] args) {
            FlightSearch flightSearch = new FlightSearch();

            boolean result = flightSearch.runFlightSearch(
                    "25/12/2025",   // departureDate
                    "mel",          // departureAirportCode
                    false,          // emergencyRowSeating
                    "30/12/2025",   // returnDate
                    "pvg",          // destinationAirportCode
                    "economy",      // seatingClass
                    2,              // adultPassengerCount
                    1,              // childPassengerCount
                    1               // infantPassengerCount
            );

            if (result) {
                System.out.println("Flight search validation successful!");
                System.out.println("The attributes have been initialized as follows:");
                System.out.println("------------------------------------------------");
                System.out.println("Departure Date        : 25/12/2025");
                System.out.println("Return Date           : 30/12/2025");
                System.out.println("Departure Airport     : mel");
                System.out.println("Destination Airport   : pvg");
                System.out.println("Emergency Row Seating : false");
                System.out.println("Seating Class         : economy");
                System.out.println("Adult Passengers      : 2");
                System.out.println("Child Passengers      : 1");
                System.out.println("Infant Passengers     : 1");
            } else {
                System.out.println("Flight search validation failed. Attributes were not initialized.");
            }
        }


}
