package com.epam.project.rest;

import com.epam.project.model.Depo;
import com.epam.project.service.DepoService;
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
@RequestMapping(value = "/depo")
public class DepoRestController {
    private static final Logger LOGGER= LogManager.getLogger();

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({IllegalArgumentException.class})
    public String incorrestDataError() {
        return "{  \"response\" : \"Incorrect Data Error\' }";
    }

    @Autowired
    private DepoService depoService;

    // curl -v localhost:8088/depo/getAll
    @ResponseBody
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    public List<Depo> getAllDepo(){
        LOGGER.debug("rest:getAllDepo");
        List<Depo> depos=depoService.getAllDepo();
        return depos;
    }

    //curl -v localhost:8088/depo/getById/2
    @ResponseBody
    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    public  Depo getById(@PathVariable(value = "id") Integer id){
        LOGGER.debug("rest:getById",id);
        return depoService.getDepoById(id);
    }

// curl -H "Content-Type: application/json" -X POST -d '{"name":"Vitebsk"}' -v localhost:8088/depo/add
    @ResponseBody
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public Integer addDepo(@RequestBody Depo depo){
        LOGGER.debug("rest:addDepo",depo);
        Integer id= depoService.addDepo(depo);
        return id;
    }
    






}
