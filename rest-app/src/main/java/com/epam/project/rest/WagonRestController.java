package com.epam.project.rest;

import com.epam.project.model.Wagon;
import com.epam.project.service.WagonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    // curl -v localhost:8088/wagon/getAllWagon
    @ResponseBody
    @RequestMapping(value = "/getAllWagon",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    public List<Wagon> getAllWagon(){
        LOGGER.debug("rest:getAllWagon");
        return wagonService.getAllWagon();
    }
    
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

    // curl -H "Content-Type: application/json" -X PUT -d '{"id":"14176","type":"closed type","depoId":"1","countOfSeat":"38","dateOfBuilder":"2009-10-24"}' -v localhost:8088/wagon/update
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Integer updateWagon(@RequestBody Wagon wagon){
        LOGGER.debug("rest:updateWagon");
        return wagonService.updateWagon(wagon);
    }

    //curl -X DELETE -v localhost:8088/wagon/delete/14176
    @ResponseBody
    @RequestMapping(value = "/delete/{idWagon}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public Integer deleteWagon(@PathVariable(value = "idWagon") Integer idWagon){
        LOGGER.debug("rest:deleteWagon");
        return wagonService.deleteWagon(idWagon);
    }

    //curl -v localhost:8088/wagon/countOfWagon/1
    @ResponseBody
    @RequestMapping(value = "/countOfWagon/{idDepo}",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Integer countOfWagonByDepo(@PathVariable(value = "idDepo") Integer idDepo){
        LOGGER.debug("rest:countOfWagonByDepo");
        return wagonService.countWagonByDepo(idDepo);
    }

    //curl -v localhost:8088/wagon/sumOfSeatsByDepo/2
    @ResponseBody
    @RequestMapping(value = "/sumOfSeatsByDepo/{idDepo}",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Integer sumOfSeatsByDepo(@PathVariable(value = "idDepo") Integer idDepo){
        LOGGER.debug("rest:sumOfSeatsByDepo");
        return wagonService.sumOfSeatsByDepo(idDepo);
    }
    //curl -v localhost:8088/wagon/getByDate/2015-08-01/2016-09-01
    @RequestMapping(value = "/getByDate/{from}/{to}",method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.FOUND)
    public List<Wagon> getByDate(@PathVariable(value = "from")String from,
                                 @PathVariable(value = "to") String to){
        LOGGER.debug("rest:getByDate");
        List<Wagon> wagons=wagonService.getWagonByDate(LocalDate.parse(from),LocalDate.parse(to));
        return wagons;
    }





}
