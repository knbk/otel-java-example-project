This project reproduces the bug in https://github.com/open-telemetry/opentelemetry-java-instrumentation/issues/10017.

# Usage

Run

```shell
gradle app:run
```

* The `MuzzleMatcher` will warn that opentelemetry-api v1_31 has mismatched references.
* The helper class `ApplicationMeterFactory131` cannot be found in the application class loader.
* The logged metric export for `test` will have the default histogram buckets.

Now enable the `io.opentelemetry:opentelemetry-sdk-extension-incubator:1.32.0-alpha` dependency in `app/build.gradle`
and run again. The explicit bucket boundary advice will now work as expected.
