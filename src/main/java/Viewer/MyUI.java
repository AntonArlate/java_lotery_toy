package Viewer;

import Presenter.MyPresenterInterface;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class MyUI implements MyUIInterface {
    private MyPresenterInterface presenter;
    private Scanner in = new Scanner(System.in);

    @Override
    public void setPresenter(MyPresenterInterface presenter) {
        this.presenter = presenter;
    }

    @Override
    public void start() {
        Boolean begin = true;
        String com;
        String dataString;
//        System.out.print("\033[H\033[J");

        int id = 0;
        String name = "";
        int amount = 0;
        int chance = 0;

        while (begin) {

            System.out.println("------");
            System.out.println("Введите число для соответствующей задачи или иное для выхода:");
            System.out.println("0. Загрузить тестовые данные");
            System.out.println("1. Добавить новую игрушку на розыгрыш");
            System.out.println("2. Вывести список оставшихся игрушек");
            System.out.println("3. Режим модификации");
            System.out.println("4. Разыграть игрушку");

            com = in.nextLine();

            switch (com) {
                case "0": // Загрузить тестовые данные
                    TestData testData = new TestData();

                    for (int i = 0; i < testData.testString.length; i++) {
                        for (int j = 0; j < testData.testString[0].length; j++) {
                            if (j == 0) {
                                name = testData.testString[i][j];
                            }
                            if (j == 1) {
                                amount = Integer.parseInt(testData.testString[i][j]);
                            }
                            if (j == 2) {
                                chance = Integer.parseInt(testData.testString[i][j]);
                            }
                        }
                        presenter.addToy(name, amount, chance);
                    }
                    break;

                case "1": // Добавить новую игрушку на розыгрыш

                    String temp;

                    System.out.print("Введите ID (оставить пустым для автоинкремента): ");
                    temp = in.nextLine();
                    if (!Objects.equals(temp, "")) {
                        id = Integer.parseInt(temp);
                    } else id = -1;

                    System.out.print("Введите Name: ");
                    name = in.nextLine();
                    System.out.print("Введите Количество (не равно нулю, можно изменить позже): ");
                    amount = in.nextInt();
                    System.out.println("Введите Относительный шанс");
                    System.out.print("может быть любое число, чем больше тем вероятней выпадение: ");
                    chance = in.nextInt();
                    in.nextLine();

                    if (id == -1) {
                        presenter.addToy(name, amount, chance);
                    } else {
                        presenter.addToy(id, name, amount, chance);
                    }
                    System.out.println(">> Выполнено <<");
                    break;

                case "2": // Вывести список оставшихся игрушек
//                    нужно получить значения полей в текстовой форме. Будем массив использовать
                    try {
                        System.out.println(Arrays.deepToString(presenter.getAllToysAsString())); // вывод надо в отдельную функцию
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case "3": // Режим модификации
                    try {
                        System.out.println(Arrays.deepToString(presenter.getAllToysAsString())); // вывод надо в отдельную функцию
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.print("Введите ID игрушки: ");
                    id = in.nextInt();
                    in.nextLine();
                    menuModification(id);
                    break;

                case "4": // Разыграть игрушку
                    System.out.print("Введите имя или любой другой идентификатор победителя: ");
                    String contestant = in.nextLine();
                    System.out.println("ID, Название игрушки, победитель -> " + Arrays.toString(presenter.lottery(contestant)));
                    break;

                default:
                    begin = false;
                    break;
            }
        }

    }

    @Override
    public void sendErrorMessage(String message) {
        System.out.println(message);
    }

    private void menuModification(int id) {
        String name;

        Boolean begin = true;
        String com;
        while (begin) {

            System.out.println("------");
            System.out.println("Выбрана игрушка для редактирования: " + Arrays.toString(presenter.getToyToString(id)));
            System.out.println("Введите число для соответствующей задачи или иное для возврата в предыдущее меню:");
            System.out.println("1. Изменить Имя");
            System.out.println("2. Изменить Количество");
            System.out.println("3. Изменить Шанс");

            com = in.nextLine();

            switch (com) {
                case "1":
                    System.out.print("Введите новое значение: ");
                    presenter.setNameUseId(id, in.nextLine());
                    break;

                case "2":
                    System.out.print("Введите новое значение: ");
                    presenter.setAmountUseId(id, Integer.parseInt(in.nextLine()));
                    break;

                case "3":
                    System.out.print("Введите новое значение: ");
                    presenter.setChanceUseId(id, Integer.parseInt(in.nextLine()));
                    break;

                default:
                    begin = false;
                    break;
            }
            System.out.println(">> Выполнено <<");
        }
    }
}
class TestData{
    String[][] testString = {
            {"Котик", "5", "75"},
            {"Медведь", "3", "40"},
            {"Единорог", "1", "15"}
    };
}

