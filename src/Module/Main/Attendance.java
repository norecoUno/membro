package Module.Main;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class Attendance extends javax.swing.JInternalFrame {

    static Statement stmtAttendance, stmtUpdate, stmt;
    static Statement stmtSelectSched;
    public static int BatchID;
    public static String myTitle;
    static String nowDate = myFunctions.getDate();
    static DefaultTableCellRenderer cellAlignCenterRenderer = new DefaultTableCellRenderer();
    static DefaultTableModel model;
    public static AddNewParticipants frmAdd;
    public static CurrentSignature frmCSignature;
    public static UpdateParticipant frmUpdate;
    public static CurrentPhoto1 frmcphoto;

    public Attendance() {
        initComponents();
        cmdAdd.setMnemonic('A');
        cmdUpdate.setMnemonic('E');
        cmdDelete.setMnemonic('R');
        cmdExit.setMnemonic('x');
        cmdPrint.setMnemonic('P');
        cmdCancelPart.setMnemonic('t');
        cmdPrint.setEnabled(false);
        cmdRePrint.setEnabled(false);
    }

    public void showFrmAdd() {
        frmAdd = new AddNewParticipants(this, true);
        frmAdd.setVisible(true);
    }

    public void showFrmUpdate() {
        frmUpdate = new UpdateParticipant(this, true);
        frmUpdate.setVisible(true);
    }

    public static void rsUpdatePartRec(int bID) {
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "UPDATE dbo.participantsTBL "
                + "SET print_count=1, date_printed='" + nowDate + "' WHERE batchID=" + bID + "";

        try {
            stmtUpdate = conn.createStatement();
            stmtUpdate.executeUpdate(createString);
            stmtUpdate.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void rsClosedSched(int bID) {
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "UPDATE dbo.scheduleTBL "
                + "SET sched_stat=1 WHERE batchID='" + bID + "'";

        try {
            stmtUpdate = conn.createStatement();
            stmtUpdate.executeUpdate(createString);
            stmtUpdate.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void rsUpdatePartStat(String pID) {
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "UPDATE dbo.participantsTBL "
                + "SET part_stat=1 WHERE partID='" + pID + "'";

        try {
            stmtUpdate = conn.createStatement();
            stmtUpdate.executeUpdate(createString);
            stmtUpdate.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void rsUpdatePrntCount(String pID) {
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "UPDATE dbo.participantsTBL "
                + "SET print_count=print_count+1 WHERE partID=" + pID;

        try {
            stmtUpdate = conn.createStatement();
            stmtUpdate.executeUpdate(createString);
            stmtUpdate.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void populateTBL() {

        Connection conn = DBConn.getConnection();
        String createString;
        createString = "SELECT partID, part_lname, "
                + "part_fname, part_mname, part_ext, "
                + "address,  CONVERT(char(10), date_encoded, 101),"
                + "CONVERT(char(10), date_printed, 101)"
                + "FROM participantsTBL WHERE batchID=" + BatchID + "AND part_stat=0 ORDER BY part_lname,part_fname";

        try {
            stmtAttendance = conn.createStatement();
            ResultSet rs = stmtAttendance.executeQuery(createString);

            model = (DefaultTableModel) jTblAttendance.getModel();

            cellAlignCenterRenderer.setHorizontalAlignment(0);

            jTblAttendance.setRowHeight(22);
            jTblAttendance.getColumnModel().getColumn(0).setCellRenderer(cellAlignCenterRenderer);
            jTblAttendance.getColumn("Capture").setCellRenderer(new ButtonRenderer11(1));
            jTblAttendance.getColumn("Capture").setCellEditor(new ButtonEditor11(new JCheckBox(), 1));
            jTblAttendance.getColumn("Signature").setCellRenderer(new ButtonRenderer11(2));
            jTblAttendance.getColumn("Signature").setCellEditor(new ButtonEditor11(new JCheckBox(), 2));

            jTblAttendance.setColumnSelectionAllowed(false);

            model.setNumRows(0);

            int rc = 0;

            while (rs.next()) {
                String prnflg = null;

                if (rs.getString(8) == null) {
                    prnflg = "Not Yet Printed";
                } else {
                    prnflg = rs.getString(8);
                }

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5), rs.getString(6), rs.getString(7), prnflg, "Capture", "Signature"});
                rc++;
            }

            if (rc == 0) {
                cmdPrint.setEnabled(false);
                cmdRePrint.setEnabled(false);
            } else {
                cmdPrint.setEnabled(true);
                cmdRePrint.setEnabled(true);
            }

            stmtAttendance.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    void capture() {
        int col = 0; //set column value to 0
        int row = jTblAttendance.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (jTblAttendance.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please a record from the list!");
            return;
        } else {
            String memID = jTblAttendance.getValueAt(row, col).toString();
            String name = jTblAttendance.getValueAt(row, 1).toString() + ", " + jTblAttendance.getValueAt(row, 2).toString() + " " + jTblAttendance.getValueAt(row, 3).toString();
            CurrentPhoto1.name = name;
            CurrentPhoto1.memID = memID;
            showFrmCPhoto();
        }
    }

    void pirma() {
        int col = 0; //set column value to 0
        int row = jTblAttendance.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (jTblAttendance.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please a record from the list!");
            return;
        } else {
            String memID = jTblAttendance.getValueAt(row, col).toString();
            String name = jTblAttendance.getValueAt(row, 1).toString() + ", " + jTblAttendance.getValueAt(row, 2).toString() + " " + jTblAttendance.getValueAt(row, 3).toString();
            CurrentSignature.name = name;
            CurrentSignature.memID = memID;
            showFrmSignaturePad();
        }
    }

    void showFrmCPhoto() {
        frmcphoto = new CurrentPhoto1(this, true);
        frmcphoto.setVisible(true);
    }

    void showFrmSignaturePad() {
        frmCSignature = new CurrentSignature(this, true);
        frmCSignature.setVisible(true);
    }

    class ButtonEditor11 extends DefaultCellEditor {

        protected JButton button;
        private String label;
        private boolean isPushed;
        int flg;

        public ButtonEditor11(JCheckBox checkBox, int x) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            flg = x;
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
            // Icon ico1 = new javax.swing.ImageIcon(getClass().getResource("/images/innn.png"));
            // button.setIcon(ico1);
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                if (flg == 1) {
                    capture();
                } else if (flg == 2) {
                    pirma();
                }
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

    class ButtonRenderer11 extends JButton implements TableCellRenderer {

        int iflg;

        public ButtonRenderer11(int ico) {
            setOpaque(true);
            iflg = ico;
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
//            Icon ico1 = new javax.swing.ImageIcon(getClass().getResource("/images/summary.png"));
//            Icon ico2 = new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"));
//
//            if (iflg == 1) {
//                setIcon(ico1);
//            } else if (iflg == 2) {
//                setIcon(ico2);
//            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    ////////////
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblAttendance = new javax.swing.JTable();
        cmdAdd = new javax.swing.JButton();
        cmdUpdate = new javax.swing.JButton();
        cmdDelete = new javax.swing.JButton();
        cmdExit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        cmdCloseSchedule = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cmdPrint = new javax.swing.JButton();
        cmdCancelPart = new javax.swing.JButton();
        cmdRePrint = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtBatchNo = new javax.swing.JTextField();
        cmdRefresh = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("PMES Participant Attendance");
        setAutoscrolls(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosed(evt);
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

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Membership/Images/attendance.png"))); // NOI18N
        jToolBar1.add(jLabel4);

        jLabel7.setForeground(new java.awt.Color(255, 102, 0));
        jLabel7.setText(" Note: Here  you can encode the PMES attendance and print the participants certificates");
        jToolBar1.add(jLabel7);

        jTblAttendance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID No.", "Last Name", "First Name", "Middle Name", "Ext", "Address", "Encoded", "Printed", "Capture", "Signature"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblAttendance.setToolTipText("List of All Participant Present in this Scheduled Seminar");
        jTblAttendance.getTableHeader().setReorderingAllowed(false);
        jTblAttendance.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jTblAttendanceMouseMoved(evt);
            }
        });
        jScrollPane1.setViewportView(jTblAttendance);
        if (jTblAttendance.getColumnModel().getColumnCount() > 0) {
            jTblAttendance.getColumnModel().getColumn(0).setResizable(false);
        }
        //set column width
        jTblAttendance.getColumnModel().getColumn(0).setMaxWidth(50);
        jTblAttendance.getColumnModel().getColumn(4).setMaxWidth(50);
        jTblAttendance.getColumnModel().getColumn(6).setMaxWidth(100);

        cmdAdd.setText("Add");
        cmdAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });

        cmdUpdate.setText("Edit");
        cmdUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUpdateActionPerformed(evt);
            }
        });

        cmdDelete.setText("Remove");
        cmdDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteActionPerformed(evt);
            }
        });

        cmdExit.setText("Exit");
        cmdExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExitActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cmdCloseSchedule.setText("Close Schedule");
        cmdCloseSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCloseScheduleActionPerformed(evt);
            }
        });

        jLabel1.setText("Reminders: If a schedule is closed it will be no longer updatable. Please also check if all the cert. are printed. ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmdCloseSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdCloseSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        cmdPrint.setText("Print Certificates");
        cmdPrint.setToolTipText("Print Participants Certificates");
        cmdPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdPrintActionPerformed(evt);
            }
        });

        cmdCancelPart.setText("Cancel Participant");
        cmdCancelPart.setToolTipText("Cancel Participant Attendance");
        cmdCancelPart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelPartActionPerformed(evt);
            }
        });

        cmdRePrint.setText("Re-Print Certificate");
        cmdRePrint.setToolTipText("Re-Print Participants Certificates");
        cmdRePrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRePrintActionPerformed(evt);
            }
        });

        jLabel2.setText("Batch No.:");

        txtBatchNo.setBackground(new java.awt.Color(204, 204, 204));
        txtBatchNo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtBatchNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0)));
        txtBatchNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBatchNoActionPerformed(evt);
            }
        });

        cmdRefresh.setText("Refresh");
        cmdRefresh.setToolTipText("Cancel Participant Attendance");
        cmdRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmdAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                            .addComponent(cmdUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                            .addComponent(cmdDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                            .addComponent(cmdExit, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                            .addComponent(cmdRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBatchNo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmdPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmdRePrint, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmdCancelPart, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBatchNo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdExit))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdPrint)
                    .addComponent(cmdRePrint)
                    .addComponent(cmdCancelPart))
                .addGap(7, 7, 7)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        this.setTitle(myTitle);
        populateTBL();
        String BNo = Integer.toString(BatchID);
        txtBatchNo.setText(BNo);

    }//GEN-LAST:event_formInternalFrameOpened

    private void cmdExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdExitActionPerformed

    private void jTblAttendanceMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblAttendanceMouseMoved
    }//GEN-LAST:event_jTblAttendanceMouseMoved

    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
        showFrmAdd();
    }//GEN-LAST:event_cmdAddActionPerformed

    private void formInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosed
        //statflg = 1;
    }//GEN-LAST:event_formInternalFrameClosed

    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteActionPerformed
        int col = 0; //set column value to 0
        int row = jTblAttendance.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (jTblAttendance.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please a record from the list!");
            return;
        } else {
            int col2 = 7; //set column value to 0
            String prn = jTblAttendance.getValueAt(row, col2).toString();
            if (prn != "Not Yet Printed") {
                JOptionPane.showMessageDialog(this, "Record cannot be removed! Certificate is already printed!");
                return;
            } else {
                String Pid = jTblAttendance.getValueAt(row, col).toString();
                int i = myFunctions.msgboxYesNo("Are you sure you want delete this current record?");
                if (i == 0) {
                    myDataenvi.rsDeleteParticipant(Pid);
                    populateTBL();
                    JOptionPane.showMessageDialog(this, "Record has been successfully deleted!");
                } else if (i == 1) {
                    return; //do nothing
                } else if (i == 2) {
                    this.dispose(); //exit window
                    return;
                }
            }
        }

    }//GEN-LAST:event_cmdDeleteActionPerformed

    private void cmdUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUpdateActionPerformed

        int col = 0; //set column value to 0
        int row = jTblAttendance.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (jTblAttendance.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please a record from the list!");
            return;
        } else {
            String id = jTblAttendance.getValueAt(row, col).toString();
            UpdateParticipant.partID = id;
            UpdateParticipant.lname = jTblAttendance.getValueAt(row, 1).toString();
            UpdateParticipant.fname = jTblAttendance.getValueAt(row, 2).toString();
            UpdateParticipant.mname = jTblAttendance.getValueAt(row, 3).toString();
            if (jTblAttendance.getValueAt(row, 4) == null) {
                UpdateParticipant.ext = "";
            } else {
                UpdateParticipant.ext = jTblAttendance.getValueAt(row, 4).toString();
            }
            UpdateParticipant.paddress = jTblAttendance.getValueAt(row, 5).toString();
            showFrmUpdate();
        }
    }//GEN-LAST:event_cmdUpdateActionPerformed

    private void cmdCloseScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseScheduleActionPerformed
        int i = myFunctions.msgboxYesNo("Are you sure you want to close this schedule?" + (char) 10
                + "Note: If you close this schedule it will never be updatable. Do you want proceed?");
        if (i == 0) {
            rsClosedSched(BatchID);
            this.dispose();
            JOptionPane.showMessageDialog(this, "Schedule is now closed!. Contact your administrator if their are any" + (char) 10
                    + "important changes in this schedule!");
        } else if (i == 1) {
            return; //do nothing
        } else if (i == 2) {
            this.dispose(); //exit window
            return;
        }
    }//GEN-LAST:event_cmdCloseScheduleActionPerformed

    private void cmdPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdPrintActionPerformed
        int i = myFunctions.msgboxYesNo("System will now generate the print preview. PROCEED PRINTING?" + (char) 10
                + "Note: If you select YES you will not be able print the certificates, unless you go to Re-print Certificates.");
        if (i == 0) {
            myReports.rptCertificate(BatchID);
            // rsUpdatePartRec(BatchID);
            populateTBL();
        } else if (i == 1) {
            return; //do nothing
        } else if (i == 2) {
            this.dispose(); //exit window
            return;
        }

    }//GEN-LAST:event_cmdPrintActionPerformed

    private void cmdCancelPartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelPartActionPerformed
        int col = 0; //set column value to 0
        int row = jTblAttendance.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (jTblAttendance.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please a record from the list!");
            return;
        } else {
            String prn = jTblAttendance.getValueAt(row, col).toString();
            int i = myFunctions.msgboxYesNo("Are you sure you want to cancel this participant record?");
            if (i == 0) {
                rsUpdatePartStat(prn);
                populateTBL();
                JOptionPane.showMessageDialog(this, "Record sucessfully canceled!");
            } else if (i == 1) {
                return; //do nothing
            } else if (i == 2) {
                this.dispose(); //exit window
                return;
            }
        }
    }//GEN-LAST:event_cmdCancelPartActionPerformed

    private void cmdRePrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRePrintActionPerformed

        int col = 0; //set column value to 0
        int row = jTblAttendance.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (jTblAttendance.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No record selected! Please a record from the list!");
            return;
        } else {
            int col2 = 7; //set column value to 0
            String prn = jTblAttendance.getValueAt(row, col2).toString();
            if (prn == "Not Yet Printed") {
                JOptionPane.showMessageDialog(this, "Cannot be re-printed! This Record is not yet been printed, go to print Certificates");
                return;
            } else {
                int i = myFunctions.msgboxYesNo("System will now generate the print preview. PROCEED PRINTING?");
                if (i == 0) {
                    String id = jTblAttendance.getValueAt(row, col).toString();
                    rsUpdatePrntCount(id);
                    myReports.rptCertReprint1b1(id);
                } else if (i == 1) {
                    return; //do nothing
                } else if (i == 2) {
                    this.dispose(); //exit window
                    return;
                }

            }
        }
    }//GEN-LAST:event_cmdRePrintActionPerformed

    private void txtBatchNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBatchNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBatchNoActionPerformed

    private void cmdRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRefreshActionPerformed
        populateTBL();
    }//GEN-LAST:event_cmdRefreshActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAdd;
    private javax.swing.JButton cmdCancelPart;
    private javax.swing.JButton cmdCloseSchedule;
    private javax.swing.JButton cmdDelete;
    private javax.swing.JButton cmdExit;
    private javax.swing.JButton cmdPrint;
    private javax.swing.JButton cmdRePrint;
    private javax.swing.JButton cmdRefresh;
    private javax.swing.JButton cmdUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTblAttendance;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField txtBatchNo;
    // End of variables declaration//GEN-END:variables
}
