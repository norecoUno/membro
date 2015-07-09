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

public class SelectCrew extends javax.swing.JDialog {

    public static IssueMeter frmParent;
    static Statement stmt;
    static DefaultTableCellRenderer cellAlignCenterRenderer = new DefaultTableCellRenderer();
    static DefaultTableModel model;
    static DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    public static String acctno, sn;
    static String nowDate = myFunctions.getDate2();

    public SelectCrew(IssueMeter parent, boolean modal) {
        this.frmParent = parent;
        this.setModal(modal);
        initComponents();
        setLocationRelativeTo(this);
    }

    void processnow() {
        int col = 0; //set column value to 0
        int row = tbl.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (tbl.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please a record from the list!");
            return;
        } else {
            String CrewID = tbl.getValueAt(row, col).toString();
            int an =Integer.parseInt(acctno);

            myDataenvi.rsUpdateConnStat(an, 4);
            UpdateMeterPost(Integer.parseInt(CrewID));
            
            frmParent.refreshTAblePArent();
            frmParent.dispose();
            this.dispose();
            JOptionPane.showMessageDialog(this, "Meter Issuance Successfully Done!");
        }
    }

    public static void UpdateMeterPost(int crewid) {
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "UPDATE meterPostTBL "
                + "SET Flag=2, AcctNo='" + acctno + "', CurIssuedTo='" + crewid + "', CurIssueDate='" + nowDate + "' "
                + "WHERE MeterSN='" + sn + "'";


        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    void populateTBL(String wat) {
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "SELECT CrewID, CrewName FROM crew WHERE crewID<>2 AND CrewName LIKE '%" + wat + "%' ORDER BY CrewName";

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model = (DefaultTableModel) tbl.getModel();

            renderer.setHorizontalAlignment(0);

            tbl.setRowHeight(21);
            tbl.getColumnModel().getColumn(0).setCellRenderer(renderer);
            //tbl.getColumnModel().getColumn(2).setCellRenderer(renderer);
            //tbl.getColumnModel().getColumn(3).setCellRenderer(renderer);
            tbl.getColumn("Select").setCellRenderer(new ButtonRenderer());
            tbl.getColumn("Select").setCellEditor(new ButtonEditor(new JCheckBox()));

            model.setNumRows(0);

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), "Select"});
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
                processnow();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtsearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Select Crew");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name of Crew", "Select"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class
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
        tbl.getColumnModel().getColumn(0).setPreferredWidth(40);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(250);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(80);

        jLabel1.setText("List of Authorized NORECO I Crew");

        txtsearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtsearch.setCaretColor(new java.awt.Color(0, 102, 0));
        txtsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsearchActionPerformed(evt);
            }
        });
        txtsearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtsearchFocusLost(evt);
            }
        });
        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsearchKeyPressed(evt);
            }
        });

        jLabel2.setText("Search Here:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 61, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(9, 9, 9))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        populateTBL(txtsearch.getText());
        txtsearch.requestFocus();
    }//GEN-LAST:event_formWindowOpened

    private void txtsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsearchActionPerformed
    }//GEN-LAST:event_txtsearchActionPerformed

    private void txtsearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtsearchFocusLost
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
    }//GEN-LAST:event_txtsearchFocusLost

    private void txtsearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyPressed
        populateTBL(txtsearch.getText());
    }//GEN-LAST:event_txtsearchKeyPressed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                SelectCrew dialog = new SelectCrew(frmParent, true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl;
    private javax.swing.JTextField txtsearch;
    // End of variables declaration//GEN-END:variables
}
