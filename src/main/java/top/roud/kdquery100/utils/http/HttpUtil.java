package top.roud.kdquery100.utils.http;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @description : TODO
 * @author: guangrui_hu
 * @date: 2023/1/4
 * @version:
 */
public class HttpUtil {
    public static HttpResult doPost(String url, Object obj, int connectTimeout, int socketTimeout) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse resp = null;
        HttpResult result = new HttpResult();

        try {
            Map<String, String> params = ObjectToMapUtil.objectToMap(obj);
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
            httpPost.setConfig(requestConfig);
            if (params != null && params.size() > 0) {
                List<NameValuePair> list = new ArrayList();
                Iterator var11 = params.entrySet().iterator();

                while(var11.hasNext()) {
                    Map.Entry<String, String> entry = (Map.Entry)var11.next();
                    list.add(new BasicNameValuePair((String)entry.getKey(), (String)entry.getValue()));
                }
//                httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
                httpPost.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
            }

            resp = httpClient.execute(httpPost);
            String body = EntityUtils.toString(resp.getEntity(), "UTF-8");
            int statusCode = resp.getStatusLine().getStatusCode();
            result.setStatus(statusCode);
            result.setBody(body);
        } catch (Exception var21) {
            var21.printStackTrace();
        } finally {
            if (null != resp) {
                try {
                    resp.close();
                } catch (IOException var20) {
                    var20.printStackTrace();
                }
            }

        }
        return result;
    }
}
