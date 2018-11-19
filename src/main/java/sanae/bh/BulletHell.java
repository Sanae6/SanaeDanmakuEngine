package sanae.bh;

import lombok.Getter;
import org.kohsuke.args4j.CmdLineParser;
import sanae.bh.game.StateManager;
import sanae.bh.game.states.Menu;
import sanae.bh.game.states.Stage;
import sanae.bh.misc.CommandLineValues;
import sanae.bh.misc.Debug;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

public class BulletHell extends JFrame {
    @Getter private static BulletHell bh;
    private String[] args;
    public StateManager stateManager = new StateManager();
    CommandLineValues vals; // parsed arguments
    @Getter
    private int height = 640;
    @Getter
    private int width = 640;
    public static void main(String[]args){
        bh = new BulletHell();
        bh.args = args;
        try {
            bh.start();
        } catch (IOException e) {
            Debug.log("TopFrame","Could not load engine");
            e.printStackTrace();
        }
    }

    public BulletHell() {
        super("Sanae Bullet Hell");
        setMinimumSize(new Dimension(width,height));
        setMaximumSize(new Dimension(width,height));
        setResizable(false);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().add(stateManager);
        pack();
        setVisible(true);
        //call start right after
    }

    private void start() throws IOException {
        vals = new CommandLineValues(args);
        if (vals.getSource() != null){
            JarFile jarFile = new JarFile(vals.getSource());
            Attributes attribs = jarFile.getManifest().getMainAttributes();
            BulletHell.getBh().stateManager.setState(new Stage(vals.getSource(), attribs.getValue("Hell-Class") == null ? attribs.getValue("Main-Class") : attribs.getValue("Hell-Class")));
        }else {
            stateManager.setState(new Menu()); //maybe setup command line starting
        }
    }
}
