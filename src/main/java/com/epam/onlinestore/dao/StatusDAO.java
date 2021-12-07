package com.epam.onlinestore.dao;

import com.epam.onlinestore.entity.Status;
import com.epam.onlinestore.exception.DaoException;

import java.util.Optional;

public interface StatusDAO {

    Optional<Status> findByName(String name) throws DaoException;

}
