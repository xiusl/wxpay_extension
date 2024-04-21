package wxpay.model;

import com.google.gson.annotations.SerializedName;

public class Amount {

    @SerializedName("currency")
    private String currency;

    @SerializedName("total_amount")
    private Integer totalAmount;

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Amount{" +
                "currency='" + currency + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
