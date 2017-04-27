package jgraphics;

import java.awt.datatransfer.DataFlavor;
import java.awt.event.KeyEvent;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import javax.activation.ActivationDataFlavor;
import javax.activation.DataHandler;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.ListModel;
import javax.swing.TransferHandler;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import jsolitaire.Board;
import jsolitaire.Card;
import jsolitaire.Deck;
import jsolitaire.Hints;
import jsolitaire.Move;
import jsolitaire.StackModel;

/**
 * Represents a single game panel.
 *
 * @author Jakub Zarybnický (xzaryb00)
 * @author Jiří Záleský (xzales12)
 */
public class GamePanel extends javax.swing.JInternalFrame {

    private static final long serialVersionUID = 1L;

    private final int number;
    private final GameWindow parent;
    private Board board;
    private Hints hints;
    private Timer timer;

    /**
     * Constructs a new GamePanel
     *
     * @param number The index of this panel
     * @param parent This panel's parent
     */
    public GamePanel(int number, GameWindow parent) {
        initComponents();

        this.number = number;
        this.parent = parent;

        ((BasicInternalFrameUI) super.getUI()).setNorthPane(null);

        // Keyboard shortcuts
        newButton.setMnemonic(KeyEvent.VK_N);
        openButton.setMnemonic(KeyEvent.VK_O);
        saveButton.setMnemonic(KeyEvent.VK_S);
        closeButton.setMnemonic(KeyEvent.VK_X);
        hintButton.setMnemonic(KeyEvent.VK_Z);
        undoButton.setMnemonic(KeyEvent.VK_Y);
    }

