package lk.salesreporter.io;

import lk.salesreporter.model.Product;
import lk.salesreporter.service.SalesAnalyzer;
import java.util.List;
import java.util.Map;

public class ReportGenerator {
    private final SalesAnalyzer analyzer;

    public ReportGenerator(SalesAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    public String generateReport(List<Product> products) {
        StringBuilder report = new StringBuilder();

        report.append("============================================\n");
        report.append("       PRODUCT SALES SUMMARY REPORT\n");
        report.append("============================================\n");
        report.append("--- Revenue Per Product ---\n");

        for (Product product : products) {
            report.append(String.format("%s %s %s $%.2f%n",
                    product.getProductId(),
                    product.getProductName(),
                    product.getCategory(),
                    product.getRevenue()));
        }

        report.append("--- Revenue Per Category ---\n");
        Map<String, Double> categoryRevenue =
                analyzer.calculateRevenueByCategory(products);

        for (Map.Entry<String, Double> entry : categoryRevenue.entrySet()) {
            report.append(String.format("%s : $%.2f%n",
                    entry.getKey(), entry.getValue()));
        }

        report.append("--- Highlights ---\n");

        Product bestSeller = analyzer.findBestSellingProduct(products);
        Product highestRevenue = analyzer.findHighestRevenueProduct(products);

        if (bestSeller != null) {
            report.append(String.format("Best-Selling Product : %s (%d units)%n",
                    bestSeller.getProductName(), bestSeller.getQuantitySold()));
        }

        if (highestRevenue != null) {
            report.append(String.format("Highest Revenue : %s ($%.2f)%n",
                    highestRevenue.getProductName(), highestRevenue.getRevenue()));
        }

        report.append(String.format("Grand Total Revenue : $%.2f%n",
                analyzer.calculateGrandTotalRevenue(products)));
        report.append("============================================\n");

        return report.toString();
    }
}


