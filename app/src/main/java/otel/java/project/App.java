/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package otel.java.project;


import java.util.Arrays;
import java.util.List;
import java.util.Random;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.metrics.DoubleHistogram;

public class App {
    static List<Double> buckets = Arrays.asList(0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0);
    static DoubleHistogram histogram = GlobalOpenTelemetry.getMeter("test").histogramBuilder("test").setExplicitBucketBoundariesAdvice(buckets).build();

    static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        App app = new App();

        histogram.record(random.nextDouble());
        if (app.findHelperClass()) {
            System.out.println("Helper class is present");
        } else {
            System.out.println("Helper class is not present");
        }
        // Wait for metrics to be exported
        Thread.sleep(10_000);
    }

    public String getGreeting() {
        return "Hello, world!";
    }

    public boolean findHelperClass() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            System.out.println("Class loader: " + classLoader);
            Class<?> clazz = classLoader.loadClass("io.opentelemetry.javaagent.instrumentation.opentelemetryapi.v1_31.metrics.ApplicationMeterFactory131");
            return clazz != null;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
