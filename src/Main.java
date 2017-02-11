import jodd.json.JsonParser;
import jodd.json.JsonSerializer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    static Map<String, List<Country>> worldMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Scanner scanner = loadFile();
        addToArray(scanner);
        printMap();
        String input = getLetterToPrint();
        try{
            if (input.matches("^[a-z]$")) {
                saveFile(getCurrentArray(input), input);
            }
            if(input.equalsIgnoreCase("x")){
                System.out.println("No states start with x");
            }
        }catch (Exception e){
            System.out.println("No States start with that!");
        }
    }

    private static String getLetterToPrint() {
        Scanner reader = new Scanner(System.in);
        System.out.println("What letter of countries do you want to print? ");
        String input = reader.nextLine().toLowerCase();
        return input;
    }

    private static Scanner loadFile() throws FileNotFoundException {
        File f = new File("countries.txt");
        Scanner scanner = new Scanner(f);
        return scanner;
    }

    public static List<Country> getCurrentArray(String letter){
        return worldMap.get(letter);

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
                worldMap.put(start, countries);
                start = columns[1].substring(0,1);
                countries = new ArrayList<>();
                Country country = new Country(columns[0], columns[1]);
                countries.add(country);
            }
        }

        worldMap.put(start, countries);


    }
    // Not needed, used for testing.
    public static void printMap() {
        //System.out.println(worldMap);
        for (String name : worldMap.keySet()) {
            String key = name.toString();
            System.out.printf("%-8s", key + ": ");
            for (Country letter : worldMap.get(name)) {
                System.out.print(letter.name + " |");
            }
            System.out.println();
        }
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



