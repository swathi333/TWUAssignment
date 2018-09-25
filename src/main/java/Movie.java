import java.util.Objects;

public class Movie implements Item {
    private final String name;
    private final int year;
    private final String director;
    private int rating;
    private final TypeOfItem typeOfItem;

    public Movie(final String name, final int year, final String director,
                 final int rating, final TypeOfItem typeOfItem) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = (rating <= 10 && rating >= 1) ? rating : 0;
        this.typeOfItem = typeOfItem;
    }

    public TypeOfItem getTypeOfItem() {
        return this.typeOfItem;
    }

    public String getItemDetails() {
        return String.format("%-25s %8s %8s %8s", this.name, this.year, this.director, (rating == 0) ? "unrated" : this.rating);
    }

    public boolean hasItem(final String movieName) {
        return this.name.equals(movieName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return Objects.equals(name, movie.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
