import com.sun.jdi.VMOutOfMemoryException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection {
    private ArrayList<Movie> movieArrayList;
    private ArrayList<String> actorList;
    private Scanner scanner;

    public MovieCollection() {
        movieArrayList = new ArrayList<Movie>();
        actorList = new ArrayList<String>();
        loadMoviesInto();
        sortMovies();
        scanner = new Scanner(System.in);
        menu();
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

    public void searchCast() {
        System.out.print("Enter the person's name: ");
        String choice = scanner.nextLine();
        ArrayList<String> sortedList = new ArrayList<String>();
        ArrayList<Movie> moviesWithActor = new ArrayList<Movie>();
        boolean actorFound = false;

        //gets every movie with the character
        for (Movie movie : movieArrayList) {
            String cast = movie.getCast();
            String[] actorList = cast.split("\\|");
            //gets all actors of the movie and checks if it is the same
            for (String actor : actorList) {
                if (actor.toLowerCase().indexOf(choice) != -1) {
                    if (!sortedList.contains(actor)) {
                        sortedList.add(actor);
                        actorFound = true;
                    }
                }
            }
        }

        sortActorList(sortedList);

        //prints out each actor
        for (int i = 0; i < sortedList.size(); i++) {
            System.out.println(i + 1 + ". " + sortedList.get(i));
        }

        int actorChoice = -1
;
        if (actorFound) {
            while (!(actorChoice > 0 && actorChoice <= sortedList.size())) {
                System.out.print("Which would you like to see all movies for? ");
                actorChoice = scanner.nextInt();
                scanner.nextLine();
            }
            String selectedActor = sortedList.get(actorChoice - 1);

            for (Movie movie : movieArrayList) {
                String movieCast = movie.getCast();
                if (movieCast.contains(selectedActor)) {
                    moviesWithActor.add(movie);
                }
            }

            int movieChoice = -1;

            for (int i = 0; i < moviesWithActor.size(); i++) {
                System.out.println(i + 1 + ". " + moviesWithActor.get(i).getName());
            }

            while (!(movieChoice > 0 && movieChoice <= sortedList.size())) {
                System.out.print("Which movie would you like to learn more about? ");
                movieChoice = scanner.nextInt();
                scanner.nextLine();
            }
            Movie selectedMovie = moviesWithActor.get(movieChoice - 1);

            System.out.println("\nName: " + selectedMovie.getName());
            System.out.println("Runtime: " + selectedMovie.getRuntime() + " minutes");
            System.out.println("Directed by: " + selectedMovie.getDirector());
            System.out.println("Cast: " + selectedMovie.getCast());
            System.out.println("Overview: " + selectedMovie.getOverview());
            System.out.println("User rating: " + selectedMovie.getUserRating() + "\n");
        } else {
            System.out.println("\nNo actors match the search term!");
        }
    }

    public void searchTitles() {
        System.out.print("Enter search term: ");
        String choice = scanner.nextLine();
        ArrayList<Movie> sortedList = new ArrayList<Movie>();
        boolean realMovie = false;

        //gets every movie with the name
        for (Movie movie : movieArrayList) {
            String movieName = movie.getName().toLowerCase();
            if (movieName.indexOf(choice) != -1) {
                sortedList.add(movie);
                realMovie = true;
            }
        }

        for (int i = 0; i < sortedList.size(); i++) {
            System.out.println(i + 1 + ". " + sortedList.get(i).getName());
        }

        int movieChoice = -1;

        if (realMovie) {

            while (!(movieChoice > 0 && movieChoice <= sortedList.size())) {
                System.out.print("Which movie would you like to learn more about? ");
                movieChoice = scanner.nextInt();
                scanner.nextLine();
            }
            Movie selectedMovie = sortedList.get(movieChoice - 1);

            System.out.println("\nName: " + selectedMovie.getName());
            System.out.println("Runtime: " + selectedMovie.getRuntime() + " minutes");
            System.out.println("Directed by: " + selectedMovie.getDirector());
            System.out.println("Cast: " + selectedMovie.getCast());
            System.out.println("Overview: " + selectedMovie.getOverview());
            System.out.println("User rating: " + selectedMovie.getUserRating() + "\n");
        } else {
            System.out.println("\nNo movie titles match the search term!");
        }
    }

    public void sortMovies() {
        //sorts alphabetically
        for (int i = 1; i < movieArrayList.size(); i++) {
            Movie current = movieArrayList.get(i);
            String name = current.getName();
            int innerIdx = i;
            while (innerIdx > 0 && name.compareTo(movieArrayList.get(innerIdx - 1).getName()) < 0) {
                movieArrayList.set(innerIdx, movieArrayList.get(innerIdx - 1));
                innerIdx--;
            }
            movieArrayList.set(innerIdx, current);
        }
    }

    public void sortActorList(ArrayList<String> actorList) {
        //sorts alphabetically
        for (int i = 1; i < actorList.size(); i++) {
            String current = actorList.get(i);
            int innerIdx = i;
            while (innerIdx > 0 && current.compareTo(actorList.get(innerIdx - 1)) < 0) {
                actorList.set(innerIdx, actorList.get(innerIdx - 1));
                innerIdx--;
            }
            actorList.set(innerIdx, current);
        }
    }

    private void loadMoviesInto() {
        try {
            File myFile = new File("src/movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            fileScanner.nextLine();
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
