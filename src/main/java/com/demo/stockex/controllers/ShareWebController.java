package com.demo.stockex.controllers;

import com.demo.stockex.domain.Share;
import com.demo.stockex.domain.Symbols;
import com.demo.stockex.domain.User;
import com.demo.stockex.services.ShareService;
import com.demo.stockex.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ShareWebController {

    private final SharesRestController sharesRestController;
    private final UserService userService;
    private final ShareService shareService;

    @GetMapping({"/buy"})
    public String getBuyPage(Model model) {
        model.addAttribute("symbols", Symbols.values());
        model.addAttribute("shareToBuy", new Share());
        model.addAttribute("shareWithPrice", new Share());
        return "/buy";
    }

    @PostMapping({"/buy"})
    public String showPrice(@ModelAttribute Share share, Model model) {
        Share shareWithPrice = sharesRestController.getShareFromIEX(share.getSymbol());
        model.addAttribute("symbols", Symbols.values());
        model.addAttribute("shareToBuy", new Share());
        model.addAttribute("shareWithPrice", shareWithPrice);
        return "/buy";
    }

    @PostMapping("/buy/chosenShare")
    public String addShareToUser(@ModelAttribute Share shareWithPrice, Principal principal) {
        User requestedUser = (User) userService.loadUserByUsername(principal.getName());
        shareService.buy(shareWithPrice, requestedUser);
        return "redirect:/user/show";
    }

    @GetMapping("/update")
    public String updateSharePrice(Principal principal ,Model model) {
        User requestedUser = (User) userService.loadUserByUsername(principal.getName());
        updateShares(requestedUser);
        double totalCostOfUserShares = 0;
        for (Share share : requestedUser.getShares().values()) {
            totalCostOfUserShares += share.getTotalCost();
        }
        model.addAttribute("updatedListOfShares", requestedUser.getShares().values());
        model.addAttribute("totalCostOfUserShares", totalCostOfUserShares);
        return "updatePrice :: shareList";
    }

    @GetMapping("/sell")
    public String getSellPage(Model model, Principal principal) {
        User requestedUser = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("user", requestedUser);
        model.addAttribute("shareToSell", new Share());
        return "sell";
    }

    @PostMapping("/sell/chosenShare")
    public String sellShare(@ModelAttribute Share shareToSell, Principal principal) {
        User requestedUser = (User) userService.loadUserByUsername(principal.getName());
        shareService.sell(requestedUser, shareToSell.getSymbol(), shareToSell.getAmount());
        return "redirect:/user/show";
    }

    private void updateShares(User user) {
        user.getShares().values().forEach(share -> {
            share.setPrice(sharesRestController.getSharePrice(share));
            shareService.save(share);
        });
    }
}
