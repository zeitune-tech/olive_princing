package sn.zeitune.olive_insurance_pricing.app.initializer;

import lombok.RequiredArgsConstructor;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sn.zeitune.olive_insurance_pricing.app.dtos.requests.ConstantRequestDTO;
import sn.zeitune.olive_insurance_pricing.app.services.ConstantService;

@Component
@RequiredArgsConstructor
public class AppInitializer implements CommandLineRunner {

    private final ConstantService constantService;


    @Override
    public void run(String... args) {

        try {

            Expression expression = new ExpressionBuilder("x + y * 3 +").variables("x", "y").build();
            System.out.println("Expression valide");
        } catch (Exception e) {
            System.out.println("Expression invalide : " + e.getMessage());
        }

        try {

            Expression expression = new ExpressionBuilder("3+2").build();
            double result = expression.evaluate();
            System.out.println("Result is: " + result);

        } catch (Exception e) {
            // Handle exception
            System.err.println("Error during initialization: " + e.getMessage());
        }
    }
}