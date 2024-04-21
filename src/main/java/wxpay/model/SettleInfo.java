package wxpay.model;

import com.google.gson.annotations.SerializedName;

public class SettleInfo {
    @SerializedName("profit_sharing")
    private Boolean profitSharing;

    @SerializedName("subsidy_amount")
    private Integer subsidyAmount;

    public Boolean getProfitSharing() {
        return profitSharing;
    }

    public Integer getSubsidyAmount() {
        return subsidyAmount;
    }

    public void setProfitSharing(Boolean profitSharing) {
        this.profitSharing = profitSharing;
    }

    public void setSubsidyAmount(Integer subsidyAmount) {
        this.subsidyAmount = subsidyAmount;
    }

    @Override
    public String toString() {
        return "SettleInfo{" +
                "profitSharing=" + profitSharing +
                ", subsidyAmount=" + subsidyAmount +
                '}';
    }
}