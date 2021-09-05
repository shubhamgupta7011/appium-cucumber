package com.test.Configurations;


import cucumber.api.java.After;
import cucumber.api.java.Before;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;


public class Hooks {

    private static ThreadLocal<AppiumDriverLocalService> service = new ThreadLocal<>();

    private final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public final AppiumDriver getDriver() {
        return driver.get();
    }

    public AppiumDriverLocalService getService() {
        return service.get();
    }

    /*
    *   Start Appium Server Programmatically before each scenario
    */
    @Before
    public void startServer() throws Exception {
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:/Users/shubhamg/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1");
        service.set(appiumServiceBuilder.build());
        service.get().start();

        if (service == null || !service.get().isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException("An appium server node is not started!");
        }

        File appDir = new File(System.getProperty("user.dir"));
        File app = new File(appDir, "/app/"+ProperityLoader.getProperityByKey("appName"));


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, ProperityLoader.getProperityByKey("PlatformVersion"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, ProperityLoader.getProperityByKey("PlatformName"));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, ProperityLoader.getProperityByKey("AutomationName"));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ProperityLoader.getProperityByKey("DeviceName"));
        capabilities.setCapability(MobileCapabilityType.UDID, ProperityLoader.getProperityByKey("UDID"));

        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 500000);
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());

        if(ProperityLoader.getProperityByKey("PlatformName").equals("Android")) {
            driver.set(new AndroidDriver<AndroidElement>(service.get().getUrl(), capabilities));
        }
        else {
            driver.set(new IOSDriver<IOSElement>(service.get().getUrl(), capabilities));
        }

//        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
//        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
//        capabilities.setCapability(MobileCapabilityType.UDID, "iPhone 6");
//        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 500000);
//        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
//        driver.set(new IOSDriver<IOSElement>(service.get().getUrl(), capabilities));
    }

    /*
    *   Stop Appium Server Programmatically before each scenario
    */
    @After
    public void stopServer() {
        if (driver.get() != null) {
            driver.get().quit();
        }
        if (service.get() != null) {
            service.get().stop();
        }
    }
}
