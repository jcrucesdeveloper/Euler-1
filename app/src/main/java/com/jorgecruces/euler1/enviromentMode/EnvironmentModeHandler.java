package com.jorgecruces.euler1.enviromentMode;

/**
 * Singleton in charge of the enviroment mode (TEST OR PRODUCTION)
 */
public class EnvironmentModeHandler {

    private static final EnvironmentModeHandler emd = new EnvironmentModeHandler();

    // EnvironmentModes

    private final boolean testMode = false;

    private EnvironmentModeHandler() {}

    public static EnvironmentModeHandler getEnvironmentModeHandler() {
        return emd;
    }

    /**
     * Return true if the enviromentMode is test, false otherwise
     * @return true in testMode, false in another mode
     */
    public boolean testMode() {
        return testMode;
    }

}
