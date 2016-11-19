package main;

public class Daemon implements Runnable{

    public void run() {
        try {
            System.out.println("Sleep daemon");
            Thread.sleep(1000);
            System.out.println("Awake daemon");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread daemon = new Thread(new Daemon());
        daemon.setDaemon(false);
        daemon.start();

        System.out.println("End Main");
    }
}
