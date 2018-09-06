package com.itschool.checongbinh.foody.Controller;

import android.util.Log;
import android.widget.TextView;

import com.itschool.checongbinh.foody.Controller.Interfaces.ChiTietQuanAnInterface;
import com.itschool.checongbinh.foody.Model.WifiQuanAnModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Binh on 5/14/17.
 */

public class ChiTietQuanController {

    WifiQuanAnModel wifiQuanAnModel;
    List<WifiQuanAnModel> wifiQuanAnModelList;

    public ChiTietQuanController(){
        wifiQuanAnModel = new WifiQuanAnModel();
        wifiQuanAnModelList = new ArrayList<>();
    }

    public void HienThiDanhSachWifiQuanAn(String maquanan, final TextView txtTenWifi, final TextView txtMatKhauWifi, final TextView txtNgayDangWifi){

        ChiTietQuanAnInterface chiTietQuanAnInterface = new ChiTietQuanAnInterface() {
            @Override
            public void HienThiDanhSachWifi(WifiQuanAnModel wifiQuanAnModel) {
                wifiQuanAnModelList.add(wifiQuanAnModel);
                txtTenWifi.setText(wifiQuanAnModel.getTen());
                txtMatKhauWifi.setText(wifiQuanAnModel.getMatkhau());
                txtNgayDangWifi.setText(wifiQuanAnModel.getNgaydang());
            }
        };

        wifiQuanAnModel.LayDanhSachWifiQuanAn(maquanan,chiTietQuanAnInterface);
    }
}
