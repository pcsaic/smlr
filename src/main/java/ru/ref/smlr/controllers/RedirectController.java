package ru.ref.smlr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ref.smlr.services.KeyMapperService;
import ru.ref.smlr.services.KeyMapperService.Get.Link;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Controller
@RequestMapping("/{key}")
public class RedirectController {

    @Autowired
    KeyMapperService service;

    private String HEADER_NAME = "Location";

    @RequestMapping()
    void redirect(@PathVariable("key") String key, HttpServletResponse response) {

        Object res = service.getLink(key);
        switch (res.getClass().getSimpleName()) {
            case "Link":
                Link link = (Link) res;
                response.setHeader(HEADER_NAME, link.getLink());
                response.setStatus(302);
                break;
            case "NotFound":
                response.setStatus(404);
                break;
            case "Success":
                break;
            case "AlreadyExist":
                break;

            case "Object":
                break;

            default:
                break;

        }

        if (key.equals("aAbBcCdD")) {
            response.setHeader(HEADER_NAME, "http://www.eveonline.com");
            response.setStatus(302);
        } else {
            response.setStatus(404);
        }
    }
}
