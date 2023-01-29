package top.roud.kdquery100.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description : TODO
 * @author: guangrui_hu
 * @date: 2023/1/19
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryResult {
    private String content;
    private String timestamp;
    private String icon;
    private String color;
    /**
     * 101  在途中
     * 102  已签收
     * 103  查无结果
     * 104  服务器错误
     */
    private Integer code;

}
