package com.nipu.controller;

import com.nipu.domain.User;
import com.nipu.repository.UserRepository;
import com.nipu.service.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by humb1t on 04.03.14.
 */
@ControllerAdvice
public class GlobalControllerAdvice
{

    @Autowired
    UserRepository userRepository;

    @ModelAttribute
    public void getUserName(HttpServletRequest request, Model model)
    {
        if (request.getUserPrincipal() != null)
        {
            User currentUser = userRepository.findByEmail(request.getUserPrincipal().getName());
            model.addAttribute(Constants.PARAM_NAME_USER_NAME,
                    currentUser != null ? currentUser.getFirstName() + " "
                            + currentUser.getLastName() : Constants.MSG_UNDEFINED);
        } else
        {
            model.addAttribute(Constants.PARAM_NAME_USER_NAME, Constants.MSG_UNDEFINED);
        }
    }
}
