// Purpose: MainFrame class implementation
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/* Enum for the stage of the program
 * MAIN: Main screen
 * CAR_IN: Car in screen
 * SLOT_SELECT: Slot select screen
 * CAR_OUT: Car out screen (Slot selecting)
 * PAYMENT: Payment screen
 * Stage Flow 1: MAIN -> CAR_IN -> SLOT_SELECT -> MAIN
 * Stage Flow 2: MAIN -> CAR_OUT -> PAYMENT -> MAIN
 */
enum Stage {
    MAIN, CAR_IN, CAR_OUT, SLOT_SELECT, PAYMENT
}

/*
 * This class is used to create the main frame of the program.
 * It contains the main method.
 */

public class MainFrame extends JFrame {
    // Declare Icon variables
    private ImageIcon carInIcon;
    private ImageIcon carOutIcon;
    private ImageIcon hereIcon;
    private ImageIcon barIcon;
    private ImageIcon coloredMidIcon;
    private ImageIcon coloredBusIcon;
    private ImageIcon coloredSmallIcon;
    private ImageIcon coloredChargerIcon;
    private ImageIcon greyMidIcon;
    private ImageIcon greyBusIcon;
    private ImageIcon greySmallIcon;
    private ImageIcon greyChargerIcon;
    private ImageIcon tableIcon;
    private ImageIcon homeIcon;
    private ImageIcon cashIcon;
    private ImageIcon creditCardIcon;
    private ImageIcon kakaoPayIcon;
    private ImageIcon naverPayIcon;

    // Declare temporary variables
    private String curLicensePlate;
    private JButton forOutBtn;

    // Set the stage to MAIN at first
    private Stage stage = Stage.MAIN;

    // Declare variables for the main panel
    private TransImg mainPanel;
    private JLabel mainLabel;
    private JButton inBtn;
    private JButton outBtn;
    
    // Declare variables for the lower panel
    private JPanel lowerPanel;
    private JLabel hereLabel;
    private ArrayList<JButton> midBtnList;
    private JLabel bar1;
    private JButton busBtn1;
    private JButton busBtn2;
    private ArrayList<JButton> smallBtnList;
    private JLabel bar2;
    private JButton midBtn6;
    private JButton midBtn7;
    private JButton chargerBtn1;
    private JButton chargerBtn2;
    private JButton homeBtn;

    // Declare variables for the parking slots
    private MidCar midCar1;
    private MidCar midCar2;
    private MidCar midCar3;
    private MidCar midCar4;
    private MidCar midCar5;
    private Bus bus1;
    private Bus bus2;
    private SmallCar smallCar1;
    private SmallCar smallCar2;
    private SmallCar smallCar3;
    private MidCar midCar6;
    private MidCar midCar7;
    private Charger charger1;
    private Charger charger2;

    // Map to store the parking slots and the corresponding vehicles.
    private Map<JButton, Vehicle> parkingLot;

