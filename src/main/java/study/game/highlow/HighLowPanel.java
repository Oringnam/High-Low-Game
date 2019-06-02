package study.game.highlow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author oringnam
 * @since 02/06/2019
 * blog : http://box0830.tistory.com/
 */
public class HighLowPanel extends JPanel {
    private JPanel leftPanel, rightPanel;
    private JLabel lblTitle, lblMark, lblHint;
    private JButton btnRandom, btnInput;
    private JTextField txtInput;
    private int nRandom, nInput;
    private JLabel lblRange, lblCount;
    private int nMin, nMax, nCount;


    private GameListener gameL;

    public HighLowPanel() {
        setPreferredSize(new Dimension(430, 320));
        setBackground(Color.white);
        setLayout(null);

        gameL = new GameListener();

        leftPanel = new JPanel();
        leftPanel.setBounds(10, 10, 200, 300);
        leftPanel.setBackground(Color.cyan);
        leftPanel.setLayout(null);
        add(leftPanel);

        rightPanel = new JPanel();
        rightPanel.setBounds(220, 10, 200, 300);
        rightPanel.setBackground(Color.pink);
        rightPanel.setLayout(null);
        add(rightPanel);

        lblTitle = new JLabel("HIGH-LOW GAME");
        lblTitle.setBounds(10, 30, 180, 50);
        lblTitle.setFont(new Font("Verdana", Font.BOLD, 18));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setVerticalAlignment(SwingConstants.CENTER);
        leftPanel.add(lblTitle);

        lblMark = new JLabel("?");
        lblMark.setBounds(10, 70, 180, 180);
        lblMark.setFont(new Font("Verdana", Font.BOLD, 100));
        lblMark.setHorizontalAlignment(SwingConstants.CENTER);
        lblMark.setVerticalAlignment(SwingConstants.CENTER);
        lblMark.setVisible(false);
        leftPanel.add(lblMark);

        lblHint = new JLabel("RIGHT");
        lblHint.setBounds(10, 240, 180, 40);
        lblHint.setFont(new Font("Verdana", Font.PLAIN, 20));
        lblHint.setHorizontalAlignment(SwingConstants.CENTER);
        lblHint.setVerticalAlignment(SwingConstants.CENTER);
        lblHint.setVisible(false);
        leftPanel.add(lblHint);

        Font fntButton = new Font("Verdana", Font.PLAIN, 12);

        btnRandom = new JButton("Generates");
        btnRandom.setBounds(20, 50, 160, 30);
        btnRandom.setFont(fntButton);

        btnRandom.addActionListener(gameL);
        rightPanel.add(btnRandom);

        txtInput = new JTextField();
        txtInput.setBounds(20, 85, 75, 30);

        txtInput.addActionListener(gameL);
        txtInput.setEnabled(false);
        rightPanel.add(txtInput);

        btnInput = new JButton("INPUT");
        btnInput.setBounds(100, 85, 80, 30);
        btnInput.setFont(fntButton);

        btnInput.addActionListener(gameL);
        btnInput.setEnabled(false);
        rightPanel.add(btnInput);

        nRandom = nInput = 0;

        nMin = 1;
        nMax = 100;

        Font fntLabel = new Font("Cambria", Font.PLAIN, 16);
        lblRange = new JLabel("[ " + nMin + " ~ " + nMax + " ]");
        lblRange.setFont(fntLabel);
        lblRange.setBounds(30, 120, 160, 30);
        lblRange.setForeground(Color.darkGray);
        rightPanel.add(lblRange);

        nCount = 0;
        lblCount = new JLabel("COUNT : " + nCount);
        lblCount.setFont(fntLabel);
        lblCount.setBounds(30, 155, 160, 30);
        lblCount.setForeground(Color.darkGray);
        rightPanel.add(lblCount);

    }

    public void continueGame() {
        int result = JOptionPane.showConfirmDialog(null, "CONTINUE?");

        if (result == JOptionPane.YES_OPTION) {
            nRandom = nInput = 0;
            nMin = 1;
            nMax = 100;
            nCount = 0;
            lblRange.setText("[ " + nMin + " ~ " + nMax + " ]");
            lblCount.setText("COUNT : " + nCount);
            lblMark.setVisible(false);
            lblHint.setVisible(false);
            txtInput.setEnabled(false);
            btnInput.setEnabled(false);
            btnRandom.setEnabled(true);
        }
    }

    private class GameListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            boolean flag = false;
            Object obj = event.getSource();

            if (obj == btnRandom) {
                nRandom = (int) (Math.random() * 100) + 1;
                lblMark.setText("?");
                lblMark.setVisible(true);

                txtInput.setEnabled(true);
                btnInput.setEnabled(true);
                btnRandom.setEnabled(false);
            } else if (obj == txtInput || obj == btnInput) {
                nInput = Integer.parseInt(txtInput.getText());
                txtInput.setText("");

                if (nRandom < nInput) {
                    lblHint.setText("HIGH");
                    nMax = nInput - 1;
                } else if (nRandom > nInput) {
                    lblHint.setText("LOW");
                    nMin = nInput + 1;
                } else {
                    lblHint.setText("RIGHT");
                    lblMark.setText("" + nRandom);
                    flag = true;
                }

                lblHint.setVisible(true);
                lblRange.setText("[ " + nMin + " ~ " + nMax + " ]");

                nCount++;
                lblCount.setText("COUNT : " + nCount);

                if (flag) {
                    continueGame();
                }
            }
        }

    }

}