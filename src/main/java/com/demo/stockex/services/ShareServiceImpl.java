package com.demo.stockex.services;

import com.demo.stockex.domain.Share;
import com.demo.stockex.repositories.ShareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ShareServiceImpl implements ShareService {

    private final ShareRepository shareRepository;

    @Override
    public Set<Share> getShares() {
        Set<Share> shares = new HashSet<>();
        shareRepository.findAll().iterator().forEachRemaining(shares::add);
        return shares;
    }

    @Override
    public Share findById(Integer id) {
        return shareRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        shareRepository.deleteById(id);
    }

    @Override
    public Share save(Share share) {
        return shareRepository.save(share);
    }
}
