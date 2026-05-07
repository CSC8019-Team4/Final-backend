package uk.ac.ncl.csc8019.team4.location;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KioskLocationTest {

    @Test
    void testNewLocationDefaultsToOpen() {
        // Arrange & Act
        KioskLocation location = new KioskLocation("Platform 3 Kiosk", "Newcastle Central");

        // Assert
        assertEquals(OperatingStatus.OPEN, location.getOperatingStatus(), 
            "A new kiosk should always start with OPEN status.");
        assertEquals("Platform 3 Kiosk", location.getName());
    }

    @Test
    void testSetOperatingStatus() {
        // Arrange
        KioskLocation location = new KioskLocation("Platform 3 Kiosk", "Newcastle Central");

        // Act
        location.setOperatingStatus(OperatingStatus.CLOSED);

        // Assert
        assertEquals(OperatingStatus.CLOSED, location.getOperatingStatus());
    }
}
