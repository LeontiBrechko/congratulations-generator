package net.leontibrechko.congratulationsgenerator.runner;

import lombok.extern.slf4j.Slf4j;
import net.leontibrechko.congratulationsgenerator.config.GeneratorConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommandLine implements CommandLineRunner {
    private final GeneratorConfig generatorConfig;

    public CommandLine(final GeneratorConfig generatorConfig) {
        this.generatorConfig = generatorConfig;
    }

    @Override
    public void run(String... args) {
        final String result = generatorConfig.congratsGenerator()
                .generateCongrats(
                        "Яна",
                        "belarusian",
                        "birthday",
                        "wife");

        log.info(result);
    }
}
