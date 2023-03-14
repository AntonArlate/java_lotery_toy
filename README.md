# Программа для розыгрыша игрушек в качестве учебного проекта.
## Info сборки
SDK: 19 Oracle OpenJDK version 19.0.1

Builder: Maven

## Навигация по модулям
[Main.java](/src/main/java/Main.java) - Точка входа. Инициирует все классы и передаёт управление в [MyUI.java](/src/main/java/Viewer/MyUI.java)

[MyUI.java](/src/main/java/Viewer/MyUI.java) - Вьюшка. Онже пользовательский интерфейс. Общается с пользователем и посылает запросы в обработчик через [MyPresenter.java](/src/main/java/Presenter/MyPresenter.java)

[MyPresenter.java](/src/main/java/Presenter/MyPresenter.java) - Связующий интерфейс

[DataRepo.java](/src/main/java/Repo/DataRepo.java) - основная backend логика программы. Ловит запросы от UI. Работает через дополнительные классы с файлами и данными

[Saver.java](/src/main/java/Repo/Saver.java) - Модуль для сохранения в файл текстовой строки. Файл создаётся автоматически с фиксированым именем. Затем дописывается уникальными значениями.

[PrizeList.txt](/PrizeList.txt) - Текстовый файл для хранения результатов розыгрыша. Создаётся автоматически, затем дописывается.

---

#### Следующие 2 файла это попытка разобраться с библиотекой OpenCSV, в программе не используется. К сожалению не хватает времени пока. Желаю оставить в репозитории для того что-бы вернуться позже.

src/main/resources/toys.csv

src/main/java/com/zetcode/OpenCSVReadEx.java