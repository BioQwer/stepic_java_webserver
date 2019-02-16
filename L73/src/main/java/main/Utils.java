package main;

public class Utils {

    public final static int CURRENT_PORT = 5050;
    public final static int TRY_CONNECT_COUNT = 10;

    static boolean checkForExit(String next) {
        if (next.equalsIgnoreCase("close")) return true;
        if (next.equalsIgnoreCase("exit")) return true;
        return false;
    }
}
