package com.oleksii.dao;

import com.oleksii.model.Keys;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeysDAO extends CrudRepository<Keys, Long> {
}
