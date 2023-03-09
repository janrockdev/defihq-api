package org.defihq.defihq_api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "<a href='http://localhost:8080/swagger-ui/index.html#'>OpenAPI</a>";
    }

}
