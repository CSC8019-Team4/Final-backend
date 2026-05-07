package uk.ac.ncl.csc8019.team4.location;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing KioskLocation entities from the database.
 * Provides CRUD operations inherited from JpaRepository plus custom query methods
 * for filtering kiosk locations by operating status.
 */
public interface KioskLocationRepository extends JpaRepository<KioskLocation, Long> {

    /**
     * Retrieves all kiosk locations with the specified operating status.
     * Results are ordered by ID in ascending order for consistent ordering.
     * 
     * @param status The operating status to filter by (e.g., OPEN, CLOSED, MAINTENANCE)
     * @return A list of KioskLocation entities matching the status, ordered by ID
     */
    List<KioskLocation> findByOperatingStatusOrderByIdAsc(OperatingStatus status);

    /**
     * Retrieves the first (lowest ID) kiosk location with the specified operating status.
     * Useful for selecting a random or default kiosk from available locations.
     * 
     * @param status The operating status to filter by (e.g., OPEN, CLOSED, MAINTENANCE)
     * @return An Optional containing the first KioskLocation matching the status, or empty if none found
     */
    Optional<KioskLocation> findFirstByOperatingStatusOrderByIdAsc(OperatingStatus status);
}
