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

public class IssueMeter extends javax.swing.JDialog {

    public static IssueMeterMain frmParent;
    static Statement stmt;
    static DefaultTableCellRenderer cellAlignCenterRenderer = new DefaultTableCellRenderer();
    static DefaultTableModel model;
    static DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    public static SelectCrew frmSelect;
    static int cnt;
    public static String acctno;

    public IssueMeter(IssueMeterMain parent, boolean modal) {
        this.frmParent = parent;
        this.setModal(modal);
        initComponents();
        setLocationRelativeTo(this);
        getRootPane().setDefaultButton(cmdsearch);
    }

    public void showFrmSelect() {
        SelectCrew.acctno = acctno;
        SelectCrew.sn = txtmetersn.getText().trim();

        frmSelect = new SelectCrew(this, true);
        frmSelect.setVisible(true);

    }
    
    public void refreshTAblePArent(){
        frmParent.populateTBL();
    }

    void populateTBL(String SN) {
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "SELECT mp.MeterSN, mt.TypeDesc "
                + "FROM meterPostTBL mp, MeterBrand mb, MeterType mt "
                + "WHERE mp.BrandID = mb.BrandID AND mp.TypeCode=mt.TypeCode and mp.MeterSN='" + SN + "' AND mp.Flag=1";

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model = (DefaultTableModel) tbl.getModel();

            renderer.setHorizontalAlignment(0);

            tbl.setRowHeight(21);
            tbl.getColumnModel().getColumn(0).setCellRenderer(renderer);
            tbl.getColumnModel().getColumn(2).setCellRenderer(renderer);
            //tbl.getColumnModel().getColumn(3).setCellRenderer(renderer);
            tbl.getColumn("Issue").setCellRenderer(new ButtonRenderer());
            tbl.getColumn("Issue").setCellEditor(new ButtonEditor(new JCheckBox()));

            model.setNumRows(0);

            cnt = 0;
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), "Issue Now"});
                cnt++;
            }

            //int r = rs.getFetchSize();
            //System.out.println(cnt);

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
                showFrmSelect();
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

        txtmetersn = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        cmdsearch = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Issue Meter");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        txtmetersn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtmetersn.setCaretColor(new java.awt.Color(0, 102, 0));
        txtmetersn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmetersnActionPerformed(evt);
            }
        });
        txtmetersn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtmetersnFocusLost(evt);
            }
        });

        jLabel1.setText("Meter Serial No.:");

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Meter Serial No", "Meter Info", "Issue"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(300);
        //tbl.getColumnModel().getColumn(2).setPreferredWidth(200);

        cmdsearch.setMnemonic('C');
        cmdsearch.setText("Check");
        cmdsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdsearchActionPerformed(evt);
            }
        });

        jToolBar1.setRollover(true);
        jToolBar1.setEnabled(false);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Membership/Images/issuemeter.png"))); // NOI18N
        jToolBar1.add(jLabel4);

        jLabel7.setForeground(new java.awt.Color(255, 102, 0));
        jLabel7.setText(" LIST OF TESTED AND CALIBRATED METERS (Ready For Issuance)");
        jToolBar1.add(jLabel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtmetersn, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdsearch)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmetersn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdsearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtmetersnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmetersnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmetersnActionPerformed

    private void txtmetersnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmetersnFocusLost
//        if (txtmetersn.getText().isEmpty() == true) {
//            lbl.setForeground(Color.red);
//            txtmetersn.requestFocus();
//            txtmetersn.setBorder(javax.swing.BorderFactory.createLineBorder(Color.red));
//            lbl.setText("Meter Serial Number is required for verification!");
//            return;
//        } else {
//            boolean isExist = isInstalled(txtmetersn.getText());
//
//            if (isExist == true) {
//                lbl.setForeground(Color.red);
//                txtmetersn.selectAll();
//                txtmetersn.requestFocus();
//                txtmetersn.setBorder(javax.swing.BorderFactory.createLineBorder(Color.red));
//                lbl.setText("Meter Serial No. already Exist!");
//                return;
//            } else {
//                lbl.setForeground(new java.awt.Color(0, 102, 0));
//                lbl.setText("Meter is Available!");
//                txtmetersn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
//            }
//        }
    }//GEN-LAST:event_txtmetersnFocusLost

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    }//GEN-LAST:event_formWindowOpened

    private void cmdsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdsearchActionPerformed
        populateTBL(txtmetersn.getText().trim());
//        int r = model.getRowCount();

        if (cnt == 0) {
            JOptionPane.showMessageDialog(this, "No Results!");
        }
        //System.out.println(cnt);
    }//GEN-LAST:event_cmdsearchActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IssueMeter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueMeter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueMeter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueMeter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                IssueMeter dialog = new IssueMeter(frmParent, true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdsearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tbl;
    private javax.swing.JTextField txtmetersn;
    // End of variables declaration//GEN-END:variables
}
