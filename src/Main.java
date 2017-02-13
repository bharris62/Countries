import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {

        Mapping mapping = new Mapping();

        try {
            Scanner scanner = loadFile();
            mapping.addToArray(scanner);
            mapping.printMap();

        }catch(FileNotFoundException fnf){
            System.out.println("sorry, your file was not found; Error:  " + fnf);
        }

        String input = mapping.getLetterToPrint();
        try{
            if (input.matches("^[a-z]$")) {
                saveFile(mapping.getCurrentArray(input), input);
            }
        }catch (Exception e){
            System.out.println("No States start with that!");
        }
    }

    private static Scanner loadFile() throws FileNotFoundException {
        File f = new File("countries.txt");
        Scanner scanner = new Scanner(f);
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



