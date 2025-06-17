package com.refindspppl.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * Test Runner untuk menjalankan semua scenario di UserFlow.feature
 */
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/com/refindspppl/features",
    glue = {"com.refindspppl.stepdefinitions"},
    plugin = {"pretty", "html:target/cucumber-report.html"},
    monochrome = true
)
public class TestRunner {
    // Tidak perlu menulis kode apapun di sini
}
