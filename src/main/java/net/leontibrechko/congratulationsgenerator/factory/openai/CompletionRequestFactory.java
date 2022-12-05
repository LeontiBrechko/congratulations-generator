package net.leontibrechko.congratulationsgenerator.factory.openai;

import com.theokanning.openai.completion.CompletionRequest;

public class CompletionRequestFactory {
    private final String aiModel;
    private final double aiTemperature;
    private final int aiMaxTokensUsage;

    public CompletionRequestFactory(final String aiModel,
                                    final double aiTemperature,
                                    final int aiMaxTokensUsage) {
        this.aiModel = aiModel;
        this.aiTemperature = aiTemperature;
        this.aiMaxTokensUsage = aiMaxTokensUsage;
    }

    public CompletionRequest createCompletionRequest(final String prompt) {
        return CompletionRequest.builder()
                .prompt(prompt)
                .model(aiModel)
                .temperature(aiTemperature)
                .maxTokens(aiMaxTokensUsage)
                .build();
    }
}
