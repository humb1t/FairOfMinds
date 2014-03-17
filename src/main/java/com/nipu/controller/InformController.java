package com.nipu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by humb1t on 01.03.14.
 */
@Controller
public class InformController
{

    @RequestMapping(value = "/who")
    public String showParty(final Model model)
    {
        return ("soy:nipu.party");
    }

    @RequestMapping(value = "/about")
    public String tellAbout(final Model model)
    {
        return ("soy:nipu.about");
    }
}
