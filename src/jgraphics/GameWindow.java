package jgraphics;

import java.awt.Color;
import java.awt.event.KeyEvent;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Represents the game window.
 *
 * @author Jakub Zarybnický (xzaryb00)
 * @author Jiří Záleský (xzales12)
 */
public class GameWindow extends javax.swing.JFrame {

    private static int count = 0;
    private static final long serialVersionUID = 1L;
    private final GamePanel[] frames = {null, null, null, null};

    /**
     * Constructs a new game window.
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
     * Autogenerated method that initializes the GUI.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addGame = new javax.swing.JButton();
        closeGame = new javax.swing.JButton();
        help = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Solitaire");
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

    /**
     * Action performed when the 'Add game' button is pressed.
     * 
     * @param evt The event created by the button.
     */
    private void addGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGameActionPerformed
        if (count != 0) {
            this.setSize(1275, 790);
        }
        for (int i = 0; i < 4; i++) {
            if (frames[i] == null) {
                frames[i] = new GamePanel(i, this);
                frames[i].setLocation((i % 2 == 0) ? 5 : 635, (i / 2 > 0) ? 400 : 40);
                frames[i].setVisible(true);

                this.add(frames[i]);
                frames[i].revalidate();

                count++;
                break;
            }
        }
    }//GEN-LAST:event_addGameActionPerformed

    /**
     * Action performed when the 'Close game' button is pressed.
     * 
     * @param evt The event created by the button.
     */
    private void closeGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeGameActionPerformed
        System.exit(0);
    }//GEN-LAST:event_closeGameActionPerformed

    /**
     * Action performed when the 'Help' button is pressed.
     * 
     * @param evt The event created by the button.
     */
    private void helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpActionPerformed
        showMessageDialog(this, "Solitaire\n\n"
                + "Main window buttons:\n"
                + "Add game - Add new game window. (max. 4)\n"
                + "Close games - Close all game windows and exit program.\n\n"
                + "Game window buttons:\n"
                + "New - Stars new game in window.\n"
                + "Open - Loads saved game.\n"
                + "Save - Saves current progress.\n"
                + "Hint - Are you desperate? This is your rescue.\n"
                + "Undo - Rollback of the last moven.\n"
                + "Close - Ends current game.\n");
    }//GEN-LAST:event_helpActionPerformed

    /**
     * Helper method that disposes of a game panel reference.
     * 
     * @param i Indes of the window.
     */
    public void rem(int i) {
        frames[i] = null;
        count--;
        if (count == 1) {
            this.setSize(645, 430);

             for (int n = 0; n < 4; n++) {
                if (frames[n] != null) {
                    frames[0] = frames[n];
                    frames[n] = null;
                    frames[0].changeNumber(0);
                    frames[0].setLocation(5, 40);
                    break;
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addGame;
    private javax.swing.JButton closeGame;
    private javax.swing.JButton help;
    // End of variables declaration//GEN-END:variables
}
