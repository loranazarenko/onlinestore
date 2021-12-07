package com.epam.onlinestore.dao;

import com.epam.onlinestore.entity.Role;
import com.epam.onlinestore.exception.DaoException;

import java.util.Optional;

public interface RoleDAO {

    Optional<Role> findByName(String name) throws DaoException;
}
