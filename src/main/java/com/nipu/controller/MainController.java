package com.nipu.controller;

import com.nipu.domain.Achievement;
import com.nipu.domain.User;
import com.nipu.repository.UserRepository;
import com.nipu.service.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * User: humb1t /Date: 15.12.13 /Time: 18:13
 */
@Controller
public class MainController
{

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {Constants.SITE_URL, Constants.INDEX_PAGE})
    public String openHomepage(HttpServletRequest request, final Model model) throws IOException
    {
        return ("soy:nipu.index");
    }

    @RequestMapping(value = Constants.USER_PROFILE_PAGE, params = {"name"})
    public String openUser(@RequestParam(value = "name") String name, final Model model) throws IOException
    {
        model.addAttribute("name", name);
        ArrayList<User> users = (ArrayList<User>) userRepository.findByLastName(name);
        ArrayList<HashMap<String, Object>> camodels = new ArrayList<HashMap<String, Object>>();
        for (User user : users)
        {
            HashMap<String, Object> camodel = new HashMap<String, Object>();
            camodel.put("user", user);
            camodel.put("achievs", new ArrayList<Achievement>(user.getAchievements()));
            camodels.add(camodel);
        }
        model.addAttribute("camodels", camodels);
        return ("soy:nipu.user");
    }


    @RequestMapping(value = Constants.E403_PAGE)
    public String errorPage(final Model model)
    {
        return ("soy:nipu.user");
    }
}
