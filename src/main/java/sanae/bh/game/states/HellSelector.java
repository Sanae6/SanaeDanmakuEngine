package sanae.bh.game.states;

import sanae.bh.BulletHell;
import sanae.bh.game.State;
import sanae.bh.game.objects.Bullet;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.text.Element;
import javax.swing.text.PlainDocument;
import javax.swing.text.TableView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

public class HellSelector extends State {
    Map<String,JarFileData> jars = new HashMap<>();
    public HellSelector() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JLabel l = new JLabel("Looking for Hell~");
        add(l);
        JProgressBar pw = new JProgressBar();
        pw.setIndeterminate(true);
        add(pw);
        DefaultListModel<String> model = new DefaultListModel();
        JList hellList = new JList(model);
        hellList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        hellList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        JScrollPane p = new JScrollPane(hellList);
        p.setPreferredSize(new Dimension(getPreferredSize().width, getPreferredSize().height));
        add(p);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JButton back = new JButton("Go Back");
        JButton start = new JButton("Start Hell");
        panel.add(back);
        panel.add(start);
        panel.setPreferredSize(new Dimension(getPreferredSize().width, Integer.MAX_VALUE));
        add(panel);
        start.addActionListener(al -> {
            if (hellList.getSelectedValue() == null) return;
            JarFileData jarFileData = jars.get(hellList.getSelectedValue());
            try {
                Attributes attribs = jarFileData.jarFile.getManifest().getMainAttributes();
                BulletHell.getBh().stateManager.setState(new Stage(jarFileData.file, attribs.getValue("Hell-Class") == null ? attribs.getValue("Main-Class") : attribs.getValue("Hell-Class")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        back.addActionListener(al->BulletHell.getBh().stateManager.setState(new Menu()));
        Timer temp = new Timer(300, ae ->{
            File file = new File(System.getProperty("user.dir")+File.separator+"hells");
            if (!file.exists()) file.mkdir();
            else if (!file.isDirectory()) {
                remove(pw);
                l.setText("There's a file called hells where it shouldn't be~");
                repaint();
                Timer temp2 = new Timer(5000,(ae2)->{
                    BulletHell.getBh().stateManager.setState(new Menu());
                });
                temp2.setRepeats(false);
                temp2.start();
            }
            for (File file2: file.listFiles()) {
                //check for 1.jar file 2. with manifest that i want
                //if found, add
                if (!file2.isFile()) continue;
                String filename = file2.getName();
                String extension = filename.substring(filename.lastIndexOf(".") + 1);
                if (!extension.equals("jar")) continue;
                JarFile jf;
                try {
                    jf = new JarFile(file2);
                    System.out.println(jf.getManifest().getMainAttributes().keySet());
                    System.out.println(jf.getManifest().getMainAttributes().getValue("Hell-Name"));
                    if (jf.getManifest().getMainAttributes().getValue("Hell-Name") == null)continue;
                    String name = jf.getManifest().getMainAttributes().getValue("Hell-Name");
                    model.addElement(name);
                    jars.put(name,new JarFileData(file2,jf));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            pw.setIndeterminate(false);
            pw.setValue(100);
        });
        temp.setRepeats(false);
        temp.start();
    }
    class JarFileData{
        JarFile jarFile;
        File file;
        JarFileData(File file, JarFile jarFile){
            this.file = file;
            this.jarFile = jarFile;
        }
    }
}
