package Module.Main;

import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class ManageProfile extends javax.swing.JInternalFrame {

    static Statement stmtIncomingShed;
    static String nowDate = myFunctions.getDate();
    static DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    static DefaultTableModel model;
    public static EditProfileNJuri frmUpdate;
    public static EditProfileJuri frmUpdate2;
    public static CurrentPhoto frmCPhoto;

    public ManageProfile() {
        initComponents();
    }

    public void showFrmCPhoto() {
        frmCPhoto = new CurrentPhoto(this, true);
        frmCPhoto.setVisible(true);
    }

    public void viewAll() {
      txtsearch.setText("");
      pane.setVisible(false);
      populateTBL();
    }

    public void viewCustom() {
        pane.setVisible(true);
        txtsearch.requestFocus();
    }

    public void showFrmUpdate() {
        frmUpdate = new EditProfileNJuri(this, true);
        frmUpdate.setVisible(true);
    }

    public void showFrmUpdate2() {
        frmUpdate2 = new EditProfileJuri(this, true);
        frmUpdate2.setVisible(true);
    }

    public void populateTBL() {


        Connection conn = DBConn.getConnection();
        String createString;
        createString = "SELECT memberID, acctname, address ,s.status, CONVERT(char(10), date_encoded, 101), juridical_stat "
                + "FROM membersTBL m INNER JOIN memStatTBL s ON m.status=s.memStatID "
                + "WHERE acctname LIKE '"+ txtsearch.getText()+"%' "
                + "ORDER BY acctname";
        //stmtIncomingShed

        try {
            stmtIncomingShed = conn.createStatement();
            ResultSet rs = stmtIncomingShed.executeQuery(createString);

            model = (DefaultTableModel) jTblManageProfile.getModel();

            renderer.setHorizontalAlignment(0);

            jTblManageProfile.setRowHeight(21);
            jTblManageProfile.getColumnModel().getColumn(0).setCellRenderer(renderer);
            jTblManageProfile.getColumnModel().getColumn(3).setCellRenderer(renderer);
            jTblManageProfile.getColumnModel().getColumn(4).setCellRenderer(renderer);
            jTblManageProfile.getColumnModel().getColumn(5).setCellRenderer(renderer);


            jTblManageProfile.setColumnSelectionAllowed(false);

            model.setNumRows(0);


            while (rs.next()) {
                int x = rs.getInt(6);
                String flg = null;
                if (x == 0) {
                    flg = "NON-JURIDICAL";
                } else {
                    flg = "JURIDICAL";
                }

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), flg});
            }

            stmtIncomingShed.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewOpt = new javax.swing.ButtonGroup();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPaneIncSched = new javax.swing.JScrollPane();
        jTblManageProfile = new javax.swing.JTable();
        cmdEdit = new javax.swing.JButton();
        cmdDelete = new javax.swing.JButton();
        cmdExit = new javax.swing.JButton();
        cmdViewExconn = new javax.swing.JButton();
        cmdPicSetup = new javax.swing.JButton();
        pane = new javax.swing.JPanel();
        txtsearch = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        opt1 = new javax.swing.JRadioButton();
        opt2 = new javax.swing.JRadioButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("View/Manage Members Record");
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

        jToolBar1.setRollover(true);
        jToolBar1.setEnabled(false);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Membership/Images/managemember.png"))); // NOI18N
        jToolBar1.add(jLabel4);

        jLabel7.setForeground(new java.awt.Color(255, 102, 0));
        jLabel7.setText("Note: Here  you can view, update and delete members profile");
        jToolBar1.add(jLabel7);

        jScrollPaneIncSched.setToolTipText("");
        jScrollPaneIncSched.setAutoscrolls(true);

        jTblManageProfile.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MemberID", "Name of Member", "Address", "Status", "Date Encoded", "Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblManageProfile.setColumnSelectionAllowed(true);
        jScrollPaneIncSched.setViewportView(jTblManageProfile);
        jTblManageProfile.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTblManageProfile.getColumnModel().getColumn(0).setMaxWidth(100);
        jTblManageProfile.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTblManageProfile.getColumnModel().getColumn(2).setPreferredWidth(90);
        jTblManageProfile.getColumnModel().getColumn(3).setPreferredWidth(90);
        jTblManageProfile.getColumnModel().getColumn(4).setPreferredWidth(90);

        cmdEdit.setMnemonic('E');
        cmdEdit.setText("Edit Profile");
        cmdEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditActionPerformed(evt);
            }
        });

        cmdDelete.setMnemonic('D');
        cmdDelete.setText("Delete");
        cmdDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteActionPerformed(evt);
            }
        });

        cmdExit.setMnemonic('x');
        cmdExit.setText("Exit");
        cmdExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExitActionPerformed(evt);
            }
        });

        cmdViewExconn.setMnemonic('E');
        cmdViewExconn.setText("View E-Conn Accounts");
        cmdViewExconn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdViewExconnActionPerformed(evt);
            }
        });

        cmdPicSetup.setMnemonic('P');
        cmdPicSetup.setText("Picture");
        cmdPicSetup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPicSetupActionPerformed(evt);
            }
        });

        pane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtsearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsearchActionPerformed(evt);
            }
        });
        txtsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsearchKeyPressed(evt);
            }
        });

        jButton1.setMnemonic('R');
        jButton1.setText("REFRESH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paneLayout = new javax.swing.GroupLayout(pane);
        pane.setLayout(paneLayout);
        paneLayout.setHorizontalGroup(
            paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLayout.createSequentialGroup()
                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(0, 225, Short.MAX_VALUE))
        );
        paneLayout.setVerticalGroup(
            paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLayout.createSequentialGroup()
                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        viewOpt.add(opt1);
        opt1.setSelected(true);
        opt1.setText("All Records");
        opt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                opt1MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                opt1MouseReleased(evt);
            }
        });
        opt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opt1ActionPerformed(evt);
            }
        });

        viewOpt.add(opt2);
        opt2.setText("Custom View");
        opt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                opt2MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                opt2MouseReleased(evt);
            }
        });
        opt2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opt2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(opt1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(opt2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(opt1)
                    .addComponent(opt2))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPaneIncSched)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdViewExconn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdPicSetup)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdExit))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 133, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneIncSched, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdDelete)
                    .addComponent(cmdExit)
                    .addComponent(cmdViewExconn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdPicSetup, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdExitActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        viewAll();
        populateTBL();
       
    }//GEN-LAST:event_formInternalFrameOpened

    private void cmdEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditActionPerformed
        int col = 0; //set column value to 0
        int row = jTblManageProfile.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (jTblManageProfile.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please a record from the list!");
            return;
        } else {
            String id = jTblManageProfile.getValueAt(row, col).toString();
            String juri = jTblManageProfile.getValueAt(row, 5).toString();

            if ("JURIDICAL".equals(juri)) {
                EditProfileJuri.memid = id;
                showFrmUpdate2();
            } else {
                EditProfileNJuri.memid = id;
//                JDialogEditProfile1.cflg=0;
//                JDialogEditProfile1.title="Edit Member Profile";
                showFrmUpdate();
            }

        }
    }//GEN-LAST:event_cmdEditActionPerformed

    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteActionPerformed
        int col = 0; //set column value to 0
        int row = jTblManageProfile.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (jTblManageProfile.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please a record from the list!");
            return;
        } else {
            String Pid = jTblManageProfile.getValueAt(row, col).toString();
            int i = myFunctions.msgboxYesNo("Are you sure you want delete this current record?");
            if (i == 0) {
                myDataenvi.rsDeleteProfile(Pid);
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

    private void cmdViewExconnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdViewExconnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdViewExconnActionPerformed

    private void cmdPicSetupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPicSetupActionPerformed
        int col = 0; //set column value to 0
        int row = jTblManageProfile.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (jTblManageProfile.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please a record from the list!");
            return;
        } else {
            String memID = jTblManageProfile.getValueAt(row, col).toString();
            String name = jTblManageProfile.getValueAt(row, 1).toString();
            CurrentPhoto.name = name;
            CurrentPhoto.memID = memID;
            showFrmCPhoto();
        }
    }//GEN-LAST:event_cmdPicSetupActionPerformed

    private void opt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opt2ActionPerformed
        viewCustom();
    }//GEN-LAST:event_opt2ActionPerformed

    private void opt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opt1ActionPerformed
        viewAll();
    }//GEN-LAST:event_opt1ActionPerformed

    private void opt2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opt2MouseClicked
    }//GEN-LAST:event_opt2MouseClicked

    private void opt1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opt1MouseClicked
    }//GEN-LAST:event_opt1MouseClicked

    private void opt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opt1MouseReleased
    }//GEN-LAST:event_opt1MouseReleased

    private void opt2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opt2MouseReleased
    }//GEN-LAST:event_opt2MouseReleased

    private void txtsearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyPressed
        populateTBL();
    }//GEN-LAST:event_txtsearchKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        txtsearch.setText("");
        populateTBL();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdDelete;
    private javax.swing.JButton cmdEdit;
    private javax.swing.JButton cmdExit;
    private javax.swing.JButton cmdPicSetup;
    private javax.swing.JButton cmdViewExconn;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPaneIncSched;
    private javax.swing.JTable jTblManageProfile;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JRadioButton opt1;
    private javax.swing.JRadioButton opt2;
    private javax.swing.JPanel pane;
    private javax.swing.JTextField txtsearch;
    private javax.swing.ButtonGroup viewOpt;
    // End of variables declaration//GEN-END:variables
}
