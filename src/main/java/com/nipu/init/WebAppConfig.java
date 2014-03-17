package com.nipu.init;

import com.google.common.collect.Lists;
import com.nipu.service.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
import pl.matisoft.soy.SoyTemplateViewResolver;
import pl.matisoft.soy.ajax.SoyAjaxController;
import pl.matisoft.soy.ajax.auth.AuthManager;
import pl.matisoft.soy.ajax.auth.ConfigurableAuthManager;
import pl.matisoft.soy.ajax.hash.HashFileGenerator;
import pl.matisoft.soy.ajax.hash.MD5HashFileGenerator;
import pl.matisoft.soy.ajax.process.OutputProcessor;
import pl.matisoft.soy.ajax.process.google.GoogleClosureOutputProcessor;
import pl.matisoft.soy.ajax.url.DefaultTemplateUrlComposer;
import pl.matisoft.soy.ajax.url.TemplateUrlComposer;
import pl.matisoft.soy.bundle.EmptySoyMsgBundleResolver;
import pl.matisoft.soy.compile.DefaultTofuCompiler;
import pl.matisoft.soy.compile.TofuCompiler;
import pl.matisoft.soy.data.DefaultToSoyDataConverter;
import pl.matisoft.soy.data.ToSoyDataConverter;
import pl.matisoft.soy.global.compile.CompileTimeGlobalModelResolver;
import pl.matisoft.soy.global.compile.EmptyCompileTimeGlobalModelResolver;
import pl.matisoft.soy.global.runtime.DefaultGlobalModelResolver;
import pl.matisoft.soy.global.runtime.GlobalModelResolver;
import pl.matisoft.soy.global.runtime.resolvers.*;
import pl.matisoft.soy.holder.CompiledTemplatesHolder;
import pl.matisoft.soy.holder.DefaultCompiledTemplatesHolder;
import pl.matisoft.soy.locale.LocaleProvider;
import pl.matisoft.soy.locale.SpringLocaleProvider;
import pl.matisoft.soy.render.DefaultTemplateRenderer;
import pl.matisoft.soy.render.TemplateRenderer;
import pl.matisoft.soy.template.DefaultTemplateFilesResolver;


@Configuration
@EnableWebMvc
@ComponentScan("com.nipu.controller")
public class WebAppConfig extends WebMvcConfigurerAdapter
{

    @Autowired
    private javax.servlet.ServletContext context;

    private boolean debugOn = true;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DefaultTemplateFilesResolver templateFilesResolver() throws Exception
    {
        final DefaultTemplateFilesResolver defaultTemplateFilesResolver = new DefaultTemplateFilesResolver();
        //defaultTemplateFilesResolver.setDebugOn(debugOn);
        defaultTemplateFilesResolver.setRecursive(true);
        defaultTemplateFilesResolver.setServletContext(context);
        defaultTemplateFilesResolver.afterPropertiesSet();

        return defaultTemplateFilesResolver;
    }

    @Bean
    public CompileTimeGlobalModelResolver compileTimeGlobalModelResolver()
    {
        return new EmptyCompileTimeGlobalModelResolver();
    }

    @Bean
    public GlobalModelResolver globalModelResolver()
    {
        final DefaultGlobalModelResolver defaultGlobalModelResolver = new DefaultGlobalModelResolver();
        defaultGlobalModelResolver.setResolvers(Lists.newArrayList(
                new RequestParametersResolver(),
                new RequestHeadersResolver(),
                new CookieResolver(),
                requestContextResolver(),
                new HttpSessionResolver(),
                webApplicationContextResolver(),
                servletContextResolver()
        ));

        return defaultGlobalModelResolver;
    }

    @Bean
    public ServletContextResolver servletContextResolver()
    {
        return new ServletContextResolver();
    }

    @Bean
    public WebApplicationContextResolver webApplicationContextResolver()
    {
        return new WebApplicationContextResolver();
    }

    @Bean
    public RequestContextResolver requestContextResolver()
    {
        return new RequestContextResolver();
    }

    @Bean
    public LocaleProvider localeProvider()
    {
        return new SpringLocaleProvider();
    }

    @Bean
    public EmptySoyMsgBundleResolver soyMsgBundleResolver()
    {
        return new EmptySoyMsgBundleResolver();
    }

