package org.changken.careapp.presenter;

import org.changken.careapp.AddUserActivity;
import org.changken.careapp.ForgetPasswordActivity;
import org.changken.careapp.MemberCenterActivity;
import org.changken.careapp.R;
import org.changken.careapp.contract.ResourceService;
import org.changken.careapp.contract.presenters.IMainPresenter;
import org.changken.careapp.contract.views.MainView;
import org.changken.careapp.datamodels.AirTableListResponse;
import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.User;
import org.changken.careapp.models.BaseModel;
import org.changken.careapp.models.ModelCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class MainPresenter implements IMainPresenter {

    private MainView mMainView;
    private BaseModel<User> mUserModel;
    private ResourceService mResourceService;

    public MainPresenter(MainView mainView, BaseModel<User> userModel, ResourceService resourceService) {
        mMainView = mainView;
        mUserModel = userModel;
        mResourceService = resourceService;
    }

    @Override
    public void loginProcess(String userId, String password) {
        if (userId.length() > 0 && password.length() > 0) {
            //設定警告視窗
            mMainView.createProgressDialog();
            getDataFromDB(userId, password);
        } else {
            //如果任一個欄位沒有輸入的話!
            mMainView.showToastMessage(mResourceService.getString(R.string.please_type_all_fields));
        }
    }

    private void getDataFromDB(String userId, String password) {
        //建立登入請求
        Map<String, String> query = new HashMap<>();
        query.put("view", "Grid%20view");
        query.put("filterByFormula", String.format("AND({user_id} = '%s', {user_password} = '%s')", userId, password));

        //執行!
        mUserModel.list(query, new ModelCallback<AirTableListResponse<User>>() {
            @Override
            public void onProgress() {
                //顯示它!
                if (mMainView.isProgressDialogCreated())
                    mMainView.showProgressDialog();
            }

            @Override
            public void onProcessEnd() {
                //關掉alert視窗
                if (mMainView.isProgressDialogCreated())
                    mMainView.dismissProgressDialog();
            }

            @Override
            public void onResponseSuccess(Call<AirTableListResponse<User>> call, Response<AirTableListResponse<User>> response) {
                //抓取資料
                List<AirTableResponse<User>> list = response.body().getRecords();
                //如果找到該筆資料
                if (list.size() > 0) {
                    //執行登入
                    mMainView.storeUserInfo(list.get(0).getFields().getIdNumber(), list.get(0).getId());
                    mMainView.goToPageNotBack(MemberCenterActivity.class);
                } else {
                    mMainView.showToastMessage(mResourceService.getString(R.string.login_failure_input_error));
                }
            }

            @Override
            public void onResponseFailure(Call<AirTableListResponse<User>> call, Response<AirTableListResponse<User>> response) {
                mMainView.showToastMessage(mResourceService.getString(R.string.login_failure_not_found_error));
            }

            @Override
            public void onFailure(Call<AirTableListResponse<User>> call, Throwable t) {
                //如果是網路沒通 or Json解析失敗!
                mMainView.showToastMessage(mResourceService.getString(R.string.login_failure_inner_error));
            }
        });
    }

    @Override
    public void isLoginProcess() {
        //確認是否有登入
        if (mMainView.checkIfLogin()) {
            mMainView.showToastMessage("哈哈您已登入唷!");
            mMainView.goToPageNotBack(MemberCenterActivity.class);
        }
    }

    @Override
    public void goToReg() {
        mMainView.goToPage(AddUserActivity.class);
    }

    @Override
    public void goToForgetPassword() {
        mMainView.goToPage(ForgetPasswordActivity.class);
    }

    @Override
    public void onCreate() {
        //檢查是否已有登入
        isLoginProcess();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onRestart() {

    }
}
