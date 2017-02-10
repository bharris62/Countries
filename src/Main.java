
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    static Map<String, List<Country>> worldMap = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {

        File f = new File("ctest.txt");
        Scanner scanner = new Scanner(f);
        addCurrentLetter(scanner);
        printMap();
    }

    public static void addCurrentLetter(Scanner scanner){
        String letters = "abcdefghijklmnopqrstuvwxyz";
        Country temp = null;
        for(int i=0; i < letters.length(); i++) {
            //System.out.println(i);
            temp = addToArray(letters.substring(i,i+1), scanner, temp);
            //printMap();
        }
    }

    public static Country addToArray(String letter, Scanner scanner, Country temp){
        List<Country> countries = new ArrayList<>();
        if (temp != null) {
            countries.add(temp);
            if(scanner.hasNext())
            temp = null;
        }

        while (scanner.hasNext()) {

            String line = scanner.nextLine();
            String[] columns = line.split("\\|");

            if(String.valueOf(columns[1].charAt(0)).equalsIgnoreCase(letter)) {
                temp = new Country(columns[0], columns[1]);
                if (String.valueOf(columns[1].charAt(0)).equalsIgnoreCase(letter)) {
                    countries.add(temp);

                } else {
                    temp = new Country(columns[0], columns[1]);
                    return temp;
                }
            }else{
                //countries.add(temp);
                worldMap.put(letter, countries);
                temp = new Country(columns[0], columns[1]);
                return temp;
            }
        }

        countries.add(temp);
        worldMap.put(letter,countries);
        return null;
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



