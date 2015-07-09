package Module.Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;

public class CostingMain extends javax.swing.JInternalFrame {

    static Statement stmt;
    static DefaultTableCellRenderer cellAlignCenterRenderer = new DefaultTableCellRenderer();
    static DefaultTableModel model;
    static DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    public static CostingOp frmCosting;

    public CostingMain() {
        initComponents();
    }

    public void showFrmCosting() {
        frmCosting = new CostingOp(this, true);
        frmCosting.setVisible(true);
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

    public void populateTBL() {


        Connection conn = DBConn.getConnection();
        String createString;
        createString = "SELECT AcctNo, MembershipID, AcctName, s.statdesc, CONVERT(char(10), TransDate, 101) ,t.typedesc, ClassCode "
                + "FROM connTBL c, connTypeTBL t, connStatTBL s "
                + "WHERE c.status=s.status AND c.connType=t.connType AND s.status=2 AND acctname LIKE '"+ txtsearch.getText()+"%' "
                + "ORDER BY AcctName";
        //stmtIncomingShed

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model = (DefaultTableModel) jTBLConn.getModel();

            renderer.setHorizontalAlignment(0);

            jTBLConn.setRowHeight(21);
            jTBLConn.getColumnModel().getColumn(0).setCellRenderer(renderer);
            jTBLConn.getColumnModel().getColumn(1).setCellRenderer(renderer);
            jTBLConn.getColumnModel().getColumn(3).setCellRenderer(renderer);
            jTBLConn.getColumnModel().getColumn(4).setCellRenderer(renderer);
            jTBLConn.getColumnModel().getColumn(5).setCellRenderer(renderer);
            jTBLConn.getColumnModel().getColumn(6).setCellRenderer(renderer);
            jTBLConn.setColumnSelectionAllowed(false);

            model.setNumRows(0);


            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});
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

        viewOpt = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTBLConn = new javax.swing.JTable();
        cmdRefresh1 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmdApproved = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cmdPreview = new javax.swing.JButton();
        cmdCosting = new javax.swing.JButton();
        cmdExit = new javax.swing.JButton();
        cmdRefresh = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        opt1 = new javax.swing.JRadioButton();
        opt2 = new javax.swing.JRadioButton();
        pane = new javax.swing.JPanel();
        txtsearch = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Costing");
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

