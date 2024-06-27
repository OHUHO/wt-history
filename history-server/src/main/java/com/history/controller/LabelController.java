package com.history.controller;

import com.history.service.LabelService;
import com.history.utils.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CSL
 * @since 2023-02-14
 */

@Api(tags = "LabelController")
@RestController
public class LabelController {

    @Autowired
    private LabelService labelService;


    @ApiOperation(value = "通过朝代Id获取词条标签")
    @GetMapping("/common/getLabelsByDynasty/{dynastyId}")
    public ResultBean getLabelsByDynastyId(@PathVariable Integer dynastyId){
        return labelService.getLabelsByDynastyId(dynastyId);
    }


    @ApiOperation(value = "获取所有的词条标签")
    @GetMapping("/user/getLabels/all")
    public ResultBean getLabels(){
        return labelService.getLabels();
    }

}
