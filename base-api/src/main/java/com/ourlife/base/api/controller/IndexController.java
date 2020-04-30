package com.ourlife.base.api.controller;

import com.ourlife.base.common.constants.ApiPathConstants;
import com.ourlife.base.common.vo.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangchao
 * @createdOn 2020/4/20
 */
@RestController
@RequestMapping(value = ApiPathConstants.INDEX_API)
public class IndexController {

    @GetMapping
    public BaseResult getIndex() {
        return BaseResult.success();
    }
}
