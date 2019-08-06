package com.example.Auction.controllers;

import com.example.Auction.domain.Auction;
import com.example.Auction.domain.AuctionStatus;
import com.example.Auction.domain.User;
import com.example.Auction.repos.AuctionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Controller
public class CreateAuctionController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private AuctionRepo auctionRepo;

    @GetMapping("/createAuction")
    public String createAuction(Model model) {
        return "createAuction";
    }

    @PostMapping("/createAuction")
    public String addAuction(
            @AuthenticationPrincipal User user,
            @Valid Auction auction,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file) throws IOException {
        auction.setOwner(user);
        System.out.println(auction.isPrivateAuction());

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorMap);
            model.addAttribute("auction", auction);
            return "createAuction";
        } else {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFileName = UUID.randomUUID().toString();
                String resultFileName = uuidFileName + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFileName));

                auction.setFilename(resultFileName);
            }
            auction.setAuctionStatus(AuctionStatus.ASSIGNED);
            auction.setBeginningAuctionTime(LocalDateTime.now().plusMinutes(1));
            auction.setPrivateAuction(false);
            auctionRepo.save(auction);

            model.addAttribute("auctions", auctionRepo.findAll());
            return "redirect:/main";
        }
    }
}
