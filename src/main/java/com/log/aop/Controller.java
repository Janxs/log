package com.log.aop;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @PostMapping("testPost")
    @LogAnn
    public String postTest(@RequestBody ReqTest reqTest){

        return reqTest.toString();
    }


    @PostMapping("list")
    @LogAnn(saveResponse = false)
    public String list(@RequestBody ReqTest reqTest){
        //
        return reqTest.toString();
    }

}
