/*
 * This class represents a bus vehicle.
 * It contains Base fee and additional fee per a minute, and the method to calculate total fee
 */
public class Bus extends Vehicle {
    // Constructor. Set base fee and additional fee
    public Bus() {
        super();
        this.baseFee = 3000;
        this.additionalFee = 100;
    }

    /* 
     * Implement the abstract method calculateParkingFee() in the superclass
     * This method will be called in the main method
     */
    @Override
    public int calculateParkingFee() {
        return this.baseFee + this.additionalFee * this.timeParked;
    }
}