    /**
     * Autogenerated method that initializes the GUI.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newButton = new javax.swing.JButton();
        openButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        stock = new javax.swing.JPanel();
        stockLabel = new javax.swing.JLabel();
        hintButton = new javax.swing.JButton();
        undoButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableau7 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableau6 = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableau1 = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableau2 = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableau5 = new javax.swing.JList<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableau3 = new javax.swing.JList<>();
        jScrollPane7 = new javax.swing.JScrollPane();
        tableau4 = new javax.swing.JList<>();
        jScrollPane8 = new javax.swing.JScrollPane();
        foundation1 = new javax.swing.JList<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        foundation2 = new javax.swing.JList<>();
        jScrollPane10 = new javax.swing.JScrollPane();
        foundation3 = new javax.swing.JList<>();
        jScrollPane11 = new javax.swing.JScrollPane();
        foundation4 = new javax.swing.JList<>();
        jScrollPane12 = new javax.swing.JScrollPane();
        waste = new javax.swing.JList<>();

        setBackground(new java.awt.Color(51, 204, 0));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setMaximumSize(new java.awt.Dimension(600, 350));
        setMinimumSize(new java.awt.Dimension(600, 350));
        setPreferredSize(new java.awt.Dimension(630, 360));

        newButton.setText("New");
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        openButton.setText("Open");
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        stock.setBackground(new java.awt.Color(51, 204, 0));
        stock.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        stock.setAlignmentX(0.0F);
        stock.setMaximumSize(new java.awt.Dimension(76, 101));
        stock.setMinimumSize(new java.awt.Dimension(76, 101));
        stock.setPreferredSize(new java.awt.Dimension(76, 101));
        stock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stockMouseClicked(evt);
            }
        });

        stockLabel.setMaximumSize(new java.awt.Dimension(74, 97));
        stockLabel.setMinimumSize(new java.awt.Dimension(74, 97));
        stockLabel.setPreferredSize(new java.awt.Dimension(74, 97));

        javax.swing.GroupLayout stockLayout = new javax.swing.GroupLayout(stock);
        stock.setLayout(stockLayout);
        stockLayout.setHorizontalGroup(
            stockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(stockLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        stockLayout.setVerticalGroup(
            stockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(stockLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        hintButton.setText("Hint");
        hintButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hintButtonActionPerformed(evt);
            }
        });

        undoButton.setText("Undo");
        undoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoButtonActionPerformed(evt);
            }
        });

        jLabel1.setMinimumSize(new java.awt.Dimension(2, 2));

        jScrollPane1.setForeground(new java.awt.Color(51, 204, 0));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setToolTipText("");
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tableau7.setBackground(new java.awt.Color(51, 204, 0));
        tableau7.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableau7.setDragEnabled(true);
        tableau7.setDropMode(javax.swing.DropMode.INSERT);
        tableau7.setMaximumSize(new java.awt.Dimension(0, 175));
        tableau7.setName("6"); // NOI18N
        tableau7.setPreferredSize(new java.awt.Dimension(73, 175));
        tableau7.setCellRenderer(new CardRenderer());
        tableau7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableauMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableau7);

        jScrollPane2.setForeground(new java.awt.Color(51, 204, 0));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setToolTipText("");
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tableau6.setBackground(new java.awt.Color(51, 204, 0));
        tableau6.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableau6.setDragEnabled(true);
        tableau6.setDropMode(javax.swing.DropMode.INSERT);
        tableau6.setName("5"); // NOI18N
        tableau6.setPreferredSize(new java.awt.Dimension(73, 175));
        tableau6.setCellRenderer(new CardRenderer());
        tableau6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableauMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableau6);

        jScrollPane3.setForeground(new java.awt.Color(51, 204, 0));
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setToolTipText("");
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tableau1.setBackground(new java.awt.Color(51, 204, 0));
        tableau1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableau1.setDragEnabled(true);
        tableau1.setDropMode(javax.swing.DropMode.INSERT);
        tableau1.setName("0"); // NOI18N
        tableau1.setPreferredSize(new java.awt.Dimension(73, 175));
        tableau1.setCellRenderer(new CardRenderer());
        tableau1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableauMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableau1);

        jScrollPane4.setForeground(new java.awt.Color(51, 204, 0));
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setToolTipText("");
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tableau2.setBackground(new java.awt.Color(51, 204, 0));
        tableau2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableau2.setDragEnabled(true);
        tableau2.setDropMode(javax.swing.DropMode.INSERT);
        tableau2.setName("1"); // NOI18N
        tableau2.setPreferredSize(new java.awt.Dimension(73, 175));
        tableau2.setCellRenderer(new CardRenderer());
        tableau2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableauMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tableau2);

        jScrollPane5.setForeground(new java.awt.Color(51, 204, 0));
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setToolTipText("");
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tableau5.setBackground(new java.awt.Color(51, 204, 0));
        tableau5.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableau5.setDragEnabled(true);
        tableau5.setDropMode(javax.swing.DropMode.INSERT);
        tableau5.setName("4"); // NOI18N
        tableau5.setPreferredSize(new java.awt.Dimension(73, 175));
        tableau5.setCellRenderer(new CardRenderer());
        tableau5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableauMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tableau5);

        jScrollPane6.setForeground(new java.awt.Color(51, 204, 0));
        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setToolTipText("");
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tableau3.setBackground(new java.awt.Color(51, 204, 0));
        tableau3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableau3.setDragEnabled(true);
        tableau3.setDropMode(javax.swing.DropMode.INSERT);
        tableau3.setName("2"); // NOI18N
        tableau3.setPreferredSize(new java.awt.Dimension(73, 175));
        tableau3.setCellRenderer(new CardRenderer());
        tableau3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableauMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tableau3);

        jScrollPane7.setForeground(new java.awt.Color(51, 204, 0));
        jScrollPane7.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane7.setToolTipText("");
        jScrollPane7.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tableau4.setBackground(new java.awt.Color(51, 204, 0));
        tableau4.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableau4.setDragEnabled(true);
        tableau4.setDropMode(javax.swing.DropMode.INSERT);
        tableau4.setName("3"); // NOI18N
        tableau4.setPreferredSize(new java.awt.Dimension(73, 175));
        tableau4.setCellRenderer(new CardRenderer());
        tableau4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableauMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tableau4);

        jScrollPane8.setForeground(new java.awt.Color(51, 204, 0));
        jScrollPane8.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane8.setToolTipText("");
        jScrollPane8.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        foundation1.setBackground(new java.awt.Color(51, 204, 0));
        foundation1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        foundation1.setAutoscrolls(false);
        foundation1.setDragEnabled(true);
        foundation1.setDropMode(javax.swing.DropMode.INSERT);
        foundation1.setMaximumSize(new java.awt.Dimension(73, 97));
        foundation1.setMinimumSize(new java.awt.Dimension(73, 97));
        foundation1.setName("7"); // NOI18N
        foundation1.setPreferredSize(new java.awt.Dimension(73, 97));
        foundation1.setVisibleRowCount(1);
        foundation1.setCellRenderer(new SingleCardRenderer());
        jScrollPane8.setViewportView(foundation1);

        jScrollPane9.setForeground(new java.awt.Color(51, 204, 0));
        jScrollPane9.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane9.setToolTipText("");
        jScrollPane9.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        foundation2.setBackground(new java.awt.Color(51, 204, 0));
        foundation2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        foundation2.setAutoscrolls(false);
        foundation2.setDragEnabled(true);
        foundation2.setDropMode(javax.swing.DropMode.INSERT);
        foundation2.setMaximumSize(new java.awt.Dimension(73, 97));
        foundation2.setMinimumSize(new java.awt.Dimension(73, 97));
        foundation2.setName("8"); // NOI18N
        foundation2.setPreferredSize(new java.awt.Dimension(73, 97));
        foundation2.setCellRenderer(new SingleCardRenderer());
        jScrollPane9.setViewportView(foundation2);

        jScrollPane10.setForeground(new java.awt.Color(51, 204, 0));
        jScrollPane10.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane10.setToolTipText("");
        jScrollPane10.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        foundation3.setBackground(new java.awt.Color(51, 204, 0));
        foundation3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        foundation3.setAutoscrolls(false);
        foundation3.setDragEnabled(true);
        foundation3.setDropMode(javax.swing.DropMode.INSERT);
        foundation3.setMaximumSize(new java.awt.Dimension(73, 97));
        foundation3.setMinimumSize(new java.awt.Dimension(73, 97));
        foundation3.setName("9"); // NOI18N
        foundation3.setPreferredSize(new java.awt.Dimension(73, 97));
        foundation3.setCellRenderer(new SingleCardRenderer());
        jScrollPane10.setViewportView(foundation3);

        jScrollPane11.setForeground(new java.awt.Color(51, 204, 0));
        jScrollPane11.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane11.setToolTipText("");
        jScrollPane11.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        foundation4.setBackground(new java.awt.Color(51, 204, 0));
        foundation4.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        foundation4.setAutoscrolls(false);
        foundation4.setDragEnabled(true);
        foundation4.setDropMode(javax.swing.DropMode.INSERT);
        foundation4.setMaximumSize(new java.awt.Dimension(73, 97));
        foundation4.setMinimumSize(new java.awt.Dimension(73, 97));
        foundation4.setName("10"); // NOI18N
        foundation4.setPreferredSize(new java.awt.Dimension(73, 97));
        foundation4.setCellRenderer(new SingleCardRenderer());
        jScrollPane11.setViewportView(foundation4);

        jScrollPane12.setForeground(new java.awt.Color(51, 204, 0));
        jScrollPane12.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane12.setToolTipText("");
        jScrollPane12.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        waste.setBackground(new java.awt.Color(51, 204, 0));
        waste.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        waste.setAutoscrolls(false);
        waste.setDragEnabled(true);
        waste.setDropMode(javax.swing.DropMode.INSERT);
        waste.setName("11"); // NOI18N
        waste.setPreferredSize(new java.awt.Dimension(73, 97));
        waste.setCellRenderer(new SingleCardRenderer());
        waste.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wasteMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(waste);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(80, 80, 80)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(5, 5, 5)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(newButton)
                .addGap(5, 5, 5)
                .addComponent(openButton)
                .addGap(5, 5, 5)
                .addComponent(saveButton)
                .addGap(18, 18, 18)
                .addComponent(hintButton)
                .addGap(5, 5, 5)
                .addComponent(undoButton)
                .addGap(27, 27, 27)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(closeButton))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newButton)
                    .addComponent(openButton)
                    .addComponent(saveButton)
                    .addComponent(hintButton)
                    .addComponent(undoButton)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(stock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1))
                .addGap(3, 3, 3))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Action performed when the close button is pressed.
     *
     * @param evt The event created by the button.
     */
    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        parent.rem(number);
        dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    /**
     * Action performed when the 'Hint' button is pressed.
     *
     * @param evt The event created by the button.
     */
    private void hintButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hintButtonActionPerformed
        Optional<Move> hint = hints.getNextHint();
        hint.ifPresent(x -> {
            StackModel<Card> fromDeck = board.getDeck(x.getFrom());
            StackModel<Card> toDeck = board.getDeck(x.getTo());

            new Thread(() -> {
                try {
                    boolean ghost = false;
                    Card fromCard = fromDeck.getElementAt(fromDeck.getSize() - x.getNumCards() - 1);

                    if (toDeck.getSize() == 0) {
                        toDeck.add(new Card(null, null, true));
                        toDeck.refresh();
                        ghost = true;
                    }

                    Card toCard = toDeck.getElementAt(toDeck.getSize() - 1);

                    fromCard.setGreyedOut(true);
                    fromDeck.refresh();
                    Thread.sleep(600);
                    fromCard.setGreyedOut(false);
                    fromDeck.refresh();
                    toCard.setGreyedOut(true);
                    toDeck.refresh();
                    Thread.sleep(600);
                    toCard.setGreyedOut(false);
                    toDeck.refresh();

                    if (ghost == true) {
                        toDeck.pop();
                    }
                } catch (InterruptedException ex) {
                }
            }).start();
        });
    }//GEN-LAST:event_hintButtonActionPerformed
    /**
     * Action performed when the 'Undo' button is pressed.
     *
     * @param evt The event created by the button.
     */
    private void undoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoButtonActionPerformed
        board.goBack();
    }//GEN-LAST:event_undoButtonActionPerformed

    /**
     * Action performed when the 'New' button is pressed.
     *
     * @param evt The event created by the button.
     */
    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        startGame(new Board());
    }//GEN-LAST:event_newButtonActionPerformed

    /**
     * Action performed when the 'Load' button is pressed.
     *
     * @param evt The event created by the button.
     */
    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        JFileChooser f = new JFileChooser();
        if (f.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
            jsolitaire.Board.deserialize(f.getSelectedFile()).apply(
                    x -> showMessageDialog(parent, x, "Chyba", JOptionPane.ERROR_MESSAGE),
                    x -> startGame(x));
        }
    }//GEN-LAST:event_openButtonActionPerformed

    /**
     * Action performed when the 'Save' button is pressed.
     *
     * @param evt The event created by the button.
     */
    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        JFileChooser f = new JFileChooser();
        if (f.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
            board.serialize(f.getSelectedFile())
                    .ifPresent(x -> showMessageDialog(parent, x, "Chyba", JOptionPane.ERROR_MESSAGE));
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    /**
     * Action performed when the stock deck is clicked.
     *
     * @param evt The event created by the deck.
     */
    private void stockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stockMouseClicked
        if (board.getDeck(Deck.STOCK).getSize() == 0) {
            board.tryToMove(new Move(Deck.WASTE, Deck.STOCK, board.getDeck(Deck.WASTE).getSize() - 1));
        } else {
            board.tryToMove(new Move(Deck.STOCK, Deck.WASTE, 0));
        }
    }//GEN-LAST:event_stockMouseClicked

    /**
     * Action performed when a tableau deck is clicked.
     *
     * @param evt The event created by the deck.
     */
    private void tableauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableauMouseClicked
        if (evt.getClickCount() > 1) {
            onStackClick(Deck.ofTableau(Integer.parseInt(evt.getComponent().getName())));
        }
    }//GEN-LAST:event_tableauMouseClicked

    /**
     * Action performed when the waste deck is clicked.
     *
     * @param evt The event created by the deck.
     */
    private void wasteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wasteMouseClicked
        if (evt.getClickCount() > 1) {
            onStackClick(Deck.WASTE);
        }
    }//GEN-LAST:event_wasteMouseClicked

    /**
     * Action performed when a tableau deck is clicked.
     *
     * @param evt The event created by the deck.
     */
    private void onStackClick(Deck deck) {
        for (int i = 0; i < 4; i++) {
            if (board.tryToMove(new Move(deck, Deck.ofFoundation(i), 0))) {
                break;
            }
        }
    }

    /**
     * Initializes a new game and starts the timer.
     *
     * @param board The board being loaded
     */
    private void startGame(Board board) {
        this.board = board;
        hints.setTarget(board);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                int time = board.getTime() + 1;
                board.setTime(time);
                int min = time / 60;
                int sec = time % 60;
                jLabel1.setText("Time: " + min + ":" + (sec < 10 ? "0" + sec : sec));
            }
        };
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 1000);

        board.setOnWin(() -> {
            showMessageDialog(this, "Gratulujeme k výhře!", "Výhra", JOptionPane.PLAIN_MESSAGE);
        });

        final ListModel<Card> stockModel = board.getDeck(Deck.STOCK);
        Runnable updateStock = () -> {
            if (stockModel.getSize() == 0) {
                stockLabel.setIcon(null);
            } else if (stockModel.getElementAt(stockModel.getSize() - 1).isGreyedOut()) {
                stockLabel.setIcon(new ImageIcon(GrayFilter.createDisabledImage(Card.BACK.getImage())));
            } else {
                stockLabel.setIcon(Card.BACK);
            }
        };
        stockModel.addListDataListener(new SimpleListDataListener(updateStock));
        updateStock.run();

        tableau1.setModel(board.getDeck(Deck.TABLEAU0));
        tableau2.setModel(board.getDeck(Deck.TABLEAU1));
        tableau3.setModel(board.getDeck(Deck.TABLEAU2));
        tableau4.setModel(board.getDeck(Deck.TABLEAU3));
        tableau5.setModel(board.getDeck(Deck.TABLEAU4));
        tableau6.setModel(board.getDeck(Deck.TABLEAU5));
        tableau7.setModel(board.getDeck(Deck.TABLEAU6));
        foundation1.setModel(board.getDeck(Deck.FOUNDATION0));
        foundation2.setModel(board.getDeck(Deck.FOUNDATION1));
        foundation3.setModel(board.getDeck(Deck.FOUNDATION2));
        foundation4.setModel(board.getDeck(Deck.FOUNDATION3));
        waste.setModel(board.getDeck(Deck.WASTE));

        ListItemTransferHandler handler = new ListItemTransferHandler(board);
        tableau1.setTransferHandler(handler);
        tableau2.setTransferHandler(handler);
        tableau3.setTransferHandler(handler);
        tableau4.setTransferHandler(handler);
        tableau5.setTransferHandler(handler);
        tableau6.setTransferHandler(handler);
        tableau7.setTransferHandler(handler);
        foundation1.setTransferHandler(handler);
        foundation2.setTransferHandler(handler);
        foundation3.setTransferHandler(handler);
        foundation4.setTransferHandler(handler);
        waste.setTransferHandler(handler);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JList<Card> foundation1;
    private javax.swing.JList<Card> foundation2;
    private javax.swing.JList<Card> foundation3;
    private javax.swing.JList<Card> foundation4;
    private javax.swing.JButton hintButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton newButton;
    private javax.swing.JButton openButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JPanel stock;
    private javax.swing.JLabel stockLabel;
    private javax.swing.JList<Card> tableau1;
    private javax.swing.JList<Card> tableau2;
    private javax.swing.JList<Card> tableau3;
    private javax.swing.JList<Card> tableau4;
    private javax.swing.JList<Card> tableau5;
    private javax.swing.JList<Card> tableau6;
    private javax.swing.JList<Card> tableau7;
    private javax.swing.JButton undoButton;
    private javax.swing.JList<Card> waste;
    // End of variables declaration//GEN-END:variables
}

