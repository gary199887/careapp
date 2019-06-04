package org.changken.careapp.contract.presenters;

public interface IAcitivityPresenter {
    void onCreate();
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
    void onRestart();
}
