package patrycja.szelc.checkout.component.service;


public interface CheckoutComponentInterface {

    void addItem(String name);

    void deleteItem(String name);

    void deleteAll();

    int checkoutTotalPrice();

}
