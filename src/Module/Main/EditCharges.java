package Module.Main;

import javax.swing.JOptionPane;

public class EditCharges extends javax.swing.JDialog {

    public static CostingOp frmParent;
    public static String qty, cost, descrip;
    public static int rsel;

    public EditCharges(CostingOp parent, boolean modal) {
        this.frmParent = parent;
        this.setModal(modal);
        initComponents();
        setLocationRelativeTo(this);
        getRootPane().setDefaultButton(cmdSave);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cmdSave = new javax.swing.JButton();
        cmdcancel = new javax.swing.JButton();
        txtcost = new javax.swing.JFormattedTextField();
        txtqty = new javax.swing.JFormattedTextField();
        txtdesc = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Charges");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jToolBar1.setRollover(true);
        jToolBar1.setEnabled(false);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Membership/Images/costing.png"))); // NOI18N
        jToolBar1.add(jLabel4);

        jLabel7.setForeground(new java.awt.Color(255, 102, 0));
        jLabel7.setText("Here you can edit the quantity and the cost of a particular charges.");
        jToolBar1.add(jLabel7);

        cmdSave.setMnemonic('S');
        cmdSave.setText("Save");
        cmdSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSaveActionPerformed(evt);
            }
        });

        cmdcancel.setMnemonic('C');
        cmdcancel.setText("Cancel");
        cmdcancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdcancelActionPerformed(evt);
            }
        });

        txtcost.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtcost.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtcost.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtcostFocusGained(evt);
            }
        });

        txtqty.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtqty.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtqty.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtqtyFocusGained(evt);
            }
        });

        txtdesc.setToolTipText("");
        txtdesc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtcost, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtqty, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmdSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdcancel)))
                .addGap(139, 139, 139))
            .addComponent(txtdesc, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txtdesc, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(txtqty, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtcost, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdSave)
                    .addComponent(cmdcancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("QTY:");

        jLabel2.setText("Cost:");

        jLabel3.setText("Desciption:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdcancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdcancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdcancelActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        txtqty.setText(qty);
        txtcost.setText(cost);
        txtdesc.setText(descrip);
    }//GEN-LAST:event_formWindowOpened

    private void cmdSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveActionPerformed
        if (txtqty.getText().isEmpty() == true
                || txtcost.getText().isEmpty() == true
                || txtdesc.getText().isEmpty() == true) {
            JOptionPane.showMessageDialog(this, "Please fill-up all the required fields!");
            return;
        } else {

            CostingOp.qty = txtqty.getText();
            CostingOp.cost = myFunctions.amountFormat(Double.parseDouble(txtcost.getText()));
            CostingOp.rsel = rsel;
            CostingOp.descrip = txtdesc.getText();

            Double calctotal = Double.parseDouble(txtqty.getText()) * Double.parseDouble(txtcost.getText());
            String finaltotal = myFunctions.amountFormat(calctotal);

            CostingOp.amountc = finaltotal;
            //System.out.println(finaltotal);

            frmParent.UpdateChanges();

            this.dispose();
        }
    }//GEN-LAST:event_cmdSaveActionPerformed

    private void txtcostFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcostFocusGained
        txtcost.selectAll();
    }//GEN-LAST:event_txtcostFocusGained

    private void txtqtyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtqtyFocusGained
        txtqty.selectAll();
    }//GEN-LAST:event_txtqtyFocusGained

    public static void main(String arg[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                EditCharges dialog = new EditCharges(frmParent, true);
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
    private javax.swing.JButton cmdSave;
    private javax.swing.JButton cmdcancel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JFormattedTextField txtcost;
    private javax.swing.JTextField txtdesc;
    private javax.swing.JFormattedTextField txtqty;
    // End of variables declaration//GEN-END:variables
}
