/**
 * Author: Mattias Lindell
 * Last edit: 19-09-27
 * Desc: UI and main for a cash register
 */
package cashregister;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CashRegisterUI extends javax.swing.JFrame {
/**
 * START CUSTOM CODE
 * This code is not auto generated
 */    
    private Receipt receipt;
    private History history;
    private History stats;
    private String filename;
    final static String DEFAULT_TITLE = "Cash register";
    final static String VERSION = "1.3";
    final static String INFO = "Cash register version: " + VERSION + "\nAuthor: Mattias Lindell \nEmail: mattiaslindell@outlook.com";
    
    /**
     * @desc Updates shown receipt in the UI
     * @param receiptTable - table to be updated
     * @param sum_field - sum field associated with the receipt table
     * @param receipt - receipt to update the table with
     */
    private void updateReceipt(JTable receiptTable, JTextField sum_field, Receipt receipt) {
        DefaultTableModel model = (DefaultTableModel) receiptTable.getModel();
        
        // Clear table
        while(model.getRowCount() > 0) {
            model.removeRow(0);
        }        
        
        // Add rows
        for(int i = 0; i < receipt.countItems(); i++) {
            Item item = receipt.getItemAt(i);            
            model.addRow(new Object[]{
            item.getSeller(),
            item.getPrice(),
            item.getDiscount(),
            item.getDiscountedPrice()
        });
        }
        
        // Update sum field
        sum_field.setText(receipt.getSum().toString());
    }
    
    /**
     * @Desc Update a table that lists receipts that are moved to the history
     */
    private void updateHistoryReceiptList() {
        DefaultTableModel model = (DefaultTableModel) receiptList.getModel();
        
        // Clear table
        while(model.getRowCount() > 0) {
            model.removeRow(0);
        }        
        
        // get receipts
        ArrayList<Receipt> receipts = history.getReceipts();
        
        // Add rows, calculate sum
        for (int i = 0; i < receipts.size(); i++) {
            Receipt tmpReceipt = history.getReceiptAt(i);
            model.addRow(new Object[] {
                tmpReceipt.getTime(),
                tmpReceipt.getSum()
            });
        }       
    }
    
    /**
     * @desc Update the table that shows statistics
     */
    private void updateStatsTable() {
        DefaultTableModel model = (DefaultTableModel) sellerTable.getModel();
        
        // Clear table
        while(model.getRowCount() > 0) {
            model.removeRow(0);
        }
        
        //Get all sellers and sort
        ArrayList<String> sellers = stats.getSellers();
        Collections.sort(sellers);
        
        // Add rows with all sellers
        for (int i = 0; i < sellers.size(); i++) {
            model.addRow(new Object[] {
                sellers.get(i),
                stats.getSoldItems(sellers.get(i)),
                stats.getSum(sellers.get(i)).toString()
            });
        }         
    }
    
    /**
     * @desc Clear all fields on the register page
     */
    private void clearFields() {
        seller_field.setText("");
        price_field.setText("");
        price_field.setValue(null);
        discount_checkbox.setSelected(false);
        discount_field.setText("50");
    }
    
        /**
     * @desc Update all fields on the register page. This clears everything
     * except the discount fields
     */
    private void updateFields() {
        seller_field.setText("");
        price_field.setText("");
        price_field.setValue(null);
    }
    
    /**
     * @desc Update the program title. Appends the name of the save file.
     */
    private void updateTitle() {
        if (filename.equals("") || filename != null) {
            this.setTitle(DEFAULT_TITLE + " - " + filename);
        }
    }
    
    /**
     * @desc Make a backup
     */
    public void backup() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
        String time = now.format(formatter);
        int i = 1;
        String backupName = "history_backup " + time;
        while(FileHandler.fileExist(backupName+".xml")) {
            backupName = backupName + "(" + i + ")";
        }
        FileHandler.saveToXML(history, backupName + ".xml");
    }
    
    public boolean save() {
        // only allow saving if the receipt has any items
        if (receipt.countItems() > 0) {
        // Clone receipt to history
        history.addReceipt(new Receipt(receipt.getItems()));
        
        // Save history
        FileHandler.saveToXML(history, filename);
        
        // clear entrys
        updateFields();
        receipt.clear();
        
        // update tables and fields
        updateReceipt(receiptTable, sum_field, receipt);
        updateHistoryReceiptList();
        
        // Backup
        backup();
        
        return true;
        }
       return false;
    }

    /**
     * Constructor
     */
    public CashRegisterUI() {
        receipt = new Receipt();
        history = new History();
        stats = new History();
        filename = new String();
        initComponents();        
        jTabbedPane1.setVisible(false);
        this.setTitle(DEFAULT_TITLE);
        fileList.getTableHeader().setUI(null);
    }
