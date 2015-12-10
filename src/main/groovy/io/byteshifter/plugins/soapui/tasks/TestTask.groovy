package io.byteshifter.plugins.soapui.tasks

import com.eviware.soapui.SoapUI
import com.eviware.soapui.tools.SoapUITestCaseRunner
import org.gradle.api.tasks.Optional

/**
 * Runs soapUI functional tests
 * task name - soaptest
 * @author Sion Williams
 */
class TestTask extends SoapUITask {
    /**
     * The TestSuite to run project file to test with
     */
    @Optional
    String[] testSuite

    /**
     * The username to use for authentication challenges
     */
    @Optional
    String username

    /**
     * The password to use for authentication challenges
     */
    @Optional
    String password

    /**
     * The WSS password-type to use for any authentications. Setting this will
     * result in the addition of WS-Security UsernamePassword tokens to any
     * outgoing request containing the specified username and password. Set to
     * either 'Text' or 'Digest'
     */
    @Optional
    String wssPasswordType

    /**
     * The domain to use for authentication challenges
     */
    @Optional
    String domain

    /**
     * The host to use for requests
     */
    @Optional
    String host

    /**
     * Overrides the endpoint to use for requests
     */
    @Optional
    String endpoint

    /**
     * Sets the output folder for reports
     */
    @Optional
    String outputFolder

    /**
     * Turns on printing of reports
     */
    boolean printReport

    /**
     * Enabled interactive groovy scripts
     */
    boolean interactive

    /**
     * Turns on exporting of all results
     */
    boolean exportAll

    /**
     * Turns on creation of reports in junit style
     */
    boolean junitReport = false

    /**
     * Tells Test Runner to skip tests.
     */
    @Optional
    boolean skip

    /**
     * If set ignore failed tests
     */
    boolean testFailIgnore

    /**
     * Specified global property values soapui.saveAfterRun
     */
    @Optional
    String[] globalProperties

    /**
     * Specified project property values
     */
    @Optional
    String[] projectProperties

    /**
     * Saves project file after running tests
     */
    boolean saveAfterRun

    /**
     * SoapUI Properties.
     */
    Properties soapuiProperties

    TestTask() {
        super('Runs soapUI functional tests')
    }
    
    @Override
    void executeAction() {
        String[] testSuites = getTestSuite()
        logger.info("running tests...")
        
        for(String testSuite : testSuites) {
            logger.info("running: " + testSuite)
            runTestSuite(testSuite)
        }
    }

    void runTestSuite(String testSuite) {
        SoapUITestCaseRunner runner = new MySoapUITestCaseRunner(
                'soapUI ' + SoapUI.SOAPUI_VERSION + ' Gradle TestCase Runner')
        runner.setProjectFile( getProjectFile() )

        runner.testSuite = testSuite
        if ( getEndpoint() ) {
            runner.endpoint = getEndpoint()
            logger.debug "Runner endpoint: " + getEndpoint()
        }

        if ( getUsername() ) {
            runner.username = getUsername()
            logger.debug "Runner username: " + getUsername()
        }

        if ( getPassword() ) {
            runner.password = getPassword()
            logger.debug "Runner password: " + getPassword()
        }

        if ( getWssPasswordType() ) {
            runner.wssPasswordType = getWssPasswordType()
            logger.debug "Runner wssPasswordType: " + getWssPasswordType()
        }

        if ( getDomain() ) {
            runner.domain = getDomain()
            logger.debug "Runner domain: " + getDomain()
        }

        if ( getHost() ) {
            runner.host = getHost()
            logger.debug "Runner host: " + getHost()
        }

        if ( getOutputFolder() ) {
            runner.outputFolder = getOutputFolder()
            logger.debug "Runner outputFolder: " + getOutputFolder()
        }

        runner.printReport = getPrintReport()
        logger.debug "Runner printReport: " + getPrintReport()

        runner.exportAll = getExportAll()
        logger.debug "Runner exportAll: " + getExportAll()

        runner.setJUnitReport(getJunitReport())
        logger.debug "Runner junitReport: " + getJunitReport()

        runner.enableUI = getInteractive()
        logger.debug "Runner enableUI: " + getInteractive()

        runner.ignoreError = getTestFailIgnore()
        logger.debug "Runner ignoreError: " + getTestFailIgnore()

        runner.saveAfterRun = getSaveAfterRun()
        logger.debug "Runner saveAfterRun: " + getSaveAfterRun()

        if ( getSettingsFile() ) {
            runner.settingsFile = getSettingsFile()
            logger.debug "Runner settingsFile: " + getSettingsFile()
        }

        if ( getProjectPassword() ) {
            runner.projectPassword = getProjectPassword()
            logger.debug "Runner projectPassword: " + getProjectPassword()
        }

        if ( getSettingsPassword() ) {
            runner.soapUISettingsPassword = getSettingsPassword()
            logger.debug "Runner soapUISettingsPassword: " + getSettingsPassword()
        }

        if ( getGlobalProperties() ) {
            runner.globalProperties = getGlobalProperties()
            logger.debug "Runner getGlobalProperties: " + getGlobalProperties()
        }

        if ( getProjectProperties() ) {
            runner.projectProperties = getProjectProperties()
            logger.debug "Runner projectProperties: " + getProjectProperties()
        }

        if ( getSoapuiProperties() && getSoapuiProperties().size() > 0 ) {
            getSoapuiProperties.keySet().each { key ->
                logger.debug "Setting ${key} value ${getSoapuiProperties.getProperty("${key}")}"
                System.setProperty((String) key, getSoapuiProperties.getProperty((String) key))
            }
        }

        runner.getLog().info('log me!')
        runner.run()
        logger.info "SoapUI test case runner complete."
    }
}

public class MySoapUITestCaseRunner extends SoapUITestCaseRunner {
    public MySoapUITestCaseRunner(){super()}
    public MySoapUITestCaseRunner(String title){super(title)}

    @Override
    void initGroovyLog() {
    }
}
