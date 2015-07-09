package Module.Main;

import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;

public class ValidatorConn extends javax.swing.JInternalFrame {

    static Statement stmt;
    static DefaultTableCellRenderer cellAlignCenterRenderer = new DefaultTableCellRenderer();
    static DefaultTableModel model2, model;
    static DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    public static ViewProfileNJuri frmView1;
    public static ViewProfileJuri frmView2;
    public static FCCLog frmlog;
    public static CreateNewConn frmCreate;
    static Double cbal;
    public static Vector err;

    public ValidatorConn() {
        initComponents();
        getRootPane().setDefaultButton(cmdValidate);
    }

    public void showFrmlog() {
        frmlog = new FCCLog(this, true);
        frmlog.setVisible(true);
    }

    public void showFrmCreate() {
        frmCreate = new CreateNewConn(this, true);
        frmCreate.setVisible(true);
    }

    public void showFrmView() {
        frmView1 = new ViewProfileNJuri(this, true);
        frmView1.setVisible(true);
    }

    public void showFrmView2() {
        frmView2 = new ViewProfileJuri(this, true);
        frmView2.setVisible(true);
    }

    public void populateEConnTBL(int memid) {


        Connection conn = DBConn.getConnection();
        String createString;
        createString = "SELECT * FROM(select c.acctno, acctname,sum(totalamt-coalesce(amtpaid,0)) as bal from bill b "
                + "inner join consumer c "
                + "on c.acctno=b.acctno "
                + "left outer join collectiondata cd  "
                + "on b.billno=cd.billno  "
                + "where c.membershipID=" + memid + "and (totalamt-coalesce(amtpaid,0)>=0) and b.billno not in (select billno from billcancelled)  "
                + "group by c.acctno,c.membershipID,acctname "
                + ")DATA "
                + "WHERE bal<>0";

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model2 = (DefaultTableModel) jTBLExist.getModel();

            renderer.setHorizontalAlignment(0);

            jTBLExist.setRowHeight(23);
            jTBLExist.getColumnModel().getColumn(0).setCellRenderer(renderer);
            jTBLExist.getColumnModel().getColumn(1).setCellRenderer(renderer);
            jTBLExist.getColumnModel().getColumn(2).setCellRenderer(renderer);
            jTBLExist.setColumnSelectionAllowed(false);

            model2.setNumRows(0);


            while (rs.next()) {
                String amnt = myFunctions.amountFormat2(rs.getString(3));
                model2.addRow(new Object[]{rs.getString(1), rs.getString(2), amnt});
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    void getTotalBal() {
        err = new Vector();

        int r = jTBLExist.getRowCount();
        int c;
        c = 0;

        double total = 0;
        while (c < r + 1) {
            try {
                String bal = jTBLExist.getValueAt(c, 2).toString();
                String acctno = jTBLExist.getValueAt(c, 0).toString();

                err.addElement("/" + acctno + ":" + bal);
                total = total + Double.parseDouble(bal.replace(",", ""));
            } catch (Exception e) {
                e.getStackTrace();
            }
            c++;


        }
        cbal = total;
    }

    public void populateTBL() {


        Connection conn = DBConn.getConnection();
        String createString;
        createString = "SELECT memberID, acctname, s.status, CONVERT(char(10), date_encoded, 101), juridical_stat FROM membersTBL m INNER JOIN memStatTBL s ON m.status=s.memStatID WHERE acctname LIKE '%" + txtEntry.getText() + "%' " + " ORDER BY acctname";
        //stmtIncomingShed

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model = (DefaultTableModel) jTBLprofile.getModel();

            renderer.setHorizontalAlignment(0);

            jTBLprofile.setRowHeight(23);
            jTBLprofile.getColumnModel().getColumn(0).setCellRenderer(renderer);
            jTBLprofile.getColumnModel().getColumn(2).setCellRenderer(renderer);
            jTBLprofile.getColumnModel().getColumn(3).setCellRenderer(renderer);
            jTBLprofile.getColumnModel().getColumn(4).setCellRenderer(renderer);
            jTBLprofile.setColumnSelectionAllowed(false);

            model.setNumRows(0);


            while (rs.next()) {
                int x = rs.getInt(5);
                String flg = null;
                if (x == 0) {
                    flg = "NON-JURIDICAL";
                } else {
                    flg = "JURIDICAL";
                }

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), flg});
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtEntry = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmdValidate = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTBLprofile = new javax.swing.JTable();
        lblPmeskjjjuuj = new javax.swing.JLabel();
        cmdApprove = new javax.swing.JButton();
        cmdViewprofile = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTBLExist = new javax.swing.JTable();
        lblPmes = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbltotal = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Validation for new application of connection ");

        txtEntry.setToolTipText("Ex. {Cadiz, Lester Ongco} OR {GLOBE Telecomm}");
        txtEntry.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtEntry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtEntryMouseClicked(evt);
            }
        });
        txtEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEntryActionPerformed(evt);
            }
        });
        txtEntry.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEntryFocusGained(evt);
            }
        });
        txtEntry.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEntryKeyPressed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Account Name: {Surname, First Name Middle Name Ext} Or Name of Comapany or Institution");

        cmdValidate.setMnemonic('S');
        cmdValidate.setText("Search");
        cmdValidate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdValidateActionPerformed(evt);
            }
        });

        jToolBar1.setRollover(true);
        jToolBar1.setEnabled(false);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Membership/Images/approved.png"))); // NOI18N
        jToolBar1.add(jLabel4);

        jLabel7.setForeground(new java.awt.Color(255, 102, 0));
        jLabel7.setText("Validates the new applicant: Check for status and existing connection/s  NOTE: Click on Members Table to Validate.");
        jToolBar1.add(jLabel7);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTBLprofile.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MemberID", "Name of Member", "Status", "Date_Encoded", "Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBLprofile.setToolTipText("");
        jTBLprofile.getTableHeader().setReorderingAllowed(false);
        jTBLprofile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBLprofileMouseClicked(evt);
            }
        });
        jTBLprofile.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jTBLprofileMouseMoved(evt);
            }
        });
        jTBLprofile.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                jTBLprofileHierarchyChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jTBLprofile);
        jTBLprofile.getColumnModel().getColumn(1).setHeaderValue("Name of Member");
        jTBLprofile.getColumnModel().getColumn(2).setHeaderValue("Status");
        jTBLprofile.getColumnModel().getColumn(3).setHeaderValue("Date_Encoded");
        jTBLprofile.getColumnModel().getColumn(4).setHeaderValue("Type");
        //set column width
        jTBLprofile.getColumnModel().getColumn(0).setMaxWidth(80);
        jTBLprofile.getColumnModel().getColumn(1).setPreferredWidth(150);

        lblPmeskjjjuuj.setForeground(new java.awt.Color(255, 153, 0));
        lblPmeskjjjuuj.setText("List of Existing Connection");

        cmdApprove.setMnemonic('C');
        cmdApprove.setText("Create New Conn(For Approval)");
        cmdApprove.setEnabled(false);
        cmdApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdApproveActionPerformed(evt);
            }
        });

        cmdViewprofile.setMnemonic('V');
        cmdViewprofile.setText("View Profile");
        cmdViewprofile.setEnabled(false);
        cmdViewprofile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdViewprofileActionPerformed(evt);
            }
        });

        jTBLExist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Account No.", "Name of Account", "Balance"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBLExist.setToolTipText("");
        jTBLExist.getTableHeader().setReorderingAllowed(false);
        jTBLExist.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jTBLExistMouseMoved(evt);
            }
        });
        jScrollPane2.setViewportView(jTBLExist);
        //set column width
        //TBLprofile.getColumnModel().getColumn(0).setMaxWidth(80);
        //jTBLprofile.getColumnModel().getColumn(1).setPreferredWidth(150);

        lblPmes.setForeground(new java.awt.Color(51, 102, 255));
        lblPmes.setText("0 match result(s) ");

        jLabel1.setText("Total Remaining Balance:");

        lbltotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbltotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltotal.setText("0.00");
        lbltotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(lblPmes, javax.swing.GroupLayout.DEFAULT_SIZE, 911, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblPmeskjjjuuj, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                        .addGap(337, 337, 337))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmdViewprofile, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmdApprove, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbltotal, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblPmes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdApprove)
                    .addComponent(cmdViewprofile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPmeskjjjuuj)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbltotal))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 945, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(cmdValidate, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 477, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdValidate)
                    .addComponent(txtEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEntryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEntryMouseClicked
        txtEntry.selectAll();
}//GEN-LAST:event_txtEntryMouseClicked

    private void txtEntryFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEntryFocusGained
        txtEntry.selectAll();
}//GEN-LAST:event_txtEntryFocusGained

    private void cmdValidateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdValidateActionPerformed

        try {
            model.setNumRows(0);
            model2.setNumRows(0);
            lbltotal.setText("0.00");
        } catch (Exception e) {
        }
        if (txtEntry.getText().isEmpty() == false) {
            populateTBL();



            int rc = model.getRowCount();
            if (rc == 0) {
                lblPmes.setText("0 match result(s)");
                cmdApprove.setEnabled(false);
                //cmdViewConn.setEnabled(false);
                cmdViewprofile.setEnabled(false);
            } else {
                lblPmes.setText(rc + " match result(s)");
                cmdApprove.setEnabled(true);
                //cmdViewConn.setEnabled(true);
                cmdViewprofile.setEnabled(true);
            }

            txtEntry.requestFocus();
        } else {
            txtEntry.requestFocus();
            lblPmes.setText("0 match result(s)");
            try {
                model.setNumRows(0);
            } catch (NullPointerException e) {
                e.getStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Please type an entry for validation!");
        }
}//GEN-LAST:event_cmdValidateActionPerformed

    private void cmdApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdApproveActionPerformed



        int col = 0; //set column value to 0
        int row = jTBLprofile.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (jTBLprofile.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please a record from the list!");
            return;
        } else {
            String id = jTBLprofile.getValueAt(row, 0).toString();
            populateEConnTBL(Integer.parseInt(id));
            getTotalBal();
            lbltotal.setText(myFunctions.amountFormat2(String.valueOf(cbal)));

            double bal = Double.parseDouble(lbltotal.getText().replace(",", ""));

            if (bal > 0) {
                showFrmlog();
            } else {
                loadcreation();
            }
        }

}//GEN-LAST:event_cmdApproveActionPerformed

    public void loadcreation() {
        int col = 0; //set column value to 0
        int row = jTBLprofile.getSelectedRow(); //get value of selected value
        String memid = jTBLprofile.getValueAt(row, col).toString();
        String nym = jTBLprofile.getValueAt(row, 1).toString();

        CreateNewConn.nym = nym;
        CreateNewConn.memid = memid;

        showFrmCreate();
    }

    private void cmdViewprofileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdViewprofileActionPerformed
        int col = 0; //set column value to 0
        int row = jTBLprofile.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (jTBLprofile.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please a record from the list!");
            return;
        } else {
            String id = jTBLprofile.getValueAt(row, col).toString();


            String juri = jTBLprofile.getValueAt(row, 4).toString();

            if ("JURIDICAL".equals(juri)) {
                ViewProfileJuri.memid = id;
                showFrmView2();
            } else {
                ViewProfileNJuri.memid = id;
                showFrmView();
            }
        }
    }//GEN-LAST:event_cmdViewprofileActionPerformed

    private void jTBLprofileMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBLprofileMouseMoved
}//GEN-LAST:event_jTBLprofileMouseMoved

    private void jTBLExistMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBLExistMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jTBLExistMouseMoved

    private void txtEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEntryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEntryActionPerformed

    private void jTBLprofileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBLprofileMouseClicked
        int row = jTBLprofile.getSelectedRow();
        String id = jTBLprofile.getValueAt(row, 0).toString();
        populateEConnTBL(Integer.parseInt(id));
        getTotalBal();




        lbltotal.setText(myFunctions.amountFormat2(String.valueOf(cbal)));
    }//GEN-LAST:event_jTBLprofileMouseClicked

    private void txtEntryKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntryKeyPressed
        try {
            model.setNumRows(0);
            model2.setNumRows(0);
            lbltotal.setText("0.00");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtEntryKeyPressed

    private void jTBLprofileHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jTBLprofileHierarchyChanged
    }//GEN-LAST:event_jTBLprofileHierarchyChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdApprove;
    private javax.swing.JButton cmdValidate;
    private javax.swing.JButton cmdViewprofile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTBLExist;
    private javax.swing.JTable jTBLprofile;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblPmes;
    private javax.swing.JLabel lblPmeskjjjuuj;
    private javax.swing.JLabel lbltotal;
    private javax.swing.JTextField txtEntry;
    // End of variables declaration//GEN-END:variables
}
