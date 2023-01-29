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
public class QueryParam {
        private String com;
        private String num;
        private String phone;
        private String from;
        private String to;
        private String resultv2;
        private String show;
        private String order;
}
