package top.roud.kdquery100.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.roud.kdquery100.entity.QueryParam;
import top.roud.kdquery100.entity.QueryRequest;
import top.roud.kdquery100.entity.QueryResult;
import top.roud.kdquery100.service.impl.QueryServiceImpl;
import top.roud.kdquery100.utils.http.HttpResult;
import top.roud.kdquery100.utils.http.HttpUtil;
import top.roud.kdquery100.utils.md.DigestUtil;
import top.roud.kdquery100.utils.result.Result;
import top.roud.kdquery100.utils.result.StatusCode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description : TODO
 * @author: guangrui_hu
 * @date: 2023/1/18
 * @version:
 */
@Service
public class QueryService implements QueryServiceImpl{

    @Override
    public Result queryKdTrack(QueryParam param) {
        String paramStr = JSONObject.toJSONString(param);
        QueryRequest queryRequest = new QueryRequest();
        queryRequest.setParam(paramStr);
        queryRequest.setCustomer("自行填入自己的Customer");
        String sign = DigestUtil.md5(paramStr + "自行填入自己的key" + "自行填入自己的Customer").toUpperCase();
        queryRequest.setSign(sign);
        ArrayList<QueryResult> list = new ArrayList<>();
        try{
            HttpResult httpResult = HttpUtil.doPost("https://poll.kuaidi100.com/poll/query.do", queryRequest, 3000, 300);
            if(httpResult.getStatus() == 200){
                String body = httpResult.getBody();
                JSONObject jsonObject = JSONObject.parseObject(body);
                if(StringUtils.contains(body, "\"status\":\"200\"")){
                    JSONArray datas = jsonObject.getJSONArray("data");
                    List<QueryResult> resList = datas.stream().map(o -> {
                        QueryResult queryResult = new QueryResult();
                        String ftime = ((JSONObject) JSONObject.toJSON(o)).getString("ftime");
                        String content = ((JSONObject) JSONObject.toJSON(o)).getString("context");
                        queryResult.setContent(content);
                        if(StringUtils.contains(content,"签收")){
                            queryResult.setIcon("Select");
                            queryResult.setColor("green");
                            queryResult.setCode(102);
                        }else {
                            queryResult.setCode(101);
                        }
                        queryResult.setTimestamp(ftime);
                        return queryResult;
                    }).collect(Collectors.toList());
                    return new Result(StatusCode.SUCCESS,resList);
                }else{
                    return getResultList(list,"查无结果", "orange", "SemiSelect", 103, StatusCode.FAIL);
                }
            }
        }catch (Exception e){
            return getResultList(list,"服务器异常，请联系管理员修复", "red", "CloseBold",104, StatusCode.ERROR);
        }
        return getResultList(list,"服务器异常，请联系管理员修复", "red", "CloseBold",104, StatusCode.ERROR);
    }

    private Result getResultList(ArrayList<QueryResult> list, String content, String color, String icon, Integer code, StatusCode sc) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        QueryResult queryResult = new QueryResult();
        queryResult.setTimestamp(time);
        queryResult.setContent(content);
        queryResult.setIcon(icon);
        queryResult.setColor(color);
        queryResult.setCode(code);
        list.add(queryResult);
        return new Result(sc, list);
    }
}