/**
 * END CUSTOM CODE
 * 
 */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane = new javax.swing.JOptionPane();
        jFileChooser1 = new javax.swing.JFileChooser();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        registerPane = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        seller_field = new javax.swing.JTextField();
        discount_checkbox = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        receiptTable = new javax.swing.JTable();
        add_button = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        sum_field = new javax.swing.JTextField();
        save_button = new javax.swing.JButton();
        price_field = new javax.swing.JFormattedTextField();
        discount_field = new javax.swing.JFormattedTextField();
        deleteButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        historyPane = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        receiptList = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        receiptTableHistoryPane = new javax.swing.JTable();
        moveButton = new javax.swing.JButton();
        deleteButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        sum_fieldHist = new javax.swing.JTextField();
        statsPane = new javax.swing.JPanel();
        sellerPane = new javax.swing.JScrollPane();
        sellerTable = new javax.swing.JTable();
        addHistory = new javax.swing.JButton();
        clearStats = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        fileList = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mItemNew = new javax.swing.JMenuItem();
        mItemOpen = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        about = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        jTabbedPane1.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(900, 700));

        registerPane.setPreferredSize(new java.awt.Dimension(900, 700));
        registerPane.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                registerPaneKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel1.setText("Pris utan rabatt");

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel2.setText("Säljare");

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel3.setText("Rabatt %");

        seller_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        seller_field.setToolTipText("");
        seller_field.setNextFocusableComponent(price_field);
        seller_field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                seller_fieldFocusGained(evt);
            }
        });

        discount_checkbox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        discount_checkbox.setNextFocusableComponent(add_button);
        discount_checkbox.setOpaque(false);

        jScrollPane2.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        jScrollPane2.setOpaque(false);

        receiptTable.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        receiptTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Säljare", "Baspris", "Rabatt %", "Rabatt pris"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        receiptTable.setToolTipText("");
        receiptTable.setGridColor(new java.awt.Color(240, 240, 240));
        receiptTable.setName(""); // NOI18N
        receiptTable.setShowVerticalLines(false);
        jScrollPane2.setViewportView(receiptTable);
        if (receiptTable.getColumnModel().getColumnCount() > 0) {
            receiptTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        }

        add_button.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        add_button.setText("Lägg till");
        add_button.setToolTipText("");
        add_button.setNextFocusableComponent(seller_field);
        add_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_buttonActionPerformed(evt);
            }
        });
        add_button.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                add_buttonKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel5.setText("Summa:");

        sum_field.setEditable(false);
        sum_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        sum_field.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        sum_field.setText("0");
        sum_field.setToolTipText("");
        sum_field.setBorder(null);
        sum_field.setOpaque(false);

        save_button.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        save_button.setText("Spara och rensa");
        save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_buttonActionPerformed(evt);
            }
        });

        price_field.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        price_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        price_field.setNextFocusableComponent(add_button);

        discount_field.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        discount_field.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        discount_field.setText("50");
        discount_field.setToolTipText("");
        discount_field.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        discount_field.setNextFocusableComponent(add_button);

        deleteButton.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        deleteButton.setText("Ta bort vald rad");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel4.setText("Kvitto");

        javax.swing.GroupLayout registerPaneLayout = new javax.swing.GroupLayout(registerPane);
        registerPane.setLayout(registerPaneLayout);
        registerPaneLayout.setHorizontalGroup(
            registerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerPaneLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(registerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registerPaneLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(seller_field, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sum_field, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registerPaneLayout.createSequentialGroup()
                        .addGroup(registerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(registerPaneLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(price_field, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(registerPaneLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(discount_checkbox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(discount_field, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(add_button))
                        .addGap(64, 64, 64)
                        .addGroup(registerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(registerPaneLayout.createSequentialGroup()
                                .addComponent(deleteButton)
                                .addGap(18, 18, 18)
                                .addComponent(save_button))
                            .addComponent(jScrollPane2))))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        registerPaneLayout.setVerticalGroup(
            registerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(registerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(seller_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(sum_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(registerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(price_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteButton)
                    .addComponent(save_button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(registerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registerPaneLayout.createSequentialGroup()
                        .addGroup(registerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(discount_checkbox)
                            .addComponent(jLabel3)
                            .addComponent(discount_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(add_button)
                        .addGap(0, 511, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Kassa", registerPane);

        historyPane.setPreferredSize(new java.awt.Dimension(900, 700));
        historyPane.setRequestFocusEnabled(false);

        receiptList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "tid", "summa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        receiptList.setGridColor(new java.awt.Color(240, 240, 240));
        receiptList.setInheritsPopupMenu(true);
        receiptList.setShowVerticalLines(false);
        receiptList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                receiptListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(receiptList);

        receiptTableHistoryPane.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Säljare", "Baspris", "Rabatt %", "Rabatt pris"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        receiptTableHistoryPane.setGridColor(new java.awt.Color(240, 240, 240));
        receiptTableHistoryPane.setRowSelectionAllowed(false);
        receiptTableHistoryPane.setShowVerticalLines(false);
        jScrollPane3.setViewportView(receiptTableHistoryPane);
        if (receiptTableHistoryPane.getColumnModel().getColumnCount() > 0) {
            receiptTableHistoryPane.getColumnModel().getColumn(0).setResizable(false);
            receiptTableHistoryPane.getColumnModel().getColumn(0).setPreferredWidth(150);
            receiptTableHistoryPane.getColumnModel().getColumn(1).setResizable(false);
        }

        moveButton.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        moveButton.setText("Flytta till kassan");
        moveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveButtonActionPerformed(evt);
            }
        });

        deleteButton2.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N
        deleteButton2.setText("Ta bort");
        deleteButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButton2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel6.setText("Alla kvitton");

        jLabel7.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel7.setText("Valt kvitto");

        jLabel8.setFont(new java.awt.Font("Liberation Sans", 1, 14)); // NOI18N
        jLabel8.setText("Summa:");

        sum_fieldHist.setEditable(false);
        sum_fieldHist.setBackground(new java.awt.Color(255, 255, 255));
        sum_fieldHist.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        sum_fieldHist.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        sum_fieldHist.setText("0");
        sum_fieldHist.setToolTipText("");
        sum_fieldHist.setBorder(null);
        sum_fieldHist.setOpaque(false);

        javax.swing.GroupLayout historyPaneLayout = new javax.swing.GroupLayout(historyPane);
        historyPane.setLayout(historyPaneLayout);
        historyPaneLayout.setHorizontalGroup(
            historyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(historyPaneLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(historyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(historyPaneLayout.createSequentialGroup()
                        .addComponent(moveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteButton2))
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(historyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(historyPaneLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sum_fieldHist, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        historyPaneLayout.setVerticalGroup(
            historyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(historyPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(historyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(25, 25, 25)
                .addGroup(historyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(moveButton)
                    .addComponent(deleteButton2)
                    .addComponent(jLabel8)
                    .addComponent(sum_fieldHist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(historyPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Historik", historyPane);

        statsPane.setPreferredSize(new java.awt.Dimension(900, 700));

        sellerTable.setAutoCreateRowSorter(true);
        sellerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Säljare", "Antal sålda varor", "Summa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sellerTable.setGridColor(new java.awt.Color(240, 240, 240));
        sellerTable.setShowVerticalLines(false);
        sellerPane.setViewportView(sellerTable);

        addHistory.setText("Lägg till...");
        addHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addHistoryActionPerformed(evt);
            }
        });

        clearStats.setText("Rensa");
        clearStats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearStatsActionPerformed(evt);
            }
        });

        fileList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "filename"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        fileList.setGridColor(new java.awt.Color(240, 240, 240));
        fileList.setShowVerticalLines(false);
        jScrollPane4.setViewportView(fileList);

        javax.swing.GroupLayout statsPaneLayout = new javax.swing.GroupLayout(statsPane);
        statsPane.setLayout(statsPaneLayout);
        statsPaneLayout.setHorizontalGroup(
            statsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statsPaneLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(statsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(statsPaneLayout.createSequentialGroup()
                        .addComponent(addHistory)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clearStats))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100)
                .addComponent(sellerPane, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        statsPaneLayout.setVerticalGroup(
            statsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statsPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearStats)
                    .addComponent(addHistory))
                .addGap(9, 9, 9)
                .addGroup(statsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sellerPane, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sammanställningar", statsPane);

        jMenu1.setText("Fil");

        mItemNew.setText("Ny historikfil");
        mItemNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemNewActionPerformed(evt);
            }
        });
        jMenu1.add(mItemNew);

        mItemOpen.setText("Öppna historikfil");
        mItemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemOpenActionPerformed(evt);
            }
        });
        jMenu1.add(mItemOpen);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Övrigt");

        about.setText("Övrig information");
        about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutActionPerformed(evt);
            }
        });
        jMenu2.add(about);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_buttonActionPerformed
/*
        // Clone receipt to history
        history.addReceipt(new Receipt(receipt.getItems()));
        
        // Save history
        FileHandler.saveToXML(history, filename);
        
        // clear entrys
        clearFields();
        receipt.clear();
        
        // update tables and fields
        updateReceipt(receiptTable, sum_field, receipt);
        updateHistoryReceiptList();
        
        // Backup
        backup();
        */
        save();
    }//GEN-LAST:event_save_buttonActionPerformed

    private void add_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_buttonActionPerformed
        boolean sellerOK = false;
        boolean priceOK = false;
        
        // Get seller
        String seller = seller_field.getText();
        if (!seller.isEmpty()) sellerOK = true;
        
        // Get price
        BigDecimal price = new BigDecimal(0);
        if (price_field.getValue() != null) {
            price = new BigDecimal(price_field.getValue().toString());
            priceOK = true;
        }        
        
        // Get discount  
        BigDecimal discount_value = new BigDecimal(0);
        if(discount_checkbox.isSelected()) {
            if (discount_field.getValue() == null) {
                discount_value = new BigDecimal(50);
            } else {
                discount_value = new BigDecimal(discount_field.getValue().toString());                           
            }
        }
        
        // Round price to two decimals
        price = price.setScale(2, RoundingMode.HALF_UP);
        
        // Round percent to 0 decimals
        discount_value = discount_value.setScale(0, RoundingMode.HALF_UP);
       
        if (sellerOK && priceOK) {
            receipt.addItem(new Item(seller, price, discount_value));
            updateReceipt(receiptTable, sum_field, receipt);
            updateFields();            
        }        
    }//GEN-LAST:event_add_buttonActionPerformed

    private void seller_fieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_seller_fieldFocusGained
        // Select text on focus
        seller_field.select(0, seller_field.getText().length());
    }//GEN-LAST:event_seller_fieldFocusGained

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // Get selected row
        int selectedRow = receiptTable.getSelectedRow();
        
        // If a row is selected, remove it from container and update table
        if (selectedRow != -1) {
            receipt.removeItemAt(selectedRow);
            updateReceipt(receiptTable, sum_field, receipt);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    
    private void moveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveButtonActionPerformed
        // check that a row is selected. If it is, ask for confirmation
        if (receiptList.getSelectedRow() != -1) {
            Object[] option = {
                "Ja",
                "Nej"
            };
             int response = JOptionPane.showOptionDialog(jTabbedPane1, 
                     "<html><body><p style='width: 200px;'>Detta kommer ta bort kvittot från historiken och flytta det till kassan för redigering. Eventuellt kvitto i kassan kommer raderas. Är du säker?</p></body></html>", 
                     "Varning", 
                     JOptionPane.YES_NO_OPTION, 
                     JOptionPane.QUESTION_MESSAGE, 
                     null, 
                     option, 
                     option[1]);

             // If yes
             if(response == JOptionPane.YES_OPTION) {
                 // remove receipt from history
                 receipt = history.removeReceipt(receiptList.getSelectedRow());
                 
                 // update tables
                 updateReceipt(receiptTable, sum_field, receipt);
                 updateHistoryReceiptList();
                 updateReceipt(receiptTableHistoryPane, sum_fieldHist, new Receipt());
                 
                 // move to tab register
                 jTabbedPane1.setSelectedIndex(0);
             }
        }
    }//GEN-LAST:event_moveButtonActionPerformed

    private void deleteButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButton2ActionPerformed
        // check that a row is selected. If it is, ask for confirmation
        if (receiptList.getSelectedRow() != -1) {
            Object[] option = {
                "Ja",
                "Nej"
            };
             int response = JOptionPane.showOptionDialog(jTabbedPane1, 
                     "<html><body><p style='width: 200px;'>Detta kommer ta bort kvittot från historiken.<br> Är du säker?</p></body></html>", 
                     "Varning", 
                     JOptionPane.YES_NO_OPTION, 
                     JOptionPane.QUESTION_MESSAGE, 
                     null, 
                     option, 
                     option[1]);

             // If yes
             if(response == JOptionPane.YES_OPTION) {
                 // delete receipt
                 history.removeReceipt(receiptList.getSelectedRow());
                 
                 // update tables
                 updateHistoryReceiptList();
                 updateReceipt(receiptTableHistoryPane, sum_fieldHist, new Receipt());
                 
                 // Save history
                FileHandler.saveToXML(history, filename);
                
                // backup
                backup();
             }
        }
    }//GEN-LAST:event_deleteButton2ActionPerformed

    private void mItemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemOpenActionPerformed
        // Get file from file opener
        int returVal = jFileChooser1.showOpenDialog(CashRegisterUI.this);
        
        // if file was chosen
        if (returVal == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser1.getSelectedFile();    
            
            // try to load it
            try {
                // load file
                history = FileHandler.loadFromXML(file.getPath());
                
                // get path
                filename = file.getPath(); 
                
                // initialize app by showing tabs                
                jTabbedPane1.setVisible(true);
                
                // update tables
                updateHistoryReceiptList();
                updateReceipt(receiptTableHistoryPane, sum_fieldHist, new Receipt());
                
                // update program title
                updateTitle();
            } catch(FileNotLoadedException e) {
                // catch error
                JOptionPane.showMessageDialog(jTabbedPane1, "Fel vid laddning av historikfil!", "Varning", JOptionPane.WARNING_MESSAGE);
            }
        }        
    }//GEN-LAST:event_mItemOpenActionPerformed

    private void mItemNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemNewActionPerformed
        // Get new file name
        String response = (String)JOptionPane.showInputDialog(jTabbedPane1, "Välj namn för historikfilen:", "Filnamn");
        
        // create file object
        File file = new File(response);
        
        // Warning if file exist and exit function
        if (FileHandler.fileExist(response)) {
            JOptionPane.showMessageDialog(jTabbedPane1, "Filen finns redan, försök igen", "Varning", JOptionPane.WARNING_MESSAGE);
        } else {
            // set filename
            filename = response;
            
            // Check if fileName ends with '.xml'.
            // If fileName doesn't end with '.xml', append '.xml'.
            if (!filename.endsWith(".xml")) {
                    filename += ".xml";
                    updateTitle();
            }
            
            // clear history
            history.clear();
            
            // show info dialogue
            JOptionPane.showMessageDialog(jTabbedPane1, "Sparar till fil med namn: " + filename);
            
            // make program visible
            jTabbedPane1.setVisible(true);
            
            // update tables
            updateHistoryReceiptList();
            updateReceipt(receiptTableHistoryPane, sum_fieldHist, new Receipt());
            
            // update program title
            updateTitle();
        }
    }//GEN-LAST:event_mItemNewActionPerformed

    private void clearStatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearStatsActionPerformed
        // cleart stats containers        
        stats.clear();
        
        // clear table
        DefaultTableModel model = (DefaultTableModel) fileList.getModel();
        int numRows = model.getRowCount();        
        for (int i = 0; i < numRows; i++) {
            model.removeRow(0);            
        }        
        updateStatsTable();
    }//GEN-LAST:event_clearStatsActionPerformed

    private void addHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addHistoryActionPerformed
        // add history file
        int returVal = jFileChooser1.showOpenDialog(CashRegisterUI.this);

        if (returVal == JFileChooser.APPROVE_OPTION) {
            File file = jFileChooser1.getSelectedFile();
            try {
                History h = FileHandler.loadFromXML(file.getPath());
                DefaultTableModel model = (DefaultTableModel) fileList.getModel();
                model.addRow(new Object[] {file.getName()});
                ArrayList<Receipt> receipts = h.getReceipts();
                receipts.forEach((r) -> {
                    stats.addReceipt(r);
                });
            } catch(FileNotLoadedException e) {
                JOptionPane.showMessageDialog(jTabbedPane1, "Fel vid laddning av historikfil!", "Varning", JOptionPane.WARNING_MESSAGE);
            }
        }
        updateStatsTable();
    }//GEN-LAST:event_addHistoryActionPerformed

    private void aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutActionPerformed
        JOptionPane.showMessageDialog(jTabbedPane1, INFO, "Information", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_aboutActionPerformed

    private void receiptListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_receiptListMouseClicked
        // Get selected row
        int selectedRow = receiptList.getSelectedRow();
        
        // Get receipt
        if (selectedRow != -1) {
            Receipt tmpReceipt = history.getReceiptAt(selectedRow);
            
            // Show receipt in receiptTable
            updateReceipt(receiptTableHistoryPane, sum_fieldHist, tmpReceipt);
        }       
    }//GEN-LAST:event_receiptListMouseClicked

    private void registerPaneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_registerPaneKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == 107){
            System.out.println("RegisterPaneKeyPressed");
            save();
        }
    }//GEN-LAST:event_registerPaneKeyPressed

    private void add_buttonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_add_buttonKeyPressed
        if (evt.getKeyCode() == 107)
            if (save()) {
                System.out.println("add_buttonKeyPressed");
               seller_field.requestFocus();
            }
                
    }//GEN-LAST:event_add_buttonKeyPressed
    
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CashRegisterUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CashRegisterUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CashRegisterUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CashRegisterUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CashRegisterUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem about;
    private javax.swing.JButton addHistory;
    private javax.swing.JButton add_button;
    private javax.swing.JButton clearStats;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton deleteButton2;
    private javax.swing.JCheckBox discount_checkbox;
    private javax.swing.JFormattedTextField discount_field;
    private javax.swing.JTable fileList;
    private javax.swing.JPanel historyPane;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JOptionPane jOptionPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem mItemNew;
    private javax.swing.JMenuItem mItemOpen;
    private javax.swing.JButton moveButton;
    private javax.swing.JFormattedTextField price_field;
    private javax.swing.JTable receiptList;
    private javax.swing.JTable receiptTable;
    private javax.swing.JTable receiptTableHistoryPane;
    private javax.swing.JPanel registerPane;
    private javax.swing.JButton save_button;
    private javax.swing.JScrollPane sellerPane;
    private javax.swing.JTable sellerTable;
    private javax.swing.JTextField seller_field;
    private javax.swing.JPanel statsPane;
    private javax.swing.JTextField sum_field;
    private javax.swing.JTextField sum_fieldHist;
    // End of variables declaration//GEN-END:variables
}
