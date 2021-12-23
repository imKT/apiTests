package com.api.runner;

import com.api.steps.EmailSteps;
import io.tapack.allure.jbehave.AllureReporter;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterControls;

import java.util.List;

public class ApiTestRunner extends JUnitStories {
    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(CodeLocations.codeLocationFromClass(this.getClass()),"**/stories/**/*.story","**/stories/exclude/*.story");
    }

    @Override
    public Configuration configuration() {

        return new MostUsefulConfiguration()
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withDefaultFormats()
                        .withFormats(Format.HTML,Format.TXT,Format.CONSOLE,Format.XML)
                        .withReporters(new AllureReporter())
                        )
                .useStoryControls(new StoryControls().doResetStateBeforeStory(true))
                .useParameterControls(new ParameterControls().useDelimiterNamedParameters(true));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new EmailSteps());
    }
}
