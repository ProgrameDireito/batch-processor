package br.com.sitetune.batch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author marcos
 */
public class Main {

    public static void main(String[] args) {
        Path filePath = Path.of("itens.csv");
        Path ap = filePath.toAbsolutePath();
        System.out.println("ap = " + ap);

        Consumer<Item> itemConsumer = item -> {
//            System.out.println("item = " + item);
        };

        Consumer<List<Item>> itemListConsumer = itemList -> {
            itemList.stream().forEach(itemConsumer);
        };

        try {
            Stream<String> lines = Files.lines(filePath);
            final int batchSize = 1000;
            lines.map((line) -> {
                String[] split = line.split(",");
                return new Item(Long.valueOf(split[0]), split[1],
                        Integer.valueOf(split[2]), Double.valueOf(split[3]));
            }).collect(StreamUtils.batchCollector(batchSize, itemListConsumer));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
