package ru.netology;

import javax.imageio.IIOException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Application application = new Application();
            application.run();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}