        jTBLConn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Account No", "MemberID", "Account Name", "Status", "AppDate", "Type", "Class"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBLConn.setToolTipText("");
        jTBLConn.getTableHeader().setReorderingAllowed(false);
        jTBLConn.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jTBLConnMouseMoved(evt);
            }
        });
        jScrollPane1.setViewportView(jTBLConn);
        //set column width
        jTBLConn.getColumnModel().getColumn(0).setMaxWidth(80);
        jTBLConn.getColumnModel().getColumn(1).setMaxWidth(80);
        jTBLConn.getColumnModel().getColumn(2).setPreferredWidth(180);
        jTBLConn.getColumnModel().getColumn(4).setMaxWidth(100);
        jTBLConn.getColumnModel().getColumn(6).setMaxWidth(50);

        cmdRefresh1.setForeground(new java.awt.Color(255, 51, 51));
        cmdRefresh1.setMnemonic('D');
        cmdRefresh1.setText("Send back to Approval");
        cmdRefresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRefresh1ActionPerformed(evt);
            }
        });

        jToolBar1.setRollover(true);
        jToolBar1.setEnabled(false);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Membership/Images/costing.png"))); // NOI18N
        jToolBar1.add(jLabel4);

        jLabel7.setForeground(new java.awt.Color(255, 102, 0));
        jLabel7.setText("Here you can add, edit costing of new  connection application and send it for payments or send it back to approval.");
        jToolBar1.add(jLabel7);

        cmdApproved.setForeground(new java.awt.Color(0, 102, 0));
        cmdApproved.setText("Approved and Send to Payment");
        cmdApproved.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdApprovedActionPerformed(evt);
            }
        });

        cmdPreview.setMnemonic('w');
        cmdPreview.setText("Print");
        cmdPreview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPreviewActionPerformed(evt);
            }
        });

        cmdCosting.setMnemonic('x');
        cmdCosting.setText("Costing");
        cmdCosting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCostingActionPerformed(evt);
            }
        });

        cmdExit.setMnemonic('x');
        cmdExit.setText("Exit");
        cmdExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdCosting, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                    .addComponent(cmdExit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(cmdPreview)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdCosting)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdExit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cmdRefresh.setMnemonic('R');
        cmdRefresh.setText("Refresh");
        cmdRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRefreshActionPerformed(evt);
            }
        });

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
                .addGap(0, 50, Short.MAX_VALUE))
        );
        paneLayout.setVerticalGroup(
            paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton1))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(opt1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(opt2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(opt1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(opt2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmdRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmdApproved, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmdRefresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmdApproved)
                            .addComponent(cmdRefresh)
                            .addComponent(cmdRefresh1)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTBLConnMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBLConnMouseMoved
}//GEN-LAST:event_jTBLConnMouseMoved

    private void cmdRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRefreshActionPerformed
        populateTBL();
}//GEN-LAST:event_cmdRefreshActionPerformed

    private void cmdExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExitActionPerformed
        this.dispose();
}//GEN-LAST:event_cmdExitActionPerformed

    private void cmdRefresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRefresh1ActionPerformed
        int col = 0; //set column value to 0
        int row = jTBLConn.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (jTBLConn.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please select a record from the list!");
            return;
        } else {
            String id = jTBLConn.getValueAt(row, col).toString();

            int i = myFunctions.msgboxYesNo("This Record will now transfer Back to Approval Section!" + "\n" + "It will not be available here in approval section unless the Approval Section sends back this record ");
            if (i == 0) {
                myDataenvi.rsUpdateConnStat(Integer.parseInt(id), 1);
                populateTBL();
                JOptionPane.showMessageDialog(this, "Record has been successfully sent!");
            } else if (i == 1) {
                return; //do nothing
            } else if (i == 2) {
                this.dispose(); //exit window
                return;
            }
        }
}//GEN-LAST:event_cmdRefresh1ActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        viewAll();
        populateTBL();
    }//GEN-LAST:event_formInternalFrameOpened

    private void cmdCostingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCostingActionPerformed
        int col = 0; //set column value to 0
        int row = jTBLConn.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (jTBLConn.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please select a record from the list!");
            return;
        } else {
            String id = jTBLConn.getValueAt(row, col).toString();
            String nym = jTBLConn.getValueAt(row, 2).toString();

            CostingOp.acctno = Integer.parseInt(id);
            CostingOp.acctname = nym;
            showFrmCosting();
        }
    }//GEN-LAST:event_cmdCostingActionPerformed

    public void transferToPayment(int acctno) {
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "INSERT INTO costingTBL (AcctNo, description, qty, unit, cost, COAID, total) "
                + " SELECT AcctNo, description, qty, unit, cost, COAID, total FROM costingTempTBL WHERE AcctNo=" + acctno;

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void cmdApprovedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdApprovedActionPerformed
        int col = 0; //set column value to 0
        int row = jTBLConn.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (jTBLConn.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please select a record from the list!");
            return;
        } else {
            String id = jTBLConn.getValueAt(row, col).toString();
            String nym = jTBLConn.getValueAt(row, 2).toString();
            boolean x = myDataenvi.checkCosting(Integer.parseInt(id));


            if (x == true) {
                int i = myFunctions.msgboxYesNo("This Record will now transfer to payment!");
                if (i == 0) {



                    int dbl = determineIfBigloads(Integer.parseInt(id));
                    String capt = null;
                    String note = null;

                    if (dbl == 1) {
                        capt = "SUBTOTAL>>";
                        note = "NOTE: The amount of Energy Deposit stated above is only refundable upon termination of contract on electric service, as per Coop Policy.";
                    } else {
                        capt = "TOTAL";
                        note = "";
                    }
                    try {
                        myReports.rptCosting(Integer.parseInt(id), nym, "HANGYAD, BAIS CITY", capt, note, "PAYMENT COSTING");
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(CostingMain.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(CostingMain.class.getName()).log(Level.SEVERE, null, ex);
                    }



                    myDataenvi.rsUpdateConnStat(Integer.parseInt(id), 6);
                    transferToPayment(Integer.parseInt(id));
                    populateTBL();







                    JOptionPane.showMessageDialog(this, "Record has been successfully sent!");



                } else if (i == 1) {
                    return; //do nothing
                } else if (i == 2) {
                    this.dispose(); //exit window
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Account is not yet been costed!");
            }
        }
}//GEN-LAST:event_cmdApprovedActionPerformed

    private void cmdPreviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPreviewActionPerformed

        int col = 0; //set column value to 0
        int row = jTBLConn.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (jTBLConn.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please select a record from the list!");
            return;
        } else {
            String id = jTBLConn.getValueAt(row, col).toString();
            String nym = jTBLConn.getValueAt(row, 2).toString();

            boolean x = myDataenvi.checkCosting(Integer.parseInt(id));


            if (x == true) {
                int dbl = determineIfBigloads(Integer.parseInt(id));
                String capt = null;
                String note = null;

                if (dbl == 1) {
                    capt = "SUBTOTAL>>";
                    note = "NOTE: The amount of Energy Deposit stated above is only refundable upon termination of contract on electric service, as per Coop Policy.";
                } else {
                    capt = "TOTAL";
                    note = "";
                }
                try {
                    myReports.rptCosting(Integer.parseInt(id), nym, "HANGYAD, BAIS CITY", capt, note, "PAYMENT COSTING (DRAFT)");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CostingMain.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(CostingMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Not yet costed!", "Administrator", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }



    }//GEN-LAST:event_cmdPreviewActionPerformed

    private void opt1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opt1MouseClicked
   }//GEN-LAST:event_opt1MouseClicked

    private void opt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opt1MouseReleased
   }//GEN-LAST:event_opt1MouseReleased

    private void opt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opt1ActionPerformed
        viewAll();
    }//GEN-LAST:event_opt1ActionPerformed

    private void opt2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opt2MouseClicked
   }//GEN-LAST:event_opt2MouseClicked

    private void opt2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opt2MouseReleased
   }//GEN-LAST:event_opt2MouseReleased

    private void opt2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opt2ActionPerformed
        viewCustom();
    }//GEN-LAST:event_opt2ActionPerformed

    private void txtsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsearchActionPerformed

    private void txtsearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyPressed
        populateTBL();
    }//GEN-LAST:event_txtsearchKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        txtsearch.setText("");
        populateTBL();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static int determineIfBigloads(int AcctID) {

        int t = 0;
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "SELECT COAID FROM costingTempTBL WHERE AcctNo=" + AcctID + " AND COAID=54";
        //stmtIncomingShed

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                t++;

            }

            //t= rs.getFetchSize();

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.getStackTrace();
        }
        return t;

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdApproved;
    private javax.swing.JButton cmdCosting;
    private javax.swing.JButton cmdExit;
    private javax.swing.JButton cmdPreview;
    private javax.swing.JButton cmdRefresh;
    private javax.swing.JButton cmdRefresh1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTBLConn;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JRadioButton opt1;
    private javax.swing.JRadioButton opt2;
    private javax.swing.JPanel pane;
    private javax.swing.JTextField txtsearch;
    private javax.swing.ButtonGroup viewOpt;
    // End of variables declaration//GEN-END:variables
}
