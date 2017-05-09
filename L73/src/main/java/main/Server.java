package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {

	private static final Logger LOGGER = LogManager.getLogger(Server.class);
	private static final Integer PORT = 5050;
	private static final int N_THREADS = 10;
	private static ServerSocket serverSocket;

	public static void main(String[] args) throws IOException {

		LOGGER.info("Start!");

		serverSocket = new ServerSocket(PORT);

		ExecutorService executorService = Executors.newFixedThreadPool(N_THREADS);

		LOGGER.info("Server started");
		System.out.println("Server started");
		while (true) {
			Socket socket = serverSocket.accept();
			LOGGER.info("Accept {}",socket);
			executorService.execute(new Worker(socket));
		}
	}

}
