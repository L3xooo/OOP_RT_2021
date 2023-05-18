package sk.stuba.fei.uim.oop.board;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.tree.Tree;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
public class Board extends JPanel {
    ArrayList<Tree> trees;
    Tree actualTree;

    public Board() {
        setBackground(Color.cyan);
        this.trees = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Tree tree : trees) {
            tree.drawTree(g);
        }
    }
}
