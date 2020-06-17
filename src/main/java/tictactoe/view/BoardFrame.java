package tictactoe.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class BoardFrame extends javax.swing.JFrame {
    private final JButton squareButton[][] = new JButton[3][3];
    
    public BoardFrame() {
        initComponents();
        addButtonToBoard();
    }
    
    private void addButtonToBoard() {
        boardPanel.setLayout(new GridLayout(3,3));
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                squareButton[i][j] = new JButton();
                squareButton[i][j].setBackground(Color.CYAN);
                squareButton[i][j].setPreferredSize(new Dimension(97,97));
                boardPanel.add(squareButton[i][j]);
            }
        }
    }
    
    public JButton[][] getSquareButton() {
        return squareButton;
    }
    
    public void updateGameBoard(char[][] gameBoard) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++ )
                squareButton[i][j].setText(String.valueOf(gameBoard[i][j]));
        }
    }
    
    public void setConnectionLabel(String input) {
       connectionLabel.setText(input);
    }
    
    public void setPlayerLabel(String input) {
        playerLabel.setText(input);
    }
    
    public void showCustomDialogBox(String input) {
        JOptionPane.showMessageDialog(null, input);
    }
    
    public void disableClientButton() {
        clientButton.setEnabled(false);
    }
    public void disableServerButton() {
        serverButton.setEnabled(false);
    }
    
    public void addServerButonListener(ActionListener al) {
        serverButton.addActionListener(al);
    }
    
    public void addClientButonListener(ActionListener al) {
        clientButton.addActionListener(al);
    }
    
    public void addResetButonListener(ActionListener al) {
        resetButton.addActionListener(al);
    }
    
    public void addSquareButonListener(ActionListener al) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                squareButton[i][j].addActionListener(al);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        playerLabel = new javax.swing.JLabel();
        boardPanel = new javax.swing.JPanel();
        serverButton = new javax.swing.JButton();
        clientButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        connectionLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Made Akira");

        titleLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        titleLabel.setText("TIC TAC TOE");

        playerLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        playerLabel.setText("WELCOME");

        boardPanel.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout boardPanelLayout = new javax.swing.GroupLayout(boardPanel);
        boardPanel.setLayout(boardPanelLayout);
        boardPanelLayout.setHorizontalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 295, Short.MAX_VALUE)
        );
        boardPanelLayout.setVerticalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        serverButton.setText("SERVER");

        clientButton.setText("CLIENT");

        resetButton.setText("RESET");

        connectionLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(serverButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(boardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addComponent(titleLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(connectionLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(playerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(playerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverButton)
                    .addComponent(clientButton)
                    .addComponent(resetButton))
                .addGap(40, 40, 40)
                .addComponent(connectionLabel)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(BoardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BoardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BoardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BoardFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BoardFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boardPanel;
    private javax.swing.JButton clientButton;
    private javax.swing.JLabel connectionLabel;
    private javax.swing.JLabel playerLabel;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton serverButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
