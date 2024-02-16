
/*
 * This is the abstract class for all vehicles
 * It contains the basic information of a vehicle
 * It also contains the abstract method calculateParkingFee()
 * which will be implemented in the subclasses
 */
public abstract class Vehicle {
    protected String licensePlate; // license plate number
    protected int timeParked = 0;  // time parked in minutes
    protected int baseFee;       // base fee
    protected int additionalFee; // additional fee
    protected boolean occupied = false;  // Storing if the parking space occupied

    // Constructors
    public Vehicle() {
    }

    // Getters and setters
    public String getLicensePlate() {
        return licensePlate;
    }

    public int getTimeParked() {
        return timeParked;
    }

    public int getBaseFee() {
        return baseFee;
    }

    public int getAdditionalFee() {
        return additionalFee;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setTimeParked(int timeParked) {
        this.timeParked = timeParked;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setBaseFee(int baseFee) {
        this.baseFee = baseFee;
    }

    public void setAdditionalFee(int additionalFee) {
        this.additionalFee = additionalFee;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    // Abstract method
    public abstract int calculateParkingFee();
}
