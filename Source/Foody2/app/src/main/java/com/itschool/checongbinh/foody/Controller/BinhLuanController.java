package com.itschool.checongbinh.foody.Controller;

import com.itschool.checongbinh.foody.Model.BinhLuanModel;

import java.util.List;

/**
 * Created by Binh on 6/14/17.
 */

public class BinhLuanController {
    BinhLuanModel binhLuanModel;

    public  BinhLuanController(){
        binhLuanModel = new BinhLuanModel();
    }

    public void ThemBinhLuan(String maQuanAn, BinhLuanModel binhLuanModel, List<String> listHinh){
        binhLuanModel.ThemBinhLuan(maQuanAn,binhLuanModel,listHinh);
    }
}
