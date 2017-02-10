
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    static Map<String, List<Country>> worldMap = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {

        File f = new File("countries.txt");
        Scanner scanner = new Scanner(f);
        addCurrentLetter(scanner);
        printMap();
    }

    public static void addCurrentLetter(Scanner scanner){
        String letters = "abcdefghijklmnopqrstuvwxyz";
        for(int i=0; i < letters.length(); i++) {
            //System.out.println(i);
            addToArray(letters.substring(i,i+1), scanner);
            //System.out.println(letters.charAt(i));
        }
    }

    public static void addToArray(String letter, Scanner scanner){
        List<Country> countries = new ArrayList<>();
        Country temp = null;

        while (scanner.hasNext()) {

            String line = scanner.nextLine();
            String[] columns = line.split("\\|");

            if(String.valueOf(columns[1].charAt(0)).equalsIgnoreCase(letter)) {
                if (temp != null && String.valueOf(columns[1].charAt(0)).equalsIgnoreCase(letter)) {
                    countries.add(temp);
                    temp = new Country(columns[0], columns[1]);

                } else {
                    temp = new Country(columns[0], columns[1]);

                }
            }else{
                countries.add(temp);
                temp = new Country(columns[0], columns[1]);
                break;
            }
        }

        countries.add(temp);
        worldMap.put(letter,countries);
    }


    public static void printMap(){
        System.out.println(worldMap);
        for(String name : worldMap.keySet()) {
            String key = name.toString();
            System.out.printf("%-8s", key + ": ");
            for (Country letter : worldMap.get(name)) {
                System.out.print(letter.name + " |");
            }
            System.out.println();
        }

    }

    public static void addtoHash(String letter, Map<String, List<Country>> worldMap, List<Country> countries){
        worldMap.put(letter, countries);
    }

    static void writeFile(String fileName, String fileContent) throws IOException {
        File f = new File("countries.txt");
        FileWriter fw = new FileWriter(f);
        fw.write(fileContent);
        fw.close();
    }
}



