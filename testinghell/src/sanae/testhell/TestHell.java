package sanae.testhell;

import processing.core.PApplet;
import sanae.bh.BulletHell;
import sanae.bh.bs.HellApplet;
import sanae.bh.misc.Debug;

import java.awt.*;
import java.io.File;

public class TestHell extends HellApplet {
    BulletHell bh = BulletHell.getBh();

    public TestHell(File selfJar) {
        super(selfJar);
    }

    @Override
    public void settings(){
        size(bh.getWidth(),bh.getHeight());
    }
    @Override
    public void setup(){
        surface.setTitle("you are mom gaye");
        textAlign(CENTER);
    }
    @Override
    public void draw() {
        clear();
        text("This is a test applet",width/2f,height/2f);
    }
}
