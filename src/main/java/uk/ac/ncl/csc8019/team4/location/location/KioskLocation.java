package uk.ac.ncl.csc8019.team4.location;

import jakarta.persistence.*;

/**
 * Entity class representing a physical kiosk location where food orders can be placed.
 * Each kiosk is located at a specific train station and can be in various operating states.
 * Maps to the kiosk_locations table in the database.
 */
@Entity
@Table(name = "kiosk_locations")
public class KioskLocation {

    /** Unique identifier for the kiosk location. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Descriptive name of the kiosk location (e.g., "Platform 3 Kiosk"). Maximum 120 characters. */
    @Column(nullable = false, length = 120)
    private String name;

    /** Name of the train station where this kiosk is located. Maximum 120 characters. */
    @Column(name = "station_name", nullable = false, length = 120)
    private String stationName;

    /** Current operating status of the kiosk (e.g., OPEN, CLOSED, MAINTENANCE). Defaults to OPEN. */
    @Enumerated(EnumType.STRING)
    @Column(name = "operating_status", nullable = false, length = 20)
    private OperatingStatus operatingStatus = OperatingStatus.OPEN;

    /**
     * No-argument constructor for JPA entity instantiation.
     * Required by JPA specification for proxy generation.
     */
    protected KioskLocation() {}

    /**
     * Constructs a new KioskLocation with the specified name and station.
     * The operating status is initialized to OPEN by default.
     * 
     * @param name The descriptive name of the kiosk location
     * @param stationName The name of the train station where this kiosk is located
     */
    public KioskLocation(String name, String stationName) {
        this.name = name;
        this.stationName = stationName;
    }

    /**
     * Gets the unique identifier of the kiosk location.
     * 
     * @return The kiosk location ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the descriptive name of the kiosk location.
     * 
     * @return The name of the kiosk (e.g., "Platform 3 Kiosk")
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the name of the train station where this kiosk is located.
     * 
     * @return The station name
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * Gets the current operating status of the kiosk.
     * 
     * @return The operating status (OPEN, CLOSED, MAINTENANCE, etc.)
     */
    public OperatingStatus getOperatingStatus() {
        return operatingStatus;
    }

    /**
     * Sets the operating status of the kiosk.
     * 
     * @param operatingStatus The new operating status
     */
    public void setOperatingStatus(OperatingStatus operatingStatus) {
        this.operatingStatus = operatingStatus;
    }
}
