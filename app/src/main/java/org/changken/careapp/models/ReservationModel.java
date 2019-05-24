package org.changken.careapp.models;

import org.changken.careapp.datamodels.AirTableDeleteResponse;
import org.changken.careapp.datamodels.AirTableListResponse;
import org.changken.careapp.datamodels.AirTableRequest;
import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.Reservation;
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

public class ReservationModel extends BaseModel<Reservation> {

    private ReservationService mReservationService;

    public ReservationModel() {
        mReservationService = RetrofitManager
                .getInstance()
                .getRetrofit()
                .create(ReservationService.class);
    }

    @Override
    public void list(Map<String, String> queryMap, ModelCallback<AirTableListResponse<Reservation>> modelCallback) {
        executeRequest(
                mReservationService.getReservationList(AUTH_HEADER, queryMap),
                modelCallback
        );
    }

    @Override
    public void get(String recordId, ModelCallback<AirTableResponse<Reservation>> modelCallback) {
        executeRequest(
                mReservationService.getReservation(AUTH_HEADER, recordId),
                modelCallback
        );
    }

    @Override
    public void add(Reservation data, ModelCallback<AirTableResponse<Reservation>> modelCallback) {
        executeRequest(
                mReservationService.addReservation(AUTH_HEADER, new AirTableRequest<>(data)),
                modelCallback
        );
    }

    @Override
    public void update(String recordId, Reservation data, ModelCallback<AirTableResponse<Reservation>> modelCallback) {
        executeRequest(
                mReservationService.updateReservation(AUTH_HEADER, recordId, new AirTableRequest<>(data)),
                modelCallback
        );
    }

    @Override
    public void delete(String recordId, ModelCallback<AirTableDeleteResponse> modelCallback) {
        executeRequest(
                mReservationService.deleteReservation(AUTH_HEADER, recordId),
                modelCallback
        );
    }

    /**
     * 定義取得資料的方法
     */
    private interface ReservationService {
        //記得要指定接受json，大雷!
        //列出所有預約
        @GET("reservation")
        @Headers({
                "Accept: application/json, charset=utf8"
        })
        Call<AirTableListResponse<Reservation>> getReservationList(@Header("Authorization") String authorization, @QueryMap(encoded = true) Map<String, String> queryMap);

        //顯示特定預約
        @GET("reservation/{record_id}")
        @Headers({
                "Accept: application/json, charset=utf8"
        })
        Call<AirTableResponse<Reservation>> getReservation(@Header("Authorization") String authorization, @Path("record_id") String recordId);

        //新增預約
        @POST("reservation")
        @Headers({
                "Accept: application/json, charset=utf8",
                "Content-Type: application/json"
        })
        Call<AirTableResponse<Reservation>> addReservation(@Header("Authorization") String authorization, @Body AirTableRequest<Reservation> data);

        //更新預約資料
        @PATCH("reservation/{record_id}")
        @Headers({
                "Accept: application/json, charset=utf8",
                "Content-Type: application/json"
        })
        Call<AirTableResponse<Reservation>> updateReservation(@Header("Authorization") String authorization, @Path("record_id") String recordId, @Body AirTableRequest<Reservation> data);

        //刪除預約資料
        @DELETE("reservation/{record_id}")
        @Headers({
                "Accept: application/json, charset=utf8"
        })
        Call<AirTableDeleteResponse> deleteReservation(@Header("Authorization") String authorization, @Path("record_id") String recordId);
    }
}
