
import Module.Main.DBConn;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class main {
static Statement stmt;
    public static void main(String[] args) throws IllegalAccessException {
//        int x = getMaxAcctNo();
//        System.out.println(x);
//        
//        int y=  getValidAcctNo(x);
//        System.out.println(y);
//        


        try {
            try {
                try {
//                    UIManager.put("nimbusBase", new Color(191, 98, 14));
//                    UIManager.put("nimbusBlueGrey", new Color(242, 242, 189));
//                    UIManager.put("control", new Color(242, 242, 189));
                    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.getStackTrace();
                }


                JFrame frame = (JFrame) Class.forName("Module.User.Login").newInstance();
                frame.setVisible(true);
                
            } catch (InstantiationException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (java.lang.ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    

//    static int getMaxAcctNo() {
//        int maxAN = 0;
//        Connection conn = DBConn.getConnection();
//        String createString;
//        createString = "SELECT COALESCE(MAX(AcctNo), 0) AS AcctNo FROM Consumer";
//
//        try {
//            stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(createString);
//
//            while (rs.next()) {
//                maxAN = rs.getInt(1);
//            }
//
//            stmt.close();
//            conn.close();
//
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, "Error RSQuery/004: Query Select All Areas!");
//        }
//
//        return maxAN;
//    }
//
//    
//    
//    static int getValidAcctNo(int maxAcctNo) {
//        boolean found = false;
//        int getacctno = 0;
//
//        int acctno = maxAcctNo + 1;
//        String reverse = new StringBuffer(String.valueOf(acctno)).reverse().toString(); //058136
//        System.out.println("Last Acct No." + acctno);
//        System.out.println(reverse);
//
//
//
//        while (found != true) {
//            int i = 0;
//            int digitTotal = 0;
//
//            char[] cArray = reverse.toCharArray();
//            while (i < String.valueOf(reverse).length()) {
//                char c = cArray[i];
//                String v = String.valueOf(c);
//                digitTotal += Integer.parseInt(v) * (i + 1);
//                i++;
//            }
//            if ((digitTotal % 11) == 0) {
//                found = true;
//
//                String valid = new StringBuffer(reverse).reverse().toString();
//                getacctno = Integer.parseInt(valid);
//            } else {
//                int num = Integer.parseInt(new StringBuffer(reverse).reverse().toString());
//                num++;
//                reverse = new StringBuffer(String.valueOf(num)).reverse().toString();
//            }
//        }
//
//        return getacctno;
//
//    }
//    
}
