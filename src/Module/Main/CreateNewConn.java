package Module.Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CreateNewConn extends javax.swing.JDialog {

    public static ValidatorConn frmParent;
    static Statement stmt;
    static String classCode;
    public static String nym, memid;
    static String nowDate = myFunctions.getDate();

    public CreateNewConn(ValidatorConn parent, boolean modal) {
        this.frmParent = parent;
        this.setModal(modal);
        initComponents();
        setLocationRelativeTo(this);
    }

    public void populate_combo() {
        //Populate Combo Area
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "SELECT ClassCode, ClassDesc FROM ConsumerClass ORDER BY ClassDesc;";

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                cmbClass.addItem(new Item(rs.getString(1), rs.getString(2)));
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error RSQuery/004: Query Select All Areas!");
        }
    }

    

    static int getMaxAcctNo() {
        int maxAN = 0;
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "SELECT COALESCE(MAX(AcctNo), 0) AS AcctNo FROM Consumer";

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                maxAN = rs.getInt(1);
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error RSQuery/004: Query Select All Areas!");
        }

        return maxAN;
    }

    int getValidAcctNo(int maxAcctNo) {
        boolean found = false;
        int getacctno = 0;

        int acctno = maxAcctNo + 1;
        String reverse = new StringBuffer(String.valueOf(acctno)).reverse().toString(); //058136
//        System.out.println("Last Acct No." + acctno);
//        System.out.println(reverse);



        while (found != true) {
            int i = 0;
            int digitTotal = 0;

            char[] cArray = reverse.toCharArray();
            while (i < String.valueOf(reverse).length()) {
                char c = cArray[i];
                String v = String.valueOf(c);
                digitTotal += Integer.parseInt(v) * (i + 1);
                i++;
            }
            if ((digitTotal % 11) == 0) {
                found = true;

                String valid = new StringBuffer(reverse).reverse().toString();
                getacctno = Integer.parseInt(valid);
            } else {
                int num = Integer.parseInt(new StringBuffer(reverse).reverse().toString());
                num++;
                reverse = new StringBuffer(String.valueOf(num)).reverse().toString();
            }
        }

        return getacctno;

    }

    class Item {

        private String id;
        private String description;

        public Item(String id, String description) {
            this.id = id;
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        public String toString() {
            return description;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        typeapp = new javax.swing.ButtonGroup();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtname = new javax.swing.JTextField();
        txtaddress = new javax.swing.JTextField();
        cmbClass = new javax.swing.JComboBox();
        cmdCreate = new javax.swing.JButton();
        cmdCancel = new javax.swing.JButton();
        opt1 = new javax.swing.JRadioButton();
        opt2 = new javax.swing.JRadioButton();
        txttcode = new javax.swing.JFormattedTextField();
        txtrcode = new javax.swing.JFormattedTextField();
        txtseq = new javax.swing.JFormattedTextField();
        txtedate = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New Connection");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jToolBar1.setRollover(true);
        jToolBar1.setEnabled(false);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Membership/Images/application.png"))); // NOI18N
        jToolBar1.add(jLabel4);

        jLabel7.setForeground(new java.awt.Color(255, 102, 0));
        jLabel7.setText("Create New Connection (For Approval)");
        jToolBar1.add(jLabel7);

        txtname.setToolTipText("");
        txtname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtnameMouseClicked(evt);
            }
        });
        txtname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtnameFocusGained(evt);
            }
        });

        txtaddress.setToolTipText("");
        txtaddress.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtaddress.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtaddressMouseClicked(evt);
            }
        });
        txtaddress.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtaddressFocusGained(evt);
            }
        });

        cmbClass.setForeground(new java.awt.Color(102, 102, 102));
        cmbClass.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-SELECT-" }));
        cmbClass.setToolTipText("");
        cmbClass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        cmbClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbClassActionPerformed(evt);
            }
        });

        cmdCreate.setMnemonic('r');
        cmdCreate.setText("Create");
        cmdCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCreateActionPerformed(evt);
            }
        });

        cmdCancel.setMnemonic('C');
        cmdCancel.setText("Cancel");
        cmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelActionPerformed(evt);
            }
        });

        typeapp.add(opt1);
        opt1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        opt1.setForeground(new java.awt.Color(51, 102, 255));
        opt1.setMnemonic('P');
        opt1.setSelected(true);
        opt1.setText("Permanent");
        opt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opt1ActionPerformed(evt);
            }
        });

        typeapp.add(opt2);
        opt2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        opt2.setForeground(new java.awt.Color(51, 102, 255));
        opt2.setMnemonic('T');
        opt2.setText("Temporary");
        opt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opt2ActionPerformed(evt);
            }
        });

        txttcode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txttcode.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        txtrcode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtrcode.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        txtseq.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtseq.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        txtedate.setToolTipText("");
        txtedate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtedate.setEnabled(false);

        jLabel28.setForeground(new java.awt.Color(102, 102, 102));
        jLabel28.setText(" (Applicable For Temp Conn. Only)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtedate, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtrcode)
                            .addComponent(txttcode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addComponent(opt1)
                        .addGap(18, 18, 18)
                        .addComponent(opt2))
                    .addComponent(txtseq, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtaddress, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbClass, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmdCreate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdCancel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(opt1)
                                .addComponent(opt2))
                            .addComponent(txttcode, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(txtrcode, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtseq, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtaddress, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbClass, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtedate, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCreate)
                    .addComponent(cmdCancel)))
        );

        jLabel3.setText("Town Code:");

        jLabel5.setText("Route Code:");

        jLabel6.setText("Account Name:");

        jLabel9.setText("Address:");

        jLabel12.setText("Route Seq. No.: ");

        jLabel13.setText("Class:");

        jLabel14.setText("Termination Date:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel12)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnameFocusGained
        // TODO add your handling code here:
}//GEN-LAST:event_txtnameFocusGained

    private void txtnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtnameMouseClicked
        // TODO add your handling code here:
}//GEN-LAST:event_txtnameMouseClicked

    private void txtaddressFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtaddressFocusGained
        // TODO add your handling code here:
}//GEN-LAST:event_txtaddressFocusGained

    private void txtaddressMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtaddressMouseClicked
        // TODO add your handling code here:
}//GEN-LAST:event_txtaddressMouseClicked

    private void cmbClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbClassActionPerformed
        try {
            Item item = (Item) cmbClass.getSelectedItem();
            classCode = item.getId();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbClassActionPerformed

    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdCancelActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        populate_combo();
        txtname.setText(nym);
    }//GEN-LAST:event_formWindowOpened

    private void opt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opt1ActionPerformed
        txtedate.setEnabled(false);
    }//GEN-LAST:event_opt1ActionPerformed

    private void cmdCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCreateActionPerformed
