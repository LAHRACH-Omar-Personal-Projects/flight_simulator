package app.views.env.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class PhoneInputsHandler implements Runnable {
    public static StringProperty movement = new SimpleStringProperty();
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8082);

            String order;
            while (true) {
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                order = in.readLine();
                movement.setValue(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
