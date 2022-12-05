package net.leontibrechko.congratulationsgenerator.config;

import com.theokanning.openai.OpenAiService;
import net.leontibrechko.congratulationsgenerator.factory.openai.CompletionRequestFactory;
import net.leontibrechko.congratulationsgenerator.factory.openai.PromptFactory;
import net.leontibrechko.congratulationsgenerator.security.KeyStoreSecretName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIConfiguration {
    private final KeyStoreConfiguration keyStoreConfiguration;

    public OpenAIConfiguration(final KeyStoreConfiguration keyStoreConfiguration) {
        this.keyStoreConfiguration = keyStoreConfiguration;
    }

    @Bean
    public OpenAiService openAiService() {
        return new OpenAiService(
                keyStoreConfiguration.localKeyStore().loadSecret(KeyStoreSecretName.OPEN_AI_API_KEY),
                300
        );
    }

    @Bean
    public PromptFactory promptFactory() {
        return new PromptFactory();
    }

    @Bean
    public CompletionRequestFactory completionRequestFactory() {
        return new CompletionRequestFactory(
                "text-davinci-003",
                0.25,
                1024
        );
    }
}
