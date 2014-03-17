package com.nipu.service;

import com.nipu.domain.Achievement;
import com.nipu.domain.User;
import com.nipu.repository.AchievementRepository;
import com.nipu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: humb1t
 * Date: 24.11.13
 * Time: 2:44
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserService
{
    @Autowired
    AchievementRepository achievementRepository;
    @Autowired
    UserRepository userRepository;

    public boolean addAchievement(String email, Achievement achievement)
    {
        User user = userRepository.findByEmail(email);
        user.addAchievement(achievement);
        user = userRepository.save(user);
        return true;
    }

}
