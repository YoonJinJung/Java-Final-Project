/*
 * This class is a subclass of Vehicle. 
 * It contains Base fee and additional fee per a minute, and the method to calculate total fee
 */
public class Charger extends Vehicle {
    // Constructor. Set base fee and additional fee
    public Charger() {
        super();
        this.baseFee = 2000;
        this.additionalFee = 30;
    }

    /* 
     * Implement the abstract method calculateParkingFee() in the superclass
     * This method will be called in the main method
     */
    @Override
    public int calculateParkingFee() {
        return (this.baseFee + this.additionalFee * this.timeParked) + (100 * this.timeParked);
    }
}
