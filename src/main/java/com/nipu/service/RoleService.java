package com.nipu.service;

import com.nipu.domain.Role;
import com.nipu.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: humb1t
 * Date: 09.12.13
 * Time: 21:53
 * To change this template use File | Settings | File Templates.
 */
@Service
public class RoleService
{
    @Autowired
    private RoleRepository roleRepository;

    public Role getRole(long id)
    {
        return roleRepository.findOne(id);
    }
}
