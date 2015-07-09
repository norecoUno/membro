package Module.Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class NewSched extends javax.swing.JInternalFrame {

    static Statement stmtSelectAllAreas;
    static int areaCode;
    static String nowDate = myFunctions.getDate();
    

    //private final static String newline = "\n";
    public NewSched() {
        initComponents();
        cmdExit.setMnemonic('C');
        getRootPane().setDefaultButton(cmdCreate);

    }

   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtVenue = new javax.swing.JTextField();
        txtDate = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        cmbArea = new javax.swing.JComboBox();
        cmdExit = new javax.swing.JButton();
        cmdCreate = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setClosable(true);
        setIconifiable(true);
        setTitle("New PMES Schedule");
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

        txtVenue.setToolTipText("Ex. Bindoy Municipal Hall");
        txtVenue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        txtDate.setToolTipText("01/01/2011");
        txtDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtDate.setSelectionEnd(10);
        txtDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDateActionPerformed(evt);
            }
        });
        txtDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDateFocusGained(evt);
            }
        });

        txtAddress.setToolTipText("Specific Address: Ex. Bulod, Bindoy Negros Oriental  ");
        txtAddress.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        cmbArea.setForeground(new java.awt.Color(102, 102, 102));
        cmbArea.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-SELECT-" }));
        cmbArea.setToolTipText("Municipal/City");
        cmbArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAreaActionPerformed(evt);
            }
        });

        cmdExit.setText("Cancel");
        cmdExit.setToolTipText("Cancel and exit window");
        cmdExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExitActionPerformed(evt);
            }
        });

        cmdCreate.setText("Schedule");
        cmdCreate.setToolTipText("Create new schedule");
        cmdCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCreateActionPerformed(evt);
            }
        });
        cmdCreate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmdCreateKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtVenue, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                        .addComponent(cmbArea, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAddress))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmdCreate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdExit)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbArea, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(txtVenue, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCreate)
                    .addComponent(cmdExit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setLabelFor(txtVenue);
        jLabel2.setText("Venue:");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setLabelFor(txtDate);
        jLabel3.setText("Date:");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setLabelFor(txtDate);
        jLabel5.setText("Address:");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setLabelFor(txtVenue);
        jLabel6.setText("Area:");

        jToolBar1.setRollover(true);
        jToolBar1.setEnabled(false);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Membership/Images/newsched.png"))); // NOI18N
        jToolBar1.add(jLabel4);

        jLabel7.setForeground(new java.awt.Color(255, 102, 0));
        jLabel7.setText(" Reminder: Please fill-up all the required fields.");
        jToolBar1.add(jLabel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void add() {
        //get value of the textfields

        int strAreaCode = areaCode;
        String strAddress = txtAddress.getText();
        String strVenue = txtVenue.getText();
        String strDate = txtDate.getText();

        //check if the txtfields are not  empty
        if (txtVenue.getText().isEmpty() == true || txtDate.getText().isEmpty() == true || txtAddress.getText().isEmpty() == true || cmbArea.getSelectedItem() == "-SELECT-") {
            JOptionPane.showMessageDialog(this, "Please fill-up all the required fields!");
        } else {
            //check if the date entered is valid
            if (myFunctions.isValidDate(strDate) == false) {
                return;
            } else {
                int dupflg = myDataenvi.findDupSched(strDate, strAreaCode);

                if (dupflg == 0) {
                    //add the new record to the database
                    myDataenvi.rsAddSched(strVenue, strDate, strAreaCode, strAddress);
                } else {
                    int i = myFunctions.msgboxYesNoCancel("System detected an existing schedule in this particular date and area. Would you like to proceed setting this new schedule?");
                    if (i == 0) {
                        //proceed add the new record to the database even it is already existed
                        myDataenvi.rsAddSched(strVenue, strDate, strAreaCode, strAddress);
                    } else if (i == 1) {
                        return; //do nothing
                    } else if (i == 2) {
                        this.dispose(); //exit window
                        return;
                    } else {
                        return; //do nothing
                    }

                }

            }
            //clear the fields and set the focus to the first textfield
            txtVenue.setText("");
            txtDate.setText(nowDate);
            txtAddress.setText("");
            cmbArea.setSelectedIndex(0);
            cmbArea.requestFocus();

            //inform user for succesfull process
            JOptionPane.showMessageDialog(this, "New schedule has been successfully set!");
        }

    }

    private void cmdExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdExitActionPerformed

    private void cmdCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCreateActionPerformed
        add();
    }//GEN-LAST:event_cmdCreateActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened

        txtDate.setText(nowDate);


        //Populate Combo Area
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "SELECT * FROM areaTBL ORDER BY area_desc;";

        try {
            stmtSelectAllAreas = conn.createStatement();
            ResultSet rs = stmtSelectAllAreas.executeQuery(createString);

            while (rs.next()) {
                cmbArea.addItem(new Item(rs.getInt(1), rs.getString(2)));
            }

            stmtSelectAllAreas.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error RSQuery/004: Query Select All Areas!");
        }
    }//GEN-LAST:event_formInternalFrameOpened

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

    private void cmbAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAreaActionPerformed
        try {
            Item item = (Item) cmbArea.getSelectedItem();
            areaCode = item.getId();
        } catch (Exception e) {
        }

    }//GEN-LAST:event_cmbAreaActionPerformed

    private void txtDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDateActionPerformed

    private void txtDateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDateFocusGained
        txtDate.selectAll();
    }//GEN-LAST:event_txtDateFocusGained

    private void cmdCreateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmdCreateKeyPressed
        add();
    }//GEN-LAST:event_cmdCreateKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbArea;
    private javax.swing.JButton cmdCreate;
    private javax.swing.JButton cmdExit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtVenue;
    // End of variables declaration//GEN-END:variables
}
