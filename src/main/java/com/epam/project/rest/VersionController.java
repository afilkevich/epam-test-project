package com.epam.project.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by master on 16.3.17.
 */
@RestController
public class VersionController {

    private static final String VERSION = "version-1.0";

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public String getVersion() {
        return VERSION;
    }
}
