package sn.zeitune.olive_insurance_pricing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OliveInsurancePricingApplication {

    public static void main(String[] args) {
        SpringApplication.run(OliveInsurancePricingApplication.class, args);
    }

}