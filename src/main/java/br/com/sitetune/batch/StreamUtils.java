/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sitetune.batch;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collector;

/**
 *
 * @author marcos
 */
public class StreamUtils {

    /**
     * Creates a new batch collector
     *
     * @param batchSize the batch size after which the batchProcessor should be
     * called
     * @param batchProcessor the batch processor which accepts batches of
     * records to process
     * @param <T> the type of elements being processed
     * @return a batch collector instance
     */
    public static <T> Collector<T, List<T>, List<T>> batchCollector(int batchSize, Consumer<List<T>> batchProcessor) {
        return new BatchCollector<T>(batchSize, batchProcessor);
    }

}
