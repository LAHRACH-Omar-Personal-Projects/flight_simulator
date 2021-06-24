package ma.ensaj.flightcontroller;

import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton roll_left_btn;
    private FloatingActionButton roll_right_btn;
    private FloatingActionButton pitch_up_btn;
    private FloatingActionButton pitch_down_btn;
    private FloatingActionButton accelerate_btn;
    private FloatingActionButton decelerate_btn;

    private static final String IP_ADDRESS = "192.168.43.189";
    private static final int PORT = 8082;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtons();
        handButtonsPress();
    }

    //INIT BUTTONS VIEWS
    private void initButtons() {
        roll_left_btn = findViewById(R.id.roll_left_btn);
        roll_right_btn = findViewById(R.id.roll_right_btn);
        pitch_up_btn = findViewById(R.id.pitch_up_btn);
        pitch_down_btn = findViewById(R.id.pitch_down_btn);
        accelerate_btn = findViewById(R.id.accelerate_btn);
        decelerate_btn = findViewById(R.id.decelerate_btn);
    }

    //HANDLE BUTTONS PRESS EVENT
    private void handButtonsPress() {
        roll_left_btn.setOnTouchListener((v, event) -> {
            rollLeft();
            return false;
        });
        roll_right_btn.setOnTouchListener((v, event) -> {
            rollRight();
            return false;
        });
        pitch_up_btn.setOnTouchListener((v, event) -> {
            pitchUp();
            return false;
        });
        pitch_down_btn.setOnTouchListener((v, event) -> {
            pitchDown();
            return false;
        });
        accelerate_btn.setOnTouchListener((v, event) -> {
            accelerate();
            return false;
        });
        decelerate_btn.setOnTouchListener((v, event)-> {
            decelerate();
            return false;
        });
    }

    //ROLL
    public void rollLeft() {
        setOrderMessage("ROLL LEFT");
    }
    public void rollRight() {
        setOrderMessage("ROLL RIGHT");
    }

    //PITCH
    public void pitchUp() {
        setOrderMessage("PITCH UP");
    }
    public void pitchDown() {
        setOrderMessage("PITCH DOWN");
    }

    //SPEED
    public void accelerate() {
        setOrderMessage("ACCELERATE");
    }
    public void decelerate() {
        setOrderMessage("DECELERATE");
    }

    //ORDER MESSAGE
    public void setOrderMessage(String orderMessage) {
        Runnable runnable = () -> {
            try {
                Socket socket = new Socket(IP_ADDRESS, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(orderMessage);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}