//        int x = SetAcctNo();
//        System.out.println(x);
        String tcode = txtrcode.getText();
        String rcode = txtrcode.getText();
        String seq = txtseq.getText();
        String name = txtname.getText();
        String address = txtaddress.getText();
        String edate = txtedate.getText();
        //int maxacctno = SetAcctNo();
        int valacctno = getValidAcctNo(getMaxAcctNo());



        int ctype = 0;
        if (opt1.isSelected() == true) {
            ctype = 1;

            if (
                    //tcode.isEmpty() == true
//                    || rcode.isEmpty() == true
//                    || seq.isEmpty() == true
                   // ||
                    name.isEmpty() == true
                    || address.isEmpty()
                    || cmbClass.getSelectedItem() == "-SELECT-") {
                JOptionPane.showMessageDialog(this, "Please fill-up all the required fields!");
                //return;
            } else {
                myDataenvi.rsAddConn(tcode, rcode, seq, name, name, classCode, memid, seq, address, ctype, nowDate, edate, valacctno);
                
                myDataenvi.AddConsumer(String.valueOf(valacctno));
                this.dispose();
                JOptionPane.showMessageDialog(this, "New Application Successfully Created!");
            }
        } else {
            ctype = 2;

            if (
                    //tcode.isEmpty() == true
//                    || rcode.isEmpty() == true
//                    || seq.isEmpty() == true
                   // ||
                    name.isEmpty() == true
                    || address.isEmpty()
                    || edate.isEmpty()
                    || cmbClass.getSelectedItem() == "-SELECT-") {
                JOptionPane.showMessageDialog(this, "Please fill-up all the required fields!");
                //return;
            } else {
                myDataenvi.rsAddConn(tcode, rcode, seq, name, name, classCode, memid, seq, address, ctype, nowDate, txtedate.getText(), valacctno);
                this.dispose();
                JOptionPane.showMessageDialog(this, "New Application Successfully Created!");
            }
        }

        //System.out.println(ctype);


    }//GEN-LAST:event_cmdCreateActionPerformed

    void tagStatus() {
    }

    private void opt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opt2ActionPerformed
        txtedate.setEnabled(true);
    }//GEN-LAST:event_opt2ActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                CreateNewConn dialog = new CreateNewConn(frmParent, true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbClass;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdCreate;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JRadioButton opt1;
    private javax.swing.JRadioButton opt2;
    private javax.swing.JTextField txtaddress;
    private javax.swing.JTextField txtedate;
    private javax.swing.JTextField txtname;
    private javax.swing.JFormattedTextField txtrcode;
    private javax.swing.JFormattedTextField txtseq;
    private javax.swing.JFormattedTextField txttcode;
    private javax.swing.ButtonGroup typeapp;
    // End of variables declaration//GEN-END:variables
}
