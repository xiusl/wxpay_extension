package wxpay.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CombinePrepayRequest {
    @SerializedName("combine_app_id")
    private String combineAppid;
    @SerializedName("combine_out_trade_no")
    private String combineOutTradeNo;
    @SerializedName("combine_mchid")
    private String combineMchid;
    @SerializedName("sub_orders")
    private List<ReqSubOrderCompatible> subOrders;
    @SerializedName("notify_url")
    private String notifyUrl;

    public String getCombineAppid() {
        return combineAppid;
    }

    public void setCombineAppid(String combineAppid) {
        this.combineAppid = combineAppid;
    }

    public String getCombineOutTradeNo() {
        return combineOutTradeNo;
    }

    public void setCombineOutTradeNo(String combineOutTradeNo) {
        this.combineOutTradeNo = combineOutTradeNo;
    }

    public String getCombineMchid() {
        return combineMchid;
    }

    public void setCombineMchid(String combineMchid) {
        this.combineMchid = combineMchid;
    }

    public List<ReqSubOrderCompatible> getSubOrders() {
        return subOrders;
    }

    public void setSubOrders(List<ReqSubOrderCompatible> subOrders) {
        this.subOrders = subOrders;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    @Override
    public String toString() {
        return "CombinePrepayRequest{" +
                "combineAppid='" + combineAppid + '\'' +
                ", combineOutTradeNo='" + combineOutTradeNo + '\'' +
                ", combineMchid='" + combineMchid + '\'' +
                ", subOrders=" + subOrders +
                ", notifyUrl='" + notifyUrl + '\'' +
                '}';
    }
}






