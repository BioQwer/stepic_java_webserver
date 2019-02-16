package main;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

class PingPongWorker implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(PingPongWorker.class);
    private final Socket socket;

    public PingPongWorker(Socket socket) {
        this.socket = socket;
        LOGGER.info("The connection is established.");
        try {
            LOGGER.info(String.format("LocalPort = %dInetAddress.HostAddress = %sReceiveBufferSize (SO_RCVBUF) = %d",
                    socket.getLocalPort(),
                    socket.getInetAddress().getHostAddress(),
                    socket.getReceiveBufferSize()));
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new
                InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            String input;

            LOGGER.info("Wait for messages");

            while ((input = in.readLine()) != null) {
                if (input.equalsIgnoreCase("exit")) break;
                out.println(input);
                LOGGER.info("Get from client = " + input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(socket);
        }
        LOGGER.info("Client is gone");
    }
}
