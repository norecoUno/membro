package Module.Main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class CurrentPhoto1 extends javax.swing.JDialog {

    public static Attendance frmParent;
    public static String name;
    public static Capturer1 frmCapturer;
    public static String memID;

    public CurrentPhoto1(Attendance parent, boolean modal) {
        this.frmParent = parent;
        this.setModal(modal);
        initComponents();
        setLocationRelativeTo(this);
          //JOptionPane.showMessageDialog(null, memID);
        //   setPIC();
    }

    public void showFrmCapturer() {
        frmCapturer = new Capturer1(this, true);
        frmCapturer.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        captured = new javax.swing.JLabel();
        cmdEdit = new javax.swing.JButton();
        cmdExit = new javax.swing.JButton();
        lblname = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Profile Picture");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        captured.setForeground(new java.awt.Color(255, 102, 0));
        captured.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Membership/Images/nophoto.jpg"))); // NOI18N
        captured.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        captured.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                capturedMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                capturedMouseReleased(evt);
            }
        });
        captured.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                capturedMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                capturedMouseMoved(evt);
            }
        });

        cmdEdit.setMnemonic('C');
        cmdEdit.setText("Change");
        cmdEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditActionPerformed(evt);
            }
        });

        cmdExit.setMnemonic('x');
        cmdExit.setText("Exit");
        cmdExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExitActionPerformed(evt);
            }
        });

        lblname.setText("jLabel1");

        jLabel2.setForeground(new java.awt.Color(255, 153, 0));
        jLabel2.setText("Current Profile Picture");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(captured, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdExit)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(captured, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(lblname)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmdEdit)
                            .addComponent(cmdExit))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void capturedMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_capturedMousePressed
   }//GEN-LAST:event_capturedMousePressed

    private void capturedMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_capturedMouseReleased
    }//GEN-LAST:event_capturedMouseReleased

    private void capturedMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_capturedMouseDragged
    }//GEN-LAST:event_capturedMouseDragged

    private void capturedMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_capturedMouseMoved
    }//GEN-LAST:event_capturedMouseMoved

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //   System.out.println(memID);
        lblname.setText(name + "' s");
        try {
            String path = getPicPath();
            // File file = new File("192.168.1.192/img/29754.jpg");
            File file = new File(path + memID + ".jpg");

            BufferedImage myImage = ImageIO.read(file);
            captured.setIcon(new ImageIcon(myImage));
            captured.revalidate();
        } catch (IOException ex) {

        }
    }//GEN-LAST:event_formWindowOpened

    public static String getPicPath() {
        String Str = mod.others.paths.getPicPathConfig();
        return Str;
    }

    private void cmdExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdExitActionPerformed

    private void cmdEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditActionPerformed
        Capturer1.memID = memID;
        this.dispose();
        showFrmCapturer();
    }//GEN-LAST:event_cmdEditActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                CurrentPhoto1 dialog = new CurrentPhoto1(frmParent, true);
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
    private javax.swing.JLabel captured;
    private javax.swing.JButton cmdEdit;
    private javax.swing.JButton cmdExit;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblname;
    // End of variables declaration//GEN-END:variables
}
