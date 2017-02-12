import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by BHarris on 2/12/17.
 */
public class Mapping {


    public String getLetterToPrint() {
        Scanner reader = new Scanner(System.in);
        System.out.println("What letter of countries do you want to print? ");
        String input = reader.nextLine().toLowerCase();
        return input;
    }


    public  List<Country> getCurrentArray(String letter){
        return Main.worldMap.get(letter);

    }


    public static void addToArray(Scanner scanner) {
        String start = "a";
        List<Country> countries = new ArrayList<>();
        while (scanner.hasNext()) {

            String line = scanner.nextLine();
            String[] columns = line.split("\\|");

            if(columns[1].startsWith(start)){
                Country country = new Country(columns[0], columns[1]);
                countries.add(country);

            }else {
                Main.worldMap.put(start, countries);
                start = columns[1].substring(0,1);
                countries = new ArrayList<>();
                Country country = new Country(columns[0], columns[1]);
                countries.add(country);
            }
        }

        Main.worldMap.put(start, countries);
    }
    // Not needed, used for testing.
    public static void printMap() {
        //System.out.println(worldMap);
        for (String name : Main.worldMap.keySet()) {
            String key = name.toString();
            System.out.printf("%-8s", key + ": ");
            for (Country letter : Main.worldMap.get(name)) {
                System.out.print(letter.name + " |");
            }
            System.out.println();
        }
    }
}
