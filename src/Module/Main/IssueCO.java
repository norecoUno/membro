//SELECT AcctNo, MembershipID, AcctName, s.statdesc, CONVERT(char(10), TransDate, 101) ,t.typedesc, ClassCode 
//FROM connTBL c, connTypeTBL t, connStatTBL s 
//WHERE c.status=s.status AND c.connType=t.connType AND c.Status=6
//ORDER BY AcctName
package Module.Main;

import Module.Override.override_payment;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class IssueCO extends javax.swing.JInternalFrame {

    static Statement stmt;
    static DefaultTableCellRenderer cellAlignCenterRenderer = new DefaultTableCellRenderer();
    static DefaultTableModel model;
    static DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
    static String nowDate = myFunctions.getDate();
    public static override_payment frmo;

    public IssueCO() {
        initComponents();
    }

    public void showFrmOverride() {
        frmo = new override_payment(this, true);
        frmo.setVisible(true);
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
                + " FROM connTBL c, connTypeTBL t, connStatTBL s "
                + " WHERE c.status=s.status AND c.connType=t.connType AND c.Status=6 and acctname LIKE '" + txtsearch.getText() + "%' "
                + " ORDER BY AcctName";

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
                String paidFlag = null;
                int paid = determineIfPaid(rs.getInt(1));
                paidFlag = "UNPAID";
                if (paid == 0) {
                    paidFlag = "PAID";
                } else {
                }

                boolean x = checkCostingIfPaid(rs.getInt(1));

                if (x == false) {
                    paidFlag = "UNPAID";
                } else if (x == true) {
                    paidFlag = "PAID";
                }

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), paidFlag, rs.getString(5), rs.getString(6), rs.getString(7)});
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public static int determineIfPaid(int AcctID) {

        int t = 0;
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "SELECT Count(*) FROM costingTBL WHERE AcctNo=" + AcctID;
        //stmtIncomingShed

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                t = rs.getInt(1);
            }

            //t= rs.getFetchSize();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.getStackTrace();
        }
        return t;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewOpt = new javax.swing.ButtonGroup();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTBLConn = new javax.swing.JTable();
        cmdApprove = new javax.swing.JButton();
        cmdExit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        opt1 = new javax.swing.JRadioButton();
        opt2 = new javax.swing.JRadioButton();
        pane = new javax.swing.JPanel();
        txtsearch = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        cmdExit1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Connect Order Issuance");
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

        jLabel7.setForeground(new java.awt.Color(255, 102, 0));
        jLabel7.setText("Here you can issue the connect order for the Paid Accounts.");
        jToolBar1.add(jLabel7);

        jTBLConn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Account No", "MemberID", "Account Name", "Status of Payment", "AppDate", "Type", "Class"
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

        cmdApprove.setForeground(new java.awt.Color(0, 153, 51));
        cmdApprove.setMnemonic('A');
        cmdApprove.setText("Issue CO and Send to TSD ");
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(opt1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(opt2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

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
                .addGap(0, 258, Short.MAX_VALUE))
        );
        paneLayout.setVerticalGroup(
            paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLayout.createSequentialGroup()
                .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        cmdExit1.setMnemonic('x');
        cmdExit1.setText("Override");
        cmdExit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExit1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1159, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1159, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdExit1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdApprove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdExit)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdExit)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmdApprove)
                        .addComponent(cmdExit1)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTBLConnMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTBLConnMouseMoved
   }//GEN-LAST:event_jTBLConnMouseMoved

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        viewAll();
        populateTBL();
    }//GEN-LAST:event_formInternalFrameOpened

    private void cmdExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExitActionPerformed
        this.dispose();

    }//GEN-LAST:event_cmdExitActionPerformed

    private void cmdApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdApproveActionPerformed
        int col = 0; //set column value to 0
        int row = jTBLConn.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (jTBLConn.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please select a record from the list!");
            return;
        } else {
            String acctno = jTBLConn.getValueAt(row, col).toString();
            String memid = jTBLConn.getValueAt(row, 1).toString();
            boolean x = checkCostingIfPaid(Integer.parseInt(acctno));

            if (x == false) {
                JOptionPane.showMessageDialog(this, "This Account is not yet paid!");
                return;

            } else if (x == true) {
                myReports.rptConnectOrder(acctno, nowDate, memid);
                myDataenvi.rsUpdateConnStat(Integer.parseInt(acctno), 3);
                populateTBL();
            }

        }
    }//GEN-LAST:event_cmdApproveActionPerformed

    public static boolean checkCostingIfPaid(int acctno) {
        boolean fexist = false;

        Connection conn = DBConn.getConnection();
        String createString;
        createString = "SELECT AcctNo FROM costingTBL WHERE AcctNo=" + acctno + " AND transID IS NOT NULL";
        //stmtIncomingShed

        int t = 0;
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

        if (t != 0) {
            fexist = true;
        }
        return fexist;
    }

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
        populateTBL();
    }//GEN-LAST:event_txtsearchActionPerformed

    private void txtsearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchKeyPressed
        populateTBL();
    }//GEN-LAST:event_txtsearchKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        txtsearch.setText("");
        populateTBL();
      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmdExit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExit1ActionPerformed
        int col = 0; //set column value to 0
        int row = jTBLConn.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (jTBLConn.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please a record from the list!");
        } else {
            String id = jTBLConn.getValueAt(row, col).toString();
            override_payment.acctno =Integer.parseInt(id);
            showFrmOverride();
        }
    }//GEN-LAST:event_cmdExit1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdApprove;
    private javax.swing.JButton cmdExit;
    private javax.swing.JButton cmdExit1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel7;
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
