package com.riyanmeidyprayoga.utskuis.model;

import com.google.gson.annotations.SerializedName;
@SuppressWarnings("unused")
public class DataModel {
    @SerializedName("id")
    private String mId;
    @SerializedName("nim")
    private String mNim;
    @SerializedName("nik")
    private String mNik;
    @SerializedName("nama")
    private String mNama;
    @SerializedName("alamat")
    private String mAlamat;
    @SerializedName("no")
    private String mNo;


    public String getId() { return mId; }
    public void setId(String id) { mId = id; }

    public String getNim() { return mNim; }
    public void setNim(String nim) { mNim = nim; }

    public String getNik() { return mNik; }
    public void setNik(String nik) { mNik = nik; }

    public String getNama() { return mNama; }
    public void setNama(String nama) { mNama = nama; }

    public String getAlamat() { return mAlamat; }
    public void setAlamat(String alamat) { mAlamat = alamat; }

    public String getNo() { return mNo; }
    public void setNo(String no) { mNo = no; }
}