    @Bean
    public ToSoyDataConverter toSoyDataConverter()
    {
        return new DefaultToSoyDataConverter();
    }

    public TemplateRenderer templateRenderer()
    {
        final DefaultTemplateRenderer defaultTemplateRenderer = new DefaultTemplateRenderer();
        defaultTemplateRenderer.setToSoyDataConverter(toSoyDataConverter());
        defaultTemplateRenderer.setDebugOn(debugOn);

        return defaultTemplateRenderer;
    }

    @Bean
    public TofuCompiler tofuCompiler()
    {
        final DefaultTofuCompiler defaultTofuCompiler = new DefaultTofuCompiler();
        defaultTofuCompiler.setCompileTimeGlobalModelResolver(compileTimeGlobalModelResolver());
        defaultTofuCompiler.setDebugOn(debugOn);

        return defaultTofuCompiler;
    }

    @Bean
    public ViewResolver viewResolver() throws Exception
    {
        final SoyTemplateViewResolver soyTemplateViewResolver = new SoyTemplateViewResolver();
        soyTemplateViewResolver.setDebugOn(debugOn);
        soyTemplateViewResolver.setTemplateRenderer(templateRenderer());
        soyTemplateViewResolver.setGlobalModelResolver(globalModelResolver());
        soyTemplateViewResolver.setLocaleProvider(localeProvider());
        soyTemplateViewResolver.setSoyMsgBundleResolver(soyMsgBundleResolver());
        soyTemplateViewResolver.setCompiledTemplatesHolder(compiledTemplatesHolder());

        return soyTemplateViewResolver;
    }

    @Bean
    public CompiledTemplatesHolder compiledTemplatesHolder() throws Exception
    {
        final DefaultCompiledTemplatesHolder defaultCompiledTemplatesHolder = new DefaultCompiledTemplatesHolder();
        defaultCompiledTemplatesHolder.setDebugOn(debugOn);
        defaultCompiledTemplatesHolder.setTemplatesFileResolver(templateFilesResolver());
        defaultCompiledTemplatesHolder.setTofuCompiler(tofuCompiler());

        return defaultCompiledTemplatesHolder;
    }

    @Bean
    public OutputProcessor closureCompilerProcessor()
    {
        return new GoogleClosureOutputProcessor();
    }

    @Bean
    public ViewResolver contentNegotiatingViewResolver() throws Exception
    {
        final ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
        contentNegotiatingViewResolver.setViewResolvers(Lists.newArrayList(viewResolver()));
        contentNegotiatingViewResolver.setDefaultViews(Lists.<View>newArrayList(new MappingJacksonJsonView()));

        return contentNegotiatingViewResolver;
    }

    @Bean
    public TemplateUrlComposer templateUrlComposer() throws Exception
    {
        final DefaultTemplateUrlComposer urlComposer = new DefaultTemplateUrlComposer();
        urlComposer.setSiteUrl(Constants.SITE_URL);
        urlComposer.setTemplateFilesResolver(templateFilesResolver());
        urlComposer.setHashFileGenerator(hashFileGenerator());

        return urlComposer;
    }

    @Bean
    public HashFileGenerator hashFileGenerator()
    {
        final MD5HashFileGenerator md5HashFileGenerator = new MD5HashFileGenerator();
        md5HashFileGenerator.setDebugOn(debugOn);
        md5HashFileGenerator.afterPropertiesSet();

        return md5HashFileGenerator;
    }

    @Bean
    public SoyAjaxController soyAjaxController() throws Exception
    {
        final SoyAjaxController soyAjaxController = new SoyAjaxController();
        soyAjaxController.setDebugOn(debugOn);
        soyAjaxController.setLocaleProvider(localeProvider());
        soyAjaxController.setSoyMsgBundleResolver(soyMsgBundleResolver());
        soyAjaxController.setTemplateFilesResolver(templateFilesResolver());
        soyAjaxController.setTofuCompiler(tofuCompiler());
        soyAjaxController.setOutputProcessors(Lists.newArrayList(closureCompilerProcessor()));
        soyAjaxController.setAuthManager(authManager());

        return soyAjaxController;
    }

    @Bean
    public AuthManager authManager()
    {
        return new ConfigurableAuthManager(Lists.newArrayList("templates/client-words.soy", "templates/server-time.soy"));
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
}
