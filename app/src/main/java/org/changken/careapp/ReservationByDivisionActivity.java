package org.changken.careapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.support.v7.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import org.changken.careapp.adapter.DivisionSpinnerAdapter;
import org.changken.careapp.adapter.SubDivisionSpinnerAdapter;
import org.changken.careapp.contract.IntentData;
import org.changken.careapp.datamodels.AirTableListResponse;
import org.changken.careapp.datamodels.AirTableResponse;
import org.changken.careapp.datamodels.Division;
import org.changken.careapp.datamodels.SubDivision;
import org.changken.careapp.models.BaseModel;
import org.changken.careapp.models.DivisionModel;
import org.changken.careapp.models.ModelCallback;
import org.changken.careapp.models.SubDivisionModel;
import org.changken.careapp.tools.Helper;
import org.changken.careapp.tools.Nav;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class ReservationByDivisionActivity extends BaseNavActivity {

    private Button reservationDivisionButton;
    private SearchView searchView;
    private Spinner divisionSpinner, subDivisionSpinner;
    private DivisionSpinnerAdapter divisionSpinnerAdapter;
    private SubDivisionSpinnerAdapter subDivisionSpinnerAdapter;
    private BaseModel<Division> divisionModel;
    private BaseModel<SubDivision> subDivisionModel;
    private List<AirTableResponse<Division>> divisionData;
    private List<AirTableResponse<SubDivision>> subDivisionData;

    private void initView() {
        searchView = (SearchView) findViewById(R.id.searchView);
        divisionSpinner = (Spinner) findViewById(R.id.division_spinner);
        subDivisionSpinner = (Spinner) findViewById(R.id.subDivision_spinner);
        reservationDivisionButton = (Button) findViewById(R.id.reservation_division_button);
        divisionModel = new DivisionModel();
        subDivisionModel = new SubDivisionModel();
        divisionData = new ArrayList<>();
        subDivisionData = new ArrayList<>();
    }

    private void initData() {
        setReservationDivisionBtnStatus(false, false);

        divisionSpinnerAdapter = new DivisionSpinnerAdapter(this, divisionData);
        divisionSpinnerAdapter.setDropDownViewResource(R.layout.spinner_cell);
        divisionSpinner.setAdapter(divisionSpinnerAdapter);
        //更新大科別資料
        updateDivisionSpinner();

        subDivisionSpinnerAdapter = new SubDivisionSpinnerAdapter(this, subDivisionData);
        subDivisionSpinnerAdapter.setDropDownViewResource(R.layout.spinner_cell);
        subDivisionSpinner.setAdapter(subDivisionSpinnerAdapter);
    }

    private void updateDivisionSpinner() {
        updateDivisionSpinner("", false);
    }

    private void updateDivisionSpinner(String formula) {
        updateDivisionSpinner(formula, false);
    }

    private void updateDivisionSpinner(boolean isReset){
        updateDivisionSpinner("", isReset);
    }

    private void updateDivisionSpinner(String formula, boolean isReset) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("view", "Grid%20view");
        queryMap.put("filterByFormula", formula);

        divisionModel.list(queryMap, new ModelCallback<AirTableListResponse<Division>>() {
            @Override
            public void onResponseSuccess(Call<AirTableListResponse<Division>> call, Response<AirTableListResponse<Division>> response) {
                divisionData = response.body().getRecords();
                divisionSpinnerAdapter.updateData(divisionData);
                divisionSpinner.setSelection(0, true);

                // 如果是清除搜尋框
                // 以第一筆大科別來更新子科別
                if(isReset && response.body().getRecords().size() > 0){
                    int firstDivId = response.body().getRecords().get(0).getFields().getDiv_id();
                    updateSubDivisionSpinner("{div_id} = '" + firstDivId + "'");
                }
            }

            @Override
            public void onResponseFailure(Call<AirTableListResponse<Division>> call, Response<AirTableListResponse<Division>> response) {
                showToastMessage("not found error");
            }

            @Override
            public void onFailure(Call<AirTableListResponse<Division>> call, Throwable t) {
                showToastMessage("no internet");
            }
        });
    }

    private void updateSubDivisionSpinner() {
        updateSubDivisionSpinner("");
    }

    private void updateSubDivisionSpinner(String formula) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("view", "Grid%20view");
        queryMap.put("filterByFormula", formula);

        subDivisionModel.list(queryMap, new ModelCallback<AirTableListResponse<SubDivision>>() {
            @Override
            public void onResponseSuccess(Call<AirTableListResponse<SubDivision>> call, Response<AirTableListResponse<SubDivision>> response) {
                subDivisionData = response.body().getRecords();
                subDivisionSpinnerAdapter.updateData(subDivisionData);
                subDivisionSpinner.setSelection(0, true);
            }

            @Override
            public void onResponseFailure(Call<AirTableListResponse<SubDivision>> call, Response<AirTableListResponse<SubDivision>> response) {
                showToastMessage("not found error");
            }

            @Override
            public void onFailure(Call<AirTableListResponse<SubDivision>> call, Throwable t) {
                showToastMessage("no internet");
            }
        });
    }

    //綁定listener
    private void initListeners() {
        //搜尋結果
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //搜尋大科別
                updateDivisionSpinner("{div_name} = '" + query + "'");
                //更新小科別
                updateSubDivisionSpinner("{div_name} = '" + query + "'");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //關閉searchview
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                //更新大科別
                updateDivisionSpinner(true);
                return false;
            }
        });

        reservationDivisionButton.setOnClickListener((v) -> {
            //傳送intent
            int position = subDivisionSpinner.getSelectedItemPosition();

            final int subDiv_id;

            if (position != -1) {
                subDiv_id = subDivisionData.get(position).getFields().getSubDiv_id();
                goToPage(ViewDivisionActivity.class, (intent) -> {
                    //抓取position
                    intent.putExtra("subDiv_id", subDiv_id);
                });
            } else {
                showToastMessage("none selected");
            }
        });

        //顯示子科別
        divisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSubDivisionSpinner("{div_id} = '" + divisionData.get(position).getFields().getDiv_id() + "'");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                setReservationDivisionBtnStatus(false, false);
                showToastMessage("none selected");
            }
        });

        subDivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setReservationDivisionBtnStatus(true, true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                setReservationDivisionBtnStatus(false, false);
                showToastMessage("none select");
            }
        });
    }

    private void setReservationDivisionBtnStatus(boolean isEnabled, boolean isClicked) {
        reservationDivisionButton.setEnabled(isEnabled);
        reservationDivisionButton.setClickable(isClicked);
    }

    //顯示toast訊息
    private void showToastMessage(String message) {
        Toast.makeText(ReservationByDivisionActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    //intent
    private void goToPage(Class<?> cls) {
        goToPage(cls, (intent) -> {
            //nothing
        });
    }

    private void goToPage(Class<?> cls, IntentData intentData) {
        Intent intent = new Intent(this, cls);
        //放置資料使用
        intentData.putData(intent);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
        initListeners();
    }

    @Override
    protected Nav.MenuClickAction getMenuClickAction() {
        return new Nav.MenuClickAction() {
            @Override
            public void goMemberCenter() {
                goToPage(MemberCenterActivity.class);
            }

            @Override
            public void goPersonalInfo() {
                goToPage(EditProfileActivity.class);
            }

            @Override
            public void goReg() {
                goToPage(ReservationActivity.class);
            }

            @Override
            public void goQueryCancel() {

            }

            @Override
            public void goRegRecord() {
                startActivity(new Intent(ReservationByDivisionActivity.this, RegistrationRecordActivity.class));
                finish();
            }

            @Override
            public void goTrafficGuide() {
                goToPage(TrafficInfoActivity.class);
            }

            @Override
            public void goCheckIn() {
                goToPage(QRActivity.class);
            }

            @Override
            public void goLogout() {
                Helper.logoutProcess(ReservationByDivisionActivity.this);
                goToPage(MainActivity.class);
            }
        };
    }

    @Override
    protected int getMyOwnContentView() {
        return R.layout.content_reservation_by_division;
    }
}
