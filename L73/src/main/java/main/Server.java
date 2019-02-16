package main;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	private static final Logger LOGGER = LogManager.getLogger(Server.class);
	private static final Integer PORT = 5050;
	private static final int N_THREADS = 50;
	private static ServerSocket serverSocket;

	private ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);

	public void start() {
		try {
			serverSocket = new ServerSocket(PORT);
			LOGGER.info("Start!");
			LOGGER.info("Server started");
			while (true) {
				Socket socket = serverSocket.accept();
				LOGGER.info("Accept {}", socket);
				executorService.submit(new PingPongWorker(socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop(){
		IOUtils.closeQuietly(serverSocket);
		executorService.shutdown();
	}

	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}

}
