package uk.ac.ncl.csc8019.team4.location;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import uk.ac.ncl.csc8019.team4.auth.StaffLock;

/**
 * REST Controller for managing kiosk locations.
 * Provides endpoints to retrieve open kiosk locations and update their operating status.
 * All endpoints are mapped under /api/locations.
 */
@RestController
@RequestMapping("/api/locations")
public class KioskLocationController {

    /** Repository for accessing and persisting kiosk location data. */
    private final KioskLocationRepository locations;

    /**
     * Constructor for KioskLocationController.
     * 
     * @param locations Repository for kiosk location data access
     */
    public KioskLocationController(KioskLocationRepository locations) {
        this.locations = locations;
    }

    /**
     * Retrieves all kiosk locations with OPEN operating status.
     * Returns locations ordered by ID in ascending order for consistent ordering.
     * This endpoint is public and accessible to all authenticated users.
     * 
     * @return A list of all open KioskLocation entities, ordered by ID
     */
    @GetMapping
    public List<KioskLocation> listOpenLocations() {
        // Query the database for all locations with OPEN status, ordered by ID
        return locations.findByOperatingStatusOrderByIdAsc(OperatingStatus.OPEN);
    }

    /**
     * Updates the operating status of a kiosk location.
     * Restricted to staff members only (enforced by @StaffLock annotation).
     * Allows staff to open, close, or mark locations as under maintenance.
     * 
     * @param id The ID of the kiosk location to update
     * @param status The new operating status (OPEN, CLOSED, MAINTENANCE, etc.)
     * @return The updated KioskLocation entity with the new status
     * @throws ResponseStatusException with HTTP 404 NOT_FOUND if the location does not exist
     * @throws AccessDeniedException if the request is not made by a staff member
     */
    @PatchMapping("/{id}/status")
    @StaffLock
    public KioskLocation updateStatus(@PathVariable Long id, @RequestParam OperatingStatus status) {
        // Find the kiosk location by ID, throwing 404 if not found
        KioskLocation location = locations
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found: " + id));
        
        // Update the operating status
        location.setOperatingStatus(status);
        
        // Persist the updated location and return it
        return locations.save(location);
    }
}
