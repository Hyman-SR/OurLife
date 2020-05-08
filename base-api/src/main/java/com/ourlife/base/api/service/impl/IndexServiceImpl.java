package com.ourlife.base.api.service.impl;

import com.ourlife.base.api.service.IndexService;
import com.ourlife.base.common.util.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author zhangchao
 * @createdOn 2020/5/8
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Override
    public String image2Char(String imagePath, String charKey) {
        String imageChar = StringUtils.image2Char(imagePath, charKey);
        System.out.println(imageChar);
        return imageChar;
    }
}
