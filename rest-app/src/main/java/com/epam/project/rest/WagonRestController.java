package com.epam.project.rest;

import com.epam.project.model.Wagon;
import com.epam.project.service.WagonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by master on 16.3.17.
 */
 @CrossOrigin
 @RestController
 @RequestMapping(value = "/wagon")
public class WagonRestController {
    private static final Logger LOGGER= LogManager.getLogger();

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({IllegalArgumentException.class})
    public String incorrestDataError() {
        return "{  \"response\" : \"Incorrect Data Error\' }";
    }


    @Autowired
    private WagonService wagonService;
    
//curl -v localhost:8088/wagon/get/14176
    @ResponseBody
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Wagon getWagonById(@PathVariable(value = "id") Integer id){
        LOGGER.debug("rest:getWagonById");
        return wagonService.getWagonById(id);
    }

    // curl -v localhost:8088/wagon/getByDepo/1
    @ResponseBody
    @RequestMapping(value = "/getByDepo/{idDepo}",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    public List<Wagon> getWagonByDepo(@PathVariable(value = "idDepo") Integer idDepo){
        LOGGER.debug("rest:getWagonByDepo");
        List<Wagon>wagons= wagonService.getAllWagonByDepo(idDepo);
        return wagons;
    }


    // curl -H "Content-Type: application/json" -X POST -d '{"id":"14152","type":"closed type","depoId":"2","countOfSeat":"38","dateOfBuilder":"2009-10-24"}' -v localhost:8088/wagon/add
    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Integer addWagon(@RequestBody Wagon wagon){
        LOGGER.debug("rest:addWagon");
        wagonService.addWagon(wagon);
        return wagon.getId();
    }
    

}
