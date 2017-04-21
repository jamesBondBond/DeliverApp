package com.example.anshul.deliverapp.onservables;

import java.util.Observable;

/**
 * Created by anshul.g on 3/7/2017.
 */

public class FragmentObserver extends Observable {
    @Override
    public void notifyObservers() {
        setChanged(); // Set the changed flag to true, otherwise observers won't be notified.
        super.notifyObservers();
    }
}