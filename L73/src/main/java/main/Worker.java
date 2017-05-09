package main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Worker extends Thread {

	private static final Logger LOGGER = LogManager.getLogger(Worker.class);

	private InputStream in = null;
	private OutputStream out = null;

	public Worker(Socket socket) throws IOException {
		LOGGER.info("{}", socket.getInetAddress());
		in = socket.getInputStream();
		out = socket.getOutputStream();
	}

	@Override
	public void run() {
		try {
			String strings = IOUtils.toString(in);
			if (strings != null) {
				if (strings.equals("Bue.")) {
					in.close();
					out.close();
					return;
				}
				System.out.println(strings);
				out.write(strings.getBytes());
				out.flush();
			}
		} catch (IOException e) {
			LOGGER.error(e);
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				LOGGER.error(e);
			}
		}
		LOGGER.info("ok");
	}
}
