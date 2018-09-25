public class AuthorizedCommand implements Command {
    private final Command command;

    public AuthorizedCommand(Command command) {
        this.command = command;
    }

    @Override
    public void action(Library library, OutputDriver outputDriver, InputDriver inputDriver,
                       TypeOfItem typeOfItem, UserAccounts userAccounts) {
        User user = userAccounts.loggedInUser;
        if (user != null&& user.isLoggedIn()) {
            command.action(library, outputDriver, inputDriver, typeOfItem, userAccounts);
        } else {
            outputDriver.print("Enter library number password and name of the item");
            user = userAccounts.login(inputDriver.readInput(), inputDriver.readInput());
            command.action(library, outputDriver, inputDriver, typeOfItem, userAccounts);
        }
    }
}
