package ru.gravitana.chat_server;

import ru.gravitana.chat_server.client.domain.ClientController;
import ru.gravitana.chat_server.client.ui.ClientGUI;
import ru.gravitana.chat_server.server.domain.ServerController;
import ru.gravitana.chat_server.server.repository.FileStorage;
import ru.gravitana.chat_server.server.ui.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerController serverController = new ServerController(new ServerWindow(), new FileStorage());

        new ClientController(new ClientGUI(), serverController);
        new ClientController(new ClientGUI(), serverController);
    }
}
