// <Фамилия><Имя><Отчество><датарождения><номертелефона><пол>

package Repo;

import java.io.*;
import java.util.Scanner;

public class Saver implements SaverInterface {

    @Override
    public boolean saveInFile(String str) throws IOException{
        boolean checkSave = false;
        String filPath = "PrizeList" + ".txt";
        File file = new File(filPath);
        //если файла нет создадим его
        if (!(file.exists() && !file.isDirectory())){
                file.createNewFile();
        }


        // сканируем отсутствие дубликата
            // открываем файл на запись если нет дупликатов
            if (!checkduplicate(filPath, str)){
                FileWriter fileW = new FileWriter(filPath, true);
                fileW.write(str + "\n"); // производим запись
                checkSave = true;
                fileW.close();
            }
            else {
                throw new IOException("В файле обнаружен дубликат, добавление отменено");
            }


        return checkSave;
    }

    private boolean checkduplicate (String filPath, String str) throws IOException {
        FileReader fileR = new FileReader(filPath);
        Scanner scan = new Scanner(fileR);

        while (scan.hasNextLine()) {
            String eq = scan.nextLine();
            if (str.equals(eq)){
                fileR.close();
                return true;
            }
        }

        fileR.close();
        return false;
    }
}
