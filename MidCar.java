/*
 * MidCar class
 * This class is a subclass of Vehicle
 * It contains Base fee and additional fee per a minute, and the method to calculate total fee
 */
public class MidCar extends Vehicle {
    // Constructor. Set base fee and additional fee
    public MidCar() {
        super();
        this.baseFee = 2000;
        this.additionalFee = 50;
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
