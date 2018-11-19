package sanae.bh.game;

import sanae.bh.game.states.Menu;

import javax.swing.*;
import java.awt.*;

public class StateManager extends JPanel {
    public StateManager(){
        setLayout(new GridBagLayout());
    }
    public void setState(State state){
        removeAll();
        //getGraphics().clearRect(getX(),getY(),getWidth(),getHeight());
        GridBagConstraints stateConstraints = new GridBagConstraints();
        stateConstraints.weightx = stateConstraints.weighty = 1.0;
        stateConstraints.fill = GridBagConstraints.BOTH;
        add(state,stateConstraints);
//        state.setBorder(BorderFactory.createLoweredBevelBorder());
//        setBorder(BorderFactory.createLoweredBevelBorder());

        revalidate();
        repaint();
        System.out.println("Changed States to " + state.getClass().getName());
    }
}
