package lk.salesreporter.output;

import java.io.FileWriter;
import java.io.IOException;

public class FileOutput implements OutputStrategy {
    private final String filePath;

    public FileOutput(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void output(String report) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(report);
        }
    }
}
