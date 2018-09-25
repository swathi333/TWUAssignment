//Library Managament System starts with this application
public class BibliotecaApplicationDriver {

    InputDriver inputDriver = new InputDriver();
    OutputDriver outputDriver = new OutputDriver();

    public static void main(String[] args) {

        BibliotecaApplicationDriver bibliotecaApplicationDriver = new BibliotecaApplicationDriver();
        bibliotecaApplicationDriver.start();
    }

    private void start() {

        LibraryManagement libraryManagement = new LibraryManagement(inputDriver, outputDriver);
        libraryManagement.printWelcome();
        int input = 0;
        do {
            libraryManagement.printMenu();
            input = inputDriver.readMenuInput();
            libraryManagement.chooseMenu(input);

        } while (input != 0);
    }
}
