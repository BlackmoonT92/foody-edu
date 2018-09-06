package com.itschool.checongbinh.foody.View;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.itschool.checongbinh.foody.Adapters.AdapterRecyclerHinhBinhLuan;
import com.itschool.checongbinh.foody.Model.BinhLuanModel;
import com.itschool.checongbinh.foody.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Binh on 5/12/17.
 */

public class HienThiChiTietBinhLuanActivity extends AppCompatActivity {

    CircleImageView circleImageView;
    TextView txtTieuDeBinhLuan,txtNoiDungBinhLuan,txtSoDiem;
    RecyclerView recyclerViewHinhBinhLuan;
    List<Bitmap> bitmapList;
    BinhLuanModel binhLuanModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_layout_binhluan);

        circleImageView = (CircleImageView) findViewById(R.id.cicleImageUser);
        txtTieuDeBinhLuan = (TextView) findViewById(R.id.txtTieudebinhluan);
        txtNoiDungBinhLuan = (TextView) findViewById(R.id.txtNodungbinhluan);
        txtSoDiem = (TextView) findViewById(R.id.txtChamDiemBinhLuan);
        recyclerViewHinhBinhLuan = (RecyclerView) findViewById(R.id.recyclerHinhBinhLuan);

        bitmapList = new ArrayList<>();

        binhLuanModel = getIntent().getParcelableExtra("binhluanmodel");

        txtTieuDeBinhLuan.setText(binhLuanModel.getTieude());
        txtNoiDungBinhLuan.setText(binhLuanModel.getNoidung());
        txtSoDiem.setText(binhLuanModel.getChamdiem() + "");
        setHinhAnhBinhLuan(circleImageView,binhLuanModel.getThanhVienModel().getHinhanh());

        for (String linkhinh : binhLuanModel.getHinhanhBinhLuanList()){
            StorageReference storageHinhUser = FirebaseStorage.getInstance().getReference().child("hinhanh").child(linkhinh);
            long ONE_MEGABYTE = 1024 * 1024;
            storageHinhUser.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    bitmapList.add(bitmap);
                    if(bitmapList.size() == binhLuanModel.getHinhanhBinhLuanList().size()){
                        AdapterRecyclerHinhBinhLuan adapterRecyclerHinhBinhLuan = new AdapterRecyclerHinhBinhLuan(HienThiChiTietBinhLuanActivity.this,R.layout.custom_layout_hinhbinhluan,bitmapList,binhLuanModel,true);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(HienThiChiTietBinhLuanActivity.this,2);
                        recyclerViewHinhBinhLuan.setLayoutManager(layoutManager);
                        recyclerViewHinhBinhLuan.setAdapter(adapterRecyclerHinhBinhLuan);
                        adapterRecyclerHinhBinhLuan.notifyDataSetChanged();
                    }
                }
            });
        }

    }

    private void setHinhAnhBinhLuan(final CircleImageView circleImageView, String linkhinh){
        StorageReference storageHinhUser = FirebaseStorage.getInstance().getReference().child("thanhvien").child(linkhinh);
        long ONE_MEGABYTE = 1024 * 1024;
        storageHinhUser.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                circleImageView.setImageBitmap(bitmap);
            }
        });
    }
}
