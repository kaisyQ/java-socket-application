package com.kaisyq.framework.core.http.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.kaisyq.framework.core.http.server.exceptions.ServerRunningException;

public class Server {

    private static final short MAX_THREADS = 50;

    private final ExecutorService executorService;

    private Server instance;

    public Server() {
        this.executorService = Executors.newFixedThreadPool(MAX_THREADS);
    }
    
    public void build() throws ServerRunningException {

        if (instance != null) {
            throw new ServerRunningException();
        }

        try(var httpServer = new ServerSocket(8080)) {
            System.out.println("___Server was successfuly created!___");

            while (true) {
                
                Socket socket = httpServer.accept();
                
                System.out.println("Client connected!");

        
                Runnable handler = () -> {
                    try {
                        extracted(socket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                };
                // @TODO сделать отмену выполнения задачи если время выполнения слишком большое
                this.executorService.submit(handler);
            }
           
        } catch (IOException ex) {
            System.err.println("Cannot start server... Error" + ex.getMessage());
        }
    }
    private void extracted(Socket socket) throws IOException {
        try (
                BufferedReader input = new BufferedReader(
                    new InputStreamReader(
                        socket.getInputStream(), 
                        StandardCharsets.UTF_8)
                );
                PrintWriter output = new PrintWriter(socket.getOutputStream());
            ) 
        {
            String line;

            StringBuilder res = new StringBuilder();

            while ((line = input.readLine()) != null) {
                res.append("<p>" + line + "</p>"); 
            }


            System.out.println(res.toString());

            output.println("HTTP/1.1 200 OK");
            output.println("Content-Type: text/html; charset=utf-8");
            output.println();

            output.println(res.toString());

            output.flush();
 
        } catch (IOException e) {
            System.err.println("Error processing client request: " + e.getMessage());
        }
    
        System.out.println("Client disconnected!");
    }

}
