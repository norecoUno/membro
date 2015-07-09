package Module.Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class BarangayManage extends javax.swing.JInternalFrame {

    static Statement stmt;
    static int areaCode;
    public static BarangayAdd frmAdd;
    public static BarangayEdit frmEdit;
    static Statement stmtPreviousShed;
    static DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    static DefaultTableModel model;

    public BarangayManage() {
        initComponents();
        populateArea();

        TableColumn idClmn = tbl.getColumn("id");
        idClmn.setMaxWidth(0);
        idClmn.setMinWidth(0);
        idClmn.setPreferredWidth(0);
    }

    public void showFrmAdd() {
        frmAdd = new BarangayAdd(this, true);
        frmAdd.setVisible(true);
    }

    public void showFrmEdit() {
        frmEdit = new BarangayEdit(this, true);
        frmEdit.setVisible(true);
    }

    void populateArea() {

        //Populate Combo Area
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "SELECT * FROM areaTBL ORDER BY area_desc;";

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                cmbArea.addItem(new Item(rs.getInt(1), rs.getString(2)));
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error RSQuery/004: Query Select All Areas!");
        }
    }

    class Item {

        private int id;
        private String description;

        public Item(int id, String description) {
            this.id = id;
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        public String toString() {
            return description;
        }
    }

    public void populateTBL() {

        Connection conn = DBConn.getConnection();
        String createString;

        createString = "SELECT * FROM barangayTBL WHERE areacode=" + areaCode + " ORDER BY brgy_name";
        try {
            stmtPreviousShed = conn.createStatement();
            ResultSet rs = stmtPreviousShed.executeQuery(createString);

            model = (DefaultTableModel) tbl.getModel();

            renderer.setHorizontalAlignment(0);

            tbl.setRowHeight(21);
            //tbl.getColumnModel().getColumn(1).setCellRenderer(renderer);
            tbl.setColumnSelectionAllowed(false);

            model.setNumRows(0);

            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt(1), rs.getString("brgy_name")});
            }

            stmtPreviousShed.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbArea = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        cmdAdd = new javax.swing.JButton();
        cmdUpdate = new javax.swing.JButton();
        cmdDelete = new javax.swing.JButton();
        cmdExit = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setTitle("Barangay Management");

        cmbArea.setForeground(new java.awt.Color(102, 102, 102));
        cmbArea.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-SELECT-" }));
        cmbArea.setToolTipText("Municipal/City");
        cmbArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAreaActionPerformed(evt);
            }
        });

        jLabel1.setText("Barangay:");

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Barangay Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl);
        if (tbl.getColumnModel().getColumnCount() > 0) {
            tbl.getColumnModel().getColumn(0).setResizable(false);
            tbl.getColumnModel().getColumn(1).setResizable(false);
        }

        cmdAdd.setText("Add");
        cmdAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });

        cmdUpdate.setText("Edit");
        cmdUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUpdateActionPerformed(evt);
            }
        });

        cmdDelete.setText("Remove");
        cmdDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteActionPerformed(evt);
            }
        });

        cmdExit.setText("Exit");
        cmdExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbArea, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmdAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdExit, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbArea, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdExit)))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAreaActionPerformed
        try {
            Item item = (Item) cmbArea.getSelectedItem();
            areaCode = item.getId();
            populateTBL();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbAreaActionPerformed

    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
        if ("--SELECT--".equals(cmbArea.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "No record selected! Please select a record from the list!");
        } else {
            BarangayAdd.areaCode = areaCode;
            showFrmAdd();
        }
    }//GEN-LAST:event_cmdAddActionPerformed

    private void cmdUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUpdateActionPerformed

        int col = 0; //set column value to 0
        int row = tbl.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (tbl.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please a record from the list!");
            return;
        } else {
            String id = tbl.getValueAt(row, col).toString();
            BarangayEdit.bid = Integer.parseInt(id);
            showFrmEdit();
        }
    }//GEN-LAST:event_cmdUpdateActionPerformed

    public static void rsDeleteBrgy(int bid) {
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "DELETE FROM barangayTBL WHERE brgy_id=" + bid;

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteActionPerformed
        int col = 0; //set column value to 0
        int row = tbl.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (tbl.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please a record from the list!");
            return;
        } else {
            String id = tbl.getValueAt(row, col).toString();
            int i = myFunctions.msgboxYesNo("Are you sure you want delete this current record?");
            if (i == 0) {
                rsDeleteBrgy(Integer.parseInt(id));
                populateTBL();
                JOptionPane.showMessageDialog(this, "Record has been successfully deleted!");
            } else if (i == 1) {
                return; //do nothing
            } else if (i == 2) {
                this.dispose(); //exit window
                return;
            }

        }
    }//GEN-LAST:event_cmdDeleteActionPerformed

    private void cmdExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdExitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbArea;
    private javax.swing.JButton cmdAdd;
    private javax.swing.JButton cmdDelete;
    private javax.swing.JButton cmdExit;
    private javax.swing.JButton cmdUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl;
    // End of variables declaration//GEN-END:variables
}
