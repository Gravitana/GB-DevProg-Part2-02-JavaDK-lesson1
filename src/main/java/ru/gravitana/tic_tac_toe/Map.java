package ru.gravitana.tic_tac_toe;

import javax.swing.*;
import java.awt.*;

public class Map extends JPanel {
    Map() {
        setBackground(Color.GRAY);
    }

    void startNewGame(int mode, int fSzX, int fSzY, int winLen) {
        System.out.printf("Mode: %d;\nSize: x=%d, y=%d;\nWin Length: %d", mode, fSzX, fSzY, winLen);
    }
}
