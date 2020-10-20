package com.demo.stockex.services;

import com.demo.stockex.domain.Share;
import com.demo.stockex.domain.User;
import com.demo.stockex.repositories.ShareRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ShareServiceImplTest {

    @Mock
    ShareRepository shareRepository;

    ShareService shareService;

    User user;
    Share share1;
    Share share2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        shareService = new ShareServiceImpl(shareRepository);

        user = new User();
        user.setId(1);
        user.setUsername("Bob");
        user.setPassword("123");
        user.setBalance(10000.0);


        share1 = new Share();
        share1.setId(1);
        share1.setSymbol("AAPL");
        share1.setPrice(10.0);
        share1.setAmount(5);

        share2 = new Share();
        share2.setId(2);
        share2.setSymbol("GE");
        share2.setPrice(12.0);
        share2.setAmount(3);

    }

    @Test
    void getShares() {

        List<Share> shares = new ArrayList<>();
        shares.add(share1);
        shares.add(share2);

        when(shareRepository.findAll()).thenReturn(shares);

        assertEquals(2, shareService.getShares().size());
    }

    @Test
    void save() {
        when(shareRepository.save(any(Share.class))).thenReturn(share1);
        shareService.save(share1);
        verify(shareRepository, times(1)).save(any(Share.class));
    }

    @Test
    void findById() {
        Optional<Share> shareOptional = Optional.of(share1);

        when(shareRepository.findById(anyInt())).thenReturn(shareOptional);

        assertEquals("AAPL", shareService.findById(1).getSymbol());
    }

    @Test
    void deleteById() {
        shareService.deleteById(anyInt());
        verify(shareRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void buy() {
        Double finBalance = user.getBalance() - (share1.getTotalCost());

        Map<String, Share> map = new HashMap<>();

        when(shareRepository.save(any(Share.class))).thenReturn(share1);

        shareService.buy(share1, user);
        assertEquals(finBalance, user.getBalance());
    }

    @Test
    void sell() {
        user.getShares().put(share1.getSymbol(), share1);

        when(shareRepository.save(any(Share.class))).thenReturn(share1);

        shareService.sell(user, share1.getSymbol(), 3);

        assertEquals(2, user.getShares().get(share1.getSymbol()).getAmount());
    }
}