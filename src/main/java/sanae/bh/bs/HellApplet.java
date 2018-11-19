package sanae.bh.bs;

import lombok.Getter;
import processing.core.PApplet;

import java.awt.*;
import java.io.File;
import java.util.jar.JarFile;

public class HellApplet extends PApplet {
    BulletSystem bs = new BulletSystem();
    @Getter private File selfJar;
    public HellApplet(File selfJar){
        this.selfJar = selfJar;
    }

    @Override
    public void setup() {
        getSurface().setVisible(false);
    }
}
