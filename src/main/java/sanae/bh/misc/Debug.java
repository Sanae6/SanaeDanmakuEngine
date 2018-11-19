package sanae.bh.misc;

public class Debug {
    public static boolean debugMode;
    public static void log(Object s){
        System.out.println(s);
    }
    public static void log(String namespace, Object s){
        System.out.println("["+namespace+"] " + s);
    }
}
