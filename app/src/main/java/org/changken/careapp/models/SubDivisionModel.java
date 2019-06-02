package org.changken.careapp.models;

import org.changken.careapp.datamodels.AirTableDeleteResponse;
import org.changken.careapp.datamodels.AirTableListResponse;
import org.changken.careapp.datamodels.AirTableRequest;
import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.SubDivision;
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

public class SubDivisionModel extends BaseModel<SubDivision> {

    private SubDivisionService mSubDivisionService;

    public SubDivisionModel() {
        mSubDivisionService = RetrofitManager
                .getInstance()
                .getRetrofit()
                .create(SubDivisionService.class);
    }

    @Override
    public void list(Map<String, String> queryMap, ModelCallback<AirTableListResponse<SubDivision>> modelCallback) {
        executeRequest(
                mSubDivisionService.getSubDivisionList(AUTH_HEADER, queryMap),
                modelCallback
        );
    }

    @Override
    public void get(String recordId, ModelCallback<AirTableResponse<SubDivision>> modelCallback) {
        executeRequest(
                mSubDivisionService.getSubDivision(AUTH_HEADER, recordId),
                modelCallback
        );
    }

    @Override
    public void add(SubDivision data, ModelCallback<AirTableResponse<SubDivision>> modelCallback) {
        executeRequest(
                mSubDivisionService.addSubDivision(AUTH_HEADER, new AirTableRequest<>(data)),
                modelCallback
        );
    }

    @Override
    public void update(String recordId, SubDivision data, ModelCallback<AirTableResponse<SubDivision>> modelCallback) {
        executeRequest(
                mSubDivisionService.updateSubDivision(AUTH_HEADER, recordId, new AirTableRequest<>(data)),
                modelCallback
        );
    }

    @Override
    public void delete(String recordId, ModelCallback<AirTableDeleteResponse> modelCallback) {
        executeRequest(
                mSubDivisionService.deleteSubDivision(AUTH_HEADER, recordId),
                modelCallback
        );
    }

    /**
     * 定義取得資料的方法
     */
    private interface SubDivisionService {
        //記得要指定接受json，大雷!
        //列出所有子科別
        @GET("subDivision")
        @Headers({
                "Accept: application/json, charset=utf8"
        })
        Call<AirTableListResponse<SubDivision>> getSubDivisionList(@Header("Authorization") String authorization, @QueryMap(encoded = true) Map<String, String> queryMap);

        //顯示特定子科別
        @GET("subDivision/{record_id}")
        @Headers({
                "Accept: application/json, charset=utf8"
        })
        Call<AirTableResponse<SubDivision>> getSubDivision(@Header("Authorization") String authorization, @Path("record_id") String recordId);

        //新增子科別
        @POST("subDivision")
        @Headers({
                "Accept: application/json, charset=utf8",
                "Content-Type: application/json"
        })
        Call<AirTableResponse<SubDivision>> addSubDivision(@Header("Authorization") String authorization, @Body AirTableRequest<SubDivision> data);

        //更新子科別資料
        @PATCH("subDivision/{record_id}")
        @Headers({
                "Accept: application/json, charset=utf8",
                "Content-Type: application/json"
        })
        Call<AirTableResponse<SubDivision>> updateSubDivision(@Header("Authorization") String authorization, @Path("record_id") String recordId, @Body AirTableRequest<SubDivision> data);

        //刪除子科別資料
        @DELETE("subDivision/{record_id}")
        @Headers({
                "Accept: application/json, charset=utf8"
        })
        Call<AirTableDeleteResponse> deleteSubDivision(@Header("Authorization") String authorization, @Path("record_id") String recordId);
    }
}
