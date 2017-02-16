
import java.util.*;

/**
 * Created by BHarris on 2/12/17.
 */
public class Mapping {
    Map<String, List<Country>> worldMap = new HashMap<>();

    public String getLetterToPrint() throws Exception {
        Scanner reader = new Scanner(System.in);
        System.out.println("What letter of countries do you want to print? ");
        String input = reader.nextLine().toLowerCase();
        if(!input.matches("^[a-z]$")){
            throw new Exception();
        }

        return input;
    }


    public  List<Country> getCurrentArray(String letter){
        return worldMap.get(letter);

    }


    public void addToArray(Scanner scanner) {
        String start = "a";
        List<Country> countries = new ArrayList<>();
        while (scanner.hasNext()) {

            String line = scanner.nextLine();
            String[] columns = line.split("\\|");

            if(!columns[1].startsWith(start)){
                worldMap.put(start, countries);
                start = columns[1].substring(0,1);
                countries = new ArrayList<>();
            }

            Country country = new Country(columns[0], columns[1]);
            countries.add(country);
        }

        worldMap.put(start, countries);
    }

    public void printMap() {
        //System.out.println(worldMap);
        for (String name : worldMap.keySet()) {
            String key = name.toString();
            System.out.printf("%-8s", key + ": ");
            for (Country country : worldMap.get(name)) {
                System.out.print(country.name + " |");
            }
            System.out.println();
        }
    }
}
