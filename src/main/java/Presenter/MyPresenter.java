package Presenter;

import Repo.DataRepoInterface;
import Viewer.MyUIInterface;

public class MyPresenter implements MyPresenterInterface {
    private MyUIInterface uiInterface;
    private DataRepoInterface dataRepoInterface;

    public MyPresenter(MyUIInterface uiInterface, DataRepoInterface dataRepoInterface) {
        this.uiInterface = uiInterface;
        uiInterface.setPresenter(this);
        this.dataRepoInterface = dataRepoInterface;
        dataRepoInterface.setPresenter(this);
    }

    @Override
    public void addToy(int id, String name, int amount, int chance) {
        dataRepoInterface.addToy(id, name, amount, chance);
    }
    @Override
    public void addToy(String name, int amount, int chance) {
        dataRepoInterface.addToy(name, amount, chance);
    }

    @Override
    public String[][] getAllToysAsString() throws Exception {
        return dataRepoInterface.getAllToysAsString();
    }

    @Override
    public String[] getToyToString(int id) {
        return dataRepoInterface.getToyAsString(id);
    }

    @Override
    public void setNameUseId(int id, String name){
        dataRepoInterface.setNameUseId(id, name);
    }
    @Override
    public void setAmountUseId(int id, int amount){
        dataRepoInterface.setAmountUseId(id, amount);
    }
    @Override
    public void setChanceUseId(int id, int chance){
        dataRepoInterface.setChanceUseId(id, chance);
    }

    @Override
    public String[] lottery(String contestant){
        return dataRepoInterface.lottery(contestant);
    }
}
