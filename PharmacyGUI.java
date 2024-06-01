import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


class PharmacyGUI {

    private JFrame frame;
    private JTextField usernameField;
    private JTextField idField;
    private JTextField branchField;
    private JTextArea displayArea;
    private String username;
    private String userIdd;
    private String branchName;
    private Drugs drug = new Drugs();
    private Cosmetics cosmetic = new Cosmetics();
    private PlaceOrder placeOrder = new PlaceOrder();
    private Orders Order = new Orders();
    private User user1 = new User();
    private Branches branch = new Branches();
    private Costumer customer1 = new Costumer();
    private Buy buy = new Buy();
    private double sum =0;





    public static void main(String[] args) {
        PharmacyGUI window = new PharmacyGUI();
        window.frame.setVisible(true);
    }

    public PharmacyGUI() {
        initializeLogin();
    }


    private void initializeLogin() {
        frame = new JFrame("Pharmacy Login");
        frame.setBounds(100, 100, 400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 50, 100, 25);
        frame.getContentPane().add(lblUsername);

        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 200, 25);
        frame.getContentPane().add(usernameField);
        usernameField.setColumns(10);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(50, 90, 100, 25);
        frame.getContentPane().add(lblId);

        idField = new JTextField();
        idField.setBounds(150, 90, 200, 25);
        frame.getContentPane().add(idField);
        idField.setColumns(10);

        JLabel lblBranch = new JLabel("Branch Name:");
        lblBranch.setBounds(50, 130, 100, 25);
        frame.getContentPane().add(lblBranch);

        branchField = new JTextField();
        branchField.setBounds(150, 130, 200, 25);
        frame.getContentPane().add(branchField);
        branchField.setColumns(10);

        JLabel capacityLabel = new JLabel("Capacity:");
        capacityLabel.setBounds(50, 170, 100, 25);
        frame.getContentPane().add(capacityLabel);

        JTextField capacityField = new JTextField();
        capacityField.setBounds(150, 170, 200, 25);
        frame.getContentPane().add(capacityField);

