package pl.feen1e;

import javax.swing.*;

/*
 * Stwórz interfejs Swing z obszarem rysowania (np. JPanel) i polem tekstowym.
 * Po kliknięciu myszą w obszar rysowania, wyświetl współrzędne kliknięcia w polu tekstowym.
 * Dodaj obsługę zdarzeń klawiatury, tak aby po wciśnięciu klawisza "Enter" tekst w polu tekstowym został wyczyszczony.
 */
public class Exercise1 extends JFrame
{
    public Exercise1()
    {
        JLabel label = new JLabel("zadanie 1");
        add(label);
    }
}