/**
 * Card drag and drop handler.
 *
 * @author Jakub Zarybnický (xzaryb00)
 * @author Jiří Záleský (xzales12)
 */
class ListItemTransferHandler extends TransferHandler {

    private static final long serialVersionUID = 1L;
    private final DataFlavor localObjectFlavor;
    private JList<?> source;
    private int[] indices;
    private final Board board;

    protected ListItemTransferHandler(Board board) {
        super();
        localObjectFlavor = new ActivationDataFlavor(Object[].class, DataFlavor.javaJVMLocalObjectMimeType, "Array of items");
        this.board = board;
    }

    @Override
    protected java.awt.datatransfer.Transferable createTransferable(JComponent c) {
        source = (JList<?>) c;
        indices = source.getSelectedIndices();
        Object[] transferedObjects = source.getSelectedValuesList().toArray();
        return new DataHandler(transferedObjects, localObjectFlavor.getMimeType());
    }

    @Override
    public boolean canImport(TransferHandler.TransferSupport info) {
        return info.isDrop() && info.isDataFlavorSupported(localObjectFlavor);
    }

    @Override
    public int getSourceActions(JComponent c) {
        return TransferHandler.MOVE;
    }

    @Override
    public boolean importData(TransferHandler.TransferSupport info) {
        if (!canImport(info) || !(info.getDropLocation() instanceof JList.DropLocation)) {
            return false;
        }

        return board.tryToMove(new Move(
                indexToDeck(Integer.parseInt(source.getName())),
                indexToDeck(Integer.parseInt(info.getComponent().getName())),
                source.getModel().getSize() - indices[0] - 1));
    }

    private Deck indexToDeck(int i) {
        if (i < 7) {
            return Deck.ofTableau(i);
        }
        if (i < 11) {
            return Deck.ofFoundation(i - 7);
        }
        return Deck.WASTE;
    }
}
