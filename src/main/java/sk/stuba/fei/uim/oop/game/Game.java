package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.controls.GameLogic;

import javax.swing.*;
import java.awt.*;

public class Game {
    public Game(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setResizable(false);

        GameLogic logic = new GameLogic(frame);


        JButton treeButton = new JButton("Tree");
        treeButton.addActionListener(logic);
        JButton moveButton = new JButton("Move");
        moveButton.addActionListener(logic);
        JButton nextColorButton = new JButton("Next Color");
        nextColorButton.addActionListener(logic);

        JPanel gameMenu = new JPanel();
        gameMenu.setBackground(Color.lightGray);
        gameMenu.setLayout(new GridLayout(1,4));
        gameMenu.add(treeButton);
        gameMenu.add(moveButton);
        gameMenu.add(nextColorButton);
        gameMenu.add(logic.getStatusLabel());

        frame.add(gameMenu,BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