    /*
     * Constructor. Set the main frame.
     * All the components are added here.
     */
    public MainFrame() {
        // Get the image icons from the assets folder
        carInIcon = new ImageIcon("assets/carIn.png");
        carOutIcon = new ImageIcon("assets/carOut.png");
        hereIcon = new ImageIcon("assets/here.png");
        barIcon = new ImageIcon("assets/yellowbar.png");
        coloredMidIcon = new ImageIcon("assets/colored_mid.png");
        coloredBusIcon = new ImageIcon("assets/colored_bus.png");
        coloredSmallIcon = new ImageIcon("assets/colored_small.png");
        coloredChargerIcon = new ImageIcon("assets/colored_charger.png");
        greyMidIcon = new ImageIcon("assets/grey_mid.png");
        greyBusIcon = new ImageIcon("assets/grey_bus.png");
        greySmallIcon = new ImageIcon("assets/grey_small.png");
        greyChargerIcon = new ImageIcon("assets/grey_charger.png");
        tableIcon = new ImageIcon("assets/table.png");
        homeIcon = new ImageIcon("assets/home.png");
        cashIcon = new ImageIcon("assets/cash.png");
        creditCardIcon = new ImageIcon("assets/creditcard.png");
        kakaoPayIcon = new ImageIcon("assets/kakaopay.png");
        naverPayIcon = new ImageIcon("assets/naverpay.png");
        
        // Set the upper panel to card layout for the changing screens
        CardLayout cardLayout = new CardLayout();
        JPanel panelContainer = new JPanel(cardLayout);

        /*
         * Main panel
         * This panel is the main screen of the program.
         * It contains the title label and the buttons to move to the other screens. (In, Out)
         */
        mainPanel = new TransImg("assets/parkingback1.png", 0.15f);

        // Use GridBagLayout to set the components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;

        mainLabel = new JLabel("SKKU Parking System");
		mainLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        mainPanel.add(mainLabel, gbc);

        inBtn = new JButton(carInIcon);
        inBtn.setBorderPainted(false);  // Remove the border
        inBtn.setContentAreaFilled(false);  // Remove the background
        gbc.gridy = 1;
        mainPanel.add(inBtn, gbc);

		outBtn = new JButton(carOutIcon);
        outBtn.setBorderPainted(false);  
        outBtn.setContentAreaFilled(false);  
		gbc.gridy = 2;
		mainPanel.add(outBtn, gbc);

        // Add a glue to the bottom to make the components stay at the top
		gbc.gridy = 3; 
        mainPanel.add(Box.createGlue(), gbc);

        // Add action listeners to the buttons
        inBtn.addActionListener(e -> {
            stage = Stage.CAR_IN;  // Change stage to CAR_IN
            cardLayout.show(panelContainer, "carInPanel");  // Show the car in screen
        });
		outBtn.addActionListener(e -> {
            stage = Stage.CAR_OUT;  // Change stage to CAR_OUT
            cardLayout.show(panelContainer, "carOutPanel");  // Show the car out screen
            // 여기에 새 화면을 띄우는 로직을 구현
        });


        /*
         * Car in panel
         * This panel is the screen for the car in.
         * It contains the text field to enter the license plate and the confirm button.
         */
        JPanel carInPanel = new TransImg("assets/parkingback1.png", 0.15f);
        carInPanel.setLayout(new GridBagLayout());

        // Use GridBagLayout to set the components
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.anchor = GridBagConstraints.CENTER;
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.weightx = 1;
        gbc2.weighty = 1;

        // Add the title label, the text field and the confirm button
        JLabel carInMainLabel = new JLabel("SKKU Parking System");
        carInMainLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        carInPanel.add(carInMainLabel, gbc2);

        JLabel licensePlateLabel = new JLabel("Your License Plate");
        licensePlateLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        gbc2.gridy = 1;
        carInPanel.add(licensePlateLabel, gbc2);

        JTextField licensePlateField = new JTextField(4);  // For getting the license plate number
        licensePlateField.setPreferredSize(new Dimension(100, 50));
        licensePlateField.setFont(new Font("Arial", Font.PLAIN, 50));
        gbc2.gridy = 2;
        carInPanel.add(licensePlateField, gbc2);

        JButton confirmBtn = new JButton("Confirm");
        gbc2.gridy = 3;
        carInPanel.add(confirmBtn, gbc2);

        // Add action listener to the confirm button
        confirmBtn.addActionListener(e -> {
            String inputLicensePlate = licensePlateField.getText();  // Get the input license plate
            licensePlateField.setText("");  // Clear the text field
            // Check if the input is valid (4 ~ 7 characters or numbers)
            if (inputLicensePlate.length() == 0) {
                JOptionPane.showMessageDialog(this, "Please enter your license plate");
            } else if (inputLicensePlate.length() > 7 || inputLicensePlate.length() < 4) {
                JOptionPane.showMessageDialog(this, "Please enter your license plate correctly (4 ~ 7 characters or numbers)");
            } else {
                // Store the license plate number and change the stage to SLOT_SELECT
                curLicensePlate = inputLicensePlate;
                cardLayout.show(panelContainer, "selectPanel");  // Show the slot select screen
                stage = Stage.SLOT_SELECT;
            }
        });


        /*
         * Slot select panel
         * This panel is the screen for the slot select.
         * It contains the table image of the price of each slots.
         */
        JPanel selectPanel = new TransImg("assets/parkingback1.png", 0.15f);
        selectPanel.setLayout(new GridBagLayout());
        
        // Use GridBagLayout to set the components
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.anchor = GridBagConstraints.CENTER;
        gbc3.gridx = 0;
        gbc3.gridy = 0;
        gbc3.weightx = 1;
        gbc3.weighty = 1;

        JLabel selectLabel = new JLabel("Select Slot to Park");
        selectLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        selectPanel.add(selectLabel, gbc3);

        JLabel tableLabel = new JLabel(tableIcon);
        gbc3.gridy = 1;
        selectPanel.add(tableLabel, gbc3);
        

        /*
         * Car out panel
         * This panel is the screen for selecting slot to pull out.
         * It contains the title label.
         */
        JPanel carOutPanel = new TransImg("assets/parkingback1.png", 0.15f);
        carOutPanel.setLayout(new GridBagLayout());

        // Use GridBagLayout to set the components
        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.anchor = GridBagConstraints.CENTER;
        gbc4.gridx = 0;
        gbc4.gridy = 0;
        gbc4.weightx = 1;
        gbc4.weighty = 1;

        JLabel carOutMainLabel = new JLabel("SKKU Parking System");
        carOutMainLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        carOutPanel.add(carOutMainLabel, gbc4);

        JLabel carOutSelectLabel = new JLabel("Select Your Parking Slot");
        carOutSelectLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        gbc4.gridy = 1;
        carOutPanel.add(carOutSelectLabel, gbc4);

        
        /*
         * Payment panel
         * This panel is the screen for the payment.
         * It contains the title label and the buttons for the payment methods.
         * The payment methods are cash, credit card, kakao pay and naver pay.
         */
        JPanel paymentPanel = new TransImg("assets/parkingback1.png", 0.15f);
        paymentPanel.setLayout(new GridBagLayout());
        
        // Use GridBagLayout to set the components
        GridBagConstraints gbc5 = new GridBagConstraints();
        gbc5.anchor = GridBagConstraints.CENTER;
        gbc5.gridx = 0;
        gbc5.gridy = 0;
        gbc5.weightx = 1;
        gbc5.weighty = 1;

        JLabel paymentMainLabel = new JLabel("Payment");
        paymentMainLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        paymentPanel.add(paymentMainLabel, gbc5);

        // Add the buttons for the payment methods
        JButton cashBtn = new JButton(cashIcon);
        cashBtn.setBorderPainted(false);
        cashBtn.setContentAreaFilled(false);
        gbc5.gridy = 1;
        paymentPanel.add(cashBtn, gbc5);

        JButton creditCardBtn = new JButton(creditCardIcon);
        creditCardBtn.setBorderPainted(false);
        creditCardBtn.setContentAreaFilled(false);
        gbc5.gridy = 2;
        paymentPanel.add(creditCardBtn, gbc5);

        JButton kakaoPayBtn = new JButton(kakaoPayIcon);
        kakaoPayBtn.setBorderPainted(false);
        kakaoPayBtn.setContentAreaFilled(false);
        gbc5.gridy = 3;
        paymentPanel.add(kakaoPayBtn, gbc5);

        JButton naverPayBtn = new JButton(naverPayIcon);
        naverPayBtn.setBorderPainted(false);
        naverPayBtn.setContentAreaFilled(false);
        gbc5.gridy = 4;
        paymentPanel.add(naverPayBtn, gbc5);

        // Add a glue to the bottom to make the components stay at the top
        gbc5.gridy = 5;
        gbc5.weighty = 1;
        paymentPanel.add(Box.createGlue(), gbc5);
        
        // Add action listeners to the buttons
        cashBtn.addActionListener(e -> {
            // Here is the logic for cash payment
            // Show the receipt as a message dialog
            JOptionPane.showMessageDialog(this, "Base Fee: " 
            + parkingLot.get(forOutBtn).getBaseFee() 
            + "\nAdditional Fee: " 
            + parkingLot.get(forOutBtn).getAdditionalFee() 
            + " per minute"
            + "\nTime Parked: " 
            + parkingLot.get(forOutBtn).getTimeParked() 
            + " minute(s)"
            + "\nTotal Fee: " 
            + parkingLot.get(forOutBtn).calculateParkingFee());

            stage = Stage.MAIN;  // Change stage to MAIN to stop the timer.

            try {
                Thread.sleep(1000); // Represents the time required for the payment
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Cash Payment Complete"); // Inform the payment is complete

            // Change the icon of the slot to the colored one (means the slot is empty)
            if (parkingLot.get(forOutBtn).getClass() == MidCar.class) forOutBtn.setIcon(coloredMidIcon);
            else if (parkingLot.get(forOutBtn).getClass() == Bus.class) forOutBtn.setIcon(coloredBusIcon);
            else if (parkingLot.get(forOutBtn).getClass() == SmallCar.class) forOutBtn.setIcon(coloredSmallIcon);
            else if (parkingLot.get(forOutBtn).getClass() == Charger.class) forOutBtn.setIcon(coloredChargerIcon);

            // Writing Receipt file

            // Set the slot to empty, and clear the temporary variables
            parkingLot.get(forOutBtn).setOccupied(false);
            parkingLot.get(forOutBtn).setLicensePlate(null);
            forOutBtn = null;
            cardLayout.show(panelContainer, "mainPanel");
        });

        // Other payment methods are the same as the cash payment
        creditCardBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Base Fee: " 
            + parkingLot.get(forOutBtn).getBaseFee() 
            + "\nAdditional Fee: " 
            + parkingLot.get(forOutBtn).getAdditionalFee() 
            + " per minute"
            + "\nTime Parked: " 
            + parkingLot.get(forOutBtn).getTimeParked() 
            + " minute(s)"
            + "\nTotal Fee: " 
            + parkingLot.get(forOutBtn).calculateParkingFee());

            stage = Stage.MAIN;  // Change stage to MAIN to stop the timer.

            try {
                Thread.sleep(1000); // Represents the time required for the payment
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Credit card Payment Complete");

            if (parkingLot.get(forOutBtn).getClass() == MidCar.class) forOutBtn.setIcon(coloredMidIcon);
            else if (parkingLot.get(forOutBtn).getClass() == Bus.class) forOutBtn.setIcon(coloredBusIcon);
            else if (parkingLot.get(forOutBtn).getClass() == SmallCar.class) forOutBtn.setIcon(coloredSmallIcon);
            else if (parkingLot.get(forOutBtn).getClass() == Charger.class) forOutBtn.setIcon(coloredChargerIcon);
            // Writing Receipt file

            parkingLot.get(forOutBtn).setOccupied(false);
            parkingLot.get(forOutBtn).setLicensePlate(null);
            forOutBtn = null;
            cardLayout.show(panelContainer, "mainPanel");
        });

        kakaoPayBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Base Fee: " 
            + parkingLot.get(forOutBtn).getBaseFee() 
            + "\nAdditional Fee: " 
            + parkingLot.get(forOutBtn).getAdditionalFee() 
            + " per minute"
            + "\nTime Parked: " 
            + parkingLot.get(forOutBtn).getTimeParked() 
            + " minute(s)"
            + "\nTotal Fee: " 
            + parkingLot.get(forOutBtn).calculateParkingFee());

            stage = Stage.MAIN;  // Change stage to MAIN to stop the timer.

            try {
                Thread.sleep(1000); // Represents the time required for the payment
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Kakao Pay Payment Complete");

            if (parkingLot.get(forOutBtn).getClass() == MidCar.class) forOutBtn.setIcon(coloredMidIcon);
            else if (parkingLot.get(forOutBtn).getClass() == Bus.class) forOutBtn.setIcon(coloredBusIcon);
            else if (parkingLot.get(forOutBtn).getClass() == SmallCar.class) forOutBtn.setIcon(coloredSmallIcon);
            else if (parkingLot.get(forOutBtn).getClass() == Charger.class) forOutBtn.setIcon(coloredChargerIcon);
            // Writing Receipt file

            parkingLot.get(forOutBtn).setOccupied(false);
            parkingLot.get(forOutBtn).setLicensePlate(null);
            forOutBtn = null;
            cardLayout.show(panelContainer, "mainPanel");
        });

        naverPayBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Base Fee: " 
            + parkingLot.get(forOutBtn).getBaseFee() 
            + "\nAdditional Fee: " 
            + parkingLot.get(forOutBtn).getAdditionalFee() 
            + " per minute"
            + "\nTime Parked: " 
            + parkingLot.get(forOutBtn).getTimeParked() 
            + " minute(s)"
            + "\nTotal Fee: " 
            + parkingLot.get(forOutBtn).calculateParkingFee());

            stage = Stage.MAIN;  // Change stage to MAIN to stop the timer.

            try {
                Thread.sleep(1000); // Represents the time required for the payment
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Naver Pay Payment Complete");

            if (parkingLot.get(forOutBtn).getClass() == MidCar.class) forOutBtn.setIcon(coloredMidIcon);
            else if (parkingLot.get(forOutBtn).getClass() == Bus.class) forOutBtn.setIcon(coloredBusIcon);
            else if (parkingLot.get(forOutBtn).getClass() == SmallCar.class) forOutBtn.setIcon(coloredSmallIcon);
            else if (parkingLot.get(forOutBtn).getClass() == Charger.class) forOutBtn.setIcon(coloredChargerIcon);
            // Writing Receipt file

            parkingLot.get(forOutBtn).setOccupied(false);
            parkingLot.get(forOutBtn).setLicensePlate(null);
            forOutBtn = null;
            cardLayout.show(panelContainer, "mainPanel");
        });

        // Add all the panels to the panel container (card layout)
        panelContainer.add(mainPanel, "mainPanel");
        panelContainer.add(carInPanel, "carInPanel");
        panelContainer.add(selectPanel, "selectPanel");
        panelContainer.add(carOutPanel, "carOutPanel");
        panelContainer.add(paymentPanel, "paymentPanel");


        /* Lower Panel
         * This panel is the lower panel of the screen.
         * Colored slots are empty, and grey slots are occupied.
         * Home button is used to go back to the main screen.
         * Here icon represents the current location.
         */
        lowerPanel = new JPanel();
        lowerPanel.setLayout(null);  // Use absolute layout to set the components

        hereLabel = new JLabel(hereIcon);
        hereLabel.setBounds(20, 0, hereIcon.getIconWidth(), hereIcon.getIconHeight());
        lowerPanel.add(hereLabel);

        // Add the slot buttons (5 middle size, 2 bus, 3 small size, 2 charger)
        midBtnList = new ArrayList<JButton>();
        for (int i = 0; i < 5; i++) {
            midBtnList.add(new JButton(coloredMidIcon));
            midBtnList.get(i).setBorderPainted(false);  // Remove the border
            midBtnList.get(i).setContentAreaFilled(false);  // Remove the content area background
            midBtnList.get(i).setBounds(100 + 80 * i, 6, coloredMidIcon.getIconWidth(), coloredMidIcon.getIconHeight());  // x, y, width, height
            lowerPanel.add(midBtnList.get(i));
        }
        
        // Add the bar image between the slots
        bar1 = new JLabel(barIcon);
        bar1.setBounds(515, 10, barIcon.getIconWidth(), barIcon.getIconHeight());
        lowerPanel.add(bar1);

        busBtn1 = new JButton(coloredBusIcon);
        busBtn1.setBorderPainted(false);  
        busBtn1.setContentAreaFilled(false);  
        busBtn1.setBounds(550, 0, coloredBusIcon.getIconWidth(), coloredBusIcon.getIconHeight());  // x, y, width, height
        lowerPanel.add(busBtn1);

        busBtn2 = new JButton("Bus", coloredBusIcon);
        busBtn2.setBorderPainted(false);  
        busBtn2.setContentAreaFilled(false);  
        busBtn2.setBounds(630, 0, coloredBusIcon.getIconWidth(), coloredBusIcon.getIconHeight());  // x, y, width, height
        lowerPanel.add(busBtn2);

        smallBtnList = new ArrayList<JButton>();
        for (int i = 0; i < 3; i++) {
            smallBtnList.add(new JButton(coloredSmallIcon));
            smallBtnList.get(i).setBorderPainted(false);  
            smallBtnList.get(i).setContentAreaFilled(false); 
            smallBtnList.get(i).setBounds(100 + 80 * i, 290, coloredSmallIcon.getIconWidth(), coloredSmallIcon.getIconHeight());  // x, y, width, height
            lowerPanel.add(smallBtnList.get(i));
        }

        bar2 = new JLabel(barIcon);
        bar2.setBounds(355, 290, barIcon.getIconWidth(), barIcon.getIconHeight());
        lowerPanel.add(bar2);

        midBtn6 = new JButton(coloredMidIcon);
        midBtn6.setBorderPainted(false);  
        midBtn6.setContentAreaFilled(false);  
        midBtn6.setBounds(390, 290, coloredMidIcon.getIconWidth(), coloredMidIcon.getIconHeight());  // x, y, width, height
        lowerPanel.add(midBtn6);

        midBtn7 = new JButton(coloredMidIcon);
        midBtn7.setBorderPainted(false); 
        midBtn7.setContentAreaFilled(false);  
        midBtn7.setBounds(470, 290, coloredMidIcon.getIconWidth(), coloredMidIcon.getIconHeight());  // x, y, width, height
        lowerPanel.add(midBtn7);

        chargerBtn1 = new JButton(coloredChargerIcon);
        chargerBtn1.setBorderPainted(false);  
        chargerBtn1.setContentAreaFilled(false);  
        chargerBtn1.setBounds(550, 290, coloredChargerIcon.getIconWidth(), coloredChargerIcon.getIconHeight());  // x, y, width, height
        lowerPanel.add(chargerBtn1);

        chargerBtn2 = new JButton(coloredChargerIcon);
        chargerBtn2.setBorderPainted(false);  
        chargerBtn2.setContentAreaFilled(false);  
        chargerBtn2.setBounds(630, 290, coloredChargerIcon.getIconWidth(), coloredChargerIcon.getIconHeight());  // x, y, width, height
        lowerPanel.add(chargerBtn2);

        // Make instances of the vehicles
        midCar1 = new MidCar();
        midCar2 = new MidCar();
        midCar3 = new MidCar();
        midCar4 = new MidCar();
        midCar5 = new MidCar();
        bus1 = new Bus();
        bus2 = new Bus();
        smallCar1 = new SmallCar();
        smallCar2 = new SmallCar();
        smallCar3 = new SmallCar();
        midCar6 = new MidCar();
        midCar7 = new MidCar();
        charger1 = new Charger();
        charger2 = new Charger();

        // Set the parking lot map. (slot button, vehicle)
        parkingLot = new HashMap<JButton, Vehicle>();
        parkingLot.put(midBtnList.get(0), midCar1);
        parkingLot.put(midBtnList.get(1), midCar2);
        parkingLot.put(midBtnList.get(2), midCar3);
        parkingLot.put(midBtnList.get(3), midCar4);
        parkingLot.put(midBtnList.get(4), midCar5);
        parkingLot.put(busBtn1, bus1);
        parkingLot.put(busBtn2, bus2);
        parkingLot.put(smallBtnList.get(0), smallCar1);
        parkingLot.put(smallBtnList.get(1), smallCar2);
        parkingLot.put(smallBtnList.get(2), smallCar3);
        parkingLot.put(midBtn6, midCar6);
        parkingLot.put(midBtn7, midCar7);
        parkingLot.put(chargerBtn1, charger1);
        parkingLot.put(chargerBtn2, charger2);


        // Add action listeners to the slot buttons
        for (JButton midBtn : midBtnList) {
            midBtn.addActionListener(e -> {
                JButton btn = (JButton) e.getSource(); // Get the button that is clicked
                // Check the stage and the slot is occupied or not (If the slot is occupied, the button is grey)
                // While parking the car, if the slot is not occupied (Good), show the confirm dialog
                if (stage == Stage.SLOT_SELECT && !parkingLot.get(midBtn).occupied) {
                    int response = JOptionPane.showConfirmDialog(this, "Middle-Size, Confirm?", "Confirm", JOptionPane.YES_NO_OPTION);

                    if (response == JOptionPane.YES_OPTION) {  // If the user clicks yes, change the stage to MAIN and set the slot to occupied
                        stage = Stage.MAIN;
                        parkingLot.get(btn).setOccupied(true);  // Set the slot to occupied
                        parkingLot.get(btn).setLicensePlate(curLicensePlate);  // Set the license plate number
                        curLicensePlate = null;  // Clear the temporary variable
                        btn.setIcon(greyMidIcon);  // Change the icon to grey(occupied)
                        JOptionPane.showMessageDialog(this, "Parking Complete (" + parkingLot.get(btn).getLicensePlate() + ")");  // Show the dialog
                        cardLayout.show(panelContainer, "mainPanel");  // Show the main screen
                        start(btn);  // Start the timer (Multi-threading)
                    } 
                }
                else if (stage == Stage.SLOT_SELECT) JOptionPane.showMessageDialog(this, "This slot is occupied");  // While parking the car, if the slot is occupied, show the dialog
                else if (stage == Stage.CAR_OUT && parkingLot.get(btn).occupied) {   // While pulling out the car, if the slot is occupied (Good), show the confirm dialog
                    int response = JOptionPane.showConfirmDialog(this, "Pull Out " + parkingLot.get(btn).getLicensePlate() + " ?", "Confirm", JOptionPane.YES_NO_OPTION);

                    if (response == JOptionPane.YES_OPTION) {
                        forOutBtn = btn;  // Store the slot button to the temporary variable
                        stage = Stage.PAYMENT;  // Change the stage to PAYMENT
                        cardLayout.show(panelContainer, "paymentPanel");  // Show the payment screen
                    } 
                }
                else if (stage == Stage.CAR_OUT) JOptionPane.showMessageDialog(this, "This slot is not occupied");  // While pulling out the car, if the slot is not occupied, show the dialog
            });
        }

        // Other slot buttons are the same as the middle size slot buttons
        busBtn1.addActionListener(e -> {
            JButton btn = (JButton) e.getSource();
            if (stage == Stage.SLOT_SELECT && !parkingLot.get(busBtn1).occupied) {
                int response = JOptionPane.showConfirmDialog(this, "Bus, Confirm?", "Confirm", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    stage = Stage.MAIN;
                    parkingLot.get(btn).setOccupied(true);
                    parkingLot.get(btn).setLicensePlate(curLicensePlate);
                    curLicensePlate = null;
                    btn.setIcon(greyBusIcon);
                    JOptionPane.showMessageDialog(this, "Parking Complete (" + parkingLot.get(btn).licensePlate + ")");
                    cardLayout.show(panelContainer, "mainPanel");
                    start(btn);
                } 
            }
            else if (stage == Stage.SLOT_SELECT) JOptionPane.showMessageDialog(this, "This slot is occupied");
            else if (stage == Stage.CAR_OUT && parkingLot.get(btn).occupied) {
                int response = JOptionPane.showConfirmDialog(this, "Pull Out " + parkingLot.get(btn).getLicensePlate() + " ?", "Confirm", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    forOutBtn = btn;
                    stage = Stage.PAYMENT;
                    cardLayout.show(panelContainer, "paymentPanel");
                } 
            }
            else if (stage == Stage.CAR_OUT) JOptionPane.showMessageDialog(this, "This slot is not occupied");
        });

        busBtn2.addActionListener(e -> {
            JButton btn = (JButton) e.getSource();
            if (stage == Stage.SLOT_SELECT && !parkingLot.get(busBtn2).occupied) {
                int response = JOptionPane.showConfirmDialog(this, "Bus, Confirm?", "Confirm", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    stage = Stage.MAIN;
                    parkingLot.get(btn).setOccupied(true);
                    parkingLot.get(btn).setLicensePlate(curLicensePlate);
                    curLicensePlate = null;
                    btn.setIcon(greyBusIcon);
                    JOptionPane.showMessageDialog(this, "Parking Complete (" + parkingLot.get(btn).licensePlate + ")");
                    cardLayout.show(panelContainer, "mainPanel");
                    start(btn);
                } 
            }
            else if (stage == Stage.SLOT_SELECT) JOptionPane.showMessageDialog(this, "This slot is occupied");
            else if (stage == Stage.CAR_OUT && parkingLot.get(btn).occupied) {
                int response = JOptionPane.showConfirmDialog(this, "Pull Out " + parkingLot.get(btn).getLicensePlate() + " ?", "Confirm", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    forOutBtn = btn;
                    stage = Stage.PAYMENT;
                    cardLayout.show(panelContainer, "paymentPanel");
                } 
            }
            else if (stage == Stage.CAR_OUT) JOptionPane.showMessageDialog(this, "This slot is not occupied");
        });

        for (JButton smallBtn : smallBtnList) {
            smallBtn.addActionListener(e -> {
                JButton btn = (JButton) e.getSource();
                if (stage == Stage.SLOT_SELECT && !parkingLot.get(smallBtn).occupied) {
                    int response = JOptionPane.showConfirmDialog(this, "Small-Size, Confirm?", "Confirm", JOptionPane.YES_NO_OPTION);

                    if (response == JOptionPane.YES_OPTION) {
                        stage = Stage.MAIN;
                        parkingLot.get(btn).setOccupied(true);
                        parkingLot.get(btn).setLicensePlate(curLicensePlate);
                        curLicensePlate = null;
                        btn.setIcon(greySmallIcon);
                        JOptionPane.showMessageDialog(this, "Parking Complete (" + parkingLot.get(btn).licensePlate + ")");
                        cardLayout.show(panelContainer, "mainPanel");
                        start(btn);
                    } 
                }
                else if (stage == Stage.SLOT_SELECT) JOptionPane.showMessageDialog(this, "This slot is occupied");
                else if (stage == Stage.CAR_OUT && parkingLot.get(btn).occupied) {
                    int response = JOptionPane.showConfirmDialog(this, "Pull Out " + parkingLot.get(btn).getLicensePlate() + " ?", "Confirm", JOptionPane.YES_NO_OPTION);

                    if (response == JOptionPane.YES_OPTION) {
                        forOutBtn = btn;
                        stage = Stage.PAYMENT;
                        cardLayout.show(panelContainer, "paymentPanel");
                    } 
                }
                else if (stage == Stage.CAR_OUT) JOptionPane.showMessageDialog(this, "This slot is not occupied");
            });
        }

        midBtn6.addActionListener(e -> {
            JButton btn = (JButton) e.getSource();
            if (stage == Stage.SLOT_SELECT && !parkingLot.get(midBtn6).occupied) {
                int response = JOptionPane.showConfirmDialog(this, "Middle-Size, Confirm?", "Confirm", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    stage = Stage.MAIN;
                    parkingLot.get(btn).setOccupied(true);
                    parkingLot.get(btn).setLicensePlate(curLicensePlate);
                    curLicensePlate = null;
                    btn.setIcon(greyMidIcon);
                    JOptionPane.showMessageDialog(this, "Parking Complete (" + parkingLot.get(btn).licensePlate + ")");
                    cardLayout.show(panelContainer, "mainPanel");
                    start(btn);
                } 
            }
            else if (stage == Stage.SLOT_SELECT) JOptionPane.showMessageDialog(this, "This slot is occupied");
            else if (stage == Stage.CAR_OUT && parkingLot.get(btn).occupied) {
                int response = JOptionPane.showConfirmDialog(this, "Pull Out " + parkingLot.get(btn).getLicensePlate() + " ?", "Confirm", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    forOutBtn = btn;
                    stage = Stage.PAYMENT;
                    cardLayout.show(panelContainer, "paymentPanel");
                } 
            }
            else if (stage == Stage.CAR_OUT) JOptionPane.showMessageDialog(this, "This slot is not occupied");
        });

        midBtn7.addActionListener(e -> {
            JButton btn = (JButton) e.getSource();
            if (stage == Stage.SLOT_SELECT && !parkingLot.get(midBtn7).occupied) {
                int response = JOptionPane.showConfirmDialog(this, "Middle-Size, Confirm?", "Confirm", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    stage = Stage.MAIN;
                    parkingLot.get(btn).setOccupied(true);
                    parkingLot.get(btn).setLicensePlate(curLicensePlate);
                    curLicensePlate = null;
                    btn.setIcon(greyMidIcon);
                    JOptionPane.showMessageDialog(this, "Parking Complete (" + parkingLot.get(btn).licensePlate + ")");
                    cardLayout.show(panelContainer, "mainPanel");
                    start(btn);
                } 
            }
            else if (stage == Stage.SLOT_SELECT) JOptionPane.showMessageDialog(this, "This slot is occupied");
            else if (stage == Stage.CAR_OUT && parkingLot.get(btn).occupied) {
                int response = JOptionPane.showConfirmDialog(this, "Pull Out " + parkingLot.get(btn).getLicensePlate() + " ?", "Confirm", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    forOutBtn = btn;
                    stage = Stage.PAYMENT;
                    cardLayout.show(panelContainer, "paymentPanel");
                } 
            }
            else if (stage == Stage.CAR_OUT) JOptionPane.showMessageDialog(this, "This slot is not occupied");
        });

        chargerBtn1.addActionListener(e -> {
            JButton btn = (JButton) e.getSource();
            if (stage == Stage.SLOT_SELECT && !parkingLot.get(chargerBtn1).occupied) {
                int response = JOptionPane.showConfirmDialog(this, "Charger, Confirm?", "Confirm", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    stage = Stage.MAIN;
                    parkingLot.get(btn).setOccupied(true);
                    parkingLot.get(btn).setLicensePlate(curLicensePlate);
                    curLicensePlate = null;
                    btn.setIcon(greyChargerIcon);
                    JOptionPane.showMessageDialog(this, "Parking Complete (" + parkingLot.get(btn).licensePlate + ")");
                    cardLayout.show(panelContainer, "mainPanel");
                    start(btn);
                } 
            }
            else if (stage == Stage.SLOT_SELECT) JOptionPane.showMessageDialog(this, "This slot is occupied");
            else if (stage == Stage.CAR_OUT && parkingLot.get(btn).occupied) {
                int response = JOptionPane.showConfirmDialog(this, "Pull Out " + parkingLot.get(btn).getLicensePlate() + " ?", "Confirm", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    forOutBtn = btn;
                    stage = Stage.PAYMENT;
                    cardLayout.show(panelContainer, "paymentPanel");
                } 
            }
            else if (stage == Stage.CAR_OUT) JOptionPane.showMessageDialog(this, "This slot is not occupied");
        });

        chargerBtn2.addActionListener(e -> {
            JButton btn = (JButton) e.getSource();
            if (stage == Stage.SLOT_SELECT && !parkingLot.get(chargerBtn2).occupied) {
                int response = JOptionPane.showConfirmDialog(this, "Charger, Confirm?", "Confirm", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    stage = Stage.MAIN;
                    parkingLot.get(btn).setOccupied(true);
                    parkingLot.get(btn).setLicensePlate(curLicensePlate);
                    curLicensePlate = null;
                    btn.setIcon(greyChargerIcon);
                    JOptionPane.showMessageDialog(this, "Parking Complete (" + parkingLot.get(btn).licensePlate + ")");
                    cardLayout.show(panelContainer, "mainPanel");
                    start(btn);
                } 
            }
            else if (stage == Stage.SLOT_SELECT) JOptionPane.showMessageDialog(this, "This slot is occupied");
            else if (stage == Stage.CAR_OUT && parkingLot.get(btn).occupied) {
                int response = JOptionPane.showConfirmDialog(this, "Pull Out " + parkingLot.get(btn).getLicensePlate() + " ?", "Confirm", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    forOutBtn = btn;
                    stage = Stage.PAYMENT;
                    cardLayout.show(panelContainer, "paymentPanel");
                } 
            }
            else if (stage == Stage.CAR_OUT) JOptionPane.showMessageDialog(this, "This slot is not occupied");
        });

        // Add the home button
        homeBtn = new JButton(homeIcon);
        homeBtn.setBorderPainted(false);  // 버튼 테두리 제거
        homeBtn.setContentAreaFilled(false);  // 버튼 내용 영역 배경 제거
        homeBtn.setBounds(20, 350, homeIcon.getIconWidth(), homeIcon.getIconHeight());  // x, y, width, height
        lowerPanel.add(homeBtn);

        // Add action listener to the home button
        homeBtn.addActionListener(e -> {
            // Clear the temporary variables and change the stage to MAIN
            curLicensePlate = null;
            forOutBtn = null;
            licensePlateField.setText("");
            stage = Stage.MAIN;
            cardLayout.show(panelContainer, "mainPanel");
        });


        // Add the panels to the Main frame
        this.setLayout(new GridLayout(2,1));
        this.add(panelContainer);
        this.add(lowerPanel);
        
        // Set the frame
        this.setSize(750, 1000);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }          
    
    /*
     * This method is used to start the timer.
     * It uses SwingWorker to run the timer in the background.
     * The timer is updated every 1 minute.
     * When the car is pulled out (occupied -> unoccupied), the timer is stopped.
     */
    private void start(JButton btn) {
        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception { // Work Thread (Background Thread)
                while (parkingLot.get(btn).isOccupied()) {
                    Thread.sleep(60000);   // 1 minute
                    parkingLot.get(btn).timeParked++;  // Increase the time parked
                    publish(parkingLot.get(btn).timeParked);  // For logging the timer
                }
                return null;
            }

            // For logging the timer
            @Override
            protected void process(List<Integer> chunks) {
                Integer value = chunks.get(chunks.size() - 1);
                System.out.println(parkingLot.get(btn).getLicensePlate() + "'s parked time is " + value + " minutes");
            }

            // For logging the parking fee
            @Override
            protected void done() {
                // 작업이 끝났을 때 호출
                System.out.println("Parking fee is " + parkingLot.get(btn).calculateParkingFee() + " won");
            }
        };
        worker.execute();  // Start the timer
    }

    public static void main(String[] args) {
        // Create the main frame
        MainFrame frame = new MainFrame();
    }
}