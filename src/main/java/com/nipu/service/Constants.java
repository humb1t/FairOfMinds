package com.nipu.service;

/**
 * Created by humb1t on 12.02.14.
 */
public interface Constants
{

    //Infrastructure pages
    String SITE_URL = "/";
    String LOGIN_PAGE = "/login";
    String LOGOUT_PAGE = "/logout";
    String VERIFICATION_PAGES_ROOT = "/ver";
    String ADMIN_PAGES_ROOT = "/admin";
    String ACHIEV_PAGES_ROOT = "/achiev";
    String E403_PAGE = "/403";
    //Business pages
    String INDEX_PAGE = "/index";
    String REGISTRATION_PAGE = "/regestration";
    String USER_PROFILE_PAGE = "/user";
    String ACHIEV_LIST = "/list";
    String ACHIEV_ISERTATION_PAGE = ACHIEV_PAGES_ROOT + "/added";
    String ACHIEV_CREATION_PAGE = ACHIEV_PAGES_ROOT + "/add";
    //Roles
    String VERIFICATOR_ROLE = "VERF";
    String ADMIN_ROLE = "ADMN";
    String USER_ROLE = "USER";
    String APPLICANT_ROLE = "APPL";
    String EMPLOYER_ROLE = "EMPL";
    String DONATOR_ROLE = "DNTR";
    String BAN_ROLE = "NBAN";
    String LIFETIME_BAN_ROLE = "LBAN";
    Object MSG_UNDEFINED = "undefined";
    String PARAM_NAME_USER_NAME = "user_name";
}
