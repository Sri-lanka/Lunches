package com.sena.lunches.controller.error;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomErrorController implements ErrorController {


    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status != null) {
            int statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "custom_error/Error404";
            } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "custom_error/Error404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "custom_error/Error404";
            }
        }
        return "custom_error/Error404";
    }

    public String getErrorPath() {
        return null;
    }
}

