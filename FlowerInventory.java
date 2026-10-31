/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gamolo
 */
public class FlowerInventory extends javax.swing.JFrame {
    private Connection conn;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FlowerInventory.class.getName());

    /**
     * Creates new form FlowerInventory
     */
    
    
    
    public FlowerInventory() {
        initComponents();

    // =========================
    // ADMIN BUTTON DESIGN
    // EXACTLY THE SAME AS
    // USER HOMEPAGE
    // =========================

    btnDashboard.setBackground(
        new java.awt.Color(255, 182, 193)
    );

    btnFlowers.setBackground(
        new java.awt.Color(255, 182, 193)
    );

    btnCategories.setBackground(
        new java.awt.Color(255, 182, 193)
    );

    btnOrders.setBackground(
        new java.awt.Color(255, 182, 193)
    );

    btnUsers.setBackground(
        new java.awt.Color(255, 182, 193)
    );

    btnLogout.setBackground(
        new java.awt.Color(255, 182, 193)
    );


    // =========================
    // BUTTON TEXT COLOR
    // SAME AS USER HOMEPAGE
    // =========================

    btnDashboard.setForeground(
        java.awt.Color.BLACK
    );

    btnFlowers.setForeground(
        java.awt.Color.BLACK
    );

    btnCategories.setForeground(
        java.awt.Color.BLACK
    );

    btnOrders.setForeground(
        java.awt.Color.BLACK
    );

    btnUsers.setForeground(
        java.awt.Color.BLACK
    );

    btnLogout.setForeground(
        java.awt.Color.BLACK
    );


    // =========================
    // SERIF FONT
    // SAME AS USER HOMEPAGE
    // =========================

    btnDashboard.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.BOLD,
            15
        )
    );

    btnFlowers.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.BOLD,
            15
        )
    );

    btnCategories.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.BOLD,
            15
        )
    );

    btnOrders.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.BOLD,
            15
        )
    );

    btnUsers.setFont(
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
    // SAME AS USER HOMEPAGE
    // =========================

    btnDashboard.setFocusPainted(false);
    btnFlowers.setFocusPainted(false);
    btnCategories.setFocusPainted(false);
    btnOrders.setFocusPainted(false);
    btnUsers.setFocusPainted(false);
    btnLogout.setFocusPainted(false);


    // =========================
    // STARTUP
    // =========================

    setLocationRelativeTo(null);

    setExtendedState(
        javax.swing.JFrame.MAXIMIZED_BOTH
    );

    ConnectDB();

    showDashboard();
}
    
    
    
    
    
    
    
    
    
    private void clearContentPanel() {

    panelContent.removeAll();
    panelContent.setLayout(new BorderLayout());
    panelContent.revalidate();
    panelContent.repaint();
}
    
    private void showDashboard() {
    clearContentPanel();

    // ==========================================
    // MAIN DASHBOARD PANEL
    // ==========================================

    panelContent.setLayout(
        new org.netbeans.lib.awtextra.AbsoluteLayout()
    );

    panelContent.setBackground(
        new java.awt.Color(255, 255, 255)
    );

    // ==========================================
    // DASHBOARD TITLE
    // ==========================================

    JLabel title =
        new JLabel("ADMIN DASHBOARD");

    title.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.BOLD,
            30
        )
    );

    title.setForeground(
        new java.awt.Color(
            80,
            50,
            50
        )
    );

    title.setHorizontalAlignment(
        javax.swing.SwingConstants.CENTER
    );

    panelContent.add(
        title,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(
            0,
            20,
            1070,
            50
        )
    );

    // ==========================================
    // LOAD CURRENT COUNTS
    // ==========================================

    loadDashboard();

    // ==========================================
    // FLOWER CARD
    // ==========================================

    JPanel flowerCard =
        createDashboardCard(
            "FLOWERS",
            lblFlowerCount,
            new java.awt.Color(
                255,
                228,
                235
            )
        );

    panelContent.add(
        flowerCard,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(
            100,
            110,
            350,
            170
        )
    );

    // ==========================================
    // CATEGORY CARD
    // ==========================================

    JPanel categoryCard =
        createDashboardCard(
            "CATEGORIES",
            lblCategoryCount,
            new java.awt.Color(
                235,
                225,
                255
            )
        );

    panelContent.add(
        categoryCard,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(
            620,
            110,
            350,
            170
        )
    );

    // ==========================================
    // USER CARD
    // ==========================================

    JPanel userCard =
        createDashboardCard(
            "USERS",
            lblUserCount,
            new java.awt.Color(
                225,
                245,
                235
            )
        );

    panelContent.add(
        userCard,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(
            100,
            330,
            350,
            170
        )
    );

    // ==========================================
    // ORDER CARD
    // ==========================================

    JPanel orderCard =
        createDashboardCard(
            "ORDERS",
            lblOrderCount,
            new java.awt.Color(
                255,
                240,
                215
            )
        );

    panelContent.add(
        orderCard,
        new org.netbeans.lib.awtextra.AbsoluteConstraints(
            620,
            330,
            350,
            170
        )
    );

    // ==========================================
    // REFRESH
    // ==========================================

    panelContent.revalidate();
    panelContent.repaint();
}
    
    private JPanel createDashboardCard(
        String title,
        JLabel countLabel,
        java.awt.Color backgroundColor) {

    JPanel card =
        new JPanel();

    card.setLayout(
        new java.awt.BorderLayout()
    );

    card.setBackground(
        backgroundColor
    );

    card.setBorder(
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
                15,
                15,
                15,
                15
            )
        )
    );

    // ==========================================
    // CARD TITLE
    // ==========================================

    JLabel lblTitle =
        new JLabel(
            title,
            javax.swing.SwingConstants.CENTER
        );

    lblTitle.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.BOLD,
            22
        )
    );

    lblTitle.setForeground(
        new java.awt.Color(
            70,
            50,
            50
        )
    );

    // ==========================================
    // COUNT
    // ==========================================

    countLabel.setFont(
        new java.awt.Font(
            "Serif",
            java.awt.Font.BOLD,
            42
        )
    );

    countLabel.setForeground(
        new java.awt.Color(
            60,
            60,
            60
        )
    );

    countLabel.setHorizontalAlignment(
        javax.swing.SwingConstants.CENTER
    );

    // ==========================================
    // ADD TO CARD
    // ==========================================

    card.add(
        lblTitle,
        java.awt.BorderLayout.NORTH
    );

    card.add(
        countLabel,
        java.awt.BorderLayout.CENTER
    );

    return card;
}
    
    
    
    
    
    private void showCategories() {

    clearContentPanel();

    // ==========================================
    // MAIN PANEL
    // ==========================================

    JPanel mainPanel = new JPanel(
        new BorderLayout(15, 15)
    );

    mainPanel.setBackground(Color.WHITE);

    mainPanel.setBorder(
        BorderFactory.createEmptyBorder(
            25,
            30,
            25,
            30
        )
    );

    // ==========================================
    // TITLE
    // ==========================================

    JLabel title = new JLabel(
        "CATEGORY MANAGEMENT",
        javax.swing.SwingConstants.CENTER
    );

    title.setFont(
        new Font(
            "Serif",
            Font.BOLD,
            30
        )
    );

    title.setForeground(
        new Color(
            80,
            50,
            50
        )
    );

    mainPanel.add(
        title,
        BorderLayout.NORTH
    );

    // ==========================================
    // TABLE MODEL
    // ==========================================

    DefaultTableModel model =
        new DefaultTableModel(
            new Object[]{
                "Category ID",
                "Category Name"
            },
            0
        ) {

            @Override
            public boolean isCellEditable(
                int row,
                int column
            ) {
                return false;
            }
        };

    // ==========================================
    // TABLE
    // ==========================================

    JTable table =
        new JTable(model);

    table.setRowHeight(35);

    table.setFont(
        new Font(
            "Serif",
            Font.PLAIN,
            15
        )
    );

    table.setForeground(
        Color.BLACK
    );

    table.setBackground(
        Color.WHITE
    );

    table.setGridColor(
        new Color(
            220,
            220,
            220
        )
    );

    table.setShowGrid(true);

    table.setSelectionBackground(
        new Color(
            255,
            218,
            225
        )
    );

    table.setSelectionForeground(
        Color.BLACK
    );

    // ==========================================
    // TABLE HEADER
    // ==========================================

    javax.swing.table.JTableHeader header =
        table.getTableHeader();

    header.setPreferredSize(
        new java.awt.Dimension(
            0,
            40
        )
    );

    header.setFont(
        new Font(
            "Serif",
            Font.BOLD,
            16
        )
    );

    header.setBackground(
        new Color(
            255,
            182,
            193
        )
    );

    header.setForeground(
        Color.BLACK
    );

    header.setReorderingAllowed(false);

    // ==========================================
    // COLUMN WIDTH
    // ==========================================

    table.getColumnModel()
        .getColumn(0)
        .setPreferredWidth(150);

    table.getColumnModel()
        .getColumn(1)
        .setPreferredWidth(400);

    // ==========================================
    // SCROLL PANE
    // ==========================================

    JScrollPane scrollPane =
        new JScrollPane(table);

    scrollPane.setBackground(
        Color.WHITE
    );

    scrollPane.getViewport()
        .setBackground(
            Color.WHITE
        );

    scrollPane.setBorder(
        BorderFactory.createLineBorder(
            new Color(
                220,
                220,
                220
            )
        )
    );

    mainPanel.add(
        scrollPane,
        BorderLayout.CENTER
    );

    // ==========================================
    // BUTTON PANEL
    // ==========================================

    JPanel buttonPanel =
        new JPanel(
            new FlowLayout(
                FlowLayout.CENTER,
                20,
                5
            )
        );

    buttonPanel.setBackground(
        Color.WHITE
    );

    // ==========================================
    // BUTTONS
    // ==========================================

    JButton btnAdd =
        new JButton(
            "Add Category"
        );

    JButton btnEdit =
        new JButton(
            "Edit Category"
        );

    JButton btnArchive =
    new JButton("Archive Category");

JButton btnViewArchive =
    new JButton("View Archived Categories");

    // ==========================================
    // BUTTON DESIGN
    // ==========================================

    JButton[] buttons = {
    btnAdd,
    btnEdit,
    btnArchive,
    btnViewArchive
};

    for (JButton button : buttons) {

        button.setPreferredSize(
            new java.awt.Dimension(
                150,
                35
            )
        );

        button.setBackground(
            new Color(
                255,
                182,
                193
            )
        );

        button.setForeground(
            Color.BLACK
        );

        button.setFont(
            new Font(
                "Serif",
                Font.BOLD,
                15
            )
        );

        button.setFocusPainted(false);
    }

    // ==========================================
    // ADD CATEGORY
    // ==========================================

    btnAdd.addActionListener(e -> {

        JTextField txtCategory =
            new JTextField();

        txtCategory.setFont(
            new Font(
                "Serif",
                Font.PLAIN,
                15
            )
        );

        int result =
            JOptionPane.showConfirmDialog(
                this,
                txtCategory,
                "Add Category",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );

        if (result ==
            JOptionPane.OK_OPTION) {

            String categoryName =
                txtCategory.getText().trim();

            if (categoryName.isEmpty()) {

                JOptionPane.showMessageDialog(
                    this,
                    "Please enter a category name.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            try {

                String sql =
                    "INSERT INTO categories " +
                    "(Category_Name) VALUES (?)";

                PreparedStatement pst =
                    conn.prepareStatement(sql);

                pst.setString(
                    1,
                    categoryName
                );

                pst.executeUpdate();

                pst.close();

                JOptionPane.showMessageDialog(
                    this,
                    "Category added successfully!"
                );

                showCategories();

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(
                    this,
                    "Error adding category:\n"
                    + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    });

    // ==========================================
    // EDIT CATEGORY
    // ==========================================

    btnEdit.addActionListener(e -> {

        int selectedRow =
            table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Please select a category first.",
                "Edit Category",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int categoryId =
            Integer.parseInt(
                model.getValueAt(
                    selectedRow,
                    0
                ).toString()
            );

        String oldName =
            model.getValueAt(
                selectedRow,
                1
            ).toString();

        JTextField txtCategory =
            new JTextField(
                oldName
            );

        txtCategory.setFont(
            new Font(
                "Serif",
                Font.PLAIN,
                15
            )
        );

        int result =
            JOptionPane.showConfirmDialog(
                this,
                txtCategory,
                "Edit Category",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );

        if (result ==
            JOptionPane.OK_OPTION) {

            String newName =
                txtCategory.getText().trim();

            if (newName.isEmpty()) {

                JOptionPane.showMessageDialog(
                    this,
                    "Category name cannot be empty.",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            try {

                String sql =
                    "UPDATE categories " +
                    "SET Category_Name = ? " +
                    "WHERE Category_id = ?";

                PreparedStatement pst =
                    conn.prepareStatement(sql);

                pst.setString(
                    1,
                    newName
                );

                pst.setInt(
                    2,
                    categoryId
                );

                pst.executeUpdate();

                pst.close();

                JOptionPane.showMessageDialog(
                    this,
                    "Category updated successfully!"
                );

                showCategories();

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(
                    this,
                    "Error updating category:\n"
                    + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    });

    // ==========================================
    // DELETE CATEGORY
    // ==========================================

    btnArchive.addActionListener(e -> {

    int selectedRow = table.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(
            this,
            "Please select a category first.",
            "Archive Category",
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    int modelRow = table.convertRowIndexToModel(selectedRow);

    int categoryId = Integer.parseInt(
        model.getValueAt(modelRow, 0).toString()
    );

    String categoryName =
        model.getValueAt(modelRow, 1).toString();

    int confirm = JOptionPane.showConfirmDialog(
        this,
        "Archive category \"" + categoryName + "\"?",
        "Archive Category",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE
    );

    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    String sql =
        "UPDATE categories "
        + "SET Is_Archived = 1 "
        + "WHERE Category_id = ? "
        + "AND Is_Archived = 0";

    try (PreparedStatement pst = conn.prepareStatement(sql)) {

        pst.setInt(1, categoryId);

        int updated = pst.executeUpdate();

        if (updated > 0) {
            JOptionPane.showMessageDialog(
                this,
                "Category archived successfully!"
            );
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Category was not found or is already archived."
            );
        }

        showCategories();

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(
            this,
            "Error archiving category:\n" + ex.getMessage(),
            "Archive Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
    
});
    
    

    buttonPanel.add(btnAdd);
buttonPanel.add(btnEdit);
buttonPanel.add(btnArchive);
buttonPanel.add(btnViewArchive);

    mainPanel.add(
        buttonPanel,
        BorderLayout.SOUTH
    );

    // ==========================================
    // LOAD CATEGORIES
    // ==========================================

    try {

        String sql =
    "SELECT Category_id, Category_Name "
    + "FROM categories "
    + "WHERE Is_Archived = 0 "
    + "ORDER BY Category_id";

        PreparedStatement pst =
            conn.prepareStatement(sql);

        ResultSet rs =
            pst.executeQuery();

        while (rs.next()) {

            model.addRow(
                new Object[]{
                    rs.getInt("Category_id"),
                    rs.getString("Category_Name")
                }
            );
        }

        rs.close();
        pst.close();

    } catch (SQLException ex) {

        JOptionPane.showMessageDialog(
            this,
            "Error loading categories:\n"
            + ex.getMessage(),
            "Database Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    // ==========================================
    // DISPLAY PANEL
    // ==========================================

    panelContent.setLayout(
        new BorderLayout()
    );

    panelContent.setBackground(
        Color.WHITE
    );

    panelContent.add(
        mainPanel,
        BorderLayout.CENTER
    );

    panelContent.revalidate();
    panelContent.repaint();
}
    private void showArchivedCategories() {

    DefaultTableModel model = new DefaultTableModel(
        new Object[]{"Category ID", "Category Name"}, 0
    );

    JTable table = new JTable(model);

    String sql =
        "SELECT Category_id, Category_Name "
        + "FROM categories "
        + "WHERE Is_Archived = 1 "
        + "ORDER BY Category_id";

    try (
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery()
    ) {
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("Category_id"),
                rs.getString("Category_Name")
            });
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(
            this,
            "Error loading archived categories:\n"
                + ex.getMessage()
        );
        return;
    }

    JTableHeaderStyle(table);

    JButton btnRestore = new JButton("Restore Category");

    btnRestore.addActionListener(e -> {

        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(
                this,
                "Please select a category to restore."
            );
            return;
        }

        int modelRow = table.convertRowIndexToModel(row);

        int categoryId = Integer.parseInt(
            model.getValueAt(modelRow, 0).toString()
        );

        restoreCategory(categoryId);
    });

    JPanel panel = new JPanel(new BorderLayout(10, 10));

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setPreferredSize(
        new java.awt.Dimension(600, 300)
    );

    panel.add(scrollPane, BorderLayout.CENTER);
    panel.add(btnRestore, BorderLayout.SOUTH);

    JOptionPane.showMessageDialog(
        this,
        panel,
        "Archived Categories",
        JOptionPane.PLAIN_MESSAGE
    );
}

private void JTableHeaderStyle(JTable table) {

    table.setRowHeight(30);

    table.getTableHeader().setFont(
        new Font("Serif", Font.BOLD, 15)
    );

    table.getTableHeader().setBackground(
        new Color(255, 182, 193)
    );
}

private void restoreCategory(int categoryId) {

    String sql =
        "UPDATE categories "
        + "SET Is_Archived = 0 "
        + "WHERE Category_id = ? "
        + "AND Is_Archived = 1";

    try (PreparedStatement pst = conn.prepareStatement(sql)) {

        pst.setInt(1, categoryId);

        int updated = pst.executeUpdate();

        if (updated > 0) {
            JOptionPane.showMessageDialog(
                this,
                "Category restored successfully!"
            );
        } else {
            JOptionPane.showMessageDialog(
                this,
                "Archived category was not found."
            );
        }

        showCategories();

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(
            this,
            "Error restoring category:\n" + ex.getMessage(),
            "Restore Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
    
    
    
    private void addCategory(String categoryName) {
    String sql = "INSERT INTO categories (Category_Name) VALUES (?)";

    try (PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setString(1, categoryName);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(this, "Category added successfully!");

        loadCategoryCount();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(
            this,
            "Error adding category:\n" + e.getMessage(),
            "Database Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
    
    
    private void loadCategoryTable(
        DefaultTableModel model) {

    model.setRowCount(0);

    String sql =
        "SELECT Category_id, Category_Name "
        + "FROM categories "
        + "ORDER BY Category_id";

    try (
        PreparedStatement pst =
            conn.prepareStatement(sql);
        ResultSet rs =
            pst.executeQuery()
    ) {

        while (rs.next()) {

            model.addRow(
                new Object[]{
                    rs.getInt("Category_id"),
                    rs.getString("Category_Name")
                }
            );
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            this,
            "Error loading categories:\n"
            + e.getMessage()
        );
    }
}
    
    private void addCategory(
        String categoryName,
        DefaultTableModel model,
        JTextField txtCategoryName) {

    String sql =
        "INSERT INTO categories (Category_Name) "
        + "VALUES (?)";

    try (
        PreparedStatement pst =
            conn.prepareStatement(sql)
    ) {

        pst.setString(1, categoryName);

        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this,
            "Category added successfully!"
        );

        txtCategoryName.setText("");

        loadCategoryTable(model);

        loadCategoryCount();

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            this,
            "Error adding category:\n"
            + e.getMessage()
        );
    }
}
    
    private void deleteCategory(int categoryId) {
    String sql = "DELETE FROM categories WHERE Category_id = ?";

    try (PreparedStatement pst = conn.prepareStatement(sql)) {

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this category?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        pst.setInt(1, categoryId);
        pst.executeUpdate();

        JOptionPane.showMessageDialog(this, "Category deleted successfully!");

        loadCategoryCount();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(
            this,
            "Error deleting category:\n" + e.getMessage(),
            "Database Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
    
    
    private void editCategory(
        int categoryId,
        String currentName) {

    JTextField txtCategory =
            new JTextField(currentName);

    JPanel panel =
            new JPanel(new GridLayout(2, 2, 10, 10));

    panel.add(new JLabel("Category Name:"));
    panel.add(txtCategory);

    int result = JOptionPane.showConfirmDialog(
            this,
            panel,
            "Edit Category",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
    );

    if (result != JOptionPane.OK_OPTION) {
        return;
    }

    String newName =
            txtCategory.getText().trim();

    if (newName.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Category name cannot be empty."
        );

        return;
    }

    String sql =
            "UPDATE categories "
            + "SET Category_Name = ? "
            + "WHERE Category_id = ?";

    try (PreparedStatement pst =
                 conn.prepareStatement(sql)) {

        pst.setString(1, newName);
        pst.setInt(2, categoryId);

        pst.executeUpdate();

        JOptionPane.showMessageDialog(
                this,
                "Category updated successfully!"
        );

        loadCategoryCount();

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Error updating category:\n"
                + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
    
    private void deleteCategory(
        int categoryId,
        DefaultTableModel model) {

    int choice =
        JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this category?",
            "Delete Category",
            JOptionPane.YES_NO_OPTION
        );

    if (choice != JOptionPane.YES_OPTION) {
        return;
    }

    String sql =
        "DELETE FROM categories "
        + "WHERE Category_id = ?";

    try (
        PreparedStatement pst =
            conn.prepareStatement(sql)
    ) {

        pst.setInt(1, categoryId);

        pst.executeUpdate();

        JOptionPane.showMessageDialog(
            this,
            "Category deleted successfully!"
        );

        loadCategoryTable(model);

        loadCategoryCount();

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            this,
            "Cannot delete this category.\n"
            + "It may already have flowers assigned to it.\n\n"
            + e.getMessage()
        );
    }
}
    
    
    
    private void showFlowers() {

    clearContentPanel();

    JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
    mainPanel.setBackground(Color.WHITE);
    mainPanel.setBorder(
        BorderFactory.createEmptyBorder(25, 30, 25, 30)
    );

    JLabel title = new JLabel(
        "FLOWER MANAGEMENT",
        javax.swing.SwingConstants.CENTER
    );

    title.setFont(new Font("Serif", Font.BOLD, 30));
    title.setForeground(new Color(80, 50, 50));

    mainPanel.add(title, BorderLayout.NORTH);

    DefaultTableModel model = new DefaultTableModel(
        new Object[]{
            "Flower ID",
            "Flower Name",
            "Category",
            "Price",
            "Stock"
        },
        0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    JTable table = new JTable(model);

    table.setRowHeight(35);
    table.setFont(new Font("Serif", Font.PLAIN, 15));
    table.setForeground(Color.BLACK);
    table.setBackground(Color.WHITE);
    table.setGridColor(new Color(220, 220, 220));
    table.setShowGrid(true);

    table.setSelectionBackground(
        new Color(255, 218, 225)
    );

    table.setSelectionForeground(Color.BLACK);

    javax.swing.table.JTableHeader header =
        table.getTableHeader();

    header.setPreferredSize(
        new java.awt.Dimension(0, 40)
    );

    header.setFont(
        new Font("Serif", Font.BOLD, 16)
    );

    header.setBackground(
        new Color(255, 182, 193)
    );

    header.setForeground(Color.BLACK);
    header.setReorderingAllowed(false);

    JScrollPane scrollPane = new JScrollPane(table);

    scrollPane.setBackground(Color.WHITE);

    scrollPane.getViewport().setBackground(
        Color.WHITE
    );

    scrollPane.setBorder(
        BorderFactory.createLineBorder(
            new Color(220, 220, 220)
        )
    );

    mainPanel.add(
        scrollPane,
        BorderLayout.CENTER
    );

    JPanel buttonPanel = new JPanel(
        new FlowLayout(
            FlowLayout.CENTER,
            20,
            5
        )
    );

    buttonPanel.setBackground(Color.WHITE);

    JButton btnAdd = new JButton("Add Flower");
    JButton btnEdit = new JButton("Edit Flower");
    JButton btnArchive = new JButton("Archive Flower");
    JButton btnViewArchive = new JButton("View Archived Flowers");

    JButton[] buttons = {
      btnAdd,
      btnEdit,
      btnArchive,
      btnViewArchive      
    };

    for (JButton button : buttons) {

        button.setPreferredSize(
            new java.awt.Dimension(150, 35)
        );

        button.setBackground(
            new Color(255, 182, 193)
        );

        button.setForeground(Color.BLACK);

        button.setFont(
            new Font("Serif", Font.BOLD, 15)
        );

        button.setFocusPainted(false);
    }

    btnAdd.addActionListener(e -> {
        showAddFlowerDialog(model);
    });

    btnEdit.addActionListener(e -> {

        int selectedRow =
            table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Please select a flower first.",
                "Edit Flower",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int flowerId =
            Integer.parseInt(
                model.getValueAt(
                    selectedRow,
                    0
                ).toString()
            );

        showEditFlowerDialog(
            flowerId,
            model
        );
    });

    
    
    btnArchive.addActionListener(e -> {

    int selectedRow = table.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(
            this,
            "Please select a flower first.",
            "Archive Flower",
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    int flowerId = Integer.parseInt(
        model.getValueAt(selectedRow, 0).toString()
    );

    archiveFlower(flowerId);
});
    
    btnViewArchive.addActionListener(e -> showArchivedFlowers());
    
    

    buttonPanel.add(btnAdd);
    buttonPanel.add(btnEdit);
    buttonPanel.add(btnArchive);
    buttonPanel.add(btnViewArchive);

    mainPanel.add(
        buttonPanel,
        BorderLayout.SOUTH
    );

    loadFlowerTable(model);

    panelContent.setLayout(
        new BorderLayout()
    );

    panelContent.setBackground(
        Color.WHITE
    );

    panelContent.add(
        mainPanel,
        BorderLayout.CENTER
    );

    panelContent.revalidate();
    panelContent.repaint();
}
    
    
    private void showAddFlowerDialog(DefaultTableModel model) {

    JTextField txtName =
            new JTextField();

    JComboBox<String> cmbCategory =
            new JComboBox<>();

    JTextField txtPrice =
            new JTextField();

    JTextField txtStock =
            new JTextField();

    JTextField txtDescription =
            new JTextField();

    JTextField txtImage =
            new JTextField();

    java.util.Map<String, Integer>
            categoryMap =
            new java.util.HashMap<>();

    String categorySQL =
    "SELECT Category_id, Category_Name "
    + "FROM categories "
    + "WHERE Is_Archived = 0 "
    + "ORDER BY Category_Name";

    try (PreparedStatement pst =
                 conn.prepareStatement(categorySQL);
         ResultSet rs =
                 pst.executeQuery()) {

        while (rs.next()) {

            int id =
                    rs.getInt("Category_id");

            String name =
                    rs.getString("Category_Name");

            cmbCategory.addItem(name);

            categoryMap.put(
                    name,
                    id
            );
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Error loading categories:\n"
                + e.getMessage()
        );

        return;
    }

    JPanel panel =
            new JPanel(
                    new GridLayout(
                            6,
                            2,
                            10,
                            10
                    )
            );

    panel.add(
            new JLabel("Flower Name:")
    );
    panel.add(txtName);

    panel.add(
            new JLabel("Category:")
    );
    panel.add(cmbCategory);

    panel.add(
            new JLabel("Price:")
    );
    panel.add(txtPrice);

    panel.add(
            new JLabel("Stock:")
    );
    panel.add(txtStock);

    panel.add(
            new JLabel("Description:")
    );
    panel.add(txtDescription);

    panel.add(
            new JLabel("Image Path:")
    );
    panel.add(txtImage);

    int result =
            JOptionPane.showConfirmDialog(
                    this,
                    panel,
                    "Add Flower",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

    if (result != JOptionPane.OK_OPTION) {
        return;
    }

    try {

        String name =
                txtName.getText().trim();

        double price =
                Double.parseDouble(
                        txtPrice.getText().trim()
                );

        int stock =
                Integer.parseInt(
                        txtStock.getText().trim()
                );

        String description =
                txtDescription.getText().trim();

        String image =
                txtImage.getText().trim();

        if (name.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the flower name."
            );

            return;
        }

        if (cmbCategory.getSelectedItem()
                == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a category."
            );

            return;
        
            
            
        }

        String categoryName =
                cmbCategory
                        .getSelectedItem()
                        .toString();

        int categoryId =
                categoryMap.get(categoryName);
        
        String checkSQL =
                "SELECT COUNT(*) AS total "
                + "FROM flowers "
                + "WHERE LOWER(Flower_Name) = LOWER(?) "
                + "AND Category_id = ?";

        try (PreparedStatement checkPst =
                     conn.prepareStatement(checkSQL)) {

            checkPst.setString(
                    1,
                    name
            );

            checkPst.setInt(
                    2,
                    categoryId
            );

            ResultSet checkRs =
                    checkPst.executeQuery();

            if (checkRs.next()
                    && checkRs.getInt("total") > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "This flower already exists "
                        + "in this category!\n\n"
                        + "Flower Name: "
                        + name,
                        "Duplicate Flower",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }
        }


        // =====================================
        // INSERT FLOWER
        // =====================================

        String sql =
                "INSERT INTO flowers "
                + "(Flower_Name, Category_id, "
                + "Price, Stock, Description, Image) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pst =
                     conn.prepareStatement(sql)) {

            pst.setString(
                    1,
                    name
            );

            pst.setInt(
                    2,
                    categoryId
            );

            pst.setDouble(
                    3,
                    price
            );

            pst.setInt(
                    4,
                    stock
            );

            pst.setString(
                    5,
                    description
            );

            pst.setString(
                    6,
                    image
            );

            pst.executeUpdate();
        }


        // =====================================
        // SUCCESS
        // =====================================

        JOptionPane.showMessageDialog(
                this,
                "Flower added successfully!"
        );

        loadFlowerTable(model);
        loadFlowerCount();


    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Price and Stock must be valid numbers."
        );

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Error adding flower:\n"
                + e.getMessage()
        );
    }
}
    
    
    
    private void showEditFlowerDialog(
        int flowerId,
        DefaultTableModel model) {

    JTextField txtName =
            new JTextField();

    JComboBox<String> cmbCategory =
            new JComboBox<>();

    JTextField txtPrice =
            new JTextField();

    JTextField txtStock =
            new JTextField();

    JTextField txtDescription =
            new JTextField();

    JTextField txtImage =
            new JTextField();

    java.util.Map<String, Integer>
            categoryMap =
            new java.util.HashMap<>();

    // Load categories
    String categorySQL =
            "SELECT Category_id, Category_Name "
            + "FROM categories "
            + "ORDER BY Category_Name";

    try (PreparedStatement pst =
                 conn.prepareStatement(categorySQL);
         ResultSet rs =
                 pst.executeQuery()) {

        while (rs.next()) {

            int id =
                    rs.getInt("Category_id");

            String name =
                    rs.getString("Category_Name");

            cmbCategory.addItem(name);

            categoryMap.put(
                    name,
                    id
            );
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Error loading categories:\n"
                + e.getMessage()
        );

        return;
    }

    // Load existing flower
    String flowerSQL =
            "SELECT Flower_Name, Category_id, "
            + "Price, Stock, Description, Image "
            + "FROM flowers "
            + "WHERE Flower_id = ?";

    try (PreparedStatement pst =
                 conn.prepareStatement(flowerSQL)) {

        pst.setInt(1, flowerId);

        ResultSet rs =
                pst.executeQuery();

        if (rs.next()) {

            txtName.setText(
                    rs.getString("Flower_Name")
            );

            txtPrice.setText(
                    String.valueOf(
                            rs.getDouble("Price")
                    )
            );

            txtStock.setText(
                    String.valueOf(
                            rs.getInt("Stock")
                    )
            );

            txtDescription.setText(
                    rs.getString("Description")
            );

            txtImage.setText(
                    rs.getString("Image")
            );

            int categoryId =
                    rs.getInt("Category_id");

            String categoryNameSQL =
                    "SELECT Category_Name "
                    + "FROM categories "
                    + "WHERE Category_id = ?";

            try (PreparedStatement cp =
                         conn.prepareStatement(
                                 categoryNameSQL)) {

                cp.setInt(1, categoryId);

                ResultSet crs =
                        cp.executeQuery();

                if (crs.next()) {

                    cmbCategory.setSelectedItem(
                            crs.getString(
                                    "Category_Name"
                            )
                    );
                }
            }
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Error loading flower:\n"
                + e.getMessage()
        );

        return;
    }

    JPanel panel =
            new JPanel(
                    new GridLayout(
                            6,
                            2,
                            10,
                            10
                    )
            );

    panel.add(
            new JLabel("Flower Name:")
    );
    panel.add(txtName);

    panel.add(
            new JLabel("Category:")
    );
    panel.add(cmbCategory);

    panel.add(
            new JLabel("Price:")
    );
    panel.add(txtPrice);

    panel.add(
            new JLabel("Stock:")
    );
    panel.add(txtStock);

    panel.add(
            new JLabel("Description:")
    );
    panel.add(txtDescription);

    panel.add(
            new JLabel("Image Path:")
    );
    panel.add(txtImage);

    int result =
            JOptionPane.showConfirmDialog(
                    this,
                    panel,
                    "Edit Flower",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

    if (result != JOptionPane.OK_OPTION) {
        return;
    }

    try {

        String name =
                txtName.getText().trim();

        double price =
                Double.parseDouble(
                        txtPrice.getText().trim()
                );

        int stock =
                Integer.parseInt(
                        txtStock.getText().trim()
                );

        String description =
                txtDescription.getText().trim();

        String image =
                txtImage.getText().trim();

        String categoryName =
                cmbCategory
                        .getSelectedItem()
                        .toString();

        int categoryId =
                categoryMap.get(categoryName);

        String sql =
                "UPDATE flowers SET "
                + "Flower_Name = ?, "
                + "Category_id = ?, "
                + "Price = ?, "
                + "Stock = ?, "
                + "Description = ?, "
                + "Image = ? "
                + "WHERE Flower_id = ?";

        try (PreparedStatement pst =
                     conn.prepareStatement(sql)) {

            pst.setString(1, name);
            pst.setInt(2, categoryId);
            pst.setDouble(3, price);
            pst.setInt(4, stock);
            pst.setString(5, description);
            pst.setString(6, image);
            pst.setInt(7, flowerId);

            pst.executeUpdate();
        }

        JOptionPane.showMessageDialog(
                this,
                "Flower updated successfully!"
        );

        loadFlowerTable(model);
        loadFlowerCount();

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
                this,
                "Price and Stock must be valid numbers."
        );

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Error updating flower:\n"
                + e.getMessage()
        );
    }
}
    
    private void deleteFlower(
        int flowerId,
        DefaultTableModel model) {

    int choice =
            JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this flower?",
                    "Delete Flower",
                    JOptionPane.YES_NO_OPTION
            );

    if (choice != JOptionPane.YES_OPTION) {
        return;
    }

    String sql =
            "DELETE FROM flowers "
            + "WHERE Flower_id = ?";

    try (PreparedStatement pst =
                 conn.prepareStatement(sql)) {

        pst.setInt(1, flowerId);

        pst.executeUpdate();

        JOptionPane.showMessageDialog(
                this,
                "Flower deleted successfully!"
        );

        loadFlowerTable(model);
        loadFlowerCount();

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Cannot delete this flower.\n\n"
                + "This flower may already be used "
                + "in an existing order.",
                "Delete Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
    
    private void archiveFlower(int flowerId) {

    int confirm = JOptionPane.showConfirmDialog(
        this,
        "Move this flower to the archive?",
        "Archive Flower",
        JOptionPane.YES_NO_OPTION
    );

    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    String insertSQL =
        "INSERT INTO archive "
        + "(Flower_id, Flower_Name, Category_id, "
        + "Price, Stock, Description, Image) "
        + "SELECT Flower_id, Flower_Name, Category_id, "
        + "Price, Stock, Description, Image "
        + "FROM flowers WHERE Flower_id = ?";

    String deleteSQL =
        "DELETE FROM flowers WHERE Flower_id = ?";

    try {
        conn.setAutoCommit(false);

        try (PreparedStatement insert =
                 conn.prepareStatement(insertSQL);
             PreparedStatement delete =
                 conn.prepareStatement(deleteSQL)) {

            insert.setInt(1, flowerId);

            int inserted = insert.executeUpdate();

            if (inserted == 0) {
                conn.rollback();
                JOptionPane.showMessageDialog(
                    this, "Flower not found."
                );
                return;
            }

            delete.setInt(1, flowerId);
            delete.executeUpdate();

            conn.commit();

            JOptionPane.showMessageDialog(
                this,
                "Flower moved to archive successfully!"
            );

        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.setAutoCommit(true);
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(
            this,
            "Could not archive flower:\n"
            + ex.getMessage(),
            "Archive Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    showFlowers();
    loadFlowerCount();
}
    private void showArchivedFlowers() {

    DefaultTableModel model = new DefaultTableModel(
        new Object[]{
            "Flower ID",
            "Flower Name",
            "Category ID",
            "Price",
            "Stock",
            "Description",
            "Image"
        }, 0
    );

    JTable table = new JTable(model);

    String sql = "SELECT Flower_id, Flower_Name, Category_id, "
               + "Price, Stock, Description, Image "
               + "FROM archive ORDER BY Flower_id";

    try (
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery()
    ) {
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("Flower_id"),
                rs.getString("Flower_Name"),
                rs.getInt("Category_id"),
                rs.getDouble("Price"),
                rs.getInt("Stock"),
                rs.getString("Description"),
                rs.getString("Image")
            });
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(
            this,
            "Error loading archived flowers:\n" + ex.getMessage()
        );
        return;
    }

    JButton btnRestore = new JButton("Restore Flower");

    btnRestore.addActionListener(e -> {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(
                this,
                "Please select a flower first."
            );
            return;
        }

        int flowerId = Integer.parseInt(
            model.getValueAt(row, 0).toString()
        );

        restoreFlower(flowerId);
    });

    JPanel panel = new JPanel(new BorderLayout());

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setPreferredSize(new java.awt.Dimension(750, 300));

    panel.add(scrollPane, BorderLayout.CENTER);
    panel.add(btnRestore, BorderLayout.SOUTH);

    JOptionPane.showMessageDialog(
        this,
        panel,
        "Archived Flowers",
        JOptionPane.PLAIN_MESSAGE
    );
}


private void restoreFlower(int flowerId) {

    String insertSQL =
        "INSERT INTO flowers "
        + "(Flower_id, Flower_Name, Category_id, "
        + "Price, Stock, Description, Image) "
        + "SELECT Flower_id, Flower_Name, Category_id, "
        + "Price, Stock, Description, Image "
        + "FROM archive WHERE Flower_id = ?";

    String deleteSQL =
        "DELETE FROM archive WHERE Flower_id = ?";

    try {
        conn.setAutoCommit(false);

        try {
            int inserted;

            try (PreparedStatement pst =
                    conn.prepareStatement(insertSQL)) {

                pst.setInt(1, flowerId);
                inserted = pst.executeUpdate();
            }

            if (inserted != 1) {
                throw new SQLException(
                    "Archived flower was not found."
                );
            }

            try (PreparedStatement pst =
                    conn.prepareStatement(deleteSQL)) {

                pst.setInt(1, flowerId);

                if (pst.executeUpdate() != 1) {
                    throw new SQLException(
                        "Could not remove the archived record."
                    );
                }
            }

            conn.commit();

        } catch (SQLException ex) {
            conn.rollback();
            throw ex;

        } finally {
            conn.setAutoCommit(true);
        }

        JOptionPane.showMessageDialog(
            this,
            "Flower restored successfully!"
        );

        showFlowers();
        loadFlowerCount();

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(
            this,
            "Error restoring flower:\n" + ex.getMessage(),
            "Restore Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
    
    
    
    
    private void showUsers() {

    clearContentPanel();

    // ==========================================
    // MAIN PANEL
    // ==========================================

    JPanel mainPanel = new JPanel(
        new BorderLayout(15, 15)
    );

    mainPanel.setBackground(Color.WHITE);

    mainPanel.setBorder(
        BorderFactory.createEmptyBorder(
            25,
            30,
            25,
            30
        )
    );

    // ==========================================
    // TITLE
    // ==========================================

    JLabel title = new JLabel(
        "USER MANAGEMENT",
        javax.swing.SwingConstants.CENTER
    );

    title.setFont(
        new Font(
            "Serif",
            Font.BOLD,
            30
        )
    );

    title.setForeground(
        new Color(
            80,
            50,
            50
        )
    );

    mainPanel.add(
        title,
        BorderLayout.NORTH
    );

    // ==========================================
    // TABLE MODEL
    // ==========================================

    DefaultTableModel model =
        new DefaultTableModel(
            new Object[]{
                "User ID",
                "Firstname",
                "Lastname",
                "Email",
                "Mobile Number",
                "Address",
                "Role"
            },
            0
        ) {

            @Override
            public boolean isCellEditable(
                int row,
                int column
            ) {

                // User ID cannot be edited
                return column != 0;
            }
        };

    // ==========================================
    // TABLE
    // ==========================================

    JTable table =
        new JTable(model);

    table.setRowHeight(35);

    table.setFont(
        new Font(
            "Serif",
            Font.PLAIN,
            14
        )
    );

    table.setForeground(
        Color.BLACK
    );

    table.setBackground(
        Color.WHITE
    );

    table.setGridColor(
        new Color(
            220,
            220,
            220
        )
    );

    table.setShowGrid(true);

    table.setSelectionBackground(
        new Color(
            255,
            218,
            225
        )
    );

    table.setSelectionForeground(
        Color.BLACK
    );

    // ==========================================
    // TABLE HEADER
    // ==========================================

    javax.swing.table.JTableHeader header =
        table.getTableHeader();

    header.setPreferredSize(
        new java.awt.Dimension(
            0,
            40
        )
    );

    header.setFont(
        new Font(
            "Serif",
            Font.BOLD,
            16
        )
    );

    header.setBackground(
        new Color(
            255,
            182,
            193
        )
    );

    header.setForeground(
        Color.BLACK
    );

    header.setReorderingAllowed(false);

    // ==========================================
    // SCROLL PANE
    // ==========================================

    JScrollPane scrollPane =
        new JScrollPane(table);

    scrollPane.setBackground(
        Color.WHITE
    );

    scrollPane.getViewport()
        .setBackground(
            Color.WHITE
        );

    scrollPane.setBorder(
        BorderFactory.createLineBorder(
            new Color(
                220,
                220,
                220
            )
        )
    );

    mainPanel.add(
        scrollPane,
        BorderLayout.CENTER
    );

    // ==========================================
    // BUTTON PANEL
    // ==========================================

    JPanel buttonPanel =
        new JPanel(
            new FlowLayout(
                FlowLayout.CENTER,
                20,
                5
            )
        );

    buttonPanel.setBackground(
        Color.WHITE
    );

    // ==========================================
    // SAVE BUTTON
    // ==========================================

    JButton btnSave =
        new JButton(
            "Save Changes"
        );

    // ==========================================
    // DELETE BUTTON
    // ==========================================

    JButton btnDelete =
        new JButton(
            "Delete User"
        );

    // ==========================================
    // BUTTON DESIGN
    // ==========================================

    JButton[] buttons = {
        btnSave,
        btnDelete
    };

    for (JButton button : buttons) {

        button.setPreferredSize(
            new java.awt.Dimension(
                150,
                35
            )
        );

        button.setBackground(
            new Color(
                255,
                182,
                193
            )
        );

        button.setForeground(
            Color.BLACK
        );

        button.setFont(
            new Font(
                "Serif",
                Font.BOLD,
                15
            )
        );

        button.setFocusPainted(false);
    }

    // ==========================================
    // SAVE CHANGES
    // ==========================================

    btnSave.addActionListener(e -> {

        int selectedRow = table.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(
            this,
            "Please select an admin first.",
            "Save Changes",
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    // Convert selected table row to the correct model row
    int modelRow = table.convertRowIndexToModel(selectedRow);

    try {
        int userId = Integer.parseInt(
            model.getValueAt(modelRow, 0).toString()
        );

        String firstName =
            model.getValueAt(modelRow, 1).toString().trim();

        String lastName =
            model.getValueAt(modelRow, 2).toString().trim();

        String email =
            model.getValueAt(modelRow, 3).toString().trim();

        String mobile =
            model.getValueAt(modelRow, 4).toString().trim();

        String address =
            model.getValueAt(modelRow, 5).toString().trim();

        if (firstName.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Firstname cannot be empty."
            );
            return;
        }

        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Email cannot be empty."
            );
            return;
        }

        String sql =
            "UPDATE users SET "
            + "Firstname = ?, "
            + "Lastname = ?, "
            + "Email = ?, "
            + "`Mobile Number` = ?, "
            + "Address = ? "
            + "WHERE User_id = ? "
            + "AND LOWER(Role) = 'admin'";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, email);
            pst.setString(4, mobile);
            pst.setString(5, address);
            pst.setInt(6, userId);

            int updated = pst.executeUpdate();

            if (updated > 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "Admin information updated successfully!"
                );
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "No admin account was updated."
                );
            }
        }

        showUsers();

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(
            this,
            "Error updating admin:\n" + ex.getMessage(),
            "Database Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
});

    // ==========================================
    // DELETE USER
    // ==========================================

    btnDelete.addActionListener(e -> {

        int selectedRow =
            table.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Please select a user first.",
                "Delete User",
                JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int userId =
            Integer.parseInt(
                model.getValueAt(
                    selectedRow,
                    0
                ).toString()
            );

        String userName =
            model.getValueAt(
                selectedRow,
                1
            ).toString();

        int confirm =
            JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete\n"
                + "user: " + userName + "?",
                "Delete User",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

        if (confirm != JOptionPane.YES_OPTION) {

            return;
        }

        try {

            String sql =
                "DELETE FROM users " +
                "WHERE User_id = ?";

            PreparedStatement pst =
                conn.prepareStatement(sql);

            pst.setInt(
                1,
                userId
            );

            pst.executeUpdate();

            pst.close();

            JOptionPane.showMessageDialog(
                this,
                "User deleted successfully!"
            );

            showUsers();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                this,
                "Cannot delete this user.\n"
                + "The user may have existing orders or "
                + "other related records.\n\n"
                + ex.getMessage(),
                "Delete Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    });
    
    

    buttonPanel.add(
        btnSave
    );

    buttonPanel.add(
        btnDelete
    );

    mainPanel.add(
        buttonPanel,
        BorderLayout.SOUTH
    );

    // ==========================================
    // LOAD USERS FROM DATABASE
    // ==========================================

    try {

        String sql =
            "SELECT User_id, Firstname, Lastname, Email, "
          + "`Mobile Number`, Address, Role "
          + "FROM users "
          + "WHERE LOWER(Role) = 'admin' "
          + "ORDER BY User_id";

        PreparedStatement pst =
            conn.prepareStatement(sql);

        ResultSet rs =
            pst.executeQuery();

        while (rs.next()) {

            model.addRow(
                new Object[]{
                    rs.getInt("User_id"),
                    rs.getString("Firstname"),
                    rs.getString("Lastname"),
                    rs.getString("Email"),
                    rs.getString("Mobile Number"),
                    rs.getString("Address"),
                    rs.getString("Role")
                }
            );
        }

        rs.close();
        pst.close();

    } catch (SQLException ex) {

        JOptionPane.showMessageDialog(
            this,
            "Error loading users:\n"
            + ex.getMessage(),
            "Database Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    // ==========================================
    // DISPLAY PANEL
    // ==========================================

    panelContent.setLayout(
        new BorderLayout()
    );

    panelContent.setBackground(
        Color.WHITE
    );

    panelContent.add(
        mainPanel,
        BorderLayout.CENTER
    );

    panelContent.revalidate();
    panelContent.repaint();
}
    
    
    
    
    private void showOrders() {

    clearContentPanel();

    // ==========================================
    // MAIN PANEL
    // ==========================================

    JPanel mainPanel = new JPanel(
        new BorderLayout(15, 15)
    );

    mainPanel.setBackground(Color.WHITE);

    mainPanel.setBorder(
        BorderFactory.createEmptyBorder(
            25,
            30,
            25,
            30
        )
    );

    // ==========================================
    // TITLE
    // ==========================================

    JLabel title = new JLabel(
        "ORDER MANAGEMENT",
        javax.swing.SwingConstants.CENTER
    );

    title.setFont(
        new Font(
            "Serif",
            Font.BOLD,
            30
        )
    );

    title.setForeground(
        new Color(
            80,
            50,
            50
        )
    );

    mainPanel.add(
        title,
        BorderLayout.NORTH
    );

    // ==========================================
    // ORDER PANEL
    // ==========================================

    JPanel orderPanel = new JPanel();

    orderPanel.setLayout(
        new BoxLayout(
            orderPanel,
            BoxLayout.Y_AXIS
        )
    );

    orderPanel.setBackground(
        Color.WHITE
    );

    // ==========================================
    // SCROLL PANE
    // ==========================================

    JScrollPane scrollPane =
        new JScrollPane(orderPanel);

    scrollPane.setBackground(
        Color.WHITE
    );

    scrollPane.getViewport()
        .setBackground(
            Color.WHITE
        );

    scrollPane.setBorder(
        BorderFactory.createLineBorder(
            new Color(
                220,
                220,
                220
            )
        )
    );

    mainPanel.add(
        scrollPane,
        BorderLayout.CENTER
    );

    // ==========================================
    // LOAD ORDERS
    // ==========================================

    try {

        String sql =
            "SELECT Order_id, User_id, Order_Date, " +
            "Total_Amount, Status " +
            "FROM orders " +
            "ORDER BY Order_Date DESC";

        PreparedStatement pst =
            conn.prepareStatement(sql);

        ResultSet rs =
            pst.executeQuery();

        while (rs.next()) {

            int orderId =
                rs.getInt("Order_id");

            int userId =
                rs.getInt("User_id");

            String orderDate =
                rs.getString("Order_Date");

            double totalAmount =
                rs.getDouble("Total_Amount");

            String status =
                rs.getString("Status");

            // ==================================
            // ORDER CARD
            // ==================================

            JPanel orderCard =
                new JPanel(
                    new BorderLayout(
                        15,
                        10
                    )
                );

            orderCard.setBackground(
                Color.WHITE
            );

            orderCard.setBorder(
                BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(
                        new Color(
                            220,
                            220,
                            220
                        )
                    ),
                    BorderFactory.createEmptyBorder(
                        15,
                        20,
                        15,
                        20
                    )
                )
            );

            orderCard.setMaximumSize(
                new java.awt.Dimension(
                    Integer.MAX_VALUE,
                    150
                )
            );

            // ==================================
            // LEFT INFORMATION PANEL
            // ==================================

            JPanel infoPanel =
                new JPanel();

            infoPanel.setLayout(
                new BoxLayout(
                    infoPanel,
                    BoxLayout.Y_AXIS
                )
            );

            infoPanel.setBackground(
                Color.WHITE
            );

            JLabel lblOrder =
                new JLabel(
                    "Order #" + orderId
                );

            lblOrder.setFont(
                new Font(
                    "Serif",
                    Font.BOLD,
                    20
                )
            );

            lblOrder.setForeground(
                new Color(
                    0,
                    128,
                    0
                )
            );

            JLabel lblUser =
                new JLabel(
                    "Customer ID: " + userId
                );

            lblUser.setFont(
                new Font(
                    "Serif",
                    Font.PLAIN,
                    14
                )
            );

            lblUser.setForeground(
                new Color(
                    70,
                    70,
                    70
                )
            );

            JLabel lblDate =
                new JLabel(
                    "Date: " + orderDate
                );

            lblDate.setFont(
                new Font(
                    "Serif",
                    Font.PLAIN,
                    14
                )
            );

            lblDate.setForeground(
                new Color(
                    70,
                    70,
                    70
                )
            );

            JLabel lblTotal =
                new JLabel(
                    String.format(
                        "Total: ₱%.2f",
                        totalAmount
                    )
                );

            lblTotal.setFont(
                new Font(
                    "Serif",
                    Font.BOLD,
                    16
                )
            );

            lblTotal.setForeground(
                Color.RED
            );

            infoPanel.add(lblOrder);
            infoPanel.add(
                Box.createVerticalStrut(5)
            );
            infoPanel.add(lblUser);
            infoPanel.add(
                Box.createVerticalStrut(3)
            );
            infoPanel.add(lblDate);
            infoPanel.add(
                Box.createVerticalStrut(5)
            );
            infoPanel.add(lblTotal);

            orderCard.add(
                infoPanel,
                BorderLayout.CENTER
            );

            // ==================================
            // RIGHT STATUS PANEL
            // ==================================

            JPanel statusPanel =
                new JPanel();

            statusPanel.setLayout(
                new BoxLayout(
                    statusPanel,
                    BoxLayout.Y_AXIS
                )
            );

            statusPanel.setBackground(
                Color.WHITE
            );

            JLabel lblStatus =
                new JLabel(
                    "STATUS"
                );

            lblStatus.setFont(
                new Font(
                    "Serif",
                    Font.BOLD,
                    14
                )
            );

            lblStatus.setForeground(
                new Color(
                    80,
                    80,
                    80
                )
            );

            JComboBox<String> cmbStatus =
                new JComboBox<>(
                    new String[]{
                        "Pending",
                        "Approved",
                        "Completed",
                        "Cancelled"
                    }
                );

            cmbStatus.setSelectedItem(
                status
            );

            cmbStatus.setFont(
                new Font(
                    "Serif",
                    Font.PLAIN,
                    14
                )
            );

            cmbStatus.setPreferredSize(
                new java.awt.Dimension(
                    150,
                    35
                )
            );

            cmbStatus.setMaximumSize(
                new java.awt.Dimension(
                    150,
                    35
                )
            );

            JButton btnSave =
                new JButton(
                    "Save Status"
                );

            btnSave.setPreferredSize(
                new java.awt.Dimension(
                    150,
                    35
                )
            );

            btnSave.setMaximumSize(
                new java.awt.Dimension(
                    150,
                    35
                )
            );

            btnSave.setBackground(
                new Color(
                    255,
                    182,
                    193
                )
            );

            btnSave.setForeground(
                Color.BLACK
            );

            btnSave.setFont(
                new Font(
                    "Serif",
                    Font.BOLD,
                    14
                )
            );

            btnSave.setFocusPainted(
                false
            );

            // ==================================
            // SAVE STATUS
            // ==================================

            btnSave.addActionListener(e -> {

                String newStatus =
                    cmbStatus
                        .getSelectedItem()
                        .toString();

                updateOrderStatus(
                    orderId,
                    newStatus
                );

                showOrders();
            });

            statusPanel.add(
                lblStatus
            );

            statusPanel.add(
                Box.createVerticalStrut(5)
            );

            statusPanel.add(
                cmbStatus
            );

            statusPanel.add(
                Box.createVerticalStrut(8)
            );

            statusPanel.add(
                btnSave
            );

            orderCard.add(
                statusPanel,
                BorderLayout.EAST
            );

            orderPanel.add(
                orderCard
            );

            orderPanel.add(
                Box.createVerticalStrut(15)
            );
        }

        rs.close();
        pst.close();

    } catch (SQLException ex) {

        JOptionPane.showMessageDialog(
            this,
            "Error loading orders:\n"
            + ex.getMessage(),
            "Database Error",
            JOptionPane.ERROR_MESSAGE
        );
    }

    // ==========================================
    // DISPLAY PANEL
    // ==========================================

    panelContent.setLayout(
        new BorderLayout()
    );

    panelContent.setBackground(
        Color.WHITE
    );

    panelContent.add(
        mainPanel,
        BorderLayout.CENTER
    );

    panelContent.revalidate();
    panelContent.repaint();
}
    
    
    private void updateOrderStatus(
        int orderId,
        String newStatus) {

    String sql =
            "UPDATE orders "
            + "SET Status = ? "
            + "WHERE Order_id = ?";

    try (PreparedStatement pst =
                 conn.prepareStatement(sql)) {

        pst.setString(1, newStatus);
        pst.setInt(2, orderId);

        pst.executeUpdate();

        JOptionPane.showMessageDialog(
                this,
                "Order status updated to "
                + newStatus + "!"
        );

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
                this,
                "Error updating order status:\n"
                + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}

    
    
    
    
    private void loadFlowerTable(DefaultTableModel model) {

    model.setRowCount(0);

    String sql =
        "SELECT f.Flower_id, "
        + "f.Flower_Name, "
        + "c.Category_Name, "
        + "f.Price, "
        + "f.Stock "
        + "FROM flowers f "
        + "JOIN categories c "
        + "ON f.Category_id = c.Category_id "
        + "ORDER BY f.Flower_id";

    try (
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery()
    ) {

        while (rs.next()) {

            model.addRow(new Object[]{
                rs.getInt("Flower_id"),
                rs.getString("Flower_Name"),
                rs.getString("Category_Name"),
                rs.getDouble("Price"),
                rs.getInt("Stock")
            });
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            this,
            "Error loading flowers:\n" + e.getMessage()
        );
    }
}

   
    
    
    
    
    private void ConnectDB() {

    try {

        Class.forName(
            "com.mysql.cj.jdbc.Driver"
        );

        conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/"
            + "flower_inventory_management"
            + "?useSSL=false&serverTimezone=UTC",
            "root",
            ""
        );

        System.out.println(
            "Admin database connected!"
        );

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
    
    private void loadDashboard() {

    loadFlowerCount();
    loadCategoryCount();
    loadUserCount();
    loadOrderCount();

}
    
    private void loadFlowerCount() {

    String sql =
        "SELECT COUNT(*) AS total "
        + "FROM flowers";

    try (
        PreparedStatement pst =
            conn.prepareStatement(sql);

        ResultSet rs =
            pst.executeQuery()
    ) {

        if (rs.next()) {

            lblFlowerCount.setText(
                String.valueOf(
                    rs.getInt("total")
                )
            );
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            this,
            "Error loading flower count:\n"
            + e.getMessage()
        );
    }
}
    
    private void loadCategoryCount() {

    String sql =
        "SELECT COUNT(*) AS total "
        + "FROM categories";

    try (
        PreparedStatement pst =
            conn.prepareStatement(sql);

        ResultSet rs =
            pst.executeQuery()
    ) {

        if (rs.next()) {

            lblCategoryCount.setText(
                String.valueOf(
                    rs.getInt("total")
                )
            );
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            this,
            "Error loading category count:\n"
            + e.getMessage()
        );
    }
}
    
    private void loadUserCount() {

    String sql =
        "SELECT COUNT(*) AS total "
        + "FROM users "
        + "WHERE role = 'customer'";

    try (
        PreparedStatement pst =
            conn.prepareStatement(sql);

        ResultSet rs =
            pst.executeQuery()
    ) {

        if (rs.next()) {

            lblUserCount.setText(
                String.valueOf(
                    rs.getInt("total")
                )
            );
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            this,
            "Error loading user count:\n"
            + e.getMessage()
        );
    }
}
    
    private void loadOrderCount() {

    String sql =
        "SELECT COUNT(*) AS total "
        + "FROM orders";

    try (
        PreparedStatement pst =
            conn.prepareStatement(sql);

        ResultSet rs =
            pst.executeQuery()
    ) {

        if (rs.next()) {

            lblOrderCount.setText(
                String.valueOf(
                    rs.getInt("total")
                )
            );
        }

    } catch (SQLException e) {

        JOptionPane.showMessageDialog(
            this,
            "Error loading order count:\n"
            + e.getMessage()
        );
    }
}

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContent = new javax.swing.JPanel();
        lblFlowerCount = new javax.swing.JLabel();
        lblCategoryCount = new javax.swing.JLabel();
        lblUserCount = new javax.swing.JLabel();
        lblOrderCount = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        btnUsers = new javax.swing.JButton();
        btnFlowers = new javax.swing.JButton();
        btnCategories = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnOrders = new javax.swing.JButton();
        admin_dashboard = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelContent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblFlowerCount.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        lblFlowerCount.setText("0");
        panelContent.add(lblFlowerCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 20, 40));

        lblCategoryCount.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        lblCategoryCount.setText("0");
        panelContent.add(lblCategoryCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 140, -1, -1));

        lblUserCount.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        lblUserCount.setText("0");
        panelContent.add(lblUserCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, -1, -1));

        lblOrderCount.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        lblOrderCount.setText("0");
        panelContent.add(lblOrderCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 320, -1, -1));

        jLabel5.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel5.setText("FLOWERS");
        panelContent.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, -1, -1));

        jLabel6.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel6.setText("CATEGORY");
        panelContent.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, -1, -1));

        jLabel7.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel7.setText("USERS");
        panelContent.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 280, -1, -1));

        jLabel8.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        jLabel8.setText("ORDERS");
        panelContent.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 280, -1, -1));

        getContentPane().add(panelContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 1070, 650));

        btnDashboard.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        btnDashboard.setText("Dashboard");
        btnDashboard.addActionListener(this::btnDashboardActionPerformed);
        getContentPane().add(btnDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 150, -1));
        btnDashboard.getAccessibleContext().setAccessibleName("");

        btnUsers.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        btnUsers.setText("Users");
        btnUsers.addActionListener(this::btnUsersActionPerformed);
        getContentPane().add(btnUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, 150, -1));
        btnUsers.getAccessibleContext().setAccessibleName("");

        btnFlowers.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        btnFlowers.setText("Flowers");
        btnFlowers.addActionListener(this::btnFlowersActionPerformed);
        getContentPane().add(btnFlowers, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 150, -1));
        btnFlowers.getAccessibleContext().setAccessibleName("");

        btnCategories.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        btnCategories.setText("Categories");
        btnCategories.addActionListener(this::btnCategoriesActionPerformed);
        getContentPane().add(btnCategories, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 150, -1));
        btnCategories.getAccessibleContext().setAccessibleName("");

        btnLogout.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addActionListener(this::btnLogoutActionPerformed);
        getContentPane().add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 690, 150, -1));

        btnOrders.setFont(new java.awt.Font("Serif", 0, 24)); // NOI18N
        btnOrders.setText("Orders");
        btnOrders.addActionListener(this::btnOrdersActionPerformed);
        getContentPane().add(btnOrders, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 150, -1));
        btnOrders.getAccessibleContext().setAccessibleName("");

        admin_dashboard.setFont(new java.awt.Font("Serif", 3, 48)); // NOI18N
        admin_dashboard.setForeground(new java.awt.Color(255, 0, 51));
        admin_dashboard.setText("ADMIN FLOWER INVENTORY");
        getContentPane().add(admin_dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Gemini_Generated_Image_a32pqwa32pqwa32p.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        // TODO add your handling code here:
        showDashboard();
        
        
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnCategoriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriesActionPerformed
        // TODO add your handling code here:
        showCategories();
        
    }//GEN-LAST:event_btnCategoriesActionPerformed

    private void btnFlowersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFlowersActionPerformed
        // TODO add your handling code here:
        
        showFlowers();
    }//GEN-LAST:event_btnFlowersActionPerformed

    private void btnOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdersActionPerformed
        // TODO add your handling code here:
        showOrders();
    }//GEN-LAST:event_btnOrdersActionPerformed

    private void btnUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsersActionPerformed
        // TODO add your handling code here:
        showUsers();
    }//GEN-LAST:event_btnUsersActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new FlowerInventory().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel admin_dashboard;
    private javax.swing.JButton btnCategories;
    private javax.swing.JButton btnDashboard;
    private javax.swing.JButton btnFlowers;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnOrders;
    private javax.swing.JButton btnUsers;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblCategoryCount;
    private javax.swing.JLabel lblFlowerCount;
    private javax.swing.JLabel lblOrderCount;
    private javax.swing.JLabel lblUserCount;
    private javax.swing.JPanel panelContent;
    // End of variables declaration//GEN-END:variables
}

