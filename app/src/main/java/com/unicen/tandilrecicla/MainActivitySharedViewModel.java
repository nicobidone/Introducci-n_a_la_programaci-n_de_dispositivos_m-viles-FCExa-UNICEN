package com.unicen.tandilrecicla;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivitySharedViewModel extends ViewModel {

    private final MutableLiveData<String> selected = new MutableLiveData<>();

    private final MutableLiveData<Boolean> logged = new MutableLiveData<>();

    private Boolean created = true;

    public void setSelect(String item) {
        this.selected.setValue(item);
    }

    public LiveData<String> getSelected() {
        return selected;
    }

    public void setLogged(Boolean logged) {
        this.logged.setValue(logged);
    }

    public MutableLiveData<Boolean> getLogged() {
        return logged;
    }

    Boolean getFirstOnCreate() {
        return created;
    }

    void setFirstOnCreate(){
        created = false;
    }
}
