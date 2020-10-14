package com.demo.stockex.repositories;

import com.demo.stockex.domain.Share;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ShareRepositoryTest {

    @Autowired
    ShareRepository shareRepository;

    @Test
    void saveAndFindByName() {
        Share share = new Share();
        share.setName("Apple");
        shareRepository.save(share);
        assertEquals("Apple", shareRepository.findByName("Apple").get().getName());
    }


}