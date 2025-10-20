// Name: Dheeraj Bhardwaj
// Student ID: s4187790
// SEF: Assignment 4

package Flight;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class FlightSearchTest {

    private Flight.FlightSearch search;

    @BeforeEach
    void setUp() {
        search = new Flight.FlightSearch();
    }

    @Test
    void testValidFlightSearch() {
        assertTrue(search.runFlightSearch("25/12/2025", "mel", false, "30/12/2025", "pvg", "economy", 2, 1, 1));
    }

    @Test
    void testPassengerBoundary() {
        assertFalse(search.runFlightSearch("25/12/2025", "mel", false, "30/12/2025", "pvg", "economy", 0, 0, 0));
        assertTrue(search.runFlightSearch("25/12/2025", "mel", false, "30/12/2025", "pvg", "economy", 9, 0, 0));
    }

    @Test
    void testChildrenInFirstClass() {
        assertFalse(search.runFlightSearch("25/12/2025", "mel", false, "30/12/2025", "pvg", "first", 1, 1, 0));
    }

    @Test
    void testInfantsInBusinessClass() {
        assertFalse(search.runFlightSearch("25/12/2025", "mel", false, "30/12/2025", "pvg", "business", 1, 0, 1));
    }

    @Test
    void testChildrenToAdultRatio() {
        assertFalse(search.runFlightSearch("25/12/2025", "mel", false, "30/12/2025", "pvg", "economy", 1, 3, 0));
    }

    @Test
    void testInfantToAdultRatio() {
        assertFalse(search.runFlightSearch("25/12/2025", "mel", false, "30/12/2025", "pvg", "economy", 1, 0, 2));
    }

    @Test
    void testPastDepartureDate() {
        assertFalse(search.runFlightSearch("01/01/2020", "mel", false, "05/01/2020", "pvg", "economy", 1, 0, 0));
    }

    @Test
    void testInvalidDateFormat() {
        assertFalse(search.runFlightSearch("2025/12/25", "mel", false, "2025/12/30", "pvg", "economy", 1, 0, 0));
    }

    @Test
    void testReturnBeforeDeparture() {
        assertFalse(search.runFlightSearch("30/12/2025", "mel", false, "25/12/2025", "pvg", "economy", 1, 0, 0));
    }

    @Test
    void testInvalidSeatingClass() {
        assertFalse(search.runFlightSearch("25/12/2025", "mel", false, "30/12/2025", "pvg", "gold", 1, 0, 0));
    }

    @Test
    void testEmergencyRowNonEconomy() {
        assertFalse(search.runFlightSearch("25/12/2025", "mel", true, "30/12/2025", "pvg", "first", 1, 0, 0));
    }

    @Test
    void testInvalidAirports() {
        assertFalse(search.runFlightSearch("25/12/2025", "xyz", false, "30/12/2025", "pvg", "economy", 1, 0, 0));
    }
}
