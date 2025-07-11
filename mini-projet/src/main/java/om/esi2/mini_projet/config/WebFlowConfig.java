package om.esi2.mini_projet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.config.FlowBuilderServicesBuilder;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.config.FlowDefinitionRegistryBuilder;
import org.springframework.webflow.config.FlowExecutorBuilder;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import om.esi2.mini_projet.services.DeclarationService;

import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.Collections;

@Configuration
public class WebFlowConfig {

    @Autowired
    private ApplicationContext applicationContext;

    // Définition manuelle du template resolver
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCacheable(false);
        return resolver;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine);
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    // WebFlow : services de construction
    @Bean
    public FlowBuilderServices flowBuilderServices(SpringTemplateEngine templateEngine) {
        MvcViewFactoryCreator viewFactoryCreator = new MvcViewFactoryCreator();
        viewFactoryCreator.setViewResolvers(Collections.singletonList(thymeleafViewResolver(templateEngine)));
        viewFactoryCreator.setUseSpringBeanBinding(true);

        return new FlowBuilderServicesBuilder()
                .setViewFactoryCreator(viewFactoryCreator)
                .setDevelopmentMode(true)
                .build();
    }

    // WebFlow : registre des flux
    @Bean
    public FlowDefinitionRegistry flowDefinitionRegistry(FlowBuilderServices flowBuilderServices) {
        return new FlowDefinitionRegistryBuilder(applicationContext, flowBuilderServices)
                .addFlowLocation("classpath:/flows/declarationFrais-flow.xml", "declarationFrais")
                .build();
    }

    // WebFlow : exécuteur de flux
    @Bean
    public FlowExecutor flowExecutor(FlowDefinitionRegistry flowRegistry) {
        return new FlowExecutorBuilder(flowRegistry)
                .setAlwaysRedirectOnPause(true)
                .build();
    }

    // Mapping handler pour les flux WebFlow
    @Bean
    public FlowHandlerMapping flowHandlerMapping(FlowDefinitionRegistry flowRegistry) {
        FlowHandlerMapping mapping = new FlowHandlerMapping();
        mapping.setOrder(-1);
        mapping.setFlowRegistry(flowRegistry);
        return mapping;
    }

    // Adaptateur WebFlow
    @Bean
    public FlowHandlerAdapter flowHandlerAdapter(FlowExecutor flowExecutor) {
        FlowHandlerAdapter adapter = new FlowHandlerAdapter();
        adapter.setFlowExecutor(flowExecutor);
        return adapter;
    }
}
