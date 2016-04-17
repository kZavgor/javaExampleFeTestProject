package com.base.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final String DEFAULT_BROWSER = System.getProperty("browser", "chrome");

    public static final String DEFAULT_LIB_DIR = System.getProperty("lib.dir", System.getProperty("user.dir")
            + File.separator + "lib");

    public static final int ELEMENT_MEGA_EXTRALONG_TIMEOUT_SECONDS = 360;
    public static final int ELEMENT_EXTRALONG_TIMEOUT_SECONDS = 180;
    public static final int ELEMENT_LONG_TIMEOUT_SECONDS = 120;
    public static final int ELEMENT_TIMEOUT_SECONDS = 180;
    public static final int ELEMENT_SMALL_TIMEOUT_MILLISECONDS = 10;
    public static final int ELEMENT_EXTRASMALL_TIMEOUT_SECONDS = 5;
    public static final int ELEMENT_MICRO_TIMEOUT_SECONDS = 2;

    public static final int SCROLL_VALUE = 50;

    public static final String TESTLINK_DEV_KEY = "insert key here";
    public static final String TESTLINK_API_URL = "http://ng-testlink.mailordercentral.com/lib/api/xmlrpc/v1/xmlrpc.php";
    public static boolean TESTLINK_ENABLED = true;

    public static String BASE_URL = System.getProperty("baseurl", "https://mail.google.com");  // Debug server by default. Needed change value before test execution

    public static final String DEFAULT_TEST_EMAIL = "test.k.zavgor@gmail.com";
    public static final String DEFAULT_TEST_PASSWORD = "!baSRnfpwnv!";
    public static final String DEFAULT_TEST_LINK_TEXT = "GOOGLE";
    public static final String DEFAULT_TEST_LINK_VAUE = "https://google.com";


}
