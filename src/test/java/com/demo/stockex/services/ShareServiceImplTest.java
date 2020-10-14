package com.demo.stockex.services;

import com.demo.stockex.domain.Share;
import com.demo.stockex.repositories.ShareRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ShareServiceImplTest {

    @Autowired
    ShareRepository shareRepository;

    ShareService shareService;

    Share share;

    @BeforeEach
    void setUp() {
        shareService = new ShareServiceImpl(shareRepository);
        share = new Share();
        share.setName("Google");
        shareRepository.save(share);
    }

    @Test
    void getShares() {
        assertEquals(1, shareService.getShares().size());
    }

    @Test
    void findById() {
        assertEquals("Google", shareService.findById(share.getId()).getName());
    }

}