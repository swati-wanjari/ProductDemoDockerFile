package com.smarbl;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Product Specification",
                version = "1.0.0",
                description = "Open API Documentation",
                termsOfService = "run code now",
                contact = @Contact(
                        name = "swati",
                        email = "swati.wanjari@smarbl.com"
                ),
                license = @License(
                        name = "smarbl",
                        url = "jhfvgyufgd"
                )
        )

)

public class ProductDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductDemoApplication.class, args);
    }

}
