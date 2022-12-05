package net.leontibrechko.congratulationsgenerator.generator;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import lombok.extern.slf4j.Slf4j;
import net.leontibrechko.congratulationsgenerator.factory.openai.CompletionRequestFactory;
import net.leontibrechko.congratulationsgenerator.factory.openai.PromptFactory;

import java.util.stream.Collectors;

@Slf4j
public class CongratsGenerator {
    private final PromptFactory promptFactory;
    private final CompletionRequestFactory completionRequestFactory;
    private final OpenAiService openAiService;

    public CongratsGenerator(final PromptFactory promptFactory,
                             final CompletionRequestFactory completionRequestFactory,
                             final OpenAiService openAiService) {
        this.promptFactory = promptFactory;
        this.completionRequestFactory = completionRequestFactory;
        this.openAiService = openAiService;
    }

    public String generateCongrats(final String recipientName,
                                   final String messageLanguage,
                                   final String occasion,
                                   final String relation) {
        final String prompt = promptFactory.createCongratsPrompt(recipientName, messageLanguage, occasion, relation);
        final CompletionRequest completionRequest = completionRequestFactory.createCompletionRequest(prompt);
        final CompletionResult completionResult = openAiService.createCompletion(completionRequest);

        log.debug("Usage for prompt:\n{}\n{}", prompt, completionResult.getUsage());

        return completionResult.getChoices()
                .stream()
                .map(CompletionChoice::getText)
                .collect(Collectors.joining("\n"));
    }
}
