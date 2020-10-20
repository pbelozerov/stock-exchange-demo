package com.demo.stockex.services;

import com.demo.stockex.domain.Share;
import com.demo.stockex.domain.User;

import java.util.List;

public interface ShareService {
    List<Share> getShares();

    Share findById(Integer id);

    void deleteById(Integer id);

    Share buy(Share share, User user);

    boolean sell(User user, String shareSymbol, Integer amount);

    Share save(Share share);


}
