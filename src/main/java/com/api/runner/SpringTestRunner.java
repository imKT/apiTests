package com.api.runner;

import com.api.config.SpringConfig;
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
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

//@RunWith(JUnitReportingRunner.class)
public class SpringTestRunner extends JUnitStories {
    private ApplicationContext appContext = getApplicationContext();

    public ApplicationContext getApplicationContext(){
        return new AnnotationConfigApplicationContext(SpringConfig.class);
    }

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
        return new SpringStepsFactory(this.configuration(), appContext);
    }
}
