package pl.feen1e;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainApp extends JFrame
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(MainApp::new);
    }

    public MainApp()
    {
        FlatLightLaf.setup();
        setTitle("Dominik Kaczmarek 281007 - Swing - Mysz i Klawiatura - Menu Główne");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel menuPanel = new JPanel();
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        menuPanel.setLayout(new BorderLayout(5, 5));
        JLabel title = new JLabel("Wybierz zadanie do uruchomienia: ", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.PLAIN, 18));
        title.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        menuPanel.add(title, BorderLayout.NORTH);
        JPanel mainMenu = new JPanel();
        mainMenu.setLayout(new GridLayout(2, 3, 20, 20));

        JButton ex1Button = new JButton("Zadanie 1");
        ex1Button.addActionListener((ActionEvent e) -> {
            new Exercise1().setVisible(true);
        });
        mainMenu.add(ex1Button);

        JButton ex2Button = new JButton("Zadanie 2");
        ex2Button.addActionListener((ActionEvent e) -> {
            new Exercise2().setVisible(true);
        });
        mainMenu.add(ex2Button);

        JButton ex3Button = new JButton("Zadanie 3");
        ex3Button.addActionListener((ActionEvent e) -> {
            new Exercise3().setVisible(true);
        });
        mainMenu.add(ex3Button);

        JButton ex4Button = new JButton("Zadanie 4");
        ex4Button.addActionListener((ActionEvent e) -> {
            new Exercise4().setVisible(true);
        });
        mainMenu.add(ex4Button);

        JButton ex5Button = new JButton("Zadanie 5");
        ex5Button.addActionListener((ActionEvent e) -> {
            new Exercise5().setVisible(true);
        });
        mainMenu.add(ex5Button);

        JButton ex6Button = new JButton("Zadanie 6");
        ex6Button.addActionListener((ActionEvent e) -> {
            new Exercise6().setVisible(true);
        });
        mainMenu.add(ex6Button);

        menuPanel.add(mainMenu, BorderLayout.CENTER);
        add(menuPanel);
        setVisible(true);
    }
}