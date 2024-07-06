package com.kaisyq.framework.core.http.core.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.kaisyq.framework.core.http.core.client.Client;
import com.kaisyq.framework.core.http.core.exceptions.HttpServerException;

public final class HttpServer implements IHttpServer {

    private static final short MAX_THREADS = 50;
    private static IHttpServer instance;

        
    private final ExecutorService executorService;
    private final int port;

    private HttpServer() {
        this.executorService = Executors.newFixedThreadPool(MAX_THREADS);
        this.port = 8080;
    }

    private HttpServer(int port) {
        this.executorService = Executors.newFixedThreadPool(MAX_THREADS);
        this.port = port;
    }
    
    public static IHttpServer build(int port) throws HttpServerException {

        if (instance != null) {
            throw new HttpServerException("Server is already running");
        }
        
        return instance = new HttpServer(port);
    }

    private void extracted(Socket socket) {
        try (Client client = new Client(socket)) 
        {
            client.write("A message from my backend");

        } catch (Exception e) {
            System.err.println("Error processing client request: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try(var httpServer = new ServerSocket(port)) {
            System.out.println("#####Server#was#successfuly#created!######");

            while (true) {
                
                Socket socket = httpServer.accept();
        
                Runnable handler = () -> {
                    try {
                        extracted(socket);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                };
                // @TODO сделать отмену выполнения задачи если время выполнения слишком большое
                this.executorService.submit(handler);
            }
           
        } catch (IOException ex) {
            System.err.println("Cannot start server... exception(message) = " + ex.getMessage());
        }
    }

}
