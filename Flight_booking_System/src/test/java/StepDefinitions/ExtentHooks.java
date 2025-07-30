package StepDefinitions;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.*;

public class ExtentHooks {
    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeAll
    public static void setUpReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("target/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @Before
    public void createTest(Scenario scenario) {
        test = extent.createTest(scenario.getName());
    }

    @After
    public void logResult(Scenario scenario) {
        if (scenario.isFailed()) test.fail("Failed");
        else test.pass("Passed");
    }

    @AfterAll
    public static void tearDownReport() {
        extent.flush();
    }
}
