package sk.stuba.fei.uim.oop.controls;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.board.Board;
import sk.stuba.fei.uim.oop.tree.Tree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

@Getter
@Setter
public class GameLogic extends UniversalAdapter{
    private static final String MOVE_BUTTON_NAME = "Move";
    private static final String TREE_BUTTON_NAME = "Tree";
    private static final String COLOR_BUTTON_NAME = "Next Color";
    private JLabel statusLabel;
    private JFrame frame;
    private Board board;
    private Color actualColor;
    private ArrayList<Color> colors;
    private boolean moveButtonActive;
    private boolean treeButtonActive;

    public GameLogic(JFrame frame){
        this.moveButtonActive = false;
        this.treeButtonActive = false;
        this.frame = frame;
        initBoard();
        this.initColors();
        this.frame.add(board);
        this.statusLabel = new JLabel();
        this.statusLabel.setOpaque(true);
        updateStatusLabel("");
    }

    private void initColors(){
        this.colors = new ArrayList<>();
        this.colors.add(Color.BLUE);
        this.colors.add(Color.BLACK);
        this.colors.add(Color.RED);
        this.setActualColor(colors.get(0));
    }

    private void initBoard(){
        this.board = new Board();
        this.board.addMouseMotionListener(this);
        this.board.addMouseListener(this);
    }

    public void updateStatusLabel(String status){
        this.statusLabel.setText(status);
        this.statusLabel.setForeground(Color.white);
        this.statusLabel.setBackground(this.getActualColor());
        this.board.revalidate();
        this.board.repaint();
    }

    public void updateStatusLabelBackground(){
        this.statusLabel.setBackground(this.getActualColor());
        this.board.revalidate();
        this.board.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (this.isTreeButtonActive()) {
            int actualX = e.getX();
            int actualY = e.getY();
            Tree actualTree = this.getBoard().getActualTree();
            int width = Math.abs(actualTree.getX()-actualX);
            int height = Math.abs(actualTree.getY()-actualY);
            actualTree.setWidth(width);
            actualTree.setHeight(height);
        }
        if (isMoveButtonActive() && this.getBoard().getActualTree()!=null) {
            int delta = -(this.getBoard().getCursorStartX()-e.getX());
            int deltaY = -(this.getBoard().getCursorStartY()-e.getY());
            this.getBoard().getActualTree().setX(this.getBoard().getActualTree().getX()+delta);
            this.getBoard().getActualTree().setY(this.getBoard().getActualTree().getY()+deltaY);
            this.getBoard().setCursorStartX(e.getX());
            this.getBoard().setCursorStartY(e.getY());
        }
        this.getBoard().revalidate();
        this.getBoard().repaint();
    }



    @Override
    public void mousePressed(MouseEvent e) {
        if (isTreeButtonActive()) {
            int startX = e.getX();
            int startY = e.getY();
            Tree actualTree = new Tree(startX,startY,0,0,this.getActualColor());
            this.getBoard().setActualTree(actualTree);
            this.getBoard().revalidate();
            this.getBoard().repaint();
        }
        if (isMoveButtonActive()) {
            for (Tree tree : this.getBoard().getTrees()) {
                if (tree.contains(e.getX(),e.getY())) {
                    this.getBoard().setActualTree(tree);
                    this.getBoard().setCursorStartX(e.getX());
                    this.getBoard().setCursorStartY(e.getY());
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isMoveButtonActive()) {
            this.getBoard().setActualTree(null);
        }
        if (isTreeButtonActive()) {
            Tree actualTree = this.getBoard().getActualTree();
            if (actualTree.getX() > e.getX()) {
                actualTree.setX(e.getX());
            }
            if (actualTree.getY() > e.getY()) {
                actualTree.setY(e.getY());
            }
            this.getBoard().getTrees().add(this.getBoard().getActualTree());
        }
        this.getBoard().revalidate();
        this.getBoard().repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source instanceof JButton) {
            if (((JButton)source).getText().equals(TREE_BUTTON_NAME)) {
                setTreeButtonActive(true);
                setMoveButtonActive(false);
            }
            if (((JButton)source).getText().equals(MOVE_BUTTON_NAME)) {
                setMoveButtonActive(true);
                setTreeButtonActive(false);
            }
            if (((JButton)source).getText().equals(COLOR_BUTTON_NAME)) {
                int actualColorIndex = this.getColors().indexOf(getActualColor());
                if (actualColorIndex+1 < this.getColors().size()) {
                    actualColor = this.getColors().get(actualColorIndex+1);
                } else {
                    actualColor = this.getColors().get(0);
                }
                this.updateStatusLabelBackground();
            }
        }
    }
}
