package org.changken.careapp.models;

import org.changken.careapp.datamodels.AirTableDeleteResponse;
import org.changken.careapp.datamodels.AirTableListResponse;
import org.changken.careapp.datamodels.AirTableRequest;
import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.Division;
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

public class DivisionModel extends BaseModel<Division> {

    private DivisionService mDivisionService;

    public DivisionModel() {
        mDivisionService = RetrofitManager
                .getInstance()
                .getRetrofit()
                .create(DivisionService.class);
    }

    @Override
    public void list(Map<String, String> queryMap, ModelCallback<AirTableListResponse<Division>> modelCallback) {
        executeRequest(
                mDivisionService.getDivisionList(AUTH_HEADER, queryMap),
                modelCallback
        );
    }

    @Override
    public void get(String recordId, ModelCallback<AirTableResponse<Division>> modelCallback) {
        executeRequest(
                mDivisionService.getDivision(AUTH_HEADER, recordId),
                modelCallback
        );
    }

    @Override
    public void add(Division data, ModelCallback<AirTableResponse<Division>> modelCallback) {
        executeRequest(
                mDivisionService.addDivision(AUTH_HEADER, new AirTableRequest<>(data)),
                modelCallback
        );
    }

    @Override
    public void update(String recordId, Division data, ModelCallback<AirTableResponse<Division>> modelCallback) {
        executeRequest(
                mDivisionService.updateDivision(AUTH_HEADER, recordId, new AirTableRequest<>(data)),
                modelCallback
        );
    }

    @Override
    public void delete(String recordId, ModelCallback<AirTableDeleteResponse> modelCallback) {
        executeRequest(
                mDivisionService.deleteDivision(AUTH_HEADER, recordId),
                modelCallback
        );
    }

    /**
     * 定義取得資料的方法
     */
    private interface DivisionService {
        //記得要指定接受json，大雷!
        //列出所有科別
        @GET("division")
        @Headers({
                "Accept: application/json, charset=utf8"
        })
        Call<AirTableListResponse<Division>> getDivisionList(@Header("Authorization") String authorization, @QueryMap(encoded = true) Map<String, String> queryMap);

        //顯示特定科別
        @GET("division/{record_id}")
        @Headers({
                "Accept: application/json, charset=utf8"
        })
        Call<AirTableResponse<Division>> getDivision(@Header("Authorization") String authorization, @Path("record_id") String recordId);

        //新增科別
        @POST("division")
        @Headers({
                "Accept: application/json, charset=utf8",
                "Content-Type: application/json"
        })
        Call<AirTableResponse<Division>> addDivision(@Header("Authorization") String authorization, @Body AirTableRequest<Division> data);

        //更新科別資料
        @PATCH("division/{record_id}")
        @Headers({
                "Accept: application/json, charset=utf8",
                "Content-Type: application/json"
        })
        Call<AirTableResponse<Division>> updateDivision(@Header("Authorization") String authorization, @Path("record_id") String recordId, @Body AirTableRequest<Division> data);

        //刪除科別資料
        @DELETE("division/{record_id}")
        @Headers({
                "Accept: application/json, charset=utf8"
        })
        Call<AirTableDeleteResponse> deleteDivision(@Header("Authorization") String authorization, @Path("record_id") String recordId);
    }
}
