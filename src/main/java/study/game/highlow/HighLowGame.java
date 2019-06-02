package study.game.highlow;

import javax.swing.*;

/**
 * @author oringnam
 * @since 02/06/2019
 * blog : http://box0830.tistory.com/
 */
public class HighLowGame {
    public static void main(String[] args) {
        // make a frame
        JFrame frame = new JFrame("HIGH-LOW GAME");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // you can't change the frame size
        frame.setResizable(false);
        HighLowPanel primary = new HighLowPanel();
        frame.getContentPane()
             .add(primary);
        frame.pack();
        frame.setVisible(true);
    }
}
