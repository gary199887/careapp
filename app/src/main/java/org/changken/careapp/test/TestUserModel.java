package org.changken.careapp.test;

import org.changken.careapp.datamodels.AirTableDeleteResponse;
import org.changken.careapp.datamodels.AirTableListResponse;
import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.User;
import org.changken.careapp.models.BaseModel;
import org.changken.careapp.models.ModelCallback;
import org.changken.careapp.models.UserModel;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class TestUserModel {
    private static BaseModel<User> userModel;

    //靜態區塊(類別)
    static {
        userModel = new UserModel();
    }

    //區塊(物件)
    {

    }

    public static void main(String[] args) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("view", "Grid%20view");
        testListAllUser(queryMap, 24);

        //testRetrieveAUser("recwMucuI6isVVdXL", "凱凱");

        /*User user = new User("木航航航", "F999888777", "12345", "122@12.com", "0912878787", "fju", "2019-10-11");
        testCreateAUser(user);*/

        /*User user = new User();
        user.setName("哈哈哈哈!");
        testUpdateAUser("recYPd8MxfFtMpNms", user);*/

        //testDeleteAUser("recYPd8MxfFtMpNms");
    }

    public static void testListAllUser(Map<String, String> queryMap, int countOfRecord) {
        userModel.list(queryMap, new ModelCallback<AirTableListResponse<User>>() {
            @Override
            public void onResponseSuccess(Call<AirTableListResponse<User>> call, Response<AirTableListResponse<User>> response) {
                if (response.body().getRecords().size() == countOfRecord) {
                    System.out.println("測試列出成功!");
                } else {
                    System.out.println("測試列出失敗!");
                }
            }

            @Override
            public void onResponseFailure(Call<AirTableListResponse<User>> call, Response<AirTableListResponse<User>> response) {
                System.out.println("測試失敗!資料404!");
            }

            @Override
            public void onFailure(Call<AirTableListResponse<User>> call, Throwable t) {
                System.out.println("測試失敗!網路不通!");
            }
        });
    }

    public static void testRetrieveAUser(String recordId, String name) {
        userModel.get(recordId, new ModelCallback<AirTableResponse<User>>() {
            @Override
            public void onResponseSuccess(Call<AirTableResponse<User>> call, Response<AirTableResponse<User>> response) {
                if (recordId.equals(response.body().getId()) &&
                        name.equals(response.body().getFields().getName())) {
                    System.out.println("測試取得成功!");
                } else {
                    System.out.println("測試取得失敗!");
                }
            }

            @Override
            public void onResponseFailure(Call<AirTableResponse<User>> call, Response<AirTableResponse<User>> response) {
                System.out.println("測試失敗!資料404!");
            }

            @Override
            public void onFailure(Call<AirTableResponse<User>> call, Throwable t) {
                System.out.println("測試失敗!網路不通!");
            }
        });
    }

    public static void testCreateAUser(User user) {
        userModel.add(user, new ModelCallback<AirTableResponse<User>>() {
            @Override
            public void onResponseSuccess(Call<AirTableResponse<User>> call, Response<AirTableResponse<User>> response) {
                if (user.getEmail().equals(response.body().getFields().getEmail()) &&
                        user.getName().equals(response.body().getFields().getName())) {
                    System.out.println("測試新增成功!");
                } else {
                    System.out.println("測試新增失敗!");
                }
            }

            @Override
            public void onResponseFailure(Call<AirTableResponse<User>> call, Response<AirTableResponse<User>> response) {
                System.out.println("測試失敗!資料404!");
            }

            @Override
            public void onFailure(Call<AirTableResponse<User>> call, Throwable t) {
                System.out.println("測試失敗!網路不通!");
            }
        });
    }

    public static void testUpdateAUser(String recordId, User user) {
        userModel.update(recordId, user, new ModelCallback<AirTableResponse<User>>() {
            @Override
            public void onResponseSuccess(Call<AirTableResponse<User>> call, Response<AirTableResponse<User>> response) {
                if (recordId.equals(response.body().getId()) &&
                        user.getName().equals(response.body().getFields().getName())) {
                    System.out.println("測試修改成功!");
                } else {
                    System.out.println("測試修改失敗!");
                }
            }

            @Override
            public void onResponseFailure(Call<AirTableResponse<User>> call, Response<AirTableResponse<User>> response) {
                System.out.println("測試失敗!資料404!");
            }

            @Override
            public void onFailure(Call<AirTableResponse<User>> call, Throwable t) {
                System.out.println("測試失敗!網路不通!");
            }
        });
    }

    public static void testDeleteAUser(String recordId) {
        userModel.delete(recordId, new ModelCallback<AirTableDeleteResponse>() {
            @Override
            public void onResponseSuccess(Call<AirTableDeleteResponse> call, Response<AirTableDeleteResponse> response) {
                if (recordId.equals(response.body().getId()) &&
                        response.body().isDeleted()) {
                    System.out.println("測試刪除成功!");
                } else {
                    System.out.println("測試刪除失敗!");
                }
            }

            @Override
            public void onResponseFailure(Call<AirTableDeleteResponse> call, Response<AirTableDeleteResponse> response) {
                System.out.println("測試失敗!資料404!");
            }

            @Override
            public void onFailure(Call<AirTableDeleteResponse> call, Throwable t) {
                System.out.println("測試失敗!網路不通!");
            }
        });
    }
}
