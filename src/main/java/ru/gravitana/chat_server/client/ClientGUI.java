package ru.gravitana.chat_server.client;

import ru.gravitana.chat_server.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClientGUI extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final ServerWindow server;
    private boolean connected;
    private String name;

    JPanel headerPanel;
    JTextArea log;
    JButton btnSend, btnLogin;
    JTextField messageField;
    JTextField tfIPAddress, tfPort, tfLogin;
    JPasswordField password;


    public ClientGUI(ServerWindow serverWindow) {
        this.server = serverWindow;

//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setTitle("Client GUI");
        setResizable(false);
        setAlwaysOnTop(true);
        setLocation(serverWindow.getX() - 500, serverWindow.getY());

        createPanel();

        setVisible(true);
    }

    private void createPanel() {

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createLogArea());
        add(createFooterPanel(), BorderLayout.SOUTH);
    }

    private Component createHeaderPanel() {
        headerPanel = new JPanel(new GridLayout(2, 3));
        tfIPAddress = new JTextField("127.0.0.1");
        tfPort = new JTextField("8189");
        tfLogin = new JTextField("Ivan Ivanovich");
        password = new JPasswordField("123456");
        btnLogin = new JButton("login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServer();
            }
        });

        headerPanel.add(tfIPAddress);
        headerPanel.add(tfPort);
        headerPanel.add(new JPanel());
        headerPanel.add(tfLogin);
        headerPanel.add(password);
        headerPanel.add(btnLogin);

        return headerPanel;    }

    private Component createLogArea() {
        log = new JTextArea();
        log.setEditable(false);
        return new JScrollPane(log);
    }

    private Component createFooterPanel() {
        JPanel panBottom = new JPanel(new BorderLayout());

        btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message();
            }
        });

        messageField = new JTextField();
        messageField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\n'){
                    message();
                }
            }
        });

        panBottom.add(messageField);
        panBottom.add(btnSend, BorderLayout.EAST);

        return panBottom;
    }

    public void message(){
        if (connected){
            String text = messageField.getText();
            if (!text.isEmpty()){
                server.message(name + ": " + text);
                messageField.setText("");
            }
        } else {
            appendLog("Нет подключения к серверу");
        }

    }

    private void appendLog(String text){
        log.append(text + "\n");
    }

    private void connectToServer() {
        if (server.connectUser(this)){
            appendLog("Вы успешно подключились!\n");
            headerPanel.setVisible(false);
            connected = true;
            name = tfLogin.getText();
            String log = server.getLog();
            if (log != null){
                appendLog(log);
            }
        } else {
            appendLog("Подключение не удалось");
        }
    }

    public void disconnectFromServer() {
        if (connected) {
            headerPanel.setVisible(true);
            connected = false;
            server.disconnectUser(this);
            appendLog("Вы были отключены от сервера!");
        }
    }

    public void answer(String text){
        appendLog(text);
    }

}
