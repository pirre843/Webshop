package se.iths.pierre.webshop;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ConsoleHandler implements OutInPutHandler {


    @Override
    public String prompt() {

        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);
        OutInPutHandler outInPutHandler = new ConsoleHandler();
        //handler.info("Ange artikelnummer");
        //String articelNummer = handler.prompt();

        try {
            return JOptionPane.showInputDialog(frame, "Ange artikelnummer");
        } finally {
            frame.dispose();
        }
    }

    @Override
    public String menu() {
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);

        try {
            return JOptionPane.showInputDialog(frame, "1, Lägg till Produkter i varukorgen\n" +
                            "2, Visa produkterna i varukorgen\n" +
                            " 3, Visa information om produkter\n" +
                            " 4, Avsluta Webshopen",
                    "Webshop meny",
                    JOptionPane.QUESTION_MESSAGE);


        } finally {
            frame.dispose();
        }
    }

    @Override
    public void info(String message) {
        //System.out.println(message);


        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true);

        try {
            JOptionPane.showMessageDialog(frame, message);

        } finally {
            frame.dispose();

        }
    }
}
