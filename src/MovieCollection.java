import com.sun.jdi.VMOutOfMemoryException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection {
    private ArrayList<Movie> movieArrayList;
    private Scanner scanner;

    public MovieCollection() {
        movieArrayList = new ArrayList<Movie>();
        loadMoviesInto();
        sortMovies();
        for (int i = 0; i < movieArrayList.size(); i++) {
            System.out.println(movieArrayList.get(i).getName());
        }
        scanner = new Scanner(System.in);
    }

    public void menu() {

        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    public void searchTitles() {

    }

    public void searchCast() {

    }

    public void sortMovies() {
        for (int i = 1; i < movieArrayList.size(); i++) {
            String name = movieArrayList.get(i).getName();
            int innerIdx = i;
            String otherName = movieArrayList.get(innerIdx - 1).getName();
            while (innerIdx > 0 && name.compareTo(otherName) < 0) {
                movieArrayList.set(innerIdx, movieArrayList.get(innerIdx - 1));
                innerIdx--;
            }
            movieArrayList.set(innerIdx, movieArrayList.get(innerIdx));
        }
    }

    private void loadMoviesInto() {
        try {
            File myFile = new File("src\\movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String name = data.substring(0, data.indexOf(","));
                data = data.substring(data.indexOf(",") + 1);
                String cast = data.substring(0, data.indexOf(","));
                data = data.substring(data.indexOf(",") + 1);
                String director = data.substring(0, data.indexOf(","));
                data = data.substring(data.indexOf(",") + 1);
                String overview = data.substring(0, data.indexOf(","));
                data = data.substring(data.indexOf(",") + 1);
                String runTime = data.substring(0, data.indexOf(","));
                data = data.substring(data.indexOf(",") + 1);
                double rating = (double) Double.parseDouble(data);
                movieArrayList.add(new Movie(name, cast, director, overview, runTime, rating));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
