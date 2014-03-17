package com.nipu.controller;

import com.google.common.collect.ImmutableMap;
import com.nipu.domain.Achievement;
import com.nipu.domain.Criteria;
import com.nipu.domain.User;
import com.nipu.repository.AchievementRepository;
import com.nipu.repository.CategoryRepository;
import com.nipu.repository.CriteriaRepository;
import com.nipu.repository.UserRepository;
import com.nipu.service.Constants;
import com.nipu.ui.model.AchievModel;
import com.nipu.ui.model.CriteriaModel;
import com.nipu.ui.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

/**
 * User: humb1t /Date: 15.12.13 /Time: 18:17
 */
@Controller
public class AchievementsController
{

    @Autowired
    private CriteriaRepository criteriaRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AchievementRepository achievementRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = Constants.ACHIEV_CREATION_PAGE)
    public String createAchiev(final Model model, Principal principal)
    {
        Iterable<Criteria> entityCriterias = criteriaRepository.findAll();
        Collection<CriteriaModel> criterias = new ArrayList<CriteriaModel>();
        for (Criteria criteria : entityCriterias)
        {
            criterias.add(new CriteriaModel(criteria));
        }
        model.addAttribute("criteries", criterias);
        return ("soy:nipu.add");
    }

    @RequestMapping(value = Constants.ACHIEV_ISERTATION_PAGE)
    public String insertAchiev(HttpServletRequest request,
                               @RequestParam("category") String categoryId,
                               @RequestParam("title") String title,
                               @RequestParam("text") String text,
                               final Model model,
                               Principal principal)
    {
        String email = principal.getName();
        double rate = 0;
        int i = 0;
        for (Criteria criteria : criteriaRepository.findAll())
        {
            rate += criteria.getWeight() *
                    Double.parseDouble(request.getParameter("criteria" + i));
            i++;
        }
        Achievement achievement = new Achievement(categoryRepository.findOne(Long.getLong(categoryId)),
                title, text, rate);
        User user = userRepository.findByEmail(email);
        user.addAchievement(achievement);
        user = userRepository.save(user);
        model.addAttribute("title", title);
        model.addAttribute("rate", rate);
        model.addAttribute("userName", user.getFirstName() + " " + user.getLastName());
        return ("soy:nipu.added");
    }

    @RequestMapping(value = Constants.ACHIEV_LIST)
    public String getList(final Model model, Principal principal)
    {
        ArrayList list = new ArrayList();
        for (User user : userRepository.findAll())
        {
            UserModel userModel = new UserModel(user);
            for (Achievement achievement : user.getAchievements())
            {
                AchievModel achievModel = new AchievModel(achievement);
                list.add(ImmutableMap.of("user", userModel, "achievement", achievModel));
            }
        }
        model.addAttribute("list", list);
        return ("soy:nipu.list");
    }
}
