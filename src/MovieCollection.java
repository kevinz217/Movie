import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection {
    private ArrayList<Movie> movieArrayList;

    public MovieCollection() {
        movieArrayList = new ArrayList<Movie>();
        loadMoviesInto();
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
