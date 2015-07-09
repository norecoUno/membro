package Module.Main;

import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;

public class Validator extends javax.swing.JInternalFrame {

    static Statement stmtAttendance;
    static DefaultTableCellRenderer cellAlignCenterRenderer = new DefaultTableCellRenderer();
    static DefaultTableModel model;
    public static AddNewMemRec1 frmAdd;

    public Validator() {
        initComponents();
        cmdValidate.setMnemonic('V');
        cmdApprove.setMnemonic('A');
        getRootPane().setDefaultButton(cmdValidate);
    }

    public void showFrmAdd() {
        frmAdd = new AddNewMemRec1(this, true);
        frmAdd.setVisible(true);
    }

    public void populateTBL() {


        Connection conn = DBConn.getConnection();
        String createString;
        createString = "SELECT * FROM"
                + "(SELECT partID, part_lname + ', ' + part_fname + ' ' + part_mname + ' ' + part_ext AS nym, "
                + "CONVERT(char(10), sched_date, 101) AS sdate, RTRIM(sched_venue) + '/'+ RTRIM(sched_address)AS sched, participantsTBL.batchID, address, mem_stat "
                + "FROM participantsTBL "
                + "INNER JOIN scheduleTBL ON participantsTBL.batchID=scheduleTBL.batchID "
                + "WHERE sched_stat=1 AND part_stat=0) TBL WHERE nym LIKE '%" + txtEntry.getText() + "%' ";

        try {
            stmtAttendance = conn.createStatement();
            ResultSet rs = stmtAttendance.executeQuery(createString);

            model = (DefaultTableModel) jTblAttendance.getModel();


            cellAlignCenterRenderer.setHorizontalAlignment(0);

            jTblAttendance.setRowHeight(22);
            jTblAttendance.getColumnModel().getColumn(0).setCellRenderer(cellAlignCenterRenderer);
            jTblAttendance.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenterRenderer);
            jTblAttendance.getColumnModel().getColumn(4).setCellRenderer(cellAlignCenterRenderer);
            jTblAttendance.setColumnSelectionAllowed(false);

            model.setNumRows(0);



            while (rs.next()) {
//                int x = rs.getInt(7);
//                String flg = null;
//
//                if (x == 0) {
//                    flg = "Not Encoded";
//                } else if (x == 1) {
//                    flg = "Encoded";
//                }

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
            }

            stmtAttendance.close();
            conn.close();
            txtEntry.selectAll();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtEntry = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmdValidate = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblAttendance = new javax.swing.JTable();
        lblPmes = new javax.swing.JLabel();
        cmdApprove = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Validation for new profile creation");

        txtEntry.setToolTipText("Ex. {Cadiz, Lester Ongco} OR {GLOBE Telecomm}");
        txtEntry.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtEntry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtEntryMouseClicked(evt);
            }
        });
        txtEntry.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEntryFocusGained(evt);
            }
        });

        jLabel1.setText("Type entry here:");

        jToolBar1.setRollover(true);
        jToolBar1.setEnabled(false);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Membership/Images/approved.png"))); // NOI18N
        jToolBar1.add(jLabel4);

        jLabel7.setForeground(new java.awt.Color(255, 102, 0));
        jLabel7.setText("Validates the new applicant: Check for PMES Attendance");
        jToolBar1.add(jLabel7);

        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Name of Participant: {Surname, First Name Middle Name Ext} ");

        cmdValidate.setText("Validate");
        cmdValidate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdValidateActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTblAttendance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PartID", "Name of Participant", "PMES Date", "PMES Venue/Address", "BatchID", "Participant Address"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblAttendance.setToolTipText("");
        jTblAttendance.getTableHeader().setReorderingAllowed(false);
        jTblAttendance.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jTblAttendanceMouseMoved(evt);
            }
        });
        jScrollPane1.setViewportView(jTblAttendance);
        //set column width
        jTblAttendance.getColumnModel().getColumn(0).setMaxWidth(80);
        jTblAttendance.getColumnModel().getColumn(2).setMaxWidth(100);
        jTblAttendance.getColumnModel().getColumn(4).setMaxWidth(80);

        lblPmes.setForeground(new java.awt.Color(51, 102, 255));
        lblPmes.setText("0 match result(s) in PMES Attendance");

        cmdApprove.setText("Create Profile");
        cmdApprove.setEnabled(false);
        cmdApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdApproveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
                            .addContainerGap())
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lblPmes, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                            .addGap(347, 347, 347)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cmdApprove, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPmes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdApprove)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEntry, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdValidate, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdValidate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdValidateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdValidateActionPerformed
        if (txtEntry.getText().isEmpty() == false) {
            populateTBL();
            int rc = model.getRowCount();
            if (rc == 0) {
                lblPmes.setText("0 match result(s) in PMES Attendance");
                cmdApprove.setEnabled(false);
            } else {
                lblPmes.setText(rc + " match result(s) in PMES Attendance");
                cmdApprove.setEnabled(true);
            }

//        cmdApprove1.setEnabled(true);
//        cmdView.setEnabled(true);
            txtEntry.requestFocus();
        } else {
            txtEntry.requestFocus();
            lblPmes.setText("0 match result(s) in PMES Attendance");
            try {
                model.setNumRows(0);
            } catch (NullPointerException e) {
                e.getStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Please type an entry for validation!");
        }
    }//GEN-LAST:event_cmdValidateActionPerformed

    private void txtEntryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEntryMouseClicked
        txtEntry.selectAll();
    }//GEN-LAST:event_txtEntryMouseClicked

    private void txtEntryFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEntryFocusGained
        txtEntry.selectAll();
    }//GEN-LAST:event_txtEntryFocusGained

    private void jTblAttendanceMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblAttendanceMouseMoved
}//GEN-LAST:event_jTblAttendanceMouseMoved

    private void cmdApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdApproveActionPerformed
        int col = 0; //set column value to 0
        int row = jTblAttendance.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (jTblAttendance.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please a record from the list!");
            return;
        } else {
            String id = jTblAttendance.getValueAt(row, 0).toString();

            boolean isfound = myDataenvi.rsIsEncodedNJ(Integer.parseInt(id));

            if (isfound==true) {
                JOptionPane.showMessageDialog(this, "This current record is already encoded in the members profiles list!");
                return;
            } else if (isfound==false) {

                AddNewMemRec1.pid = jTblAttendance.getValueAt(row, col).toString();
                AddNewMemRec1.nym = jTblAttendance.getValueAt(row, 1).toString();
                AddNewMemRec1.add = jTblAttendance.getValueAt(row, 5).toString();
                this.dispose();
                showFrmAdd();
            }

        }
    }//GEN-LAST:event_cmdApproveActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdApprove;
    private javax.swing.JButton cmdValidate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblAttendance;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblPmes;
    private javax.swing.JTextField txtEntry;
    // End of variables declaration//GEN-END:variables
}
