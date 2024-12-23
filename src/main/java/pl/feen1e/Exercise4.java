package pl.feen1e;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 * Opracuj interfejs z obszarem rysowania (np. JPanel).
 * Po naciśnięciu klawisza strzałki w górę, dół, lewo lub prawo przesuń wybraną figurę na obszarze rysowania.
 * Dodaj obsługę zdarzeń klawiatury, aby umożliwić przesuwanie wybranej figury za pomocą strzałek.
 */
public class Exercise4 extends JFrame
{
    public Exercise4()
    {
        setTitle("Zadanie 4");
        setSize(800, 600);
        setLocationRelativeTo(null);
        DrawingPanel drawingPanel = new DrawingPanel();
        add(drawingPanel);

        drawingPanel.setFocusable(true);
        drawingPanel.requestFocusInWindow();
        drawingPanel.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_UP -> drawingPanel.moveFigure(0, -10);
                    case KeyEvent.VK_DOWN -> drawingPanel.moveFigure(0, 10);
                    case KeyEvent.VK_LEFT -> drawingPanel.moveFigure(-10, 0);
                    case KeyEvent.VK_RIGHT -> drawingPanel.moveFigure(10, 0);
                }
            }
        });

        setVisible(true);
    }

    public static class DrawingPanel extends JPanel
    {
        private int x = 375;
        private int y = 250;

        protected void moveFigure(int dx, int dy)
        {
            x += dx;
            y += dy;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(x, y, 50, 50);
        }
    }
}
