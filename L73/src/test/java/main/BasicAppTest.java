package main;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BasicAppTest {

    @Test(timeout = 30000)
    public void basicWorkingOneClient() throws IOException, InterruptedException {
        Server server = new Server();

        Thread thread = new Thread(server::start);
        thread.start();
        Thread.sleep(1500);

        Client client = new Client();
        client.connectTo();
        client.startDialog("qweqwe", "2", "3", "Exit", "Bue.");

        server.stop();
    }

    @Test(timeout = 30000)
    public void basicWorking10Client() throws IOException, InterruptedException {
        Server server = new Server();

        Thread thread = new Thread(server::start);
        thread.start();
        Thread.sleep(1500);

        int tasksCount = 20;
        ExecutorService executorService = Executors.newFixedThreadPool(tasksCount);
        Collection<Callable<Object>> tasks = new ArrayList<>(tasksCount);

        for (int i = 0; i < tasksCount; i++) {
            tasks.add(() -> {
                Client client = new Client();
                client.connectTo();
                client.startDialog("qweqwe", "2", "3", "Exit", "Bue.");
                return null;
            });
        }
        executorService.invokeAll(tasks);
        server.stop();
    }
}