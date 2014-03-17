package com.nipu.controller;

import com.nipu.service.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class AuthController
{

    @RequestMapping(value = Constants.LOGIN_PAGE)
    public String loginPage(final Model model) throws IOException
    {
        return ("soy:nipu.login");
    }

    @RequestMapping(value = Constants.REGISTRATION_PAGE)
    public String regestrationPage(final Model model) throws IOException
    {
        return ("soy:nipu.regestration");
    }

    /*@RequestMapping(value = Constants.LOGOUT_PAGE, method = RequestMethod.POST)
    public String logoutPage() throws IOException
    {
        return ("soy:nipu.index");
    }*/
}
