package pl.feen1e;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/*
 * Opracuj interfejs z obszarem rysowania (np. JPanel). Dodaj obsługę zdarzeń klawiatury tak, aby:
 *  Po naciśnięciu klawisza, zmieniał się kształt lub kolor figury na obszarze rysowania.
 *  Po zwolnieniu klawisza powracał do poprzedniego kształtu lub koloru.
 *  Po wykorzystaniu klawiszy specjalnych, np. Shift lub Ctrl, zmieniały się inne właściwości wybranej figury
 *      (np. przejście z kształtu prostokąta na owal).
 */
public class Exercise6 extends JFrame
{

    public Exercise6()
    {
        setTitle("Zadanie 6");
        setSize(800, 600);
        setLocationRelativeTo(null);
        DrawingPanel drawingPanel = new DrawingPanel();
        add(drawingPanel);
        drawingPanel.setFocusable(true);
        drawingPanel.requestFocusInWindow();

        ArrayList<Integer> colorKeyEvents = new ArrayList<>();
        colorKeyEvents.add(KeyEvent.VK_R);
        colorKeyEvents.add(KeyEvent.VK_B);
        colorKeyEvents.add(KeyEvent.VK_G);

        drawingPanel.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_R -> drawingPanel.color = Color.RED;
                    case KeyEvent.VK_B -> drawingPanel.color = Color.BLUE;
                    case KeyEvent.VK_G -> drawingPanel.color = Color.GREEN;
                    case KeyEvent.VK_O -> drawingPanel.selectedShape = "oval";
                    case KeyEvent.VK_CONTROL -> drawingPanel.size = 150;
                    case KeyEvent.VK_SHIFT -> drawingPanel.size = 10;
                    case KeyEvent.VK_UP -> drawingPanel.y = 50;
                    case KeyEvent.VK_DOWN -> drawingPanel.y = 520;
                    case KeyEvent.VK_LEFT -> drawingPanel.x = 50;
                    case KeyEvent.VK_RIGHT -> drawingPanel.x = 740;
                }
                drawingPanel.repaint();
            }

            @Override
            public void keyReleased(KeyEvent e)
            {

                if (colorKeyEvents.contains(e.getKeyCode()))
                {
                    drawingPanel.color = Color.BLACK;
                }
                else if (e.getKeyCode() == KeyEvent.VK_O)
                {
                    drawingPanel.selectedShape = "rectangle";
                }
                else if (e.getKeyCode() == KeyEvent.VK_CONTROL || e.getKeyCode() == KeyEvent.VK_SHIFT)
                {
                    drawingPanel.size = 50;
                }
                else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    drawingPanel.y = 275;
                }
                else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    drawingPanel.x = 392;
                }
                drawingPanel.repaint();
            }
        });

        setVisible(true);
    }

    protected static class DrawingPanel extends JPanel
    {
        protected String selectedShape = "rectangle";
        private int x = 392;
        private int y = 275;
        protected int size = 50;
        protected Color color = Color.BLACK;

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.setColor(color);
            if (selectedShape.equals("rectangle"))
            {
                g.fillRect(x - size / 2, y - size / 2, size, size);
            }
            else if (selectedShape.equals("oval"))
            {
                g.fillOval(x - size / 2, y - size / 2, size, size);
            }
        }
    }
}