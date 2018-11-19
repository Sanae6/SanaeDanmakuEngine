package sanae.bh.game;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

public abstract class State extends JPanel {

    /// Sets to 3D
    @Getter boolean usesSeperateBG;

    public void shouldUseSeperateBackground(boolean uses3dbg) {
        this.usesSeperateBG = uses3dbg;
        if (uses3dbg){

        }
    }
}
