
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Map<String, List<Country>> worldMap = new HashMap<>();
        String letters = "abcdefghijklmnopqrstuvwxyz";

        //read in file.
        File f = new File("countries.txt");
        Scanner scanner = new Scanner(f);
        List<Country> countries = new ArrayList<>();
        int i = 0;
        while (scanner.hasNext()) {

            String line = scanner.nextLine();
            String[] columns = line.split("\\|");
            Country country = new Country(columns[0], columns[1]);

            if(columns[1].charAt(0) == (letters.charAt(i))){
                countries.add(country);
            }else{
                addtoHash(letters.substring(i, i+1), worldMap, countries);
                i++;

            }
        }

        for(String name : worldMap.keySet()) {
            String key = name.toString();
            System.out.printf("%-8s", key + ": ");
            for (Country letter : worldMap.get(name)) {
                System.out.print(letter.name + " ");

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



