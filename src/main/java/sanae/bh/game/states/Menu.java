package sanae.bh.game.states;

import lombok.Getter;
import sanae.bh.BulletHell;
import sanae.bh.game.State;

import javax.swing.*;
import java.awt.*;

public class Menu extends State {
    public Menu(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JLabel l = new JLabel("Sanae's Bullet Hell Engine");
        l.setFont(new Font(l.getFont().getFontName(),Font.PLAIN,24));
        add(l);
        JButton start = new JButton("Select Hell");
        start.addActionListener(e -> BulletHell.getBh().stateManager.setState(new HellSelector()));
        start.setToolTipText("I'm not there yet :P");
        add(start);
        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> SwingUtilities.getWindowAncestor(this).dispose());
        add(exit);
    }
}
