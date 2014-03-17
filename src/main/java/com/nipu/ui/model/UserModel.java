package com.nipu.ui.model;

import com.nipu.domain.Achievement;
import com.nipu.domain.Role;
import com.nipu.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by humb1t on 15.02.14.
 */
public class UserModel
{

    private String id;
    private String firstName;
    private String lastName;
    private List<String> roles;
    private List<AchievModel> achievements;

    public UserModel(String firstName, String lastName, List<String> roles)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
        this.achievements = new ArrayList<AchievModel>();
    }

    public UserModel(String firstName, String lastName, List<String> roles, List<AchievModel> achievements)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
        this.achievements = achievements;
    }

    public UserModel(User user)
    {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.roles = new ArrayList<String>();
        List<Role> userRoles = user.getRoles();
        for (Role role : userRoles)
        {
            this.roles.add(role.getName());
        }

        this.achievements = new ArrayList<AchievModel>();
        List<Achievement> userAchievements = user.getAchievements();
        for (Achievement achievement : userAchievements)
        {
            this.achievements.add(new AchievModel(achievement));
        }
    }

    public String getId()
    {
        return id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public List<String> getRoles()
    {
        return roles;
    }

    public void setRoles(List<String> roles)
    {
        this.roles = roles;
    }

    public List<AchievModel> getAchievements()
    {
        return achievements;
    }

    public void setAchievements(List<AchievModel> achievements)
    {
        this.achievements = achievements;
    }

    public void addAchievement(AchievModel achiev)
    {
        this.achievements.add(achiev);
    }

    public void addAchievements(List<AchievModel> achievements)
    {
        this.achievements.addAll(achievements);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserModel userModel = (UserModel) o;

        if (achievements != null ? !achievements.equals(userModel.achievements) : userModel.achievements != null)
            return false;
        if (firstName != null ? !firstName.equals(userModel.firstName) : userModel.firstName != null) return false;
        if (!lastName.equals(userModel.lastName)) return false;
        if (!roles.equals(userModel.roles)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + lastName.hashCode();
        result = 31 * result + roles.hashCode();
        return result;
    }

    @Override
    public String toString()
    {
        return "UserModel{" +
                "achievements=" + achievements +
                ", roles=" + roles +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
