package com.history.controller;

import com.history.service.EventService;
import com.history.utils.ResultBean;
import io.swagger.annotations.Api;
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

@Api(tags = "EventController")
@RestController
public class EventController {

    @Autowired
    private EventService eventService;



    @GetMapping("/common/getEventsByDynastyId/{dynastyId}")
    public ResultBean getEventsByDynastyId(@PathVariable Integer dynastyId){
        return eventService.getEventsByDynastyId(dynastyId);
    }

}
