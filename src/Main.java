import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<String, List<String>> map = readFromCSV();
        Scanner in = new Scanner(System.in);
        String answer = "";
        while(!answer.equals("exit")) {
            System.out.println("Enter type of the pokemon ('exit' to finish):");
            answer = in.nextLine().toLowerCase();
            if (!answer.equals("exit")) {
                List<String> list = map.get(answer);
                if (list == null) {
                    System.out.println("Type " + answer + " not found");
                } else {
                    boolean first = true;
                    for (String s : list) {
                        if (first) {
                            first = false;
                        } else {
                            System.out.print(", ");
                        }
                        System.out.print(s);
                    }
                    System.out.println();
                }
            }
        }
    }

    public static Map<String, List<String>> readFromCSV() throws IOException {
        BufferedReader input = null;
        Map<String, List<String>> map = new HashMap<>();
        try {
            input = new BufferedReader(new FileReader("Pokemons.csv"));
            String line;
            while ((line = input.readLine()) != null) {
                String[] items = line.split(",");
                String type = items[2];
                String name = items[1];
                List<String> list = map.get(type.toLowerCase());
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(name);
                map.put(type.toLowerCase(), list);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
            System.exit(1);
        } catch (IOException ex) {
            System.out.println("Error reading file");
            System.exit(1);
        } finally {
            if (input != null) {
                input.close();
            }
        }
        return map;
    }
}