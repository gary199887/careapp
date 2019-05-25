package org.changken.careapp.models;

import org.changken.careapp.datamodels.AirTableDeleteResponse;
import org.changken.careapp.datamodels.AirTableListResponse;
import org.changken.careapp.datamodels.AirTableRequest;
import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.Doctor;
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

public class DoctorModel extends BaseModel<Doctor> {

    private DoctorService mDoctorService;

    public DoctorModel() {
        mDoctorService = RetrofitManager
                .getInstance()
                .getRetrofit()
                .create(DoctorService.class);
    }

    @Override
    public void list(Map<String, String> queryMap, ModelCallback<AirTableListResponse<Doctor>> modelCallback) {
        executeRequest(
                mDoctorService.getDoctorList(AUTH_HEADER, queryMap),
                modelCallback
        );
    }

    @Override
    public void get(String recordId, ModelCallback<AirTableResponse<Doctor>> modelCallback) {
        executeRequest(
                mDoctorService.getDoctor(AUTH_HEADER, recordId),
                modelCallback
        );
    }

    @Override
    public void add(Doctor data, ModelCallback<AirTableResponse<Doctor>> modelCallback) {
        executeRequest(
                mDoctorService.addDoctor(AUTH_HEADER, new AirTableRequest<>(data)),
                modelCallback
        );
    }

    @Override
    public void update(String recordId, Doctor data, ModelCallback<AirTableResponse<Doctor>> modelCallback) {
        executeRequest(
                mDoctorService.updateDoctor(AUTH_HEADER, recordId, new AirTableRequest<>(data)),
                modelCallback
        );
    }

    @Override
    public void delete(String recordId, ModelCallback<AirTableDeleteResponse> modelCallback) {
        executeRequest(
                mDoctorService.deleteDoctor(AUTH_HEADER, recordId),
                modelCallback
        );
    }

    /**
     * 定義取得資料的方法
     */
    private interface DoctorService {
        //記得要指定接受json，大雷!
        //列出所有醫師
        @GET("doctor")
        @Headers({
                "Accept: application/json, charset=utf8"
        })
        Call<AirTableListResponse<Doctor>> getDoctorList(@Header("Authorization") String authorization, @QueryMap(encoded = true) Map<String, String> queryMap);

        //顯示特定醫師
        @GET("doctor/{record_id}")
        @Headers({
                "Accept: application/json, charset=utf8"
        })
        Call<AirTableResponse<Doctor>> getDoctor(@Header("Authorization") String authorization, @Path("record_id") String recordId);

        //新增醫師
        @POST("doctor")
        @Headers({
                "Accept: application/json, charset=utf8",
                "Content-Type: application/json"
        })
        Call<AirTableResponse<Doctor>> addDoctor(@Header("Authorization") String authorization, @Body AirTableRequest<Doctor> data);

        //更新醫師資料
        @PATCH("doctor/{record_id}")
        @Headers({
                "Accept: application/json, charset=utf8",
                "Content-Type: application/json"
        })
        Call<AirTableResponse<Doctor>> updateDoctor(@Header("Authorization") String authorization, @Path("record_id") String recordId, @Body AirTableRequest<Doctor> data);

        //刪除醫師資料
        @DELETE("doctor/{record_id}")
        @Headers({
                "Accept: application/json, charset=utf8"
        })
        Call<AirTableDeleteResponse> deleteDoctor(@Header("Authorization") String authorization, @Path("record_id") String recordId);
    }
}
