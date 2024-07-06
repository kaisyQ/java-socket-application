package com.kaisyq.framework.core.http.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public final class Client implements IClient, AutoCloseable {

    private final BufferedReader in;
    private final PrintWriter out;

    public Client(Socket socket) throws IOException {
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        this.out = new PrintWriter(socket.getOutputStream());
    } 

    public Client(InputStream inputStream, OutputStream outputStream) {
        this.in = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        this.out = new PrintWriter(outputStream);
    }

    @Override
    public void write(String message) {
        

    }

    @Override
    public void submit(short status, Map<String, String> headers) {
    }

    @Override
    public void close() throws Exception {
        this.in.close();
        this.out.close();
    }
}
