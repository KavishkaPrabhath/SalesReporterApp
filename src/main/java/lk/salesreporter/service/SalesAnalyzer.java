package lk.salesreporter.service;

import lk.salesreporter.model.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesAnalyzer {

    public double calculateGrandTotalRevenue(List<Product> products) {
        double total = 0.0;
        for (Product product : products) {
            total += product.getRevenue();
        }
        return total;
    }

    public Map<String, Double> calculateRevenueByCategory(List<Product> products) {
        Map<String, Double> categoryRevenue = new HashMap<>();
        for (Product product : products) {
            String category = product.getCategory();
            categoryRevenue.put(category,
                    categoryRevenue.getOrDefault(category, 0.0) + product.getRevenue());
        }
        return categoryRevenue;
    }

    public Product findBestSellingProduct(List<Product> products) {
        if (products == null || products.isEmpty()) return null;
        Product bestSeller = products.get(0);
        for (Product product : products) {
            if (product.getQuantitySold() > bestSeller.getQuantitySold()) {
                bestSeller = product;
            }
        }
        return bestSeller;
    }

    public Product findHighestRevenueProduct(List<Product> products) {
        if (products == null || products.isEmpty()) return null;
        Product highestRevenue = products.get(0);
        for (Product product : products) {
            if (product.getRevenue() > highestRevenue.getRevenue()) {
                highestRevenue = product;
            }
        }
        return highestRevenue;
    }
}
