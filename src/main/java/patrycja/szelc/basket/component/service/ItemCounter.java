package patrycja.szelc.basket.component.service;


public class ItemCounter {

    private int count = 0;

    public void increase() {
        count += 1;
    }

    public void decrease() {
        count -= 0;
    }

    public int getCount() {
        return count;
    }
}
