package org.changken.careapp.models;

import org.changken.careapp.datamodels.AirTableDeleteResponse;
import org.changken.careapp.datamodels.AirTableListResponse;
import org.changken.careapp.datamodels.AirTableRequest;
import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.DoctorTime;
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

public class DoctorTimeModel extends BaseModel<DoctorTime> {

    private DoctorTimeService mDoctorTimeService;

    public DoctorTimeModel() {
        mDoctorTimeService = RetrofitManager
                .getInstance()
                .getRetrofit()
                .create(DoctorTimeService.class);
    }

    @Override
    public void list(Map<String, String> queryMap, ModelCallback<AirTableListResponse<DoctorTime>> modelCallback) {
        executeRequest(
                mDoctorTimeService.getDoctorTimeList(AUTH_HEADER, queryMap),
                modelCallback
        );
    }

    @Override
    public void get(String recordId, ModelCallback<AirTableResponse<DoctorTime>> modelCallback) {
        executeRequest(
                mDoctorTimeService.getDoctorTime(AUTH_HEADER, recordId),
                modelCallback
        );
    }

    @Override
    public void add(DoctorTime data, ModelCallback<AirTableResponse<DoctorTime>> modelCallback) {
        executeRequest(
                mDoctorTimeService.addDoctorTime(AUTH_HEADER, new AirTableRequest<>(data)),
                modelCallback
        );
    }

    @Override
    public void update(String recordId, DoctorTime data, ModelCallback<AirTableResponse<DoctorTime>> modelCallback) {
        executeRequest(
                mDoctorTimeService.updateDoctorTime(AUTH_HEADER, recordId, new AirTableRequest<>(data)),
                modelCallback
        );
    }

    @Override
    public void delete(String recordId, ModelCallback<AirTableDeleteResponse> modelCallback) {
        executeRequest(
                mDoctorTimeService.deleteDoctorTime(AUTH_HEADER, recordId),
                modelCallback
        );
    }

    /**
     * 定義取得資料的方法
     */
    private interface DoctorTimeService {
        //記得要指定接受json，大雷!
        //列出所有值班表
        @GET("doctor_time")
        @Headers({
                "Accept: application/json, charset=utf8"
        })
        Call<AirTableListResponse<DoctorTime>> getDoctorTimeList(@Header("Authorization") String authorization, @QueryMap(encoded = true) Map<String, String> queryMap);

        //顯示特定值班表
        @GET("doctor_time/{record_id}")
        @Headers({
                "Accept: application/json, charset=utf8"
        })
        Call<AirTableResponse<DoctorTime>> getDoctorTime(@Header("Authorization") String authorization, @Path("record_id") String recordId);

        //新增值班表
        @POST("doctor_time")
        @Headers({
                "Accept: application/json, charset=utf8",
                "Content-Type: application/json"
        })
        Call<AirTableResponse<DoctorTime>> addDoctorTime(@Header("Authorization") String authorization, @Body AirTableRequest<DoctorTime> data);

        //更新值班表資料
        @PATCH("doctor_time/{record_id}")
        @Headers({
                "Accept: application/json, charset=utf8",
                "Content-Type: application/json"
        })
        Call<AirTableResponse<DoctorTime>> updateDoctorTime(@Header("Authorization") String authorization, @Path("record_id") String recordId, @Body AirTableRequest<DoctorTime> data);

        //刪除值班表資料
        @DELETE("doctor_time/{record_id}")
        @Headers({
                "Accept: application/json, charset=utf8"
        })
        Call<AirTableDeleteResponse> deleteDoctorTime(@Header("Authorization") String authorization, @Path("record_id") String recordId);
    }
}
