import java.util.Objects;

//Book has bookname, author and published year
public class Book implements Item {
    private final String name;
    private final String author;
    private final int publishedYear;
    private final TypeOfItem typeOfItem;

    Book(String name, String author, int publishedYear, TypeOfItem typeOfItem) {
        this.name = name;
        this.author = author;
        this.publishedYear = publishedYear;
        this.typeOfItem = typeOfItem;
    }

    public String getItemDetails() {

        return String.format("%-25s %8s %8s", this.name, this.author, this.publishedYear);
    }

    public boolean hasItem(String itemName) {
        return this.name.equals(itemName);
    }

    public TypeOfItem getTypeOfItem() {
        return this.typeOfItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
