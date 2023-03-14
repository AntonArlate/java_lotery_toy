package Repo;

import Presenter.MyPresenterInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DataRepo implements DataRepoInterface {
    private List<Toy> toyList = new ArrayList<>();
    private List<Toy> prizeList = new ArrayList<>();
    private MyPresenterInterface presenter;
    private SaverInterface saverInterface = new Saver();
    private String[] lastCallData;
    private int lastCallIndex;

    public DataRepo() {
        this.lastCallData = new String[4];
        this.lastCallIndex = 0;
    }

    @Override
    public void setPresenter(MyPresenterInterface presenter) {
        this.presenter = presenter;
    }

    //    это если id был сгенерирован ранее в БД или каком-то списке
    @Override
    public void addToy(int id, String name, int amount, int chance) {
//        ищем бинарным поиском позицию для сохранения сортировки по id
//        возможно в алгоритме ошибка, мало тестов
        int insert_i;

        if (toyList.size() != 0) {
            int start_i = 0;
            int end_i = toyList.size() - 1;
            int medium_i = (end_i + start_i) / 2;

            while (start_i < end_i - 1) {
                int mediumToyId = toyList.get(medium_i).getId();
                if (id > mediumToyId) {
                    start_i = medium_i + 1;
                } else {
                    end_i = medium_i;
                }
                medium_i = (end_i + start_i) / 2;
            }
//                на выходе из цикла в medium_i получим индекс ближайшего большего id
            insert_i = medium_i;
        } else insert_i = 0;

        toyList.add(insert_i, new Toy(id, name, amount, chance));
    }

    @Override
    public void addToy(String name, int amount, int chance) {
//        так как у нас была предпринята попытка сохранения сортировки то автоинкримент реализуем от последнего индекса
        int id;
        if (toyList.size() != 0) {
            id = toyList.get(toyList.size() - 1).getId() + 1;
        } else {
            id = 1;
        }
        toyList.add(new Toy(id, name, amount, chance));
    }

    @Override
    public String[][] getAllToysAsString() throws Exception {
        if(toyList.size()==0) {throw new Exception("Нет данных");}

        String[][] result = new String
                [toyList.size()]
                [toyList.get(0).getToyAsString().length]; //тут получаем один образец чтобы уточнить сколько полей выдаёт метод

        int i = 0;
        for (Toy toy : toyList) {
            result[i] = toy.getToyAsString();
            i++;
        }

        return result;
    }

//    считаем что UI и пользователь не знает индекса, по этому выполняем поиск.
//    Также сохраняем индекс для быстрого обращения из других методов
    @Override
    public String[] getToyAsString(int id) {
        String[] result = {"Не найдено"};
        int index = findIndexFromId(id);

        if (index != -1) {
            result = toyList.get(index).getToyAsString();
            this.lastCallIndex = index;
        }
        return result;
    }

    @Override
    public int findIndexFromId(int id) {
        int index;

        if (id == toyList.get(this.lastCallIndex).getId()){
            return this.lastCallIndex;
        } else {
            int start_i = 0;
            int end_i = toyList.size() - 1;
            int medium_i;

            while (start_i <= end_i) {
                medium_i = (end_i + start_i) / 2;
                int mediumToyId = toyList.get(medium_i).getId();
                if (id > mediumToyId) {
                    start_i = medium_i + 1;
                } else if (id < mediumToyId){
                    end_i = medium_i - 1;
                } else {
                    return medium_i;
                }
            }
        }

        return -1;
    }

    @Override
    public void setNameUseId(int id, String name){
        int index = findIndexFromId(id);
        if (index != -1) {
            this.toyList.get(index).setName(name);
        }
    }
    @Override
    public void setAmountUseId(int id, int amount){
        int index = findIndexFromId(id);
        if (index != -1) {
            this.toyList.get(index).setAmount(amount);
        }
    }
    @Override
    public void setChanceUseId(int id, int chance){
        int index = findIndexFromId(id);
        if (index != -1) {
            this.toyList.get(index).setChance(chance);
        }
    }

    @Override
    public String[] lottery(String contestant){
        // определяем количество видов игрушек
        int toysCount = toyList.size();
        // определяем диапазон шанса
        int allChance = 0;
        for (Toy toy: toyList) {allChance = allChance + toy.getChance();}
        // бросаем кубик
        Random rn = new Random();
        int dice = rn.nextInt(allChance + 1);
        // ищем что выпало
        for (Toy toy: toyList) {
            dice = dice - toy.getChance();
            if (dice <= 0){
                Toy prizeToy = new Toy(toy.getId(), toy.getName(), contestant);
                this.prizeList.add(prizeToy);
                // уменьшаем остаток
                toy.setAmount(toy.getAmount() - 1);
                // проверяем не последняя ли это игрушка
                if (toy.getAmount() == 0) {this.toyList.remove(toy);}
                // вызываем метод сохранения в файл
                try {
                    saverInterface.saveInFile(Arrays.toString(prizeToy.getPrizeAsString()));
                } catch (IOException e) {
                    System.out.println(e.getMessage()); // тут вывода в консоль быть не должно, но за отсутствием отдельного класса для вывода ошибок пока оставил
                }
                return prizeToy.getPrizeAsString();
            }
        }
        return new String[]{"Что-то пошло не так"};
    }
}
