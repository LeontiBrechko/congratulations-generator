package net.leontibrechko.congratulationsgenerator.config;

import net.leontibrechko.congratulationsgenerator.generator.CongratsGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneratorConfig {
    private final OpenAIConfiguration openAIConfiguration;

    public GeneratorConfig(final OpenAIConfiguration openAIConfiguration) {
        this.openAIConfiguration = openAIConfiguration;
    }

    @Bean
    public CongratsGenerator congratsGenerator() {
        return new CongratsGenerator(
                openAIConfiguration.promptFactory(),
                openAIConfiguration.completionRequestFactory(),
                openAIConfiguration.openAiService()
        );
    }
}
