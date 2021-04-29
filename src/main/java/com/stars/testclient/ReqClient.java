package com.stars.testclient;

import com.stars.annotation.*;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/4/28 17:11
 */
@MyFeignClient(baseUrl = "www.baidu.com")
public interface ReqClient {

    @Get(url = "/111")
    void testReq();

    @Post(url = "/post2")
    void postReq(@Param(value = "test") String test, @Param(value = "test") String test2);

    @Post(url = "/post2/{id}")
    void pathReq(@Path(value = "id") String id,@Param(value = "name") String name);
}
