package io.github.cdm.apigateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/login",
            "/auth/validate",
            "/auth/reset-password",

            "/api/v1/products/getAllCars",
            "/api/v1/products/getCarById",
            "/api/v1/products/getCarByModel",
            "/api/v1/products/getCarsByNameContains",

            "/api/v1/products/getAllEnergies",
            "/api/v1/products/getEnergyById",
            "/api/v1/products/getEnergyByNameOrderedByPriceDesc",
            "/api/v1/products/getEnergyByNameContains",

            "/api/v1/products/getAllShops",
            "/api/v1/products/getShopById",
            "/api/v1/products/getShopByNameOrderedByPriceDesc",
            "/api/v1/products/getShopByType",
            "/api/v1/products/getShopByNameContains",

//            "/api/v1/inventory/getInventory",

            "/api/v1/energy/getAllEnergy",
            "/api/v1/energy/getEnergyById",
            "/api/v1/energy/getAllEnergy",

            "/api/payment/create_payment",
            "/api/payment/vnpay_return",

            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
