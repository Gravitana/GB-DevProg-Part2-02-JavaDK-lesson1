package ru.gravitana.tic_tac_toe;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JFrame {
    private static final int WINDOW_WIDTH = 230;
    private static final int WINDOW_HEIGHT = 350;

    private int size = 3;
    private int sizeWin = 3;
    private int choice = 0;

    JButton btnStart;

    SettingsWindow(GameWindow gameWindow) {
        btnStart = new JButton("Start new game");

        setLocationRelativeTo(gameWindow);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                gameWindow.startNewGame(choice, size, size, sizeWin);
            }
        });

        JPanel modePanel = createModeComponent();
        JPanel sizePanel = createSizeComponent();
        JPanel winLengthPanel = createWinLengthComponent();

        JPanel menu = new JPanel(new GridLayout(3, 1));
        menu.add(modePanel);
        menu.add(sizePanel);
        menu.add(winLengthPanel);

        add(menu);
        add(btnStart, BorderLayout.SOUTH);
    }


    private JPanel createModeComponent(){
        JPanel modePanel = new JPanel(new GridLayout(3, 1));

        JLabel message = new JLabel("Выберите режим игры:");

        JRadioButton choice1 = new JRadioButton("Человек против человека");
        JRadioButton choice2 = new JRadioButton("Человек против AI");
        choice1.setSelected(true);

        choice1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice = 0;
            }
        });

        choice2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice = 1;
            }
        });

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(choice1);
        buttonGroup.add(choice2);

        modePanel.add(message);
        modePanel.add(choice1);
        modePanel.add(choice2);

        return modePanel;
    }

    private JPanel createSizeComponent(){
        JPanel modePanel = new JPanel(new GridLayout(3, 1));

        JLabel message1 = new JLabel("Выберите размеры поля:");
        JLabel message2 = new JLabel("Установленный размер поля: " + size);

        JSlider sizer = new JSlider(3, 10, size);

        sizer.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                size = sizer.getValue();
                message2.setText("Установленный размер поля: " + size);
            }
        });

        modePanel.add(message1);
        modePanel.add(message2);
        modePanel.add(sizer);

        return modePanel;
    }

    private JPanel createWinLengthComponent(){
        JPanel modePanel = new JPanel(new GridLayout(3, 1));

        JLabel message1 = new JLabel("Выберите длину для победы:");
        JLabel message2 = new JLabel("Установленная длина: " + sizeWin);

        JSlider win = new JSlider(3, 10, sizeWin);

        win.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(win.getValue() > size){
                    win.setValue(size);
                }
                sizeWin = win.getValue();
                message2.setText("Установленная длина: " + sizeWin);
            }
        });

        modePanel.add(message1);
        modePanel.add(message2);
        modePanel.add(win);

        return modePanel;
    }
}
