package com.eshed.fork.Data.model.Nutrients;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fiber {

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
