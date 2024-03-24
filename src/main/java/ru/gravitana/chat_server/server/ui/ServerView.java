package ru.gravitana.chat_server.server.ui;

import ru.gravitana.chat_server.server.domain.ServerController;

public interface ServerView {
    void showMessage(String message);
    void setServerController(ServerController serverController);
}