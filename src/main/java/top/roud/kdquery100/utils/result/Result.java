package top.roud.kdquery100.utils.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description : TODO
 * @author: guangrui_hu
 * @date: 2023/1/6
 * @version:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String code;
    private String msg;
    private Object data;
    public Result(StatusCode sc, Object data){
        this.code = sc.getCode();
        this.msg = sc.getMessage();
        this.data = data;
    }
}
