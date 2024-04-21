package wxpay.model;

import com.google.gson.annotations.SerializedName;

public class CombinePrepayResponse {
    @SerializedName("prepay_id")
    private String prepayId;

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    @Override
    public String toString() {
        return "CombinePrepayResponse{" +
                "prepayId='" + prepayId + '\'' +
                '}';
    }
}
