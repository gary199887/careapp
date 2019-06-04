package org.changken.careapp.contract.views;

public interface MainView {
    void showToastMessage(String message);
    void createProgressDialog();
    void showProgressDialog();
    void dismissProgressDialog();
    boolean isProgressDialogCreated();
    void goToPage(Class<?> cls);
    void goToPageNotBack(Class<?> cls);
    boolean checkIfLogin();
    void storeUserInfo(String user_id, String user_record_id);
}
