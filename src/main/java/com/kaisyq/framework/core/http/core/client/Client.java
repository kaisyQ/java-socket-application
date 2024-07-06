package com.kaisyq.framework.core.http.core.client;

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

        /**
         * @TODO добавить проверку на кол-во использований данного метода
         * 
         * Запись в стрим ответа должна по идее отрабатывать только 1 раз
         */

        this.out.println("HTTP/1.1 200 OK");
        this.out.println("Content-Type: text/html; charset=utf-8");
        this.out.println();
        this.out.println(message);

        this.out.flush();
    }

    @Override
    public void submit(short status, Map<String, String> headers) {
        throw new UnsupportedOperationException("Метод не реализован");
    }


    @Override
    public void close() throws Exception {
        this.in.close();
        this.out.close();
    }
}
