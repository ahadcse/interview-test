package com.interview.practice;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorPractice {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };
        task.run();
        Thread thread = new Thread(task);
        thread.start();
        System.out.println("Done!");
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(task);

        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (!executor.isShutdown()) {
                executor.shutdownNow();
            }
        }

        Callable<String> task1 = () -> {
            return "H";
        };

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> future = executorService.submit(task1);

        if (future.isDone()) {
            try {
                String s = future.get(1, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                throw new IllegalStateException("Task interrupted", e);
            }
        }
    }

    public void tryInvokeAll() throws InterruptedException {
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                () -> "task1",
                () -> "task2",
                () -> "task3");

        executor.invokeAll(callables)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .forEach(System.out::println);
    }

    public void tryInvokeAny() {
        ExecutorService executorService = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
            callable("task1", 1),
            callable("task2", 2),
            callable("task3", 3)
        );
        try {
            executorService.invokeAny(callables);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    Callable<String> callable(String taskName, long sleepDurationInseconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepDurationInseconds);
            return taskName;
        };
    }

    public void tryScheduledExecutor() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        Runnable task1 = () -> System.out.println("Scheduling: " + System.nanoTime());
        executorService.scheduleAtFixedRate(task1, 0, 1, TimeUnit.SECONDS); //Fixed time rate for task

        Runnable task2 = () -> {
          try {
              TimeUnit.SECONDS.sleep(2);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
        };
        /* Schedules a task with a fixed delay of one second between the end of an execution
        and the start of the next execution. Interval 0, 3(2+1), 6, 9.... sec*/
        executorService.scheduleWithFixedDelay(task2, 0, 1, TimeUnit.SECONDS);
    }

}
