package com.nipu.init;

/**
 * Created with IntelliJ IDEA.
 * User: humb1t
 * Date: 09.12.13
 * Time: 22:41
 * To change this template use File | Settings | File Templates.
 */

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(2)
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer
{

}
