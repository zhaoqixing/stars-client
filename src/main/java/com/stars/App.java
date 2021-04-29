package com.stars;

import com.stars.annotation.MyEnableFeignClients;
import com.stars.core.MyRegistryServeProcess;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/4/28 16:52
 */
@MyEnableFeignClients
@Configuration
@Import(MyRegistryServeProcess.class)
public class App {


}
