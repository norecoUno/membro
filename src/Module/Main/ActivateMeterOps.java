package Module.Main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ActivateMeterOps extends javax.swing.JDialog {

    public static ActivateMeter frmParent;
    public static String an;
    static Statement stmt;
    static String nowDate = myFunctions.getDate();
    static String nowDate2 = myFunctions.getDate2();
    public static MDI frmmdi;

    public ActivateMeterOps(ActivateMeter parent, boolean modal) {
        this.frmParent = parent;
        this.setModal(modal);
        initComponents();
        setLocationRelativeTo(this);

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //CONSUMER METER LOG
    public void ConsumerMeterLog(String acctno, String installdate) {
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "INSERT INTO ConsumerMeterLog(AcctNo, MeterSN, TotalMultiplier, CrewID, InstallDate, Remarks, UserID, TransDate) "
                + "SELECT AcctNo, MeterSN, Multiplier, CurIssuedTo,'" + installdate + "', 'NEW CONNECTION' ," + MDI.UserID + ",'" + nowDate2 + "' "
                + "FROM meterPostTBL WHERE AcctNo='" + acctno + "'";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //METER STATUS LOG
    public void MeterStatusLog(String acctno) {
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "INSERT INTO MeterStatusLog(MeterSN, AcctNo, ChangeDate, MSCode, PresRdng, PresDemand, Remarks, UserID, TransDate) "
                + "SELECT MeterSN, AcctNo,'" + nowDate2 + "', 4, InitReading, DemandReading, 'NEW CONNECTION' ," + MDI.UserID + ",'" + nowDate2 + "' "
                + "FROM meterPostTBL WHERE AcctNo='" + acctno + "'";

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //METER LOG
    public static void MeterLog(String acctno, String rdngdate) {
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "INSERT INTO MeterLog(MeterSN, CoopMeterSN, Brandcode, Phase, TypeCode, Wire, Voltage, AmpereCode, OwnerCode, DemandType,"
                + "EnergyDigits ,DemandDigits, MSCode, Multiplier,PresRdng, PresDemand, RdngDate , Remarks, UserID, TransDate) "
                + "SELECT MeterSN, CoopSN, BrandID, Phase, TypeCode, Wire, Voltage, AmpereCode, OwnerCode, DemandType, "
                + "EnergyDigits ,DemandDigits, 4 ,Multiplier, InitReading, DemandReading, '" + rdngdate + "' , 'Meter Issued to ' + c.CrewName ," + MDI.UserID + ",'" + nowDate2 + "' "
                + "FROM meterPostTBL mp INNER JOIN Crew c ON mp.CurIssuedTo=c.crewID WHERE AcctNo='" + acctno + "'";

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //METER
    public static void Meter(String acctno, String rdngdate) {
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "INSERT INTO Meter(MeterSN, CoopMeterSN, BrandCode, Phase, TypeCode, Wire, Voltage, AmpereCode, OwnerCode, DemandType, "
                + "EnergyDigits ,DemandDigits, MSCode, Multiplier, PresRdng, PresDemand, RdngDate) "
                + "SELECT MeterSN, CoopSN, BrandID, Phase, TypeCode, Wire, Voltage, AmpereCode, OwnerCode, DemandType, "
                + "EnergyDigits ,DemandDigits, 4 ,Multiplier, InitReading, DemandReading, '" + rdngdate + "' "
                + "FROM meterPostTBL WHERE AcctNo='" + acctno + "'";

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void ConsumerMeter(String acctno, String intalldate) {
        Connection conn = DBConn.getConnection();
        String createString;
        createString = "INSERT INTO ConsumerMeter(AcctNo, MeterSN, TotalMultiplier, CrewID, InstallDate, UserID, TransDate) "
                + "SELECT AcctNo, MeterSN, Multiplier, CurIssuedTo,'" + intalldate + "'," + MDI.UserID + ",'" + nowDate2 + "' "
                + "FROM meterPostTBL WHERE AcctNo='" + acctno + "'";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createString);
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    public void Consumer(String acctno) {
//        // MDI.UserID
//        Connection conn = DBConn.getConnection();
//        String createString;
//        createString = "INSERT INTO Consumer(AcctNo, "
//                + "TownCode, "
//                + "RouteCode, "
//                + "AcctCode, "
//                + "RouteSeqNo, "
//                + "ClassCode, "
//                + "AcctName, "
//                + "AcctAddress, "
//                + "MembershipID, "
//                + "UserID, "
//                + "TransDate) "
//                + "SELECT AcctNo, TownCode, RouteCode, AcctCode, RouteSeqNo, ClassCode, AcctName, AcctAddress, MembershipID, " + MDI.UserID + ",'" + nowDate2 + "' "
//                + "FROM connTBL WHERE AcctNo='" + acctno + "'";
//        try {
//            stmt = conn.createStatement();
//            stmt.executeUpdate(createString);
//            stmt.close();
//            conn.close();
//
//        } catch (SQLException e) {
//            //JOptionPane.showMessageDialog(null, e.getMessage());
//        }
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jLabel1 = new javax.swing.JLabel();
        Ok = new javax.swing.JButton();
        Ok1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Activate Meter");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Date Installed:");

        Ok.setText("Ok");
        Ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OkActionPerformed(evt);
            }
        });

        Ok1.setText("Cancel");
        Ok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ok1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(Ok)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Ok1)
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ok)
                    .addComponent(Ok1))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Ok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ok1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_Ok1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date testDate = null;
        try {
            testDate = sdf.parse(nowDate);
        } catch (ParseException e) {
        }
        //txtdate.setDateFormatString(nowDate);
        //txtdate.setDate(testDate);
    }//GEN-LAST:event_formWindowOpened

    private void OkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OkActionPerformed
     //   String seldate = ((JTextField) txtdate.getDateEditor().getUiComponent()).getText();

        //"IBATO ANG MGA DATA" PROCESS
//        Consumer(an);                         //BATO DATA TO Consumer TABLE FROM connTBL(TEMPORARY TABLE)
      //  ConsumerMeter(an, seldate);           //BATO DATA TO ConsumerMeter TABLE FROM meterPostTBL
      //  Meter(an, seldate);                   //BATO DATA TO Meter TABLE FROM meterPostTBL
      // MeterLog(an, seldate);                //BATO DATA TO MeterLog TABLE FROM meterPostTBL
        MeterStatusLog(an);                   //BATO DATA TO MeterStatusLog TABLE FROM meterPostTBL
       // ConsumerMeterLog(an, seldate);        //BATO DATA TO ConsumerMeterLog TABLE FROM meterPostTBL
        
        JOptionPane.showMessageDialog(null,"Meter successfully activated");
        myDataenvi.rsUpdateConnStat(Integer.parseInt(an), 8);
        frmParent.populateTBL();
        this.dispose();
    }//GEN-LAST:event_OkActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                ActivateMeterOps dialog = new ActivateMeterOps(frmParent, true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Ok;
    private javax.swing.JButton Ok1;
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
