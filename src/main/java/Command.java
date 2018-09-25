import java.util.List;

//tells what action to do
public interface Command {
    void action(Library library, OutputDriver outputDriver,
                InputDriver inputDriver, TypeOfItem typeOfItem, UserAccounts userAccounts);
}

