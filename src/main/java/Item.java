//represents items in a library
public interface Item {
    String getItemDetails();

    boolean hasItem(String itemName);

    TypeOfItem getTypeOfItem();
}
