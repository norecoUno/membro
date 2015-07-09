package Module.Main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class CurrentSignature extends javax.swing.JDialog {

    public static Attendance frmParent;
    public static String name;
    public static String memID;
    public static SignaturePad frmSignaturePad;

    public CurrentSignature(Attendance parent, boolean modal) {
        this.frmParent = parent;
        this.setModal(modal);
        initComponents();
        setLocationRelativeTo(this);
        lblname.setText(name + "' s");
    }

    void showFrmSP() {
        frmSignaturePad = new SignaturePad(this, true);
        frmSignaturePad.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        captured = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmdEdit = new javax.swing.JButton();
        cmdExit = new javax.swing.JButton();
        lblname = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Current Signature");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        captured.setForeground(new java.awt.Color(255, 102, 0));
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

        jLabel2.setForeground(new java.awt.Color(255, 153, 0));
        jLabel2.setText("Current Signature");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(captured, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmdEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdExit)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(lblname)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmdEdit)
                            .addComponent(cmdExit)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(captured, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void cmdEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditActionPerformed
        SignaturePad.memID = memID;
        this.dispose();
        showFrmSP();
    }//GEN-LAST:event_cmdEditActionPerformed

    private void cmdExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_cmdExitActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        lblname.setText(name + "' s");
        try {
            String path = getSigPathConfig();
            // File file = new File("192.168.1.192/img/29754.jpg");
            File file = new File(path + memID + ".png");

            BufferedImage myImage = ImageIO.read(file);
            captured.setIcon(new ImageIcon(myImage));
            captured.revalidate();
        } catch (IOException ex) {

        }
    }//GEN-LAST:event_formWindowOpened

    public static String getSigPathConfig() {
        String Str = mod.others.paths.getSigPathConfig();
        return Str;
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CurrentSignature.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CurrentSignature.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CurrentSignature.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CurrentSignature.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CurrentSignature dialog = new CurrentSignature(frmParent, true);
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
