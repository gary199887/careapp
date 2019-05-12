package org.changken.careapp.models;

import android.util.Log;

import org.changken.careapp.BuildConfig;
import org.changken.careapp.datamodels.AirTableDeleteResponse;
import org.changken.careapp.datamodels.AirTableRequest;
import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.AirTableListResponse;
import org.changken.careapp.datamodels.User;
import org.changken.careapp.tools.RetrofitManager;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.internal.EverythingIsNonNull;

public class UserModel extends BaseModel<User> {

    //儲存List回應
    private AirTableListResponse<User> airTableListResponse;
    //儲存CRU回應
    private AirTableResponse<User> addAirTableResponse, getAirTableResponse, updateAirTableResponse;
    //儲存D回應
    private AirTableDeleteResponse airTableDeleteResponse;
    private UserService mUserService;

    public UserModel() {
        //獲取UserService物件
        mUserService = RetrofitManager.getInstance().getRetrofit().create(UserService.class);
    }

    /**
     * list 獲取所有使用者
     *
     * @param queryMap Map<String, String>
     * @return AirTableListResponse<User>
     * */
    @Override
    public AirTableListResponse<User> list(Map<String, String> queryMap) {

        Call<AirTableListResponse<User>> airTableListResponseCall = mUserService.getUserList("Bearer " + BuildConfig.AIRTABLE_API_KEY, queryMap);

        airTableListResponseCall.enqueue(new Callback<AirTableListResponse<User>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<AirTableListResponse<User>> call, Response<AirTableListResponse<User>> response) {
                if(response.isSuccessful()) {
                    airTableListResponse = response.body();
                }else{
                    airTableListResponse = null;
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<AirTableListResponse<User>> call, Throwable t) {
                airTableListResponse = null;
            }
        });

        return airTableListResponse;
    }

    /**
     * get 獲取一位使用者
     *
     * @param recordId String
     * @return AirTableResponse<User>
     * */
    @Override
    public AirTableResponse<User> get(String recordId) {
        Call<AirTableResponse<User>> airTableResponseCall = mUserService.getUser("Bearer " + BuildConfig.AIRTABLE_API_KEY, recordId);

        airTableResponseCall.enqueue(new Callback<AirTableResponse<User>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<AirTableResponse<User>> call, Response<AirTableResponse<User>> response) {
                if(response.isSuccessful()){
                    getAirTableResponse = response.body();
                }else{
                    getAirTableResponse = null;
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<AirTableResponse<User>> call, Throwable t) {
                getAirTableResponse = null;
            }
        });

        return getAirTableResponse;
    }

    /**
     * add 新增使用者
     *
     * @param data User
     * @return AirTableResponse<User>
     * */
    @Override
    public AirTableResponse<User> add(User data) {
        Call<AirTableResponse<User>> airTableResponseCall = mUserService.addUser("Bearer " + BuildConfig.AIRTABLE_API_KEY, new AirTableRequest<User>(data));

        airTableResponseCall.enqueue(new Callback<AirTableResponse<User>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<AirTableResponse<User>> call, Response<AirTableResponse<User>> response) {
                if(response.isSuccessful()){
                    addAirTableResponse = response.body();
                    Log.i("UserModel", "[response] " + addAirTableResponse.getId());
                }else{
                    addAirTableResponse = null;
                    Log.i("UserModel", "[response] " + response.code());
                    Log.i("UserModel", "[response] err1");
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<AirTableResponse<User>> call, Throwable t) {
                addAirTableResponse = null;
                Log.i("UserModel", "[response] err2");
            }
        });

        return addAirTableResponse;
    }

    /**
     * update 更新使用者
     *
     * @param recordId String
     * @param data User
     * @return AirTableResponse<User>
     * */
    @Override
    public AirTableResponse<User> update(String recordId, User data) {
        Call<AirTableResponse<User>> airTableResponseCall = mUserService.updateUser("Bearer " + BuildConfig.AIRTABLE_API_KEY, recordId, new AirTableRequest<User>(data));

        airTableResponseCall.enqueue(new Callback<AirTableResponse<User>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<AirTableResponse<User>> call, Response<AirTableResponse<User>> response) {
                if(response.isSuccessful()){
                    updateAirTableResponse = response.body();
                }else{
                    updateAirTableResponse = null;
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<AirTableResponse<User>> call, Throwable t) {
                updateAirTableResponse = null;
            }
        });

        return updateAirTableResponse;
    }

    @Override
    public AirTableDeleteResponse delete(String recordId) {
        Call<AirTableDeleteResponse> airTableResponseCall = mUserService.deleteUser("Bearer " + BuildConfig.AIRTABLE_API_KEY, recordId);

        airTableResponseCall.enqueue(new Callback<AirTableDeleteResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<AirTableDeleteResponse> call, Response<AirTableDeleteResponse> response) {
                if(response.isSuccessful()){
                    airTableDeleteResponse = response.body();
                }else{
                    airTableDeleteResponse = null;
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<AirTableDeleteResponse> call, Throwable t) {
                airTableDeleteResponse = null;
            }
        });

        return airTableDeleteResponse;
    }

    public interface UserService{
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
