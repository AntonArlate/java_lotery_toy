/*
Желательный функционал программы:
В программе должен быть минимум один класс со следующими свойствами:
id игрушки,
текстовое название,
количество
частота выпадения игрушки (вес в % от 100)

Метод добавление новых игрушек и возможность изменения веса (частоты выпадения игрушки)
Возможность организовать розыгрыш игрушек.
Например, следующим образом:
С помощью метода выбора призовой игрушки – мы получаем эту призовую игрушку и записываем в список\массив.
Это список призовых игрушек, которые ожидают выдачи.
Еще у нас должен быть метод – получения призовой игрушки.
После его вызова – мы удаляем из списка\массива первую игрушку и сдвигаем массив. А эту игрушку записываем в текстовый файл.
Не забываем уменьшить количество игрушек

 */


import Presenter.MyPresenter;
import Presenter.MyPresenterInterface;
import Repo.DataRepo;
import Repo.DataRepoInterface;
import Viewer.MyUI;
import Viewer.MyUIInterface;

public class Main {
    public static void main(String[] args) {
        // инициализируем интерфейс пользователя
        MyUIInterface myUIInterface;
        myUIInterface = new MyUI();
        // инициализируем репозиторий для работы с файлом
        DataRepoInterface repository;
        repository = new DataRepo();
        // инициализируем презентер
        // передаём в презентер данные о репозитории и итерфейсе и инициализируем обратную связь
        MyPresenterInterface presenter;
        presenter = new MyPresenter(myUIInterface, repository);
        //передаём управление в UI
        myUIInterface.start();
    }



}
