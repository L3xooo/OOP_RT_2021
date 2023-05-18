package sk.stuba.fei.uim.oop.tree;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

@Getter
@Setter
public class Tree implements Shape{
    private Color color;
    private int x;
    private int y;
    private int width;
    private int height;

    public Tree(int x,int y,int height,int width,Color color) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }
    public void drawTree(Graphics g){
        g.setColor(this.color);
        g.fillOval(x,y,width,height/3*2);
        g.fillRect(x+(width/3),y-10+(height/3)*2,width/3,height/3+10);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public Rectangle2D getBounds2D() {
        return null;
    }

    @Override
    public boolean contains(double x, double y) {
        System.out.println(x);
        System.out.println(y);
        System.out.println(this.getX()+width);
        System.out.println(this.getY()+height);
        if ((this.getX() < x && x < this.getX()+this.getWidth()) && (this.getY() < y && y < this.getY()+this.getHeight())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Point2D p) {
        return false;
    }

    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return false;
    }

    @Override
    public boolean intersects(Rectangle2D r) {
        return false;
    }

    @Override
    public boolean contains(double x, double y, double w, double h) {
        return false;
    }

    @Override
    public boolean contains(Rectangle2D r) {
        return false;
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return null;
    }

    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return null;
    }
}
