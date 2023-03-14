package Repo;

import Presenter.MyPresenterInterface;

public interface DataRepoInterface {
    void setPresenter(MyPresenterInterface presenter);

    //    это если id был сгенерирован ранее в БД или каком-то списке
    void addToy(int id, String name, int amount, int chance);

    void addToy(String name, int amount, int chance);

    String[][] getAllToysAsString() throws Exception;

    //    считаем что UI и пользователь не знает индекса, по этому выполняем поиск.
    //    Также сохраняем индекс для быстрого обращения из других методов
    String[] getToyAsString(int id);

    int findIndexFromId(int id);

    void setNameUseId(int id, String name);

    void setAmountUseId(int id, int amount);

    void setChanceUseId(int id, int chance);

    String[] lottery(String contestant);
}
