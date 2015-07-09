package Module.Boot;

import Module.Main.DBConn;
import Module.Main.MDI;
import Module.User.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Load {

    static JMenuBar mnubr = new JMenuBar();
    static Statement stmt;
    static Statement stmtSelectSched;
    public static MDI m;
    static DefaultTableCellRenderer cellAlignCenterRenderer = new DefaultTableCellRenderer();
    static DefaultTableCellRenderer cellAlignRightRenderer = new DefaultTableCellRenderer();
    static DefaultTableModel model, model2, model3;

    public static void StandardMenus(final JFrame frame) {
        mnubr.removeAll();

        //Load Basic Main Menus
        JMenu mnu = new JMenu();
        JMenuItem iLogOff = new JMenuItem();
        JMenuItem iExit = new JMenuItem();

        mnu.setText("System");
        mnu.setMnemonic('S');

        mnubr.add(mnu);

        iLogOff.setText("Log-Off");
        iExit.setText("Exit");

        iLogOff.setMnemonic('L');
        iExit.setMnemonic('x');

        Icon ico = new javax.swing.ImageIcon("Images/Logoff.png");
        Icon ico2 = new javax.swing.ImageIcon("Images/Exit.png");

        iLogOff.setIcon(ico);
        iExit.setIcon(ico2);

        mnu.add(iLogOff);
        mnu.add(iExit);
        //--Basic Menus End Here

        //Action Listener for basic menus
        iLogOff.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //Execute when Logoff button is pressed
                frame.dispose();
                Login frm = new Login();
                frm.setVisible(true);

            }
        });

        iExit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //Execute when Exit button is pressed
                System.exit(0);
            }
        });

        frame.setJMenuBar(mnubr);
        frame.pack();
    }

    public static void ShowFrame(JFrame frm, JInternalFrame ifrm) {
        ifrm.setSize(1000, 500);
        ifrm.setVisible(true);
    }

    static void populateLst(JList lst) {
        //Populate Combo Area
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "SELECT batchID, CONVERT(char(10), sched_date, 101), sched_address, sched_venue FROM scheduleTBL WHERE sched_stat<>1 ORDER BY sched_venue;";

        try {
            DefaultListModel model = new DefaultListModel();
            lst.setModel(model);

            stmtSelectSched = conn.createStatement();
            ResultSet rs = stmtSelectSched.executeQuery(createString);

            while (rs.next()) {
                String desc = null;
                desc = "[" + rs.getString(2) + "]" + "-" + rs.getString(4).trim() + "/" + rs.getString(3);

                model.addElement(new Item(rs.getInt(1), desc));

            }

            stmtSelectSched.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error RSQuery/004: Query Select All Areas!");
        }
    }

    public static class Item {

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

    public void populateTBL(JTable tbl) {

        Connection conn = DBConn.getConnection();
        String createString;
        createString = "SELECT batchID,sched_date, sched_address, sched_venue FROM scheduleTBL WHERE sched_stat=0 ORDER BY sched_date DESC";

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
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void MainMenus(int UserID, JDesktopPane dsktop, final JFrame frame, final JInternalFrame ifrm, JTable tbls) {
        Connection conn = DBConn.getConnection();
        String createString;
        //QUERY - GROUP ALL MENU HEADER TO BE ABLE TO DISPLAY IT IN MDI FORM
        createString = "SELECT mi.MHID, mh.Caption, mh.Mnemonic "
                + "FROM userGroupsAssignTBL ua,userGroupPrevTBL up, menuItemTBL mi, menuHeaderTBL mh "
                + "WHERE ua.UGID=up.UGID AND up.prevID=mi.prevID AND mi.MHID=mh.MHID AND ua.UserID=" + UserID + " "
                + "GROUP BY mi.MHID, mh.Caption, mh.Mnemonic "
                + "ORDER BY MHID";

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                String shrt = rs.getString(3);
                char monic = shrt.charAt(0);

                JMenu mnu = new JMenu();
                mnu.setText(rs.getString(2));
                mnu.setMnemonic(monic);
                mnubr.add(mnu);

                LoadMenuItems(rs.getInt(1), UserID, mnu, dsktop, frame, ifrm, tbls);
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
        }
    }

    static void LoadMenuItems(int MHID, int UserID, JMenu mnu, final JDesktopPane ppane, final JFrame frm, final JInternalFrame ifrm, final JTable tbls) {
        Connection conn = DBConn.getConnection();
        String createString;
        //QUERY - LOAD MENUITEMS
        createString = "SELECT mi.MIID, mi.ItemCaption, mi.Mnemonic, p.className, p.display, mi.Icon "
                + "FROM userGroupsAssignTBL ua,userGroupPrevTBL up, menuItemTBL mi, menuHeaderTBL mh, privilegesTBL p "
                + "WHERE ua.UGID=up.UGID AND up.prevID=mi.prevID AND mi.MHID=mh.MHID AND p.prevID=up.prevID AND ua.UserID=" + UserID + " AND mi.MHID =" + MHID + " "
                + "GROUP BY mi.MIID, mi.ItemCaption, mi.Mnemonic, p.className, p.display, mi.Icon ORDER BY mi.ItemCaption";

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(createString);

            while (rs.next()) {
                Icon ico = new javax.swing.ImageIcon(rs.getString(6));

                String shrt = rs.getString(3);
                char monic = shrt.charAt(0);

                JMenuItem mnuI = new JMenuItem();

                mnuI.setText(rs.getString(2));
                mnuI.setMnemonic(monic);
                mnuI.setIcon(ico);
                mnu.add(mnuI);

                //Action Listener for basic menus
                final String cls = rs.getString(4);
                final int disp = rs.getInt(5);

                mnuI.addActionListener(new ActionListener() {

                    //PUT ACTION ON MENUITEM CLICK
                    public void actionPerformed(ActionEvent e) {
                        //Execute when Logoff button is pressed

                        if ("SPEC1".equals(cls)) {
                            ShowFrame(frm, ifrm);
                            Load l = new Load();
                            l.populateTBL(tbls);
                        } else {
                            try {
                                try {
                                    JInternalFrame frame = (JInternalFrame) Class.forName(cls).newInstance();
                                    ppane.add(frame);

                                    if (disp == 1) {
                                        try {
                                            frame.setMaximum(true);
                                        } catch (PropertyVetoException ex) {
                                            Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }

                                    frame.setVisible(true);
                                } catch (IllegalAccessException ex) {
                                    Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (InstantiationException ex) {
                                    Logger.getLogger(Load.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } catch (java.lang.ClassNotFoundException se) {
                                JOptionPane.showMessageDialog(null, se.getMessage());
                            }
                        }
                    }
                });

            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
        }
    }
}
