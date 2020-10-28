package com.eshed.fork.data.model.Nutrients;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TotalNutrients {

    @SerializedName("ENERC_KCAL")
    @Expose
    private Energy eNERCKCAL;
    @SerializedName("FAT")
    @Expose
    private Fat fAT;
    @SerializedName("FASAT")
    @Expose
    private SaturatedFat fASAT;
    @SerializedName("FATRN")
    @Expose
    private TransFat fATRN;
    @SerializedName("CHOCDF")
    @Expose
    private Carbohydrates cHOCDF;
    @SerializedName("FIBTG")
    @Expose
    private Fiber fIBTG;
    @SerializedName("SUGAR")
    @Expose
    private Sugar sUGAR;
    @SerializedName("PROCNT")
    @Expose
    private Protein pROCNT;
    @SerializedName("CHOLE")
    @Expose
    private Cholesterol cHOLE;
    @SerializedName("NA")
    @Expose
    private Sodium nA;

    public Energy getENERCKCAL() {
        return eNERCKCAL;
    }

    public Fat getFAT() {
        return fAT;
    }

    public Carbohydrates getCHOCDF() {
        return cHOCDF;
    }

    public Fiber getFIBTG() {
        return fIBTG;
    }

    public Sugar getSUGAR() {
        return sUGAR;
    }

    public Protein getPROCNT() {
        return pROCNT;
    }

    public Cholesterol getCHOLE() {
        return cHOLE;
    }

    public Sodium getNA() {
        return nA;
    }
}