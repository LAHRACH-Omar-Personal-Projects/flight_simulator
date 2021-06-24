package app.views.env.entities;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AirplaneController implements Runnable {
    public static volatile ListProperty<String> myJoystickOrder = new SimpleListProperty<>();
    public static volatile ListProperty<String> otherJoystickOrder = new SimpleListProperty<>();

    @Override
    public void run() {
           /* ServerSocket serverSocket = new ServerSocket(8081);
            Socket clientSocket = new Socket("192.168.43.253", 8081);

            Socket socket = serverSocket.accept();
            OutputStream outputStream = clientSocket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            myJoystickOrder.addListener((observableValue, strings, t1) -> {
                List<Message> messages = new ArrayList<>();
                for(String s: observableValue.getValue()) {
                    messages.add(new Message(s));
                }
                try {
                    objectOutputStream.writeObject(messages);

                    List<Message> listOfMessages = (List<Message>) objectInputStream.readObject();
                    List<String> orders = new ArrayList<>();
                    for(Message message: listOfMessages) {
                        orders.add(message.getText());
                    }
                    ObservableList<String> observableList = FXCollections.observableList(orders);
                    AirplaneController.otherJoystickOrder.setValue(observableList);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });*/
    }
}
