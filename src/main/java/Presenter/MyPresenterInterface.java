package Presenter;

public interface MyPresenterInterface {
    void addToy(int id, String name, int amount, int chance);

    void addToy(String name, int amount, int chance);

    String[][] getAllToysAsString() throws Exception;

    String[] getToyToString(int id);

    void setNameUseId(int id, String name);

    void setAmountUseId(int id, int amount);

    void setChanceUseId(int id, int chance);

    String[] lottery(String contestant);
}
