package Module.Main;

import Module.Boot.Load;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MDI extends javax.swing.JFrame {

    public static int UGID, UserID;
    public static Attendance frm;
    public static Load l;
    static Statement stmtSelectSched;
    static Statement stmt;
    static int sflg;

    static DefaultTableCellRenderer cellAlignCenterRenderer = new DefaultTableCellRenderer();
    static DefaultTableCellRenderer cellAlignRightRenderer = new DefaultTableCellRenderer();
    static DefaultTableModel model, model2, model3;
    public static String taxcode;

    public MDI() {
        initComponents();
        txtd.setVisible(false);
        cmdsearch.setVisible(false);
        getRootPane().setDefaultButton(cmdsearch);
        //this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH); //set JFrame maximized
        frmSelectSched.setSize(1000, 500);
    }

    public void go() {
//        populateLst();
        sflg = 0;
    }

    String GetUserName() {
        String UserName = "";

        Connection conn = DBConn.getConnection();
        String createString;
        createString = "SELECT FullName FROM Users WHERE UserID=" + UserID;

        //int rc = 0;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                UserName = rs.getString(1);
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.getStackTrace();
        }

        return UserName;
    }

    void refresh_opt() {

        if (o1.isSelected() == true) {
            sflg = 0;
            txtd.setVisible(false);
            cmdsearch.setVisible(false);
        } else if (o2.isSelected() == true) {
            sflg = 1;
            txtd.setVisible(true);
            cmdsearch.setVisible(true);
        }

    }

