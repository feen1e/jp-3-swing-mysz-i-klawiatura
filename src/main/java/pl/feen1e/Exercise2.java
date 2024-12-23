package pl.feen1e;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

/*
 * Opracuj interfejs z obszarem rysowania (np. JPanel).
 * Pozwól użytkownikowi rysować na obszarze poprzez przesuwanie myszy.
 * Dodaj obsługę zdarzeń myszy, aby można było zaznaczać obszar i przesuwać go w dowolnym kierunku za pomocą myszy.
 */
public class Exercise2 extends JFrame
{
    public Exercise2()
    {
        setTitle("Zadanie 2");
        setSize(800, 600);
        setLocationRelativeTo(null);
        DrawingPanel drawingPanel = new DrawingPanel();
        add(drawingPanel);
        setVisible(true);
    }

    static class DrawingPanel extends JPanel
    {
        private BufferedImage canvas;
        private Graphics2D g2d;
        private int prevX, prevY; // Poprzednie współrzędne myszy
        private boolean drawing = false;
        private Point dragStart = null; // Punkt początkowy przeciągania
        private int offsetX = 0, offsetY = 0; // Przesunięcie rysunku

        public DrawingPanel()
        {
            setPreferredSize(new Dimension(800, 600));
            setBackground(Color.WHITE);

            canvas = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
            g2d = canvas.createGraphics();
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.setBackground(Color.WHITE);
            g2d.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

            // Obsługa myszy
            addMouseListener(new MouseAdapter()
            {
                @Override
                public void mousePressed(MouseEvent e)
                {
                    if (SwingUtilities.isRightMouseButton(e))
                    {
                        // Rozpoczęcie przeciągania całego rysunku
                        dragStart = e.getPoint();
                    }
                    else if (SwingUtilities.isLeftMouseButton(e))
                    {
                        // Rozpoczęcie rysowania
                        drawing = true;
                        prevX = e.getX() - offsetX;
                        prevY = e.getY() - offsetY;
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e)
                {
                    drawing = false;
                    dragStart = null;
                }
            });

            addMouseMotionListener(new MouseMotionAdapter()
            {
                @Override
                public void mouseDragged(MouseEvent e)
                {
                    if (dragStart != null)
                    {
                        // Przesuwanie rysunku
                        int dx = e.getX() - dragStart.x;
                        int dy = e.getY() - dragStart.y;
                        offsetX += dx;
                        offsetY += dy;
                        dragStart = e.getPoint();
                        repaint();
                    }
                    else if (drawing)
                    {
                        // Rysowanie
                        int x = e.getX() - offsetX;
                        int y = e.getY() - offsetY;

                        // Rozszerzenie "płótna" jeśli jest za małe
                        if (x >= canvas.getWidth() || y >= canvas.getHeight() || x < 0 || y < 0)
                        {
                            int newWidth = Math.max(canvas.getWidth(), x + 100);
                            int newHeight = Math.max(canvas.getHeight(), y + 100);
                            resizeCanvas(newWidth, newHeight);
                        }

                        g2d.drawLine(prevX, prevY, x, y);
                        prevX = x;
                        prevY = y;
                        repaint();
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawImage(canvas, offsetX, offsetY, null);
        }

        private void resizeCanvas(int newWidth, int newHeight)
        {
            BufferedImage newCanvas = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = newCanvas.createGraphics();
            g2.setBackground(Color.WHITE);
            g2.clearRect(0, 0, newWidth, newHeight);
            g2.drawImage(canvas, 0, 0, null);
            g2.dispose();
            canvas = newCanvas;
            g2d = canvas.createGraphics();
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
        }
    }
}
