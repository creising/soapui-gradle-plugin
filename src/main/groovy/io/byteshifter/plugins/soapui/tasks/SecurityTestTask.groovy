package io.byteshifter.plugins.soapui.tasks

import com.eviware.soapui.SoapUI
import com.eviware.soapui.tools.SoapUISecurityTestRunner
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction

/**
 *
 * task name - securityTest
 * @author Sion Williams
 */
class SecurityTestTask extends SoapUITask {

    /**
     * The TestSuite to run project file to test with
     */
    @Optional
    String testSuite

    /**
     * The TestCase to run project file to test with
     */
    @Optional
    String testCase

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
    boolean junitReport

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
    @Optional
    Properties soapuiProperties

    /**
     * Security Test Name
     */
    @Optional
    String securityTest

    SecurityTestTask() {
        super('Runs soapUI security tests')
    }

    @TaskAction
    public void executeAction() {

        SoapUISecurityTestRunner runner = new SoapUISecurityTestRunner(
                'soapUI ' + SoapUI.SOAPUI_VERSION + ' Gradle Security Test Runner')
        runner.projectFile = projectFile

        if (endpoint) {
            runner.endpoint = endpoint
        }

        if (testSuite) {
            runner.testSuite = testSuite
        }

        if (testCase) {
            runner.testCase = testCase
        }

        if (username) {
            runner.username = username
        }

        if (password) {
            runner.password = password
        }

        if (wssPasswordType) {
            runner.wssPasswordType = wssPasswordType
        }

        if (domain) {
            runner.domain = domain
        }

        if (host) {
            runner.host = host
        }

        if (outputFolder) {
            runner.outputFolder = outputFolder
        }

        runner.printReport = printReport
        runner.exportAll = exportAll
        runner.junitReport = junitReport
        runner.enableUI = interactive
        runner.ignoreError = testFailIgnore
        runner.saveAfterRun = saveAfterRun

        if (settingsFile) {
            runner.settingsFile = settingsFile
        }

        if (projectPassword) {
            runner.projectPassword = projectPassword
        }

        if (settingsPassword) {
            runner.soapUISettingsPassword = settingsPassword
        }

        if (globalProperties) {
            runner.globalProperties = globalProperties
        }

        if (projectProperties) {
            runner.projectProperties = projectProperties
        }

        if (soapuiProperties && soapuiProperties.size() > 0){
            for (Object key : soapuiProperties.keySet()) {
                System.setProperty((String) key, soapuiProperties.getProperty((String) key))
            }
        }

        if (securityTest && securityTest.length() > 0) {
            runner.securityTestName = securityTest
        }

        runner.run()
    }
}
