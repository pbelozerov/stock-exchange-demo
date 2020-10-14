package com.demo.stockex.repositories;

import com.demo.stockex.domain.Share;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShareRepository extends CrudRepository<Share, Integer> {
    Optional<Share> findByName(String name);
}
