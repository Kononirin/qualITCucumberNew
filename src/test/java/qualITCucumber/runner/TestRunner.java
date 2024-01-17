package qualITCucumber.runner;

import org.junit.platform.suite.api.*;

import static io.cucumber.core.options.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameters({
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/features/"),
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.example"),
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
})
public class TestRunner {
}
