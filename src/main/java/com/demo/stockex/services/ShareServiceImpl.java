package com.demo.stockex.services;

import com.demo.stockex.domain.Share;
import com.demo.stockex.domain.User;
import com.demo.stockex.repositories.ShareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ShareServiceImpl implements ShareService {

    private final ShareRepository shareRepository;

    @Override
    public List<Share> getShares() {
        return shareRepository.findAll();
    }

    @Override
    public Share save(Share share) {
        return shareRepository.save(share);
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
    public Share buy(Share shareToBuy, User user) {
        if (shareToBuy.getTotalCost() > user.getBalance()) {
            return null;
        }
        user.setBalance(user.getBalance() - shareToBuy.getTotalCost());
        Map<String, Share> userShares = user.getShares();
        if (userShares.containsKey(shareToBuy.getSymbol())) {
            Share storedShare = userShares.get(shareToBuy.getSymbol());
            storedShare.setAmount(storedShare.getAmount() + shareToBuy.getAmount());
            shareRepository.save(storedShare);
            return storedShare;
        }

        shareToBuy.setUser(user);
        userShares.put(shareToBuy.getSymbol(), shareToBuy);
        return shareRepository.save(shareToBuy);
    }

    @Override
    public boolean sell(User user, String shareSymbol, Integer amount) {
        Share requestedShare = user.getShares().get(shareSymbol);
        if (requestedShare.getAmount() > amount) {
            user.setBalance(user.getBalance() + (requestedShare.getPrice() * amount));
            requestedShare.setAmount(requestedShare.getAmount() - amount);
            shareRepository.save(requestedShare);
            return true;
        } else if (requestedShare.getAmount() == amount) {
            user.setBalance(user.getBalance() + (requestedShare.getPrice() * amount));
            user.getShares().remove(requestedShare.getSymbol());
            shareRepository.deleteById(requestedShare.getId());
            return true;
        }

        return false;
    }
}
