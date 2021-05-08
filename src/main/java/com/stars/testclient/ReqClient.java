package com.stars.testclient;

import com.stars.annotation.*;
import com.stars.feign.MediaType;
import com.stars.feign.ParamBean;
import com.stars.testclient.param.TestParam;


/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/4/28 17:11
 */
//@MyFeignClient(baseUrl = "https://www.baidu.com")
@MyFeignClient(serveName = "https://www.baidu.com")
public interface ReqClient {

    @Get(url = "/111")
    void testReq();

    @Post(url = "/post2")
    void postReq(@Param(value = "test") String test, @Param(value = "test") String test2);

    @Post(url = "/post2/{id}")
    void pathReq(@Path(value = "id") String id,@Param(value = "name") String name);

    @Post(url = "/111", produces = MediaType.APPLICATION_JSON_VALUE)
    Object pathReq(@Body TestParam param);
}
