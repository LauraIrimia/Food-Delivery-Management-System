package BusinessLayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LogIn {
    public boolean register(String username, String password) {
        File file = new File("src/main/resources/register.txt");
        int ok = 1;
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] user = data.split(",");
                if (user[0].equals(username)) {
                    ok = 0;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (ok == 1) {
            try {
                FileWriter myWriter = new FileWriter(file, true);
                myWriter.write(username + "," + password + "\n");
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public boolean logIn(String username, String password) {
        File file = new File("src/main/resources/register.txt");
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] user = data.split(",");
                if (user[0].equals(username) && user[1].equals(password)) {
                    return true;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
