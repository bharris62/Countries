import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {

        Mapping mapping = new Mapping();
        Scanner scanner = returnScanner();
        mapping.addToArray(scanner);
        mapping.printMap();

        String input = mapping.getLetterToPrint();
        saveFile(mapping.getCurrentArray(input), input);


    }

    private static Scanner returnScanner() {
        Scanner scanner = null;
        try {
            File f = new File("countries.txt");
            scanner = new Scanner(f);
            return scanner;

        } catch (FileNotFoundException fnf) {
            System.out.println("sorry, your file was not found; Error:  " + fnf);
        }

        return scanner;


    }


    static void saveFile(List<Country> arry, String letter) throws IOException {

        File f = new File(letter + "_countries.txt");
        FileWriter fw = new FileWriter(f);

        fw.write("The countries that start with " + letter.toUpperCase() + "\n");
        for (Country name : arry){
            fw.write(name.getName() + "\n");
        }

        fw.close();
    }
}



