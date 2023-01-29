package top.roud.kdquery100.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.roud.kdquery100.entity.QueryParam;
import top.roud.kdquery100.service.impl.QueryServiceImpl;
import top.roud.kdquery100.utils.result.Result;

/**
 * @description : TODO
 * @author: guangrui_hu
 * @date: 2023/1/18
 * @version:
 */
@RestController
@RequestMapping("query")
public class QueryController {
    @Autowired
    private QueryServiceImpl queryService;
    @PostMapping("/kd100")
    public Result query(@RequestBody QueryParam param){
        return queryService.queryKdTrack(param);
    }
}