        JButton enterButton = new JButton("Enter");
        enterButton.setBounds(150, 210, 100, 25);
        frame.getContentPane().add(enterButton);

        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                username = usernameField.getText();
                userIdd = idField.getText();
                branchName = branchField.getText();
                String capacity = capacityField.getText();
                if (username.isEmpty() || userIdd.isEmpty() || branchName.isEmpty() || capacity.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (Double.parseDouble(capacity)<=0){
                    JOptionPane.showMessageDialog(frame, "capacity can't be smaller then 0.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Integer userId = Integer.parseInt(idField.getText());
                user1.Add(username, branchName,userId);
                frame.dispose();
                initializeMainMenu();
            }
        });
    }

    private void initializeMainMenu() {
        frame = new JFrame("Pharmacy Operating System");
        frame.setBounds(100, 100, 400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome, " + username + "! Please choose an option:");
        welcomeLabel.setBounds(50, 30, 300, 25);
        frame.getContentPane().add(welcomeLabel);

        String[] options = {"Add", "Remove", "Update", "Place an order", "Get total sales", "Exit"};
        JComboBox<String> optionsComboBox = new JComboBox<>(options);
        optionsComboBox.setBounds(50, 70, 300, 25);
        frame.getContentPane().add(optionsComboBox);

        JButton selectButton = new JButton("Select");
        selectButton.setBounds(150, 110, 100, 25);
        frame.getContentPane().add(selectButton);

        displayArea = new JTextArea();

        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) optionsComboBox.getSelectedItem();
                switch (selectedOption) {
                    case "Add":
                        initializeDrugCosmeticsMenu(selectedOption);
                        break;
                    case "Remove":
                        initializeDrugCosmeticsMenu(selectedOption);
                        break;
                    case "Update":
                        initializeDrugCosmeticsMenu(selectedOption);
                        break;
                    case "Place an order":
                        initializeOrderMenu();
                        break;
                    case "Get total sales":
                        double totalSales = Order.d_total();

                        break;
                    case "Exit":
                        System.exit(0);
                        break;
                }
            }
        });

        frame.setVisible(true);
    }

    private void initializeDrugCosmeticsMenu(String action) {
        frame.getContentPane().removeAll();
        frame.repaint();

        JLabel actionLabel = new JLabel("Choose one to " + action.toLowerCase() + ":");
        actionLabel.setBounds(50, 30, 300, 25);
        frame.getContentPane().add(actionLabel);

        String[] items = {"Drug", "Cosmetics", "Done"};
        JComboBox<String> itemsComboBox = new JComboBox<>(items);
        itemsComboBox.setBounds(50, 70, 300, 25);
        frame.getContentPane().add(itemsComboBox);

        JButton selectButton = new JButton("Select");
        selectButton.setBounds(150, 110, 100, 25);
        frame.getContentPane().add(selectButton);

        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) itemsComboBox.getSelectedItem();
                if ("Done".equals(selectedItem)) {
                    initializeMainMenu();
                } else {
                    initializeDrugCosmeticsEntry(action, selectedItem);
                }
            }
        });

        frame.repaint();
    }

    private void initializeDrugCosmeticsEntry(String action, String category) {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setBounds(100, 100, 400, 500);

        if (action.equals("Add") || action.equals("Remove")){
            JLabel nameLabel = new JLabel("Enter " + category + " name:");
            nameLabel.setBounds(50, 30, 150, 25);
            frame.getContentPane().add(nameLabel);

            JTextField nameField = new JTextField();
            nameField.setBounds(200, 30, 150, 25);
            frame.getContentPane().add(nameField);

            JLabel expirationLabel = new JLabel("Expiration Date:");
            expirationLabel.setBounds(50, 70, 150, 25);
            frame.getContentPane().add(expirationLabel);

            JTextField expirationField = new JTextField();
            expirationField.setBounds(200, 70, 150, 25);
            frame.getContentPane().add(expirationField);

            JLabel supplierLabel = new JLabel("Supplier:");
            supplierLabel.setBounds(50, 110, 150, 25);
            frame.getContentPane().add(supplierLabel);

            JTextField supplierField = new JTextField();
            supplierField.setBounds(200, 110, 150, 25);
            frame.getContentPane().add(supplierField);

            JLabel storageLabel = new JLabel("Storage Condition:");
            storageLabel.setBounds(50, 150, 150, 25);
            frame.getContentPane().add(storageLabel);

            JTextField storageField = new JTextField();
            storageField.setBounds(200, 150, 150, 25);
            frame.getContentPane().add(storageField);

            JLabel prescriptionLabel = new JLabel("Prescription:");
            prescriptionLabel.setBounds(50, 190, 150, 25);
            frame.getContentPane().add(prescriptionLabel);

            JTextField prescriptionField = new JTextField();
            prescriptionField.setBounds(200, 190, 150, 25);
            frame.getContentPane().add(prescriptionField);

            JLabel consumptionLabel = new JLabel("Consumption:");
            consumptionLabel.setBounds(50, 230, 150, 25);
            frame.getContentPane().add(consumptionLabel);

            JTextField consumptionField = new JTextField();
            consumptionField.setBounds(200, 230, 150, 25);
            frame.getContentPane().add(consumptionField);


            JLabel priceLabel = new JLabel("Price:");
            priceLabel.setBounds(50, 270, 150, 25);
            frame.getContentPane().add(priceLabel);

            JTextField priceField = new JTextField();
            priceField.setBounds(200, 270, 150, 25);
            frame.getContentPane().add(priceField);

            JLabel stockLabel = new JLabel("Stock:");
            stockLabel.setBounds(50, 310, 150, 25);
            frame.getContentPane().add(stockLabel);

            JTextField stockField = new JTextField();
            stockField.setBounds(200, 310, 150, 25);
            frame.getContentPane().add(stockField);


            JButton actionButton = new JButton(action);
            actionButton.setBounds(50, 350, 100, 25);
            frame.getContentPane().add(actionButton);

            JButton doneButton = new JButton("Done");
            doneButton.setBounds(200, 350, 100, 25);
            frame.getContentPane().add(doneButton);

            actionButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    String expiration = expirationField.getText();
                    String supplier = supplierField.getText();
                    String storage = storageField.getText();
                    String prescription = prescriptionField.getText();
                    String consumption = consumptionField.getText();
                    String price = priceField.getText();
                    String stock = stockField.getText();

                    if (name.isEmpty() || price.isEmpty() || expiration.isEmpty() || supplier.isEmpty() || storage.isEmpty() || prescription.isEmpty() || consumption.isEmpty() ) {
                        JOptionPane.showMessageDialog(frame, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (action=="Add"){
                        if (category=="Drug"){
                            try {
                                if (drug.Add(name,expiration,supplier,storage,Boolean.parseBoolean(prescription),Double.parseDouble(price),Double.parseDouble(stock),Double.parseDouble(consumption))){
                                    JOptionPane.showMessageDialog(frame, "Drug name is already add before", "Error", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        else if (category=="Cosmetics") {
                            try {
                                if (cosmetic.Add(name,expiration,supplier,storage,Double.parseDouble(price),Double.parseDouble(stock),Double.parseDouble(consumption))){
                                    JOptionPane.showMessageDialog(frame, "Cosmetics name is already add before", "Error", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                    if (action=="Remove"){
                        if (category=="Drug"){
                            try {
                                if (drug.Remove(name)){
                                    JOptionPane.showMessageDialog(frame, "Drug name is not found", "Error", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        else if (category=="Cosmetics") {
                            if (cosmetic.Remove(name)){
                                JOptionPane.showMessageDialog(frame, "Cosmetics name is not found", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(frame, category + action+ " successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            doneButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    initializeMainMenu();
                }
            });


        }
        if (action.equals("Update")) {
            if (category=="Drug"){
            JLabel nameLabel = new JLabel("Enter " + category + " name:");
            nameLabel.setBounds(50, 30, 150, 25);
            frame.getContentPane().add(nameLabel);

            JTextField nameField = new JTextField();
            nameField.setBounds(200, 30, 150, 25);
            frame.getContentPane().add(nameField);

            JLabel choiceLabel = new JLabel("Enter what do you want to modify:");
            choiceLabel.setBounds(50, 70, 300, 25);
            frame.getContentPane().add(choiceLabel);

            String[] choices = {"1) Name", "2) Expiration Date", "3) Supplier", "4) Storage Conditions",
                    "5) Prescription Needed", "6) Code", "7) Price", "8) Stock", "9) Average Monthly Consumption"};
            JComboBox<String> choiceComboBox = new JComboBox<>(choices);
            choiceComboBox.setBounds(50, 110, 300, 25);
            frame.getContentPane().add(choiceComboBox);

            JButton updateButton = new JButton("Update");
            updateButton.setBounds(150, 150, 100, 25);
            frame.getContentPane().add(updateButton);

                JButton doneButton = new JButton("Done");
                doneButton.setBounds(50, 150, 100, 25);
                frame.getContentPane().add(doneButton);

                doneButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        initializeMainMenu();
                    }
                });



            updateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    int choice = choiceComboBox.getSelectedIndex() + 1;
                    //String choicename = choiceComboBox.getSelectedItem().toString();

                    if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Name must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String newValue = JOptionPane.showInputDialog(frame, "Enter new value:");
                    try {
                        if(drug.Update(name,choice,newValue)){
                            JOptionPane.showMessageDialog(frame, "Drug is not found", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    JOptionPane.showMessageDialog(frame, category + " updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                }
            });

            }
            else {
                    JLabel nameLabel = new JLabel("Enter " + category + " name:");
                    nameLabel.setBounds(50, 30, 150, 25);
                    frame.getContentPane().add(nameLabel);

                    JTextField nameField = new JTextField();
                    nameField.setBounds(200, 30, 150, 25);
                    frame.getContentPane().add(nameField);

                    JLabel choiceLabel = new JLabel("Enter what do you want to modify:");
                    choiceLabel.setBounds(50, 70, 300, 25);
                    frame.getContentPane().add(choiceLabel);

                    String[] choices = {"1) Name", "2) Expiration Date", "3) Supplier", "4) Storage Conditions",
                            "5) Prescription Needed", "6) Code", "7) Price", "8) Stock", "9) Average Monthly Consumption"};
                    JComboBox<String> choiceComboBox = new JComboBox<>(choices);
                    choiceComboBox.setBounds(50, 110, 300, 25);
                    frame.getContentPane().add(choiceComboBox);

                    JButton updateButton = new JButton("Update");
                    updateButton.setBounds(150, 150, 100, 25);
                    frame.getContentPane().add(updateButton);

                    updateButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String name = nameField.getText();
                            int choice = choiceComboBox.getSelectedIndex() + 1;
                            String choicename = choiceComboBox.getSelectedItem().toString();

                            if (name.isEmpty()) {
                                JOptionPane.showMessageDialog(frame, "Name must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            String newValue = JOptionPane.showInputDialog(frame, "Enter new value:");
                            try {
                                if(drug.Update(name,choice,newValue)){
                                    JOptionPane.showMessageDialog(frame, "Drug is not found", "Error", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                            frame.getContentPane().removeAll();
                            frame.repaint();

                            JOptionPane.showMessageDialog(frame, category + " updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    });


            }
        }


//        frame.repaint();
        }


    private void initializeOrderMenu() {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setBounds(100, 100, 400, 200);

        JLabel orderLabel = new JLabel("Choose one to add to order:");
        orderLabel.setBounds(50, 30, 300, 25);
        frame.getContentPane().add(orderLabel);

        String[] items = {"Drug", "Cosmetics", "Done"};
        JComboBox<String> itemsComboBox = new JComboBox<>(items);
        itemsComboBox.setBounds(50, 70, 300, 25);
        frame.getContentPane().add(itemsComboBox);

        JButton selectButton = new JButton("Select");
        selectButton.setBounds(150, 110, 100, 25);
        frame.getContentPane().add(selectButton);

        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) itemsComboBox.getSelectedItem();
                if (selectedItem.equals("Done")) {
                    initializeOrderItemEntry();
                } else {
                    initializeOrderItemEntry(selectedItem);
                }
            }
        });

        frame.repaint();
    }

    private void initializeOrderItemEntry(String category) {
        frame.getContentPane().removeAll();
        frame.repaint();

        JLabel nameLabel = new JLabel("Enter " + category + " name:");
        nameLabel.setBounds(50, 30, 150, 25);
        frame.getContentPane().add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(200, 30, 150, 25);
        frame.getContentPane().add(nameField);

        JLabel quantityLabel = new JLabel("Enter quantity:");
        quantityLabel.setBounds(50, 70, 150, 25);
        frame.getContentPane().add(quantityLabel);

        JTextField quantityField = new JTextField();
        quantityField.setBounds(200, 70, 150, 25);
        frame.getContentPane().add(quantityField);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBounds(200, 110, 100, 25);
        frame.getContentPane().add(confirmButton);




        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int quantity;

                try {
                    quantity = Integer.parseInt(quantityField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Quantity must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (name.isEmpty() || quantity <= 0) {
                    JOptionPane.showMessageDialog(frame, "All fields must be filled correctly!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (category.equals("Drug")) {
                    Order.add_order(name);
                    placeOrder.placeOrder(1,name);
                } else if (category.equals("Cosmetics")) {
                    placeOrder.placeOrder(2,name);
                }

                JOptionPane.showMessageDialog(frame, category + " added to order successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                initializeOrderMenu();
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(150, 190, 100, 25);
        frame.getContentPane().add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                initializeOrderMenu();
            }
        });
        frame.repaint();
    }

    private void initializeOrderItemEntry() {
        frame.getContentPane().removeAll();
        frame.repaint();

        JLabel customerLabel = new JLabel("Enter customer name:");
        customerLabel.setBounds(50, 30, 150, 25);
        frame.getContentPane().add(customerLabel);

        JTextField customerField = new JTextField();
        customerField.setBounds(200, 30, 150, 25);
        frame.getContentPane().add(customerField);

        JLabel DateLabel = new JLabel("Enter today's Date:");
        DateLabel.setBounds(50, 70, 150, 25);
        frame.getContentPane().add(DateLabel);

        JTextField DateField = new JTextField();
        DateField.setBounds(200, 70, 150, 25);
        frame.getContentPane().add(DateField);

        JLabel idLabel = new JLabel("Enter customer ID:");
        idLabel.setBounds(50, 110, 150, 25);
        frame.getContentPane().add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(200, 110, 150, 25);
        frame.getContentPane().add(idField);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBounds(150, 150, 100, 25);
        frame.getContentPane().add(confirmButton);

        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String customerName = customerField.getText();
                String customerId = idField.getText();
                String Date = DateField.getText();

                if (customerName.isEmpty() || customerId.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields must be filled correctly!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Order.order_info(Date,customerName,Double.parseDouble(customerId),100);
                customer1.Add(customerName,Double.parseDouble(customerId));
                //String orderDetails = Order.display();
                //displayArea.append("Order placed by: " + customerName + " (ID: " + customerId + ")\n");
                //displayArea.append(orderDetails + "\n");

                JOptionPane.showMessageDialog(frame, "Order placed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                initializeMainMenu();
            }
        });
        JButton backButton = new JButton("Back");
        backButton.setBounds(60, 150, 100, 25);
        frame.getContentPane().add(backButton);

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                initializeOrderMenu();
            }
        });


        frame.repaint();
    }
}
