package Module.Main;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class COASetup extends javax.swing.JInternalFrame {

    public static Statement stmt;
    public static DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    public static DefaultTableCellRenderer renderer2 = new DefaultTableCellRenderer();
    public static DefaultTableModel model;
    public static SetUQ frmSet;
    public static EditUQSetup frmEdit;

    public COASetup() {
        initComponents();
        getRootPane().setDefaultButton(cmdfyn);
    }

    public void showFrmAdd() {
        frmSet = new SetUQ(this, true);
        frmSet.setVisible(true);
    }
    
    public void RefreshStats() {
        int col = 7; //set column value to 0
        int row = tbl.getSelectedRow(); //get value of selected value

        String cid = tbl.getValueAt(row, 0).toString();
        String stat = tbl.getValueAt(row, col).toString();

        //JOptionPane.showMessageDialog(this, stat);
        System.out.println(stat);

        int intstat = 0;

        if ("true".equals(stat)) {
            intstat = 1;
        } else if ("false".equals(stat)) {
            intstat = 0;
        }

        UpdateStat(cid, intstat);
    }
    
    public static void UpdateStat(String cid, int stat) {
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "UPDATE CostingUQSetupTBL"
                + " SET defid=" + stat + " WHERE COAID=" + cid;

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
 

    void edit() {
        int col = 0; //set column value to 0
        int row = tbl.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (tbl.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please a record from the list!");
            return;
        } else {
            //getters
            String id = tbl.getValueAt(row, col).toString();
            String unit = tbl.getValueAt(row, 3).toString();
            String qty = tbl.getValueAt(row, 4).toString();
            String amnt= tbl.getValueAt(row, 5).toString();
            //setters
            EditUQSetup.coaid = Integer.parseInt(id);
            EditUQSetup.unit=unit.trim();
            EditUQSetup.qty=qty.trim();
            EditUQSetup.amnt=amnt.trim();
            //showform;
            showFrmEdit();
        }
    }

    public void showFrmEdit() {
        frmEdit = new EditUQSetup(this, true);
        frmEdit.setVisible(true);
    }

    public void populatetblALL(String wat) {

        Connection conn = DBConn.getConnection();
        String createString;
        createString = "SELECT COAID, COACode, COADesc, COAAmt FROM COA WHERE CostingFlag=0  AND COADesc LIKE '%" + wat + "%'  ORDER BY COADesc";
        //stmtIncomingShed 
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model = (DefaultTableModel) tbl1.getModel();

            renderer.setHorizontalAlignment(0);
            renderer2.setHorizontalAlignment(SwingConstants.RIGHT);

            tbl1.setRowHeight(20);
            tbl1.getColumnModel().getColumn(0).setCellRenderer(renderer);
            //tbl1.getColumnModel().getColumn(1).setCellRenderer(renderer);
            tbl1.getColumnModel().getColumn(3).setCellRenderer(renderer2);
            tbl1.setColumnSelectionAllowed(false);

            model.setNumRows(0);


            while (rs.next()) {
                String amnt = myFunctions.amountFormat2(rs.getString(4));

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), amnt});
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }

    public void populatetblFC() {

        Connection conn = DBConn.getConnection();
        String createString;
        createString = "SELECT c.COAID, COACode, COADesc, u.unit, u.qty, COAAmt, u.defid FROM COA c INNER JOIN CostingUQSetupTBL u ON c.COAID=u.COAID WHERE CostingFlag=1 order by COADesc";
        //stmtIncomingShed 
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model = (DefaultTableModel) tbl.getModel();

            renderer.setHorizontalAlignment(0);
            renderer2.setHorizontalAlignment(SwingConstants.RIGHT);

            tbl.setRowHeight(20);
            tbl.getColumnModel().getColumn(0).setCellRenderer(renderer);
            tbl.getColumnModel().getColumn(3).setCellRenderer(renderer);
            tbl.getColumnModel().getColumn(4).setCellRenderer(renderer);
            tbl.getColumnModel().getColumn(5).setCellRenderer(renderer2);
            tbl.setColumnSelectionAllowed(false);

            tbl.getColumn("Edit").setCellRenderer(new ButtonRenderer());
            tbl.getColumn("Edit").setCellEditor(new ButtonEditor(new JCheckBox()));

            model.setNumRows(0);


            while (rs.next()) {
                String amnt = myFunctions.amountFormat2(rs.getString(6));
                
                int def = rs.getInt(7);
                
                boolean x = true;
                
                if (def==0){
                    x=false;
                }else if (def==1){
                    x=true;
                }

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4).trim(), rs.getString(5), amnt, "Edit", x});
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }

    class ButtonEditor extends DefaultCellEditor {

        protected JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);

            button.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                edit();
            }
            isPushed = false;
            return new String(label);
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }

            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tbl1 = new javax.swing.JTable();
        cmdexit1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        cmdexit2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmdfyn = new javax.swing.JButton();
        cmdexit = new javax.swing.JButton();
        cmdrefresh3 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Costing Setup");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        tbl1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COAID", "Code", "Description", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl1.setToolTipText("");
        tbl1.getTableHeader().setReorderingAllowed(false);
        tbl1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tbl1MouseMoved(evt);
            }
        });
        jScrollPane2.setViewportView(tbl1);
        tbl1.getColumnModel().getColumn(0).setMaxWidth(40);
        tbl1.getColumnModel().getColumn(2).setPreferredWidth(180);
        tbl1.getColumnModel().getColumn(3).setMaxWidth(100);

        cmdexit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Membership/Images/outtt.png"))); // NOI18N
        cmdexit1.setMnemonic('x');
        cmdexit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdexit1ActionPerformed(evt);
            }
        });

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COAID", "Code", "Description", "Unit", "Qty", "Amount", "Edit", "Default"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl.setToolTipText("");
        tbl.getTableHeader().setReorderingAllowed(false);
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblMouseReleased(evt);
            }
        });
        tbl.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tblMouseMoved(evt);
            }
        });
        jScrollPane1.setViewportView(tbl);
        tbl.getColumnModel().getColumn(0).setMaxWidth(40);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(280);
        tbl.getColumnModel().getColumn(3).setMaxWidth(100);
        tbl.getColumnModel().getColumn(4).setMaxWidth(40);
        tbl.getColumnModel().getColumn(5).setMaxWidth(100);
        tbl.getColumnModel().getColumn(6).setMaxWidth(70);

        cmdexit2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Membership/Images/innn.png"))); // NOI18N
        cmdexit2.setMnemonic('x');
        cmdexit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdexit2ActionPerformed(evt);
            }
        });

        jLabel1.setText("COA Chart of Accounts - All Records");

        jLabel2.setText("COA Chart of Accounts -  For Costing");

        txtSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });

        jLabel3.setDisplayedMnemonic('S');
        jLabel3.setText("Search Here:");

        cmdfyn.setMnemonic('S');
        cmdfyn.setText("Search");
        cmdfyn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdfynActionPerformed(evt);
            }
        });

        cmdexit.setMnemonic('x');
        cmdexit.setText("Exit");
        cmdexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdexitActionPerformed(evt);
            }
        });

        cmdrefresh3.setMnemonic('e');
        cmdrefresh3.setText("Refresh");
        cmdrefresh3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdrefresh3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdfyn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdrefresh3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdexit))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cmdexit2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, Short.MAX_VALUE)
                                        .addGap(4, 4, 4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cmdexit1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, Short.MAX_VALUE)
                                        .addGap(5, 5, 5))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(6, 6, 6)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 499, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(cmdexit2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdexit1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdfyn)
                    .addComponent(jLabel3)
                    .addComponent(cmdexit)
                    .addComponent(cmdrefresh3))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl1MouseMoved
    }//GEN-LAST:event_tbl1MouseMoved

    private void tblMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseMoved
    }//GEN-LAST:event_tblMouseMoved

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
    }//GEN-LAST:event_txtSearchKeyPressed

    private void cmdfynActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdfynActionPerformed
        populatetblALL(txtSearch.getText());
        txtSearch.selectAll();
        txtSearch.requestFocus();
    }//GEN-LAST:event_cmdfynActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        populatetblALL(txtSearch.getText());
        populatetblFC();
     
    }//GEN-LAST:event_formInternalFrameOpened

    private void cmdrefresh3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdrefresh3ActionPerformed
        populatetblALL("");
    }//GEN-LAST:event_cmdrefresh3ActionPerformed

    private void cmdexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdexitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdexitActionPerformed

    private void cmdexit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdexit2ActionPerformed
        int col = 0; //set column value to 0
        int row = tbl1.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (tbl1.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please a record from the list!");
            return;
        } else {
            //getters
            String id = tbl1.getValueAt(row, col).toString();
            //setters
            SetUQ.coaid = Integer.parseInt(id);

            //showform;
            showFrmAdd();
        }


    }//GEN-LAST:event_cmdexit2ActionPerformed

    public static void UpdateFlag(int coaid) {
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "UPDATE COA "
                + "SET CostingFlag=0 WHERE COAID=" + coaid;

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void DeleteUQ(int coaid) {
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "DELETE FROM costingUQSetupTBL WHERE COAID=" + coaid;

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void cmdexit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdexit1ActionPerformed
        int col = 0; //set column value to 0
        int row = tbl.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (tbl.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please a record from the list!");
            return;
        } else {
            //getters
            String id = tbl.getValueAt(row, col).toString();
            //setters
            SetUQ.coaid = Integer.parseInt(id);

            UpdateFlag(Integer.parseInt(id));
            DeleteUQ(Integer.parseInt(id));
            populatetblALL("");
            populatetblFC();
            //JOptionPane.showMessageDialog(null, "Done");
        }
    }//GEN-LAST:event_cmdexit1ActionPerformed

    private void tblMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseReleased
        RefreshStats();
    }//GEN-LAST:event_tblMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdexit;
    private javax.swing.JButton cmdexit1;
    private javax.swing.JButton cmdexit2;
    private javax.swing.JButton cmdfyn;
    private javax.swing.JButton cmdrefresh3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl;
    private javax.swing.JTable tbl1;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
