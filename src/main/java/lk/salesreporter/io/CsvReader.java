package lk.salesreporter.io;

import lk.salesreporter.model.Product;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public List<Product> readProducts(String filePath) throws IOException {
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (firstLine) {
                    firstLine = false;
                    if (line.toLowerCase().startsWith("product_id")) continue;
                }

                String[] values = line.split(",");
                if (values.length != 5) {
                    throw new IOException("Invalid CSV row. Expected 5 columns: " + line);
                }

                try {
                    products.add(new Product(
                            values[0].trim(),
                            values[1].trim(),
                            values[2].trim(),
                            Integer.parseInt(values[3].trim()),
                            Double.parseDouble(values[4].trim())
                    ));
                } catch (NumberFormatException e) {
                    throw new IOException("Invalid numeric value in row: " + line);
                }
            }
        }

        return products;
    }
}

