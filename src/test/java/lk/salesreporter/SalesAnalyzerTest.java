package lk.salesreporter;

import lk.salesreporter.model.Product;
import lk.salesreporter.service.SalesAnalyzer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SalesAnalyzerTest {

    private final SalesAnalyzer analyzer = new SalesAnalyzer();

    @Test
    public void testRevenueCalculation() {
        Product product = new Product(
                "P001", "Wireless Mouse", "Electronics", 12, 25.50);

        assertEquals(306.00, product.getRevenue(), 0.001);
    }

    @Test
    public void testBestSellingProduct() {
        Product mouse = new Product(
                "P001", "Wireless Mouse", "Electronics", 12, 25.50);
        Product pen = new Product(
                "P004", "Ballpoint Pen", "Stationery", 100, 0.50);

        Product result = analyzer.findBestSellingProduct(
                Arrays.asList(mouse, pen));

        assertNotNull(result);
        assertEquals("Ballpoint Pen", result.getProductName());
        assertEquals(100, result.getQuantitySold());
    }

    @Test
    public void testHighestRevenueProduct() {
        Product mouse = new Product(
                "P001", "Wireless Mouse", "Electronics", 12, 25.50);
        Product pen = new Product(
                "P004", "Ballpoint Pen", "Stationery", 100, 0.50);

        Product result = analyzer.findHighestRevenueProduct(
                Arrays.asList(mouse, pen));

        assertNotNull(result);
        assertEquals("Wireless Mouse", result.getProductName());
    }

    @Test
    public void testGrandTotalRevenue() {
        Product p1 = new Product("P001", "Product 1", "A", 10, 10.00);
        Product p2 = new Product("P002", "Product 2", "B", 5, 20.00);

        double total = analyzer.calculateGrandTotalRevenue(
                Arrays.asList(p1, p2));

        assertEquals(200.00, total, 0.001);
    }

    @Test
    public void testRevenueByCategory() {
        Product p1 = new Product("P001", "Product 1", "Electronics", 10, 10.00);
        Product p2 = new Product("P002", "Product 2", "Electronics", 5, 20.00);
        Product p3 = new Product("P003", "Product 3", "Stationery", 10, 2.00);

        var result = analyzer.calculateRevenueByCategory(
                Arrays.asList(p1, p2, p3));

        assertEquals(200.00, result.get("Electronics"), 0.001);
        assertEquals(20.00, result.get("Stationery"), 0.001);
    }
}
