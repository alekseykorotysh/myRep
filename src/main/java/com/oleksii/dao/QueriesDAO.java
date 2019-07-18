package com.oleksii.dao;

import com.oleksii.model.Queries;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueriesDAO extends CrudRepository<Queries, Long> {
}
