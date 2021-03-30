package Runners;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.sorting.SortingMethod;

@CucumberOptions(

		features = "src/test/resources/Features", 
		glue = {"StepsDefinations","Hooks"}, 
		tags = "@Sanity",

		plugin = { "json:target/cucumber.json", "json:target/cucumber.json"}, 
		//"de.monochromata.cucumber.report.PrettyReports:target/cucumber-report"
		dryRun = false, 
		monochrome = true
		)

public class TestRunner extends AbstractTestNGCucumberTests  {
	
	    @Override
	    @DataProvider(parallel = true)
	    public Object[][] scenarios() {
	        return super.scenarios();
	    }
	    
	    
		@AfterClass
		public void tearDown() {
			File reportOutputDirectory = new File("target/Cucumber-html-Report");
	        List<String> jsonFiles = new ArrayList<>();
	        jsonFiles.add("target/cucumber-report/cucumber.json");

	        String buildNumber = "V.0.1";
	        String projectName = "RIB 5D_Institut Darwin Web Application ";
	        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
	        configuration.setBuildNumber(buildNumber);
	        configuration.addClassifications("Envirnment", "UAT Test");
	        configuration.addClassifications("Browser", "Chrome");
	        configuration.addClassifications("Branch", "release/1.0");
	        configuration.setSortingMethod(SortingMethod.NATURAL);
	        configuration.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);
	        configuration.addPresentationModes(PresentationMode.PARALLEL_TESTING);
	        // points to the demo trends which is not used for other tests
	        configuration.setTrendsStatsFile(new File("target/test-classes/demo-trends.json"));

	        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
	        reportBuilder.generateReports();
		}

}
