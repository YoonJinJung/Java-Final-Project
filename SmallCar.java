/*
 * SmallCar class
 * This class is a subclass of Vehicle
 * It contains Base fee and additional fee per a minute, and the method to calculate total fee
 */
public class SmallCar extends Vehicle {
    // Constructor. Set base fee and additional fee
    public SmallCar() {
        super();
        this.baseFee = 1000;
        this.additionalFee = 40;
    }

    /* 
     * Implement the abstract method calculateParkingFee() in the superclass
     * This method will be called in the main method
     */ 
    @Override
    public int calculateParkingFee() {
        return baseFee + additionalFee * this.timeParked;
    }
}
