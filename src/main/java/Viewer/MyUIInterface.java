package Viewer;

import Presenter.MyPresenterInterface;

public interface MyUIInterface {
    void setPresenter(MyPresenterInterface presenter);

    void start();

    void sendErrorMessage(String message);
}
