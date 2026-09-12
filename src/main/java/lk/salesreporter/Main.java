package lk.salesreporter;

import lk.salesreporter.io.CsvReader;
import lk.salesreporter.io.ReportGenerator;
import lk.salesreporter.model.Product;
import lk.salesreporter.output.ConsoleOutput;
import lk.salesreporter.output.FileOutput;
import lk.salesreporter.output.OutputStrategy;
import lk.salesreporter.service.SalesAnalyzer;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if (args.length < 2 || args.length > 3) {
            printUsage();
            return;
        }

        String csvFilePath = args[0];
        String outputMethod = args[1].toLowerCase();

        if (outputMethod.equals("file") && args.length != 3) {
            System.out.println("Error: Output file path is required when output method is 'file'.");
            printUsage();
            return;
        }

        if (outputMethod.equals("console") && args.length != 2) {
            System.out.println("Error: Console output does not require an output file path.");
            printUsage();
            return;
        }

        try {
            CsvReader csvReader = new CsvReader();
            List<Product> products = csvReader.readProducts(csvFilePath);

            if (products.isEmpty()) {
                System.out.println("Error: CSV file contains no product data.");
                return;
            }

            SalesAnalyzer analyzer = new SalesAnalyzer();
            ReportGenerator reportGenerator = new ReportGenerator(analyzer);
            String report = reportGenerator.generateReport(products);

            OutputStrategy outputStrategy;

            if (outputMethod.equals("console")) {
                outputStrategy = new ConsoleOutput();
            } else if (outputMethod.equals("file")) {
                outputStrategy = new FileOutput(args[2]);
            } else {
                System.out.println("Error: Invalid output method '" + outputMethod + "'.");
                System.out.println("Use 'console' or 'file'.");
                return;
            }

            outputStrategy.output(report);

        } catch (IOException e) {
            System.out.println("Error reading or writing file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void printUsage() {
        System.out.println();
        System.out.println("Usage:");
        System.out.println("java SalesReporter <csv-file-path> <output-method> [output-file-path]");
        System.out.println();
        System.out.println("Output methods:");
        System.out.println("console - Display report on console");
        System.out.println("file    - Save report to a text file");
        System.out.println();
    }
}
