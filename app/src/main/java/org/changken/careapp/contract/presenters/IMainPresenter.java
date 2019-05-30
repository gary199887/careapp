package org.changken.careapp.contract.presenters;

public interface IMainPresenter extends IAcitivityPresenter {
    void loginProcess(String userId, String password);
    void isLoginProcess();
    void goToReg();
    void goToForgetPassword();
}
