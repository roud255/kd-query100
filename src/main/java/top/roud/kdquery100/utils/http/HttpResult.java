package top.roud.kdquery100.utils.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description : TODO
 * @author: guangrui_hu
 * @date: 2023/1/4
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpResult {
    private int status;
    private String body;
    private String error;
}
