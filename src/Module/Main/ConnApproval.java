package Module.Main;

import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;

public class ConnApproval extends javax.swing.JInternalFrame {

    static Statement stmt;
    static DefaultTableCellRenderer cellAlignCenterRenderer = new DefaultTableCellRenderer();
    static DefaultTableModel model;
    static DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();

    public ConnApproval() {
        initComponents();
    }
    
    
        public void viewAll() {
      txtsearch2.setText("");
      pane2.setVisible(false);
      populateTBL();
    }

    public void viewCustom() {
        pane2.setVisible(true);
        txtsearch2.requestFocus();
    }

    public void populateTBL() {


        Connection conn = DBConn.getConnection();
        String createString;
        createString = "SELECT AcctNo, MembershipID, AcctName, s.statdesc, CONVERT(char(10), TransDate, 101) ,t.typedesc, ClassCode "
                + "FROM connTBL c, connTypeTBL t, connStatTBL s "
                + "WHERE c.status=s.status AND c.connType=t.connType AND s.status=1 AND acctname LIKE '"+ txtsearch2.getText()+"%' "
                + "ORDER BY AcctName";
        //stmtIncomingShed

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model = (DefaultTableModel) jTBLConn.getModel();

            renderer.setHorizontalAlignment(0);

            jTBLConn.setRowHeight(23);
            jTBLConn.getColumnModel().getColumn(0).setCellRenderer(renderer);
            jTBLConn.getColumnModel().getColumn(1).setCellRenderer(renderer);
            jTBLConn.getColumnModel().getColumn(4).setCellRenderer(renderer);
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
        jToolBar1 = new javax.swing.JToolBar();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmdApprove = new javax.swing.JButton();
        cmdExit = new javax.swing.JButton();
        cmdRefresh = new javax.swing.JButton();
        cmdRefresh1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTBLConn = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        opt1 = new javax.swing.JRadioButton();
        opt2 = new javax.swing.JRadioButton();
        pane2 = new javax.swing.JPanel();
        txtsearch2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Connection App. Aproval");
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

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Membership/Images/approved.png"))); // NOI18N
        jToolBar1.add(jLabel4);

        jLabel7.setForeground(new java.awt.Color(255, 102, 0));
        jLabel7.setText("Here you can approve new connection application and send it for costing.");
        jToolBar1.add(jLabel7);

        cmdApprove.setForeground(new java.awt.Color(0, 102, 51));
        cmdApprove.setMnemonic('A');
        cmdApprove.setText("Approve and Send For Costing");
        cmdApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdApproveActionPerformed(evt);
            }
        });

        cmdExit.setMnemonic('x');
        cmdExit.setText("Exit");
        cmdExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExitActionPerformed(evt);
            }
        });

        cmdRefresh.setMnemonic('R');
        cmdRefresh.setText("Refresh");
        cmdRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRefreshActionPerformed(evt);
            }
        });

        cmdRefresh1.setForeground(new java.awt.Color(255, 51, 51));
        cmdRefresh1.setMnemonic('D');
        cmdRefresh1.setText("Disapproved");
        cmdRefresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRefresh1ActionPerformed(evt);
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
        jTBLConn.getColumnModel().getColumn(4).setMaxWidth(100);
        jTBLConn.getColumnModel().getColumn(6).setMaxWidth(50);

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
            .addComponent(opt2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(opt1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        txtsearch2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtsearch2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsearch2ActionPerformed(evt);
            }
        });
        txtsearch2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsearch2KeyPressed(evt);
            }
        });

        jButton2.setMnemonic('R');
        jButton2.setText("REFRESH");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pane2Layout = new javax.swing.GroupLayout(pane2);
        pane2.setLayout(pane2Layout);
        pane2Layout.setHorizontalGroup(
            pane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pane2Layout.createSequentialGroup()
                .addComponent(txtsearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(0, 138, Short.MAX_VALUE))
        );
        pane2Layout.setVerticalGroup(
            pane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pane2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 928, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cmdRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 370, Short.MAX_VALUE)
                        .addComponent(cmdApprove, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdRefresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdExit, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 908, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdExit)
                    .addComponent(cmdRefresh)
                    .addComponent(cmdRefresh1)
                    .addComponent(cmdApprove))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTBLConnMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBLConnMouseMoved
}//GEN-LAST:event_jTBLConnMouseMoved

    private void cmdExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdExitActionPerformed

    private void cmdRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRefreshActionPerformed
        populateTBL();
    }//GEN-LAST:event_cmdRefreshActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
       viewAll();
        populateTBL();
    }//GEN-LAST:event_formInternalFrameOpened

    private void cmdRefresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRefresh1ActionPerformed
        int col = 0; //set column value to 0
        int row = jTBLConn.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (jTBLConn.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please select a record from the list!");
            return;
        } else {
            String id = jTBLConn.getValueAt(row, col).toString();

            int i = myFunctions.msgboxYesNo("Are you sure you want to disapprove this current application?" + "\n" + "NOTE: This will permanently delete the application!");
            if (i == 0) {
                rsDisapprove(id);
                populateTBL();
                JOptionPane.showMessageDialog(this, "Record has been successfully deleted!");
            } else if (i == 1) {
                return; //do nothing
            } else if (i == 2) {
                this.dispose(); //exit window
                return;
            }
        }
    }//GEN-LAST:event_cmdRefresh1ActionPerformed

    private void cmdApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdApproveActionPerformed
        int col = 0; //set column value to 0
        int row = jTBLConn.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (jTBLConn.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please select a record from the list!");
            return;
        } else {
            String id = jTBLConn.getValueAt(row, col).toString();

            int i = myFunctions.msgboxYesNo("This Record will now transfer to Costing Section!" + "\n" + "It will not be available here in approval section unless the Costing Section sends back this record ");
            if (i == 0) {
                myDataenvi.rsUpdateConnStat(Integer.parseInt(id), 2);
                populateTBL();
                JOptionPane.showMessageDialog(this, "Record has been successfully sent!");
            } else if (i == 1) {
                return; //do nothing
            } else if (i == 2) {
                this.dispose(); //exit window
                return;
            }
        }
    }//GEN-LAST:event_cmdApproveActionPerformed

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

    private void txtsearch2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearch2KeyPressed
        populateTBL();
    }//GEN-LAST:event_txtsearch2KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        txtsearch2.setText("");
        populateTBL();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtsearch2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsearch2ActionPerformed
        populateTBL();
    }//GEN-LAST:event_txtsearch2ActionPerformed

    public static void rsUpdateStat(int AcctNo, int Stat) {
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "UPDATE connTBL SET "
                + "status='" + Stat + "' WHERE AcctNo=" + AcctNo;



        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void rsDisapprove(String acctno) {
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "DELETE FROM connTBL WHERE AcctNo='" + acctno + "'";

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdApprove;
    private javax.swing.JButton cmdExit;
    private javax.swing.JButton cmdRefresh;
    private javax.swing.JButton cmdRefresh1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTBLConn;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JRadioButton opt1;
    private javax.swing.JRadioButton opt2;
    private javax.swing.JPanel pane2;
    private javax.swing.JTextField txtsearch2;
    private javax.swing.ButtonGroup viewOpt;
    // End of variables declaration//GEN-END:variables
}
