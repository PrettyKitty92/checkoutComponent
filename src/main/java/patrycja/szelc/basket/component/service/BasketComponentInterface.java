package patrycja.szelc.basket.component.service;


public interface BasketComponentInterface {

    void addItem(String name);

    void deleteItem(String name);

    void clear();

    int checkoutTotalPrice();

}
