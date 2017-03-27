/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jgraphics;

import java.awt.Color;
import java.awt.event.KeyEvent;

/**
 *
 * @author User
 */
public class GameWindow extends javax.swing.JFrame {

    private static int count = 0;
    private static final long serialVersionUID = 1L;
    private boolean first = true;
    private final javax.swing.JInternalFrame[] frames = {null, null, null, null};

    /**
     * Creates new form Board
     */
    public GameWindow() {
        initComponents();
        super.setVisible(true);
        super.getContentPane().setBackground(Color.BLACK);

        // Main form keybinding setup
        addGame.setMnemonic(KeyEvent.VK_A);
        closeGame.setMnemonic(KeyEvent.VK_C);
        help.setMnemonic(KeyEvent.VK_H);
        addGameActionPerformed(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addGame = new javax.swing.JButton();
        closeGame = new javax.swing.JButton();
        help = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Solitaire");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(java.awt.Color.black);
        setName("frame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(645, 430));
        setResizable(false);

        addGame.setText("Add Game");
        addGame.setMaximumSize(new java.awt.Dimension(96, 25));
        addGame.setMinimumSize(new java.awt.Dimension(96, 25));
        addGame.setPreferredSize(new java.awt.Dimension(96, 25));
        addGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGameActionPerformed(evt);
            }
        });

        closeGame.setText("Close games");
        closeGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeGameActionPerformed(evt);
            }
        });

        help.setText("Help");
        help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(closeGame)
                .addGap(18, 18, 18)
                .addComponent(help, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(397, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeGame)
                    .addComponent(help))
                .addContainerGap(407, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGameActionPerformed
        if (!first) {
            this.setSize(1275, 790);
        }
        for (int i = 0; i < 4; i++) {
            if (frames[i] == null) {
                frames[i] = new GamePanel(i, this);
                frames[i].setLocation((i % 2 == 0) ? 5 : 635,
                                       (i / 2 > 0) ? 400 : 40);
                frames[i].setVisible(true);

                this.add(frames[i]);
                frames[i].repaint();
                
                first = false;
                break;
            }
        }
    }//GEN-LAST:event_addGameActionPerformed

    private void closeGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeGameActionPerformed
        System.exit(0);
    }//GEN-LAST:event_closeGameActionPerformed

    private void helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpActionPerformed
        // HELP
        javax.swing.JOptionPane.showMessageDialog(this,
                "Dopln HELP!");
    }//GEN-LAST:event_helpActionPerformed

    public void rem(int i) {
        this.remove(frames[i]);
        frames[i] = null;
        count--;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addGame;
    private javax.swing.JButton closeGame;
    private javax.swing.JButton help;
    // End of variables declaration//GEN-END:variables
}
