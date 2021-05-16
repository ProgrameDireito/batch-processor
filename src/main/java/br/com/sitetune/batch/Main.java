package br.com.sitetune.batch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Path filePath = Path.of("itens.csv");

        Consumer<Item> itemConsumer = item -> {
            System.out.println("item = " + item);
        };

        Consumer<List<Item>> itemListConsumer = itemList -> {
            itemList.stream().forEach(itemConsumer);
            //you can save this list to a Database
        };

        try {
            final int batchSize = 100;
            Stream<String> lines = Files.lines(filePath);
            lines.map((line) -> {
                //For simplicity, we will omit some values checks
                String[] split = line.split(",");
                return new Item(Long.valueOf(split[0]), split[1],
                        Integer.valueOf(split[2]), Double.valueOf(split[3]));
            }).collect(StreamUtils.batchCollector(batchSize, itemListConsumer));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
