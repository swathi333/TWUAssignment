import java.util.List;

public class PrintCommand implements Command {
    public void action(final Library library, final OutputDriver outputDriver,
                       final InputDriver inputDriver, final TypeOfItem typeOfItem,UserAccounts userAccounts) {
        if (typeOfItem.equals(TypeOfItem.BOOK)) {
            printBookDetails(library, outputDriver, typeOfItem);
            return;
        }
        printMovieDetails(library, outputDriver, typeOfItem);
    }

    private void printBookDetails(final Library library, final OutputDriver outputDriver,
                                  final TypeOfItem typeOfItem) {
        List<String> listOfStringsOfItemDetails = library.getListOfItemDetails(typeOfItem);

        outputDriver.print(String.format("%-25s %8s %20s", "Book Name", "Author", "Published Year"));
        outputDriver.print("---------------------------------------------------------------------------" +
                "------------------------------");

        for (String itemDetails : listOfStringsOfItemDetails) {
            if (typeOfItem.equals(TypeOfItem.BOOK)) {
                outputDriver.print(itemDetails);
            }
        }
    }

    private void printMovieDetails(final Library library, final OutputDriver outputDriver,
                                   final TypeOfItem typeOfItem) {
        List<String> listOfStringsOfItemDetails = library.getListOfItemDetails(typeOfItem);

        outputDriver.print(String.format("%-25s %8s %10s %8s", "Movie Name", "year", "Director", "Rating"));
        outputDriver.print("---------------------------------------------------------------------------" +
                "------------------------------");

        for (String itemDetails : listOfStringsOfItemDetails) {
            if (typeOfItem.equals(TypeOfItem.MOVIE)) {
                outputDriver.print(itemDetails);
            }
        }
    }
}
