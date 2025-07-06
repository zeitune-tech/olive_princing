package sn.zeitune.olive_insurance_pricing.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/pricing")
public class PricingController {

    @GetMapping("/health")
    public String health() {
        return "Olive Insurance Pricing Service is running!";
    }

    @GetMapping("/info")
    public String info() {
        return "Olive Insurance Pricing Service - Version 1.0.0";
    }
}
