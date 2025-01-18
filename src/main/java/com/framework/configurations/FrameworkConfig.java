package com.framework.configurations;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Config.LoadPolicy;

@Sources({
    "file:./src/main/resources/configs/dev.properties",
    "file:./src/main/resources/configs/stage.properties",
    "file:./src/main/resources/configs/test.properties"
})
@LoadPolicy(Config.LoadType.MERGE)  // This specifies the merging strategy for the property files
public interface FrameworkConfig extends Config {
	
	@DefaultValue("stage")
	String env();

    @Key("${env}.browser")
    String browser();

    @Key("${env}.base.url")
    String baseUrl();

    @Key("${env}.implicit.wait")
    @DefaultValue("10")
    int implicitWait();

    @Key("${env}.headless")
    boolean headlessMode();

    @Key("stage.db.url")
    String dbUrl();

    @Key("db.username")
    String dbUsername();

    @Key("app.version")
    String appVersion();
}
