package wxpay.model;

import com.google.gson.annotations.SerializedName;

public class ReqSubOrderCompatible {
    @SerializedName("mchid")
    private String mchid;
    @SerializedName("attach")
    private String attach;
    @SerializedName("amount")
    private Amount amount;
    @SerializedName("out_trade_no")
    private String outTradeNo;
    @SerializedName("description")
    private String description;
    @SerializedName("settle_info")
    private SettleInfo settleInfo;

    public String getMchid() {
        return mchid;
    }

    public String getAttach() {
        return attach;
    }

    public Amount getAmount() {
        return amount;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public String getDescription() {
        return description;
    }

    public SettleInfo getSettleInfo() {
        return settleInfo;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSettleInfo(SettleInfo settleInfo) {
        this.settleInfo = settleInfo;
    }

    @Override
    public String toString() {
        return "ReqSubOrderCompatible{" +
                "mchid='" + mchid + '\'' +
                ", attach='" + attach + '\'' +
                ", amount=" + amount +
                ", outTradeNo='" + outTradeNo + '\'' +
                ", description='" + description + '\'' +
                ", settleInfo=" + settleInfo +
                '}';
    }
}