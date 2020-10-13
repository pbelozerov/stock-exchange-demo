package com.demo.stockex.services;

import com.demo.stockex.domain.Share;

import java.util.Set;

public interface ShareService {
    Set<Share> getShares();

    Share findById(Integer id);

    void deleteById(Integer id);

    Share save(Share share);
}
