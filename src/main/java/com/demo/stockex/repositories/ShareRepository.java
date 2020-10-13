package com.demo.stockex.repositories;

import com.demo.stockex.domain.Share;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareRepository extends CrudRepository<Share, Integer> {
}