//    private void populateLst() {
//        //Populate Combo Area
//        refresh_opt();
//        Connection conn = DBConn.getConnection();
//        String createString;
//        createString = "SELECT batchID, CONVERT(char(10), sched_date, 101), sched_address, sched_venue FROM scheduleTBL WHERE sched_stat=" + sflg + " ORDER BY sched_venue;";
//
//        try {
//            DefaultListModel model = new DefaultListModel();
//            this.lstSched.setModel(model);
//
//            stmtSelectSched = conn.createStatement();
//            ResultSet rs = stmtSelectSched.executeQuery(createString);
//
//            while (rs.next()) {
//                String desc = null;
//                desc = "[" + rs.getString(2) + "]" + "-" + rs.getString(4).trim() + "/" + rs.getString(3);
//
//                model.addElement(new Item(rs.getInt(1), desc));
//
//            }
//
//            stmtSelectSched.close();
//            conn.close();
//
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error RSQuery/004: Query Select All Areas!");
//        }
//    }
    public class Item {

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

    public void populateTBL() {
        refresh_opt();
        Connection conn = DBConn.getConnection();
        String createString = "";

        if (o1.isSelected() == true) {
            createString = "SELECT batchID,sched_date, sched_address, sched_venue FROM scheduleTBL WHERE sched_stat=" + sflg + " ORDER BY sched_date DESC";
        } else if (o2.isSelected() == true) {
            createString = "SELECT batchID,sched_date, sched_address, sched_venue FROM scheduleTBL WHERE sched_stat=" + sflg + " AND CONVERT(varchar,sched_date,20)  LIKE '%" + txtd.getText() + "%' ORDER BY sched_date DESC";
        }
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            model2 = (DefaultTableModel) tbl.getModel();

            cellAlignCenterRenderer.setHorizontalAlignment(0);
            cellAlignRightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

            tbl.setRowHeight(45);

            tbl.getColumnModel().getColumn(0).setCellRenderer(cellAlignCenterRenderer);
//            tbl.getColumn("Open").setCellRenderer(new ButtonRenderer(1));
//            tbl.getColumn("Open").setCellEditor(new ButtonEditor(new JCheckBox(), 1));
            tbl.setColumnSelectionAllowed(false);

            model2.setNumRows(0);
            int cnt = 0;

            while (rs.next()) {
                String img = null;

                img = "/images/build.png";

                DateFormat dateFormat = new SimpleDateFormat("MMMMM dd, yyyy");
                String nwdate = dateFormat.format(rs.getDate(2));

                String lbl = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td><img src=" + getClass().getResource("/images/download.png") + ">&nbsp</td><td>" + nwdate + "</td></th>";
                String lbl2 = "<html><table border=0 cellpadding=0 cellspacing=0>"
                        + "<tr><td rowspan=2><img src=" + getClass().getResource(img) + ">&nbsp</td><td><b>" + rs.getString(4) + "<b></td></tr>"
                        + "<tr><td>" + rs.getString(3) + "</td></tr></table>";
                model2.addRow(new Object[]{rs.getInt(1), lbl, lbl2});
                cnt++;
            }

            stmt.close();
            conn.close();

            //lblcnt.setText(cnt + " Found");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

//    class ButtonEditor extends DefaultCellEditor {
//
//        protected JButton button;
//        private String label;
//        private boolean isPushed;
//        int flg;
//
//        public ButtonEditor(JCheckBox checkBox, int x) {
//            super(checkBox);
//            button = new JButton();
//            button.setOpaque(true);
//            flg = x;
//
//            button.addActionListener(new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    fireEditingStopped();
//                }
//            });
//        }
//
//        public Component getTableCellEditorComponent(JTable table, Object value,
//                boolean isSelected, int row, int column) {
//            if (isSelected) {
//                button.setForeground(table.getSelectionForeground());
//                button.setBackground(table.getSelectionBackground());
//            } else {
//                button.setForeground(table.getForeground());
//                button.setBackground(table.getBackground());
//            }
//
//            Icon ico1 = new javax.swing.ImageIcon(getClass().getResource("/images/innn.png"));
//            button.setIcon(ico1);
//
//            label = (value == null) ? "" : value.toString();
//            button.setText(label);
//            isPushed = true;
//            return button;
//        }
//
//        public Object getCellEditorValue() {
//            if (isPushed) {
//                if (flg == 1) {
//                    int col = 0; //set column value to 0
////                    int row = tbl2.getSelectedRow(); //get value of selected value
////                    String id = tbl2.getValueAt(row, col).toString();
//
//                } else if (flg == 2) {
//                }
//
//            }
//            isPushed = false;
//            return new String(label);
//        }
//
//        public boolean stopCellEditing() {
//            isPushed = false;
//            return super.stopCellEditing();
//        }
//
//        protected void fireEditingStopped() {
//            super.fireEditingStopped();
//        }
//    }
//
//    class ButtonRenderer extends JButton implements TableCellRenderer {
//
//        int iflg;
//
//        public ButtonRenderer(int ico) {
//            setOpaque(true);
//            iflg = ico;
//        }
//
//        public Component getTableCellRendererComponent(JTable table, Object value,
//                boolean isSelected, boolean hasFocus, int row, int column) {
//            if (isSelected) {
//                setForeground(table.getSelectionForeground());
//                setBackground(table.getSelectionBackground());
//            } else {
//                setForeground(table.getForeground());
//                setBackground(UIManager.getColor("Button.background"));
//            }
//
//            Icon ico1 = new javax.swing.ImageIcon(getClass().getResource("/images/editm.png"));
//            Icon ico2 = new javax.swing.ImageIcon(getClass().getResource("/images/ledgermini.png"));
//
//            if (iflg == 1) {
//                setIcon(ico1);
//            } else if (iflg == 2) {
//                setIcon(ico2);
//            }
//
//            setText((value == null) ? "" : value.toString());
//            return this;
//        }
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        myDesktop = new javax.swing.JDesktopPane();
        frmSelectSched = new javax.swing.JInternalFrame();
        jToolBar3 = new javax.swing.JToolBar();
        jLabel2 = new javax.swing.JLabel();
        cmdOpen = new javax.swing.JButton();
        cmdExit2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        o1 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmdsearch = new javax.swing.JButton();
        o2 = new javax.swing.JRadioButton();
        txtd = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lbluser = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MEMBERSHIP SYSTEM");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        frmSelectSched.setIconifiable(true);
        frmSelectSched.setResizable(true);
        frmSelectSched.setTitle("Select PMES Schedule");
        frmSelectSched.setVerifyInputWhenFocusTarget(false);
        frmSelectSched.setVisible(false);
        frmSelectSched.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                frmSelectSchedInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                frmSelectSchedInternalFrameClosed(evt);
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
                frmSelectSchedInternalFrameOpened(evt);
            }
        });

        jToolBar3.setRollover(true);

        jLabel2.setForeground(new java.awt.Color(255, 102, 0));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Membership/Images/manageincomingsched.png"))); // NOI18N
        jLabel2.setText("List of Available PMES Schedule/s");
        jToolBar3.add(jLabel2);

        cmdOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        cmdOpen.setText("Open Attendance Record");
        cmdOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdOpenActionPerformed(evt);
            }
        });

        cmdExit2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        cmdExit2.setText("Exit");
        cmdExit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExit2ActionPerformed(evt);
            }
        });

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BatchID", "Date", "Venue"
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
        jScrollPane2.setViewportView(tbl);
        if (tbl.getColumnModel().getColumnCount() > 0) {
            tbl.getColumnModel().getColumn(0).setResizable(false);
        }
        tbl.getColumnModel().getColumn(2).setPreferredWidth(400);
        tbl.getColumnModel().getColumn(0).setMaxWidth(100);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup1.add(o1);
        o1.setSelected(true);
        o1.setText("Open PMES");
        o1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                o1ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/images/group.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/images/group2.png"))); // NOI18N

        cmdsearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/range.png"))); // NOI18N
        cmdsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdsearchActionPerformed(evt);
            }
        });

        buttonGroup1.add(o2);
        o2.setText("Closed PMES");
        o2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                o2ActionPerformed(evt);
            }
        });

        txtd.setToolTipText("");
        txtd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(o1)
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(o2)
                .addGap(18, 18, 18)
                .addComponent(txtd, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdsearch)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(o1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdsearch, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtd)
                    .addComponent(o2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout frmSelectSchedLayout = new javax.swing.GroupLayout(frmSelectSched.getContentPane());
        frmSelectSched.getContentPane().setLayout(frmSelectSchedLayout);
        frmSelectSchedLayout.setHorizontalGroup(
            frmSelectSchedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar3, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
            .addGroup(frmSelectSchedLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frmSelectSchedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(frmSelectSchedLayout.createSequentialGroup()
                        .addGroup(frmSelectSchedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(frmSelectSchedLayout.createSequentialGroup()
                                .addComponent(cmdOpen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmdExit2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        frmSelectSchedLayout.setVerticalGroup(
            frmSelectSchedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frmSelectSchedLayout.createSequentialGroup()
                .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(frmSelectSchedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdOpen)
                    .addComponent(cmdExit2))
                .addContainerGap())
        );

        myDesktop.add(frmSelectSched);
        frmSelectSched.setBounds(30, 20, 930, 580);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Module/Main/user.png"))); // NOI18N
        jLabel3.setText("Current User:");

        lbluser.setForeground(new java.awt.Color(255, 153, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(myDesktop)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbluser, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 633, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(myDesktop, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbluser, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Load.StandardMenus(this);
        Load.MainMenus(UserID, myDesktop, this, this.frmSelectSched, tbl);
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH); //set JFrame maximizedt

        lbluser.setText(GetUserName());
    }//GEN-LAST:event_formWindowOpened

    private void cmdOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdOpenActionPerformed

        int col = 0; //set column value to 0
        int row = tbl.getSelectedRow(); //get value of selected value

        //trap user incase if no row selected
        if (tbl.isRowSelected(row) != true) {
            JOptionPane.showMessageDialog(this, "No schedule selected!");
            return;
        } else {
            try {
                String id = tbl.getValueAt(row, col).toString();
                Attendance.BatchID = Integer.parseInt(id);
                Attendance.myTitle = "PMES Participant Attendance";

                Attendance form = new Attendance();
                myDesktop.add(form);
                form.setMaximum(true);
                frmSelectSched.setVisible(false);
                form.setVisible(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(MDI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_cmdOpenActionPerformed

    private void cmdExit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExit2ActionPerformed
        frmSelectSched.setVisible(false);
    }//GEN-LAST:event_cmdExit2ActionPerformed

    private void frmSelectSchedInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_frmSelectSchedInternalFrameActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_frmSelectSchedInternalFrameActivated

    private void frmSelectSchedInternalFrameClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_frmSelectSchedInternalFrameClosed
   }//GEN-LAST:event_frmSelectSchedInternalFrameClosed

    private void frmSelectSchedInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_frmSelectSchedInternalFrameOpened
        frmSelectSched.setSize(700, 500);
    }//GEN-LAST:event_frmSelectSchedInternalFrameOpened

    private void o1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_o1ActionPerformed
        refresh_opt();
        //this.populateLst();
        populateTBL();
    }//GEN-LAST:event_o1ActionPerformed

    private void o2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_o2ActionPerformed
        refresh_opt();
        //this.populateLst();
        populateTBL();
    }//GEN-LAST:event_o2ActionPerformed

    private void cmdsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdsearchActionPerformed
        populateTBL();
    }//GEN-LAST:event_cmdsearchActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MDI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cmdExit2;
    private javax.swing.JButton cmdOpen;
    private javax.swing.JButton cmdsearch;
    private javax.swing.JInternalFrame frmSelectSched;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JLabel lbluser;
    private javax.swing.JDesktopPane myDesktop;
    private javax.swing.JRadioButton o1;
    private javax.swing.JRadioButton o2;
    private javax.swing.JTable tbl;
    private javax.swing.JTextField txtd;
    // End of variables declaration//GEN-END:variables
}
