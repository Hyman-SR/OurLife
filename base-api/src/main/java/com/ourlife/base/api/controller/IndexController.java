package com.ourlife.base.api.controller;

import com.ourlife.base.api.service.IndexService;
import com.ourlife.base.common.constants.ApiPathConstants;
import com.ourlife.base.common.util.ConditionsUtils;
import com.ourlife.base.common.vo.BaseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangchao
 * @createdOn 2020/4/20
 */
@RestController
@RequestMapping(value = ApiPathConstants.INDEX_API)
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 获取首页
     *
     * @return
     */
    @GetMapping
    public BaseResult getIndex() {
        return BaseResult.success();
    }

    /**
     * 图片转字符
     *
     * @param imagePath
     * @param charKey
     * @return
     */
    @GetMapping("/image2char")
    public BaseResult image2Char(@RequestParam("imagePath") String imagePath, @RequestParam("charKey") String charKey) {
        ConditionsUtils.checkArgument(StringUtils.isBlank(imagePath) || StringUtils.isBlank(charKey));
        String imageChar = indexService.image2Char(imagePath, charKey);
        return BaseResult.success(imageChar, "exchange success");
    }

}
