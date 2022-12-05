package net.leontibrechko.congratulationsgenerator.factory.openai;

public class PromptFactory {
    public String createCongratsPrompt(final String recipientName,
                                       final String messageLanguage,
                                       final String occasion,
                                       final String relation) {
        return String.format("Create a congratulatory message for %s on %s in %s. The relation is %s to me",
                recipientName, occasion, messageLanguage, relation);
    }
}
