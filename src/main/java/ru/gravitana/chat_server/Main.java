package ru.gravitana.chat_server;

import ru.gravitana.chat_server.client.ClientGUI;
import ru.gravitana.chat_server.server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();

        new ClientGUI(serverWindow);
        new ClientGUI(serverWindow);
    }
}
