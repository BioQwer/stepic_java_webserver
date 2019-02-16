package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Iterator;

public class Client implements Closeable {

    private static final Logger LOGGER = LogManager.getLogger(PingPongWorker.class);

    Socket socket;
    private int tryConnectCount = 10;

    public void connectTo() {
        try {
            LOGGER.info("Welcome to Client side");
            LOGGER.info("Connecting to... ");
            socket = new Socket("localhost", Utils.CURRENT_PORT);
        } catch (IOException e) {
            tryConnectCount -= 1;
            if (tryConnectCount > 0) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                connectTo();
            }
        }
    }

    public void startDialog() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader inu = new BufferedReader(new InputStreamReader(System.in))) {
            String fuser, fserver;
            while ((fuser = inu.readLine()) != null) {
                try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                    out.write(fuser);
                    fserver = in.readLine();
                    LOGGER.info("S::" + fserver);
                    if (Utils.checkForExit(fuser)) break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startDialog(String... commands) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            Iterator<String> iterator = Arrays.asList(commands).iterator();

            while (iterator.hasNext()) {
                String next = iterator.next();
                out.println(next);
                LOGGER.info("fromServer::" + in.readLine());
                if (Utils.checkForExit(next)) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        if (socket != null) {
            socket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();

        client.connectTo();
        client.startDialog();
        client.socket.close();
    }
}
