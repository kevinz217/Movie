public class Movie {
     final private String name;
     final private String cast;
     final private String director;
     final private String overview;
     final private String runtime;
     final private double userRating;

    public Movie(String name, String cast, String director, String overview, String runtime, double userRating) {
        this.name = name;
        this.cast = cast;
        this.director = director;
        this.overview = overview;
        this.runtime = runtime;
        this.userRating = userRating;
    }

    public double getUserRating() {
        return userRating;
    }

    public String getCast() {
        return cast;
    }

    public String getDirector() {
        return director;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public String getRuntime() {
        return runtime;
    }
}
