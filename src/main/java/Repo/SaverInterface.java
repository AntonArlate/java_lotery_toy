package Repo;

import java.io.IOException;

public interface SaverInterface {
    boolean saveInFile(String str) throws IOException;
}
