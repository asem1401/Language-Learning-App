package observer;

import model.User;

public interface ProgressObserver {
    void onProgressChanged(User user);
}