package ch.epfl.osper.oai.demoimpl;

import ch.epfl.osper.oai.interfaces.Converter;
import ch.epfl.osper.oai.interfaces.MetadataFormat;
import ch.epfl.osper.oai.interfaces.OaiConfiguration;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.util.Set;

/**
 * Created by kryvych on 18/09/15.
 */
@Configuration
@ComponentScan("ch.epfl.osper.oai.demoimpl")
public class OaiConfigurationImpl implements OaiConfiguration{

    @Autowired
    private DcOaiXmlStringConverter dcOaiXmlStringConverter;

    @Autowired
    private DifXmlStringConverter difXmlStringConverter;

    @Autowired
    private DifFormat difFormat;

    @Autowired
    private DcOaiFormat dcOaiFormat;

    @Bean(name = "recordConfiguration")
    public PropertiesFactoryBean recordConfiguration() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("recordConfiguration.properties"));
        return bean;
    }


    @Override
    @Bean
    public Set<Converter> converters() {
        Set<Converter> converters = Sets.newHashSet();
        converters.add(difXmlStringConverter);
        converters.add(dcOaiXmlStringConverter);

        return converters;
    }

    @Override
    @Bean
    public Set<MetadataFormat> formats() {
        Set<MetadataFormat> formats = Sets.newHashSet();
        formats.add(difFormat);
        formats.add(dcOaiFormat);
        return formats;
    }
}
