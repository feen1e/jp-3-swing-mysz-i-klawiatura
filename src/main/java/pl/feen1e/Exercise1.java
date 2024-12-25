package pl.feen1e;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * Stwórz interfejs Swing z obszarem rysowania (np. JPanel) i polem tekstowym.
 * Po kliknięciu myszą w obszar rysowania wyświetl współrzędne kliknięcia w polu tekstowym.
 * Dodaj obsługę zdarzeń klawiatury, tak aby po wciśnięciu klawisza "Enter" tekst w polu tekstowym został wyczyszczony.
 */
public class Exercise1 extends JFrame
{
    public Exercise1()
    {
        setTitle("Zadanie 1");
        setSize(800, 600);
        setLocationRelativeTo(null);

        JLabel description = new JLabel("Kliknij, aby wyświetlić współrzędne kliknięcia. Wciśnij Enter, aby wyczyścić tekst.",
                                        SwingConstants.CENTER);
        JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel drawingPanel = new JPanel();
        drawingPanel.setBackground(Color.WHITE);

        setLayout(new BorderLayout(5, 5));
        add(description, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        add(textField, BorderLayout.SOUTH);

        drawingPanel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                Point point = e.getPoint();
                textField.setText("Kliknięto w punkt (" + point.getX() + ", " + point.getY() + ")");
            }
        });

        textField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    textField.setText("");
                }
            }
        });
    }
}
