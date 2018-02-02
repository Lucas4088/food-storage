package gui;

import com.wat.foodmanager.CoreContextConfiguration.CoreContextConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration

@ComponentScan(value = "gui", basePackageClasses = CoreContextConfiguration.class)
public class FrontendContextConfiguration {

}
