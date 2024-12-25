package pl.feen1e;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * Stwórz interfejs z obszarem rysowania (np. JPanel). Dodaj obsługę zdarzeń myszy tak, aby:
 *  Po najechaniu kursorem na obszar rysowania, wyświetlano informację w konsoli.
 *  Po opuszczeniu obszaru rysowania kursorem wyświetlano inną informację w konsoli.
 *  Po naciśnięciu i zwolnieniu przycisku myszy wyświetlano komunikat w konsoli.
 *  Po użyciu kółka myszy zmieniano rozmiar wybranej figury na obszarze rysowania.
 */
public class Exercise5 extends JFrame
{
    public Exercise5()
    {
        setTitle("Zadanie 5");
        setSize(800, 600);
        setLocationRelativeTo(null);
        DrawingPanel drawingPanel = new DrawingPanel();
        add(drawingPanel);
        drawingPanel.setFocusable(true);
        drawingPanel.requestFocusInWindow();
        drawingPanel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                System.out.println("Najechano na obszar rysowania");
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                System.out.println("Opuszczono obszar rysowania");
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                System.out.println("Przycisk myszy został wciśnięty");
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                System.out.println("Przycisk myszy został puszczony");
            }
        });

        drawingPanel.addMouseWheelListener(e -> {
            int r = e.getWheelRotation();
            if (r < 0)
            {
                drawingPanel.resizeFigure(10);
            }
            else
            {
                drawingPanel.resizeFigure(-10);
            }
        });

        setVisible(true);
    }

    protected static class DrawingPanel extends JPanel
    {
        private int x = 392;
        private int y = 275;
        private int size = 50;

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(x - size / 2, y - size / 2, size, size);
        }

        protected void resizeFigure(int delta)
        {
            size = Math.max(10, size + delta);
            repaint();
        }
    }
}
