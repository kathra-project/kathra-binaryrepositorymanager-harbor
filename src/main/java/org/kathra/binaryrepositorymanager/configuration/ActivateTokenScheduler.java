package org.kathra.binaryrepositorymanager.configuration;

import org.apache.camel.builder.RouteBuilder;
import org.kathra.binaryrepositorymanager.Config;
import org.kathra.binaryrepositorymanager.service.UserRepositoryActivateToken;

public class ActivateTokenScheduler extends RouteBuilder {

    @Override
    public void configure() {
        Config config = new Config();
        from("scheduler://foo?delay="+config.getDelaySchedule()+"").process(UserRepositoryActivateToken.getInstance()).to("mock:success");
    }

}