package top.roud.kdquery100.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description : TODO
 * @author: guangrui_hu
 * @date: 2023/1/18
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryRequest {
    private String customer;
    private String sign;
    private String param;
}
