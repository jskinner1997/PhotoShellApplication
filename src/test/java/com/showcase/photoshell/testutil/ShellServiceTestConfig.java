package com.showcase.photoshell.testutil;

import com.showcase.photoshell.commands.ApplicationCommand;
import com.showcase.photoshell.service.ShellService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShellServiceTestConfig {

    @Bean
    ShellService shellService(){
        return new ShellService();
    }

    @Bean
    ApplicationCommand applicationCommand() {
        return new ApplicationCommand(shellService());
    }
}
