package sanae.bh.game.states;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import sanae.bh.bs.HellApplet;
import sanae.bh.game.State;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import static java.awt.GridBagConstraints.BOTH;

public class Stage extends State {
    private File jar;
    private HellApplet applet;
    private URLClassLoader cl;
    public Stage(File jar,String classLocation) {
        this.jar = jar;
        try {
            setLayout(new GridBagLayout());
            cl = new URLClassLoader(new URL[]{jar.toURI().toURL()},getClass().getClassLoader());
            Class loadedClass = cl.loadClass(classLocation);
            System.out.println("Loading " + loadedClass.getName() + " into the panel.");

            applet = (HellApplet) loadedClass.getDeclaredConstructor(File.class).newInstance(jar);
            JFrame frame = (JFrame)getTopLevelAncestor();
            PApplet.runSketch(new String[]{classLocation},applet);
            PSurfaceAWT surface = (PSurfaceAWT) applet.getSurface();
            PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surface.getNative();
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.fill = BOTH;
            add(canvas,gridBagConstraints);
            canvas.getFrame().remove(canvas);
            canvas.setSize(getPreferredSize().width,getPreferredSize().height);
            applet.frame = frame;
            canvas.getFrame().dispose();
            } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(getTopLevelAncestor(),"Could not load class: "+classLocation+"\ndue to a "+e.getClass().getSimpleName()+"\nSee console for more info.","Couldn't load hell",JOptionPane.ERROR_MESSAGE);
        }
    }
}
