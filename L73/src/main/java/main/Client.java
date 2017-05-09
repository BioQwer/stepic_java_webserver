package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws IOException {

		System.out.println("Welcome to Client side");

		Socket fromserver;

		System.out.println("Connecting to... ");

		fromserver = new Socket("localhost", 5050);
		BufferedReader in = new BufferedReader(new InputStreamReader(fromserver.getInputStream()));
		BufferedReader inu = new BufferedReader(new InputStreamReader(System.in));

		String fuser, fserver;

		while ((fuser = inu.readLine()) != null) {
			try (PrintWriter out = new PrintWriter(fromserver.getOutputStream(), true)) {
				out.write(fuser);
				fserver = in.readLine();
				System.out.println("S::" + fserver);
				if (fuser.equalsIgnoreCase("close")) break;
				if (fuser.equalsIgnoreCase("exit")) break;
			}
		}

		in.close();
		inu.close();
		fromserver.close();
	}
}
