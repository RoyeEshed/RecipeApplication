package com.eshed.fork.data.model.Nutrients;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Energy {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("quantity")
    @Expose
    private Double quantity;
    @SerializedName("unit")
    @Expose
    private String unit;

    public String getLabel() {
        return label;
    }

    public Double getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }
}