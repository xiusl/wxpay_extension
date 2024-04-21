package wxpay;

import com.wechat.pay.java.core.cipher.Signer;
import com.wechat.pay.java.core.http.RequestBody;
import com.wechat.pay.java.core.http.JsonRequestBody;
import static com.wechat.pay.java.core.util.GsonUtil.toJson;
import static java.util.Objects.requireNonNull;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.http.Constant;
import com.wechat.pay.java.core.http.DefaultHttpClientBuilder;
import com.wechat.pay.java.core.http.HostName;
import com.wechat.pay.java.core.http.HttpClient;
import com.wechat.pay.java.core.http.HttpHeaders;
import com.wechat.pay.java.core.http.HttpMethod;
import com.wechat.pay.java.core.http.HttpRequest;
import com.wechat.pay.java.core.http.HttpResponse;
import com.wechat.pay.java.core.http.JsonRequestBody;
import com.wechat.pay.java.core.http.MediaType;
import com.wechat.pay.java.core.http.QueryParameter;
import com.wechat.pay.java.core.http.RequestBody;
import com.wechat.pay.java.core.util.NonceUtil;
import com.wechat.pay.java.service.partnerpayments.app.model.PrepayWithRequestPaymentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wxpay.model.CombinePrepayRequest;
import wxpay.model.CombinePrepayResponse;


import java.time.Instant;


public class WxpayPartnerCombineAppPayService {

    private final HttpClient httpClient;
    private final HostName hostName;
    private final Signer signer;
    private static final Logger logger = LoggerFactory.getLogger(WxpayPartnerCombineAppPayService.class);

    private WxpayPartnerCombineAppPayService(HttpClient httpClient, HostName hostName, Signer signer) {
        this.httpClient = requireNonNull(httpClient);
        this.hostName = hostName;
        this.signer = signer;
    }

    public static class Builder {

        private HttpClient httpClient;
        private HostName hostName;
        private Signer signer;
        /**
         * 设置请求配置，以该配置构造默认的httpClient，若未调用httpClient()方法，则必须调用该方法
         *
         * @param config 请求配置
         * @return Builder
         */
        public Builder config(Config config) {
            this.httpClient = new DefaultHttpClientBuilder().config(config).build();
            this.signer = config.createSigner();

            return this;
        }
        /**
         * 设置微信支付域名，可选，默认为api.mch.weixin.qq.com
         *
         * @param hostName 微信支付域名
         * @return Builder
         */
        public Builder hostName(HostName hostName) {
            this.hostName = hostName;
            return this;
        }
        /**
         * 设置自定义httpClient，若未调用config()，则必须调用该方法
         *
         * @param httpClient httpClient
         * @return Builder
         */
        public Builder httpClient(HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }
        public Builder signer(Signer signer) {
            this.signer = signer;
            return this;
        }

        /**
         * 构造服务
         *
         * @return WxpayPartnerCombineAppPayService
         */
        public WxpayPartnerCombineAppPayService build() {
            return new WxpayPartnerCombineAppPayService(httpClient, hostName, signer);
        }
    }

    private CombinePrepayResponse prepay(CombinePrepayRequest request) {
        String requestPath = "https://api.mch.weixin.qq.com/v3/combine-transactions/app";

        CombinePrepayRequest realRequest = request;
        if (this.hostName != null) {
            requestPath = requestPath.replaceFirst(HostName.API.getValue(), hostName.getValue());
        }
        HttpHeaders headers = new HttpHeaders();
        headers.addHeader(Constant.ACCEPT, MediaType.APPLICATION_JSON.getValue());
        headers.addHeader(Constant.CONTENT_TYPE, MediaType.APPLICATION_JSON.getValue());
        HttpRequest httpRequest =
                new HttpRequest.Builder()
                        .httpMethod(HttpMethod.POST)
                        .url(requestPath)
                        .headers(headers)
                        .body(createRequestBody(realRequest))
                        .build();
        HttpResponse<CombinePrepayResponse> httpResponse =
                httpClient.execute(httpRequest, CombinePrepayResponse.class);
        return httpResponse.getServiceResponse();

    }

    public PrepayWithRequestPaymentResponse prepayWithRequestPayment(CombinePrepayRequest request) {
        String prepayId = prepay(request).getPrepayId();
        long timestamp = Instant.now().getEpochSecond();
        String nonceStr = NonceUtil.createNonce(32);
        String message =
                request.getCombineAppid() + "\n" + timestamp + "\n" + nonceStr + "\n" + prepayId + "\n";
        logger.debug("Message for RequestPayment signatures is[{}]", message);
        String sign = signer.sign(message).getSign();
        PrepayWithRequestPaymentResponse response = new PrepayWithRequestPaymentResponse();
        response.setAppid(request.getCombineAppid());
        response.setPartnerId(request.getCombineMchid());
        response.setPrepayId(prepayId);
        response.setPackageVal("Sign=WXPay");
        response.setNonceStr(nonceStr);
        response.setTimestamp(String.valueOf(timestamp));
        response.setSign(sign);
        return response;
    }


    private RequestBody createRequestBody(Object request) {
        return new JsonRequestBody.Builder().body(toJson(request)).build();
    }
}
