package pl.feen1e;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/*
 * Stwórz interfejs z obszarem rysowania (np. JPanel) i narysuj kilka prostych figur.
 * Po kliknięciu na figurę, zarejestruj czas od kliknięcia, a po kolejnym kliknięciu przesuń figurę
 * w nowe położenie z uwzględnieniem czasu, jaki upłynął od poprzedniego kliknięcia.
 */
public class Exercise3 extends JFrame
{
    private final DrawingPanel drawingPanel;

    private final List<Shape> shapes;
    private long lastClickTime = -1;
    private Shape selectedShape = null;

    public Exercise3()
    {
        setTitle("Zadanie 3");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        drawingPanel = new DrawingPanel();
        add(drawingPanel);

        shapes = new ArrayList<>();
        shapes.add(new Rectangle(50, 470, 50, 50));
        shapes.add(new Circle(50, 50, 25));

        drawingPanel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                if (lastClickTime == -1)
                {
                    lastClickTime = System.currentTimeMillis();
                    System.out.println("Pierwsze kliknięcie: " + lastClickTime);
                }
                else
                {
                    long currentTime = System.currentTimeMillis();
                    long elapsedTime = currentTime - lastClickTime;
                    lastClickTime = currentTime;

                    System.out.println("Drugie kliknięcie: " + currentTime);
                    System.out.println("Czas od poprzedniego kliknięcia: " + elapsedTime + " ms");

                    if (selectedShape == null)
                    {
                        return;
                    }
                    if (selectedShape.getClass() == Circle.class)
                    {
                        selectedShape.move((int) (elapsedTime / 50), (int) elapsedTime / 100);
                    }
                    else if (selectedShape.getClass() == Rectangle.class)
                    {
                        selectedShape.move((int) (elapsedTime / 50), (int) -(elapsedTime / 100));
                    }
                    drawingPanel.repaint();
                }
            }
        });

        drawingPanel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                for (Shape shape : shapes)
                {
                    if (shape.contains(e.getPoint()))
                    {
                        selectedShape = shape;
                        break;
                    }
                }
            }
        });

        setFocusable(true);
        requestFocusInWindow();
    }

    class DrawingPanel extends JPanel
    {
        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            for (Shape shape : shapes)
            {
                shape.draw(g);
            }
        }
    }

    abstract static class Shape
    {
        int x, y;

        Shape(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        abstract void draw(Graphics g);

        abstract boolean contains(Point p);

        void move(int dx, int dy)
        {
            this.x += dx;
            this.y += dy;
        }

    }

    static class Rectangle extends Shape
    {
        int width, height;

        Rectangle(int x, int y, int width, int height)
        {
            super(x, y);
            this.width = width;
            this.height = height;
        }

        @Override
        void draw(Graphics g)
        {
            g.setColor(Color.RED);
            g.fillRect(x, y, width, height);
        }

        @Override
        boolean contains(Point p)
        {
            return p.x >= x && p.x <= x + width && p.y >= y && p.y <= y + height;
        }
    }

    static class Circle extends Shape
    {
        int radius;

        Circle(int x, int y, int radius)
        {
            super(x, y);
            this.radius = radius;
        }

        @Override
        void draw(Graphics g)
        {
            g.setColor(Color.BLUE);
            g.fillOval(x, y, radius * 2, radius * 2);
        }

        @Override
        boolean contains(Point p)
        {
            double distance = Math.sqrt(Math.pow(p.x - (x + radius), 2) + Math.pow(p.y - (y + radius), 2));
            return distance <= radius;
        }
    }
}
