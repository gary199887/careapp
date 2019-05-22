package org.changken.careapp.models;

import org.changken.careapp.BuildConfig;
import org.changken.careapp.datamodels.AirTableDeleteResponse;
import org.changken.careapp.datamodels.AirTableRequest;
import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.AirTableListResponse;
import org.changken.careapp.datamodels.User;
import org.changken.careapp.tools.RetrofitManager;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public class UserModel extends BaseModel<User> {

    private UserService mUserService;

    public UserModel() {
        //獲取UserService物件
        mUserService = RetrofitManager
                .getInstance()
                .getRetrofit()
                .create(UserService.class);
    }

    /**
     * list 獲取所有使用者
     *
     * @param queryMap      Map<String, String>
     * @param modelCallback ModelCallback<AirTableListResponse<User>>
     */
    @Override
    public void list(Map<String, String> queryMap, final ModelCallback<AirTableListResponse<User>> modelCallback) {
        Call<AirTableListResponse<User>> responseCall = mUserService.getUserList("Bearer " + BuildConfig.AIRTABLE_API_KEY, queryMap);
        super.<AirTableListResponse<User>>executeRequest(responseCall, modelCallback);
    }

    /**
     * get 獲取一位使用者
     *
     * @param recordId      String
     * @param modelCallback ModelCallback<AirTableResponse<User>>
     */
    @Override
    public void get(String recordId, final ModelCallback<AirTableResponse<User>> modelCallback) {
        Call<AirTableResponse<User>> responseCall = mUserService.getUser("Bearer " + BuildConfig.AIRTABLE_API_KEY, recordId);
        super.<AirTableResponse<User>>executeRequest(responseCall, modelCallback);
    }

    /**
     * add 新增使用者
     *
     * @param data          User
     * @param modelCallback ModelCallback<AirTableResponse<User>>
     */
    @Override
    public void add(User data, final ModelCallback<AirTableResponse<User>> modelCallback) {
        Call<AirTableResponse<User>> responseCall = mUserService.addUser("Bearer " + BuildConfig.AIRTABLE_API_KEY, new AirTableRequest<User>(data));
        super.<AirTableResponse<User>>executeRequest(responseCall, modelCallback);
    }

    /**
     * update 更新使用者
     *
     * @param recordId      String
     * @param data          User
     * @param modelCallback ModelCallback<AirTableResponse<User>>
     */
    @Override
    public void update(String recordId, User data, final ModelCallback<AirTableResponse<User>> modelCallback) {
        Call<AirTableResponse<User>> responseCall = mUserService.updateUser("Bearer " + BuildConfig.AIRTABLE_API_KEY, recordId, new AirTableRequest<User>(data));
        super.<AirTableResponse<User>>executeRequest(responseCall, modelCallback);
    }

    /**
     * delete 刪除使用者
     *
     * @param recordId      String
     * @param modelCallback ModelCallback<AirTableDeleteResponse>
     */
    @Override
    public void delete(String recordId, final ModelCallback<AirTableDeleteResponse> modelCallback) {
        Call<AirTableDeleteResponse> responseCall = mUserService.deleteUser("Bearer " + BuildConfig.AIRTABLE_API_KEY, recordId);
        super.<AirTableDeleteResponse>executeRequest(responseCall, modelCallback);
    }

    /**
     * 定義取得資料的方法
     */
    private interface UserService {
        //記得要指定接受json，大雷!
        //列出所有使用者
        @GET("user")
        @Headers({
                "Accept: application/json, charset=utf8"
        })
        Call<AirTableListResponse<User>> getUserList(@Header("Authorization") String authorization, @QueryMap(encoded = true) Map<String, String> queryMap);

        //顯示特定使用者
        @GET("user/{record_id}")
        @Headers({
                "Accept: application/json, charset=utf8"
        })
        Call<AirTableResponse<User>> getUser(@Header("Authorization") String authorization, @Path("record_id") String recordId);

        //新增使用者
        @POST("user")
        @Headers({
                "Accept: application/json, charset=utf8",
                "Content-Type: application/json"
        })
        Call<AirTableResponse<User>> addUser(@Header("Authorization") String authorization, @Body AirTableRequest<User> data);

        //更新使用者資料
        @PATCH("user/{record_id}")
        @Headers({
                "Accept: application/json, charset=utf8",
                "Content-Type: application/json"
        })
        Call<AirTableResponse<User>> updateUser(@Header("Authorization") String authorization, @Path("record_id") String recordId, @Body AirTableRequest<User> data);

        //刪除使用者資料
        @DELETE("user/{record_id}")
        @Headers({
                "Accept: application/json, charset=utf8"
        })
        Call<AirTableDeleteResponse> deleteUser(@Header("Authorization") String authorization, @Path("record_id") String recordId);
    }
}
