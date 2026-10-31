/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Gamolo
 */
public class HomePage extends javax.swing.JFrame {
    
    private Connection conn;
    private int userId;
    private JPanel panelFlowers;
    private JPanel panelContent;
    
    
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HomePage.class.getName());

    /**
     * Creates new form HomePage
     */
    public HomePage(int userId) {
        initComponents();

    this.userId = userId;

    // =========================
    // NAVIGATION BUTTON COLORS
    // =========================

    btnHome.setBackground(
        new java.awt.Color(255, 182, 193)
    );

    btnCategory.setBackground(
        new java.awt.Color(255, 182, 193)
    );

    btnViewOrder.setBackground(
        new java.awt.Color(255, 182, 193)
    );

    btnCart.setBackground(
        new java.awt.Color(255, 182, 193)
    );

    btnAccount.setBackground(
        new java.awt.Color(255, 182, 193)
    );

    btnLogout.setBackground(
        new java.awt.Color(255, 182, 193)
    );

    // =========================
    // BUTTON TEXT COLOR
    // =========================

    btnHome.setForeground(
        java.awt.Color.BLACK
    );

    btnCategory.setForeground(
        java.awt.Color.BLACK
    );

    btnViewOrder.setForeground(
        java.awt.Color.BLACK
    );

    btnCart.setForeground(
        java.awt.Color.BLACK
    );

    btnAccount.setForeground(
        java.awt.Color.BLACK
    );

    btnLogout.setForeground(
        java.awt.Color.BLACK
    );

    // =========================
    // SERIF FONT
    // =========================

    btnHome.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.BOLD,
            15
        )
    );

    btnCategory.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.BOLD,
            15
        )
    );

    btnViewOrder.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.BOLD,
            15
        )
    );

    btnCart.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.BOLD,
            15
        )
    );

    btnAccount.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.BOLD,
            15
        )
    );

    btnLogout.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.BOLD,
            15
        )
    );

    // =========================
    // REMOVE FOCUS BORDER
    // =========================

    btnHome.setFocusPainted(false);
    btnCategory.setFocusPainted(false);
    btnViewOrder.setFocusPainted(false);
    btnCart.setFocusPainted(false);
    btnAccount.setFocusPainted(false);
    btnLogout.setFocusPainted(false);

    // =========================
    // STARTUP
    // =========================

    setLocationRelativeTo(null);

    setupFlowerPanel();

    ConnectDB();

    loadFlowers();
  

    }
    
    private void setupFlowerPanel() {

    panelFlowers = new JPanel();

    panelFlowers.setLayout(
        new GridLayout(0, 4, 20, 20)
    );

    panelFlowers.setBackground(
        java.awt.Color.WHITE
    );

    panelFlowers.setBorder(
        BorderFactory.createEmptyBorder(
            20, 20, 20, 20
        )
    );

    ScrollFlowers.setViewportView(
        panelFlowers
    );

    ScrollFlowers.setBackground(
        java.awt.Color.WHITE
    );

    ScrollFlowers.getViewport()
        .setBackground(
            java.awt.Color.WHITE
        );

    ScrollFlowers.setBorder(
        BorderFactory.createEmptyBorder()
    );
        
}
    
    private void ConnectDB() {

    try {

        Class.forName("com.mysql.cj.jdbc.Driver");

        conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/"
            + "flower_inventory_management"
            + "?useSSL=false&serverTimezone=UTC",
            "root",
            ""
        );

        System.out.println("Database connected!");

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
            this,
            "Database Connection Error:\n"
            + e.getMessage(),
            "Database Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
    
    
    private void loadFlowers() {

    panelFlowers.removeAll();

    String sql =
        "SELECT Flower_id, Flower_Name, Price, "
        + "Stock, Description, Image "
        + "FROM flowers "
        + "WHERE Stock > 0 "
        + "ORDER BY RAND() "
        + "LIMIT 8";

    try (
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery()
    ) {

        while (rs.next()) {

            int flowerId =
                rs.getInt("Flower_id");

            String name =
                rs.getString("Flower_Name");

            double price =
                rs.getDouble("Price");

            int stock =
                rs.getInt("Stock");

            String description =
                rs.getString("Description");

            String image =
                rs.getString("Image");

            createFlowerCard(
                flowerId,
                name,
                price,
                stock,
                description,
                image
            );
        }

        panelFlowers.revalidate();
        panelFlowers.repaint();

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            this,
            "Error loading flowers:\n"
            + e.getMessage()
        );
    }
}
    
    private void loadCategories() {

    panelFlowers.removeAll();

    String sql =
        "SELECT Category_id, Category_Name "
        + "FROM categories "
        + "ORDER BY Category_Name";

    try (
        PreparedStatement pst =
            conn.prepareStatement(sql);

        ResultSet rs =
            pst.executeQuery()
    ) {

        while (rs.next()) {

            int categoryId =
                rs.getInt("Category_id");

            String categoryName =
                rs.getString("Category_Name");

            JButton btnCategory =
                new JButton(categoryName);

            btnCategory.setPreferredSize(
                new Dimension(200, 60)
            );

            btnCategory.addActionListener(
                e -> loadFlowersByCategory(
                    categoryId,
                    categoryName
                )
            );

            panelFlowers.add(btnCategory);
        }

        panelFlowers.revalidate();
        panelFlowers.repaint();

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            this,
            "Error loading categories:\n"
            + e.getMessage()
        );
    }
}
    
    private void loadFlowersByCategory(
        int categoryId,
        String categoryName) {

    panelFlowers.removeAll();

    String sql =
        "SELECT Flower_id, Flower_Name, Price, "
        + "Stock, Description, Image "
        + "FROM flowers "
        + "WHERE Category_id = ? "
        + "AND Stock > 0";

    try (
        PreparedStatement pst =
            conn.prepareStatement(sql)
    ) {

        pst.setInt(1, categoryId);

        ResultSet rs =
            pst.executeQuery();

        while (rs.next()) {

            createFlowerCard(
                rs.getInt("Flower_id"),
                rs.getString("Flower_Name"),
                rs.getDouble("Price"),
                rs.getInt("Stock"),
                rs.getString("Description"),
                rs.getString("Image")
            );
        }

        panelFlowers.revalidate();
        panelFlowers.repaint();

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            this,
            "Error loading "
            + categoryName
            + " flowers:\n"
            + e.getMessage()
        );
    }
}
    
    private void loadOrders() {

    panelFlowers.removeAll();

    // Keep the original Home layout
    panelFlowers.setLayout(
        new GridLayout(0, 4, 20, 20)
    );

    panelFlowers.setBackground(
        java.awt.Color.WHITE
    );

    String sql =
        "SELECT Order_id, Order_Date, "
        + "Total_Amount, Status "
        + "FROM orders "
        + "WHERE User_id = ? "
        + "ORDER BY Order_Date DESC";

    try (
        PreparedStatement pst =
            conn.prepareStatement(sql)
    ) {

        pst.setInt(1, userId);

        ResultSet rs =
            pst.executeQuery();

        while (rs.next()) {

            int orderId =
                rs.getInt("Order_id");

            String orderDate =
                rs.getString("Order_Date");

            double total =
                rs.getDouble("Total_Amount");

            String status =
                rs.getString("Status");

            // =================================
            // ORDER CARD
            // =================================

            JPanel orderPanel =
                new JPanel(
                    new GridLayout(
                        4,
                        1,
                        5,
                        5
                    )
                );

            orderPanel.setBackground(
                java.awt.Color.WHITE
            );

            orderPanel.setBorder(
                BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(
                        java.awt.Color.LIGHT_GRAY,
                        1
                    ),
                    BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                    )
                )
            );

            // =================================
            // ORDER ID
            // =================================

            JLabel lblOrderId =
                new JLabel(
                    "Order #" + orderId,
                    JLabel.CENTER
                );

            lblOrderId.setFont(
                new java.awt.Font(
                    "Serif",
                    java.awt.Font.BOLD,
                    20
                )
            );

            lblOrderId.setForeground(
                new java.awt.Color(
                    0,
                    128,
                    0
                )
            );

            // =================================
            // DATE
            // =================================

            JLabel lblDate =
                new JLabel(
                    "Date: " + orderDate,
                    JLabel.CENTER
                );

            lblDate.setFont(
                new java.awt.Font(
                    "Serif",
                    java.awt.Font.PLAIN,
                    14
                )
            );

            lblDate.setForeground(
                java.awt.Color.DARK_GRAY
            );

            // =================================
            // TOTAL
            // =================================

            JLabel lblTotal =
                new JLabel(
                    "Total: ₱"
                    + String.format(
                        "%.2f",
                        total
                    ),
                    JLabel.CENTER
                );

            lblTotal.setFont(
                new java.awt.Font(
                    "Serif",
                    java.awt.Font.BOLD,
                    16
                )
            );

            lblTotal.setForeground(
                java.awt.Color.RED
            );

            // =================================
            // STATUS
            // =================================

            JLabel lblStatus =
                new JLabel(
                    "Status: " + status,
                    JLabel.CENTER
                );

            lblStatus.setFont(
                new java.awt.Font(
                    "Serif",
                    java.awt.Font.BOLD,
                    14
                )
            );

            // Status design
            if (status.equalsIgnoreCase("Pending")) {

                lblStatus.setForeground(
                    new java.awt.Color(
                        255,
                        140,
                        0
                    )
                );

            } else if (
                status.equalsIgnoreCase("Completed")
            ) {

                lblStatus.setForeground(
                    new java.awt.Color(
                        0,
                        128,
                        0
                    )
                );

            } else if (
                status.equalsIgnoreCase("Cancelled")
            ) {

                lblStatus.setForeground(
                    java.awt.Color.RED
                );

            } else {

                lblStatus.setForeground(
                    java.awt.Color.DARK_GRAY
                );
            }

            // =================================
            // ADD TO ORDER CARD
            // =================================

            orderPanel.add(
                lblOrderId
            );

            orderPanel.add(
                lblDate
            );

            orderPanel.add(
                lblTotal
            );

            orderPanel.add(
                lblStatus
            );

            // Add card
            panelFlowers.add(
                orderPanel
            );
        }

        // =================================
        // REFRESH PANEL
        // =================================

        panelFlowers.revalidate();
        panelFlowers.repaint();

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            this,
            "Error loading orders:\n"
            + e.getMessage(),
            "Database Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
    
    private void loadAccount() {

    panelFlowers.removeAll();

    // ==========================================
    // WHOLE ACCOUNT PANEL
    // ==========================================

    JPanel accountPanel =
        new JPanel(new BorderLayout());

    accountPanel.setBackground(
        java.awt.Color.WHITE
    );

    accountPanel.setBorder(
        BorderFactory.createEmptyBorder(
            40,
            60,
            40,
            60
        )
    );


    // ==========================================
    // TITLE
    // ==========================================

    JLabel lblTitle =
        new JLabel(
            "MY ACCOUNT",
            javax.swing.SwingConstants.CENTER
        );

    lblTitle.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.BOLD,
            32
        )
    );

    lblTitle.setForeground(
        new java.awt.Color(
            90,
            60,
            60
        )
    );


    // ==========================================
    // INFORMATION PANEL
    // ==========================================

    JPanel infoPanel =
        new JPanel(
            new GridLayout(
                6,
                1,
                0,
                10
            )
        );

    infoPanel.setBackground(
        java.awt.Color.WHITE
    );

    infoPanel.setBorder(
        BorderFactory.createLineBorder(
            new java.awt.Color(
                235,
                190,
                200
            ),
            2
        )
    );


    String sql =
        "SELECT Firstname, Lastname, Email, `Mobile Number`, "
        + "Address, `Security Question`, Answer "
        + "FROM users "
        + "WHERE User_id = ?";


    try (
        PreparedStatement pst =
            conn.prepareStatement(sql)
    ) {

        pst.setInt(
            1,
            userId
        );

        ResultSet rs =
            pst.executeQuery();


        if (rs.next()) {

            infoPanel.add(
                createAccountRow(
                    "Name:",
                    rs.getString("Firstname")
                )
            );
            
            infoPanel.add(
                createAccountRow(
                    "lastname",
                    rs.getString("Lastname")
                )
            );

            infoPanel.add(
                createAccountRow(
                    "Email:",
                    rs.getString("Email")
                )
            );

            infoPanel.add(
                createAccountRow(
                    "Mobile:",
                    rs.getString("Mobile Number")
                )
            );

            infoPanel.add(
                createAccountRow(
                    "Address:",
                    rs.getString("Address")
                )
            );

            infoPanel.add(
                createAccountRow(
                    "Security Question:",
                    rs.getString("Security Question")
                )
            );

            infoPanel.add(
                createAccountRow(
                    "Security Answer:",
                    rs.getString("Answer")
                )
            );
        }


    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            this,
            "Error loading account:\n"
            + e.getMessage(),
            "Database Error",
            JOptionPane.ERROR_MESSAGE
        );

        return;
    }


    // ==========================================
    // EDIT BUTTON
    // ==========================================

    JButton btnEdit =
        new JButton(
            "Edit Account"
        );

    btnEdit.setPreferredSize(
        new Dimension(
            200,
            50
        )
    );

    btnEdit.setBackground(
        new java.awt.Color(
            255,
            182,
            193
        )
    );

    btnEdit.setForeground(
        java.awt.Color.BLACK
    );

    btnEdit.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.BOLD,
            16
        )
    );

    btnEdit.setFocusPainted(false);

    btnEdit.setBorder(
        BorderFactory.createLineBorder(
            new java.awt.Color(
                240,
                150,
                165
            ),
            1
        )
    );

    btnEdit.addActionListener(
        e -> showEditAccountDialog()
    );


    // ==========================================
    // BUTTON PANEL
    // ==========================================

    JPanel buttonPanel =
        new JPanel(
            new FlowLayout(
                FlowLayout.CENTER
            )
        );

    buttonPanel.setBackground(
        java.awt.Color.WHITE
    );

    buttonPanel.add(
        btnEdit
    );
    // ==========================================
    // ADD TO WHOLE ACCOUNT PANEL
    // ==========================================
    accountPanel.add(
        lblTitle,
        BorderLayout.NORTH
    );

    accountPanel.add(
        infoPanel,
        BorderLayout.CENTER
    );

    accountPanel.add(
        buttonPanel,
        BorderLayout.SOUTH
    );
    // ==========================================
    // MAKE ACCOUNT PANEL FILL EVERYTHING
    // ==========================================
    panelFlowers.setLayout(
        new BorderLayout()
    );

    panelFlowers.setBackground(
        java.awt.Color.WHITE
    );

    panelFlowers.setBorder(
        BorderFactory.createEmptyBorder()
    );

    panelFlowers.add(
        accountPanel,
        BorderLayout.CENTER
    );


    panelFlowers.revalidate();
    panelFlowers.repaint();
}    
    private void showEditAccountDialog() {

    javax.swing.JTextField txtAddress =
        new javax.swing.JTextField();

    javax.swing.JTextField txtQuestion =
        new javax.swing.JTextField();

    javax.swing.JTextField txtAnswer =
        new javax.swing.JTextField();
    
    // =========================
    // LOAD CURRENT INFORMATION
    // =========================

    String sql =
        "SELECT Address, `Security Question`, Answer "
        + "FROM users "
        + "WHERE User_id = ?";


    try (
        PreparedStatement pst =
            conn.prepareStatement(sql)
    ) {

        pst.setInt(
            1,
            userId
        );

        ResultSet rs =
            pst.executeQuery();

        if (rs.next()) {

            String address =
                rs.getString("Address");

            String question =
                rs.getString(
                    "Security Question"
                );

            String answer =
                rs.getString("Answer");


            txtAddress.setText(
                address == null
                    ? ""
                    : address
            );

            txtQuestion.setText(
                question == null
                    ? ""
                    : question
            );

            txtAnswer.setText(
                answer == null
                    ? ""
                    : answer
            );
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            this,
            "Error loading account:\n"
            + e.getMessage(),
            "Database Error",
            JOptionPane.ERROR_MESSAGE
        );

        return;
    }


    // =========================
    // EDIT PANEL
    // =========================

    JPanel editPanel =
        new JPanel(
            new GridLayout(
                3,
                2,
                10,
                15
            )
        );

    editPanel.setBorder(
        BorderFactory.createEmptyBorder(
            10,
            10,
            10,
            10
        )
    );


    JLabel lblAddress =
        new JLabel(
            "Address:"
        );

    JLabel lblQuestion =
        new JLabel(
            "Security Question:"
        );

    JLabel lblAnswer =
        new JLabel(
            "Security Answer:"
        );


    lblAddress.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.BOLD,
            15
        )
    );

    lblQuestion.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.BOLD,
            15
        )
    );

    lblAnswer.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.BOLD,
            15
        )
    );


    editPanel.add(
        lblAddress
    );

    editPanel.add(
        txtAddress
    );


    editPanel.add(
        lblQuestion
    );

    editPanel.add(
        txtQuestion
    );


    editPanel.add(
        lblAnswer
    );

    editPanel.add(
        txtAnswer
    );


    // =========================
    // SHOW DIALOG
    // =========================

    int result =
        JOptionPane.showConfirmDialog(
            this,
            editPanel,
            "Edit Account",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );


    // =========================
    // SAVE
    // =========================

    if (result ==
        JOptionPane.OK_OPTION) {

        updateAccount(
            txtAddress.getText().trim(),
            txtQuestion.getText().trim(),
            txtAnswer.getText().trim()
        );
    }
}
    
    
    
    
    private JPanel createAccountRow(
        String title,
        String value) {

    JPanel row = new JPanel(
        new BorderLayout(
            15,
            0
        )
    );

    row.setBackground(
        java.awt.Color.WHITE
    );

    row.setBorder(
        BorderFactory.createEmptyBorder(
            8,
            10,
            8,
            10
        )
    );


    JLabel lblTitle =
        new JLabel(
            title
        );

    lblTitle.setPreferredSize(
        new Dimension(
            180,
            35
        )
    );

    lblTitle.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.BOLD,
            15
        )
    );

    lblTitle.setForeground(
        new java.awt.Color(
            90,
            60,
            60
        )
    );


    if (value == null ||
        value.trim().isEmpty()) {

        value = "Not provided";
    }


    JLabel lblValue =
        new JLabel(
            value
        );

    lblValue.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.PLAIN,
            16
        )
    );

    lblValue.setForeground(
        java.awt.Color.DARK_GRAY
    );


    row.add(
        lblTitle,
        BorderLayout.WEST
    );

    row.add(
        lblValue,
        BorderLayout.CENTER
    );


    return row;
}
    
    private void updateAccount(
        String address,
        String securityQuestion,
        String answer) {

    if (address.isEmpty()
            || securityQuestion.isEmpty()
            || answer.isEmpty()) {

        JOptionPane.showMessageDialog(
            this,
            "Please fill in all fields."
        );

        return;
    }

    String sql =
        "UPDATE users SET "
        + "Address = ?, "
        + "`Security Question` = ?, "
        + "Answer = ? "
        + "WHERE User_id = ?";

    try (
        PreparedStatement pst =
            conn.prepareStatement(sql)
    ) {

        pst.setString(1, address);
        pst.setString(2, securityQuestion);
        pst.setString(3, answer);
        pst.setInt(4, userId);

        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this,
            "Account information updated successfully!"
        );

        loadAccount();

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            this,
            "Error updating account:\n"
            + e.getMessage(),
            "Database Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
    
    
    private void loadCart() {

    panelFlowers.removeAll();

    panelFlowers.setLayout(
        new GridLayout(0, 4, 20, 20)
    );

    panelFlowers.setBackground(
        java.awt.Color.WHITE
    );

    String sql =
        "SELECT c.Cart_id, c.Flower_id, "
        + "f.Flower_Name, f.Price, "
        + "c.Quantity, "
        + "(f.Price * c.Quantity) AS Subtotal "
        + "FROM cart c "
        + "JOIN flowers f "
        + "ON c.Flower_id = f.Flower_id "
        + "WHERE c.User_id = ?";

    double total = 0;

    try (
        PreparedStatement pst =
            conn.prepareStatement(sql)
    ) {

        pst.setInt(1, userId);

        ResultSet rs =
            pst.executeQuery();

        while (rs.next()) {

            int cartId =
                rs.getInt("Cart_id");

            int flowerId =
                rs.getInt("Flower_id");

            String flowerName =
                rs.getString("Flower_Name");

            double price =
                rs.getDouble("Price");

            int quantity =
                rs.getInt("Quantity");

            double subtotal =
                rs.getDouble("Subtotal");

            total += subtotal;

            JPanel cartPanel =
                new JPanel(
                    new GridLayout(
                        5,
                        1,
                        5,
                        5
                    )
                );

            cartPanel.setBackground(
                java.awt.Color.WHITE
            );

            cartPanel.setBorder(
                BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(
                        new java.awt.Color(
                            220,
                            220,
                            220
                        ),
                        1
                    ),
                    BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                    )
                )
            );

            JLabel lblFlower =
                new JLabel(
                    "Flower: " + flowerName,
                    JLabel.CENTER
                );

            lblFlower.setFont(
                new java.awt.Font(
                    "Serif",
                    java.awt.Font.BOLD,
                    18
                )
            );

            lblFlower.setForeground(
                new java.awt.Color(
                    0,
                    128,
                    0
                )
            );

            JLabel lblPrice =
                new JLabel(
                    "Price: ₱"
                    + String.format(
                        "%.2f",
                        price
                    ),
                    JLabel.CENTER
                );

            lblPrice.setFont(
                new java.awt.Font(
                    "Serif",
                    java.awt.Font.BOLD,
                    14
                )
            );

            lblPrice.setForeground(
                java.awt.Color.RED
            );

            JLabel lblQuantity =
                new JLabel(
                    "Quantity: "
                    + quantity,
                    JLabel.CENTER
                );

            lblQuantity.setFont(
                new java.awt.Font(
                    "Serif",
                    java.awt.Font.PLAIN,
                    14
                )
            );

            JLabel lblSubtotal =
                new JLabel(
                    "Subtotal: ₱"
                    + String.format(
                        "%.2f",
                        subtotal
                    ),
                    JLabel.CENTER
                );

            lblSubtotal.setFont(
                new java.awt.Font(
                    "Serif",
                    java.awt.Font.BOLD,
                    15
                )
            );

            lblSubtotal.setForeground(
                java.awt.Color.RED
            );

            JPanel buttonPanel =
                new JPanel();

            buttonPanel.setBackground(
                java.awt.Color.WHITE
            );

            JButton btnMinus =
                new JButton("-");

            JButton btnPlus =
                new JButton("+");

            JButton btnRemove =
                new JButton("Remove");

            btnMinus.setBackground(
                new java.awt.Color(
                    255,
                    182,
                    193
                )
            );

            btnPlus.setBackground(
                new java.awt.Color(
                    255,
                    182,
                    193
                )
            );

            btnRemove.setBackground(
                new java.awt.Color(
                    255,
                    182,
                    193
                )
            );

            btnMinus.setFocusPainted(false);
            btnPlus.setFocusPainted(false);
            btnRemove.setFocusPainted(false);

            btnMinus.addActionListener(
                e -> updateCartQuantity(
                    cartId,
                    flowerId,
                    quantity - 1
                )
            );

            btnPlus.addActionListener(
                e -> updateCartQuantity(
                    cartId,
                    flowerId,
                    quantity + 1
                )
            );

            btnRemove.addActionListener(
                e -> removeFromCart(
                    cartId
                )
            );

            buttonPanel.add(btnMinus);
            buttonPanel.add(btnPlus);
            buttonPanel.add(btnRemove);

            cartPanel.add(lblFlower);
            cartPanel.add(lblPrice);
            cartPanel.add(lblQuantity);
            cartPanel.add(lblSubtotal);
            cartPanel.add(buttonPanel);

            panelFlowers.add(cartPanel);
        }

        JLabel lblTotal =
            new JLabel(
                "TOTAL: ₱"
                + String.format(
                    "%.2f",
                    total
                ),
                JLabel.CENTER
            );

        lblTotal.setFont(
            new java.awt.Font(
                "Serif",
                java.awt.Font.BOLD,
                20
            )
        );

        lblTotal.setForeground(
            java.awt.Color.RED
        );

        JButton btnPurchase =
            new JButton(
                "Purchase"
            );

        // SMALL PURCHASE BUTTON
        btnPurchase.setPreferredSize(
            new java.awt.Dimension(
                110,
                30
            )
        );

        btnPurchase.setBackground(
            new java.awt.Color(
                255,
                182,
                193
            )
        );

        btnPurchase.setForeground(
            java.awt.Color.BLACK
        );

        btnPurchase.setFont(
            new java.awt.Font(
                "Serif",
                java.awt.Font.BOLD,
                14
            )
        );

        btnPurchase.setFocusPainted(
            false
        );

        final double finalTotal =
            total;

        btnPurchase.addActionListener(
            e -> purchaseOrder(
                finalTotal
            )
        );

        JPanel checkoutPanel =
            new JPanel(
                new GridLayout(
                    2,
                    1
                )
            );

        checkoutPanel.setBackground(
            java.awt.Color.WHITE
        );

        checkoutPanel.add(lblTotal);
        checkoutPanel.add(btnPurchase);

        panelFlowers.add(
            checkoutPanel
        );

        panelFlowers.revalidate();
        panelFlowers.repaint();

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            this,
            "Error loading cart:\n"
            + e.getMessage(),
            "Database Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
    
    private void updateCartQuantity(
        int cartId,
        int flowerId,
        int newQuantity) {

    if (newQuantity <= 0) {

        removeFromCart(cartId);

        return;
    }

    String stockSql =
        "SELECT Stock FROM flowers "
        + "WHERE Flower_id = ?";

    try (
        PreparedStatement stockPst =
            conn.prepareStatement(stockSql)
    ) {

        stockPst.setInt(1, flowerId);

        ResultSet rs =
            stockPst.executeQuery();

        if (rs.next()) {

            int stock =
                rs.getInt("Stock");

            if (newQuantity > stock) {

                JOptionPane.showMessageDialog(
                    this,
                    "You cannot exceed the available stock."
                );

                return;
            }
        }

        String sql =
            "UPDATE cart "
            + "SET Quantity = ? "
            + "WHERE Cart_id = ? "
            + "AND User_id = ?";

        try (
            PreparedStatement pst =
                conn.prepareStatement(sql)
        ) {

            pst.setInt(1, newQuantity);
            pst.setInt(2, cartId);
            pst.setInt(3, userId);

            pst.executeUpdate();
        }

        loadCart();

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            this,
            "Error updating cart:\n"
            + e.getMessage()
        );
    }
}
    
    private void removeFromCart(int cartId) {

    int choice =
        JOptionPane.showConfirmDialog(
            this,
            "Remove this flower from your cart?",
            "Remove Item",
            JOptionPane.YES_NO_OPTION
        );

    if (choice != JOptionPane.YES_OPTION) {
        return;
    }

    String sql =
        "DELETE FROM cart "
        + "WHERE Cart_id = ? "
        + "AND User_id = ?";

    try (
        PreparedStatement pst =
            conn.prepareStatement(sql)
    ) {

        pst.setInt(1, cartId);
        pst.setInt(2, userId);

        pst.executeUpdate();

        loadCart();

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            this,
            "Error removing item:\n"
            + e.getMessage()
        );
    }
}
    
    
    private void purchaseOrder(double total) {

    if (total <= 0) {

        JOptionPane.showMessageDialog(
            this,
            "Your cart is empty."
        );

        return;
    }

    int choice =
        JOptionPane.showConfirmDialog(
            this,
            "Total amount: ₱"
            + String.format("%.2f", total)
            + "\n\nProceed with purchase?",
            "Confirm Purchase",
            JOptionPane.YES_NO_OPTION
        );

    if (choice != JOptionPane.YES_OPTION) {
        return;
    }

    try {

        conn.setAutoCommit(false);

        String orderSql =
            "INSERT INTO orders "
            + "(User_id, Total_Amount, Status) "
            + "VALUES (?, ?, ?)";

        int orderId;

        try (
            PreparedStatement orderPst =
                conn.prepareStatement(
                    orderSql,
                    java.sql.Statement.RETURN_GENERATED_KEYS
                )
        ) {

            orderPst.setInt(1, userId);
            orderPst.setDouble(2, total);
            orderPst.setString(3, "Pending");

            orderPst.executeUpdate();

            ResultSet keys =
                orderPst.getGeneratedKeys();

            if (!keys.next()) {

                throw new SQLException(
                    "Could not create order."
                );
            }

            orderId =
                keys.getInt(1);
        }

        String cartSql =
            "SELECT Flower_id, Quantity "
            + "FROM cart "
            + "WHERE User_id = ?";

        try (
            PreparedStatement cartPst =
                conn.prepareStatement(cartSql)
        ) {

            cartPst.setInt(1, userId);

            ResultSet cartRs =
                cartPst.executeQuery();

            while (cartRs.next()) {

                int flowerId =
                    cartRs.getInt("Flower_id");

                int quantity =
                    cartRs.getInt("Quantity");

                String priceSql =
                    "SELECT Price, Stock "
                    + "FROM flowers "
                    + "WHERE Flower_id = ?";

                double price;
                int stock;

                try (
                    PreparedStatement pricePst =
                        conn.prepareStatement(priceSql)
                ) {

                    pricePst.setInt(1, flowerId);

                    ResultSet flowerRs =
                        pricePst.executeQuery();

                    if (!flowerRs.next()) {

                        throw new SQLException(
                            "Flower not found."
                        );
                    }

                    price =
                        flowerRs.getDouble("Price");

                    stock =
                        flowerRs.getInt("Stock");
                }

                if (quantity > stock) {

                    throw new SQLException(
                        "Not enough stock available."
                    );
                }

                String itemSql =
                    "INSERT INTO order_items "
                    + "(Order_id, Flower_id, Quantity, Price) "
                    + "VALUES (?, ?, ?, ?)";

                try (
                    PreparedStatement itemPst =
                        conn.prepareStatement(itemSql)
                ) {

                    itemPst.setInt(1, orderId);
                    itemPst.setInt(2, flowerId);
                    itemPst.setInt(3, quantity);
                    itemPst.setDouble(4, price);

                    itemPst.executeUpdate();
                }

                String stockSql =
                    "UPDATE flowers "
                    + "SET Stock = Stock - ? "
                    + "WHERE Flower_id = ?";

                try (
                    PreparedStatement stockPst =
                        conn.prepareStatement(stockSql)
                ) {

                    stockPst.setInt(1, quantity);
                    stockPst.setInt(2, flowerId);

                    stockPst.executeUpdate();
                }
            }
        }

        String clearCartSql =
            "DELETE FROM cart "
            + "WHERE User_id = ?";

        try (
            PreparedStatement clearPst =
                conn.prepareStatement(clearCartSql)
        ) {

            clearPst.setInt(1, userId);

            clearPst.executeUpdate();
        }

        conn.commit();

        conn.setAutoCommit(true);

        JOptionPane.showMessageDialog(
            this,
            "Purchase successful!\n"
            + "Order ID: " + orderId,
            "Purchase Complete",
            JOptionPane.INFORMATION_MESSAGE
        );

        loadCart();

    } catch (SQLException e) {

        try {
            conn.rollback();
            conn.setAutoCommit(true);
        } catch (SQLException rollbackError) {
            rollbackError.printStackTrace();
        }

        JOptionPane.showMessageDialog(
            this,
            "Purchase failed:\n"
            + e.getMessage(),
            "Purchase Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
    
    
    
    
    private void createFlowerCard(
        int flowerId,
        String name,
        double price,
        int stock,
        String description,
        String image) {

    // =========================
    // MAIN FLOWER CARD
    // =========================
    JPanel card = new JPanel();
    card.setLayout(new BorderLayout());

    card.setPreferredSize(
        new Dimension(220, 300)
    );

    card.setBackground(
        java.awt.Color.WHITE
    );

    card.setBorder(
        BorderFactory.createLineBorder(
            new java.awt.Color(210, 210, 210),
            1
        )
    );

    // =========================
    // IMAGE PANEL
    // =========================
    JPanel imagePanel = new JPanel(
        new BorderLayout()
    );

    imagePanel.setBackground(
        java.awt.Color.WHITE
    );

    imagePanel.setPreferredSize(
        new Dimension(220, 160)
    );

    // =========================
    // FLOWER IMAGE
    // =========================
    JLabel lblImage = new JLabel();

    lblImage.setHorizontalAlignment(
        JLabel.CENTER
    );

    lblImage.setVerticalAlignment(
        JLabel.CENTER
    );

    if (image != null && !image.trim().isEmpty()) {

        java.net.URL imageURL =
            getClass().getResource(
                "/images/" + image.trim()
            );

        if (imageURL != null) {

            ImageIcon icon =
                new ImageIcon(imageURL);

            Image img =
                icon.getImage().getScaledInstance(
                    180,
                    140,
                    Image.SCALE_SMOOTH
                );

            lblImage.setIcon(
                new ImageIcon(img)
            );

        } else {

            lblImage.setText(
                "Image not found"
            );
        }

    } else {

        lblImage.setText(
            "No Image"
        );
    }

    imagePanel.add(
        lblImage,
        BorderLayout.CENTER
    );

    // =========================
    // BOTTOM INFORMATION PANEL
    // =========================
    JPanel infoPanel = new JPanel(
        new GridLayout(4, 1, 5, 5)
    );

    infoPanel.setBackground(
        java.awt.Color.WHITE
    );

    infoPanel.setBorder(
        BorderFactory.createMatteBorder(
            1,
            0,
            0,
            0,
            new java.awt.Color(210, 210, 210)
        )
    );

    // =========================
    // FLOWER NAME
    // =========================
    JLabel lblName = new JLabel(
        "<html><center>"
        + name
        + "</center></html>"
    );

    lblName.setHorizontalAlignment(
        JLabel.CENTER
    );

    lblName.setForeground(
        new java.awt.Color(0, 128, 0)
    );

    lblName.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.BOLD,
            18
        )
    );

    // =========================
    // PRICE
    // =========================
    JLabel lblPrice = new JLabel(
        "Price: ₱"
        + String.format(
            "%.2f",
            price
        )
    );

    lblPrice.setHorizontalAlignment(
        JLabel.CENTER
    );

    lblPrice.setForeground(
        java.awt.Color.RED
    );

    lblPrice.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.BOLD,
            16
        )
    );

    // =========================
    // STOCK
    // =========================
    JLabel lblStock = new JLabel(
        "Stock: " + stock
    );

    lblStock.setHorizontalAlignment(
        JLabel.CENTER
    );

    lblStock.setForeground(
        new java.awt.Color(0, 128, 0)
    );

    lblStock.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.PLAIN,
            15
        )
    );

    // =========================
    // ADD TO CART BUTTON
    // =========================
    JButton btnAddCart =
        new JButton("Add to Cart");

    btnAddCart.setBackground(
        new java.awt.Color(255, 182, 193)
    );

    btnAddCart.setForeground(
        java.awt.Color.BLACK
    );

    btnAddCart.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.BOLD,
            15
        )
    );

    btnAddCart.setFocusPainted(false);

    btnAddCart.setBorder(
        BorderFactory.createLineBorder(
            new java.awt.Color(240, 150, 165),
            1
        )
    );

    btnAddCart.addActionListener(
        e -> addToCart(
            flowerId,
            stock
        )
    );

    // =========================
    // ADD COMPONENTS
    // =========================
    infoPanel.add(lblName);
    infoPanel.add(lblPrice);
    infoPanel.add(lblStock);
    infoPanel.add(btnAddCart);

    // =========================
    // ADD TO CARD
    // =========================
    card.add(
        imagePanel,
        BorderLayout.CENTER
    );

    card.add(
        infoPanel,
        BorderLayout.SOUTH
    );

    // =========================
    // ADD CARD TO FLOWER PANEL
    // =========================
    panelFlowers.add(card);
}
    
    private void addToCart(
        int flowerId,
        int stock) {

    String checkSql =
        "SELECT Quantity FROM cart "
        + "WHERE User_id = ? "
        + "AND Flower_id = ?";

    try (
        PreparedStatement check =
            conn.prepareStatement(checkSql)
    ) {

        check.setInt(1, userId);
        check.setInt(2, flowerId);

        ResultSet rs =
            check.executeQuery();

        if (rs.next()) {

            int currentQuantity =
                rs.getInt("Quantity");

            if (currentQuantity >= stock) {

                JOptionPane.showMessageDialog(
                    this,
                    "You cannot add more than the available stock."
                );

                return;
            }

            String updateSql =
                "UPDATE cart "
                + "SET Quantity = Quantity + 1 "
                + "WHERE User_id = ? "
                + "AND Flower_id = ?";

            try (
                PreparedStatement update =
                    conn.prepareStatement(updateSql)
            ) {

                update.setInt(1, userId);
                update.setInt(2, flowerId);

                update.executeUpdate();
            }

        } else {

            String insertSql =
                "INSERT INTO cart "
                + "(User_id, Flower_id, Quantity) "
                + "VALUES (?, ?, 1)";

            try (
                PreparedStatement insert =
                    conn.prepareStatement(insertSql)
            ) {

                insert.setInt(1, userId);
                insert.setInt(2, flowerId);

                insert.executeUpdate();
            }
        }

        JOptionPane.showMessageDialog(
            this,
            "Flower added to cart!"
        );

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            this,
            "Error adding to cart:\n"
            + e.getMessage(),
            "Database Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ScrollFlowers = new javax.swing.JScrollPane();
        jLabel3 = new javax.swing.JLabel();
        btnAccount = new javax.swing.JButton();
        btnCart = new javax.swing.JButton();
        btnViewOrder = new javax.swing.JButton();
        btnCategory = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(ScrollFlowers, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, 1110, 550));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo-removebg-preview.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 240, 180));

        btnAccount.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        btnAccount.setText("Account");
        btnAccount.addActionListener(this::btnAccountActionPerformed);
        getContentPane().add(btnAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 500, 150, -1));

        btnCart.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        btnCart.setText("View Cart");
        btnCart.addActionListener(this::btnCartActionPerformed);
        getContentPane().add(btnCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 440, 150, -1));

        btnViewOrder.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        btnViewOrder.setText("View Order");
        btnViewOrder.addActionListener(this::btnViewOrderActionPerformed);
        getContentPane().add(btnViewOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, 150, -1));

        btnCategory.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        btnCategory.setText("Category");
        btnCategory.addActionListener(this::btnCategoryActionPerformed);
        getContentPane().add(btnCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 150, -1));

        btnHome.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        btnHome.setText("Home");
        btnHome.addActionListener(this::btnHomeActionPerformed);
        getContentPane().add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 150, -1));

        btnLogout.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);
        getContentPane().add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 700, 150, -1));

        jLabel1.setFont(new java.awt.Font("Serif", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("Flower Inventory Management and Ordering system");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gemini_Generated_Image_a32pqwa32pqwa32p.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
        
        loadFlowers();
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        
        int choice =
        JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to logout?",
            "Logout",
            JOptionPane.YES_NO_OPTION
        );

    if (choice == JOptionPane.YES_OPTION) {

        new CustomerLogin().setVisible(true);

        this.dispose();
    }    

    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoryActionPerformed
        // TODO add your handling code here:
        loadCategories();
    }//GEN-LAST:event_btnCategoryActionPerformed

    private void btnViewOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewOrderActionPerformed
        // TODO add your handling code here:
        loadOrders();
    }//GEN-LAST:event_btnViewOrderActionPerformed

    private void btnAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccountActionPerformed
        // TODO add your handling code here:
        loadAccount();
    }//GEN-LAST:event_btnAccountActionPerformed

    private void btnCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCartActionPerformed
        // TODO add your handling code here:
        
        loadCart();
    }//GEN-LAST:event_btnCartActionPerformed

    /**
     * @param args the command line arguments
     */
    

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new HomePage(1).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollFlowers;
    private javax.swing.JButton btnAccount;
    private javax.swing.JButton btnCart;
    private javax.swing.JButton btnCategory;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnViewOrder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
