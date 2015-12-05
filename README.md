# Gradle SoapUI plugin

![SoapUI Logo](http://www.soapui.org/images/stories/homepage/soapui_logo.png)

The plugin provides tasks for running SoapUI tests and mocks during a Gradle build.

The plugin is largely based off of the [byte-shifter plugin](https://github.com/byte-shifter-ltd/soapui-gradle-plugin)
execpt it supporst specifiying multiple test suites with all test running within each suite.



## Build Status

[![Build Status](https://travis-ci.org/creising/soapui-gradle-plugin.svg)](https://travis-ci.org/creising/soapui-gradle-plugin)


## Tasks

The `soapui` plugin pre-defines the following tasks out-of-the-box:

<table>
    <tr>
        <th>Task Name</th>
        <th>Type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>soaptest</td>
        <td>TestTask</td>
        <td>Runs the SoapUI tests as specified by the plugin properties. Internally invokes the SoapUITestCaseRunner class as described there.</td>
    </tr>
    <tr>
        <td>soapload</td>
        <td>LoadTestTask</td>
        <td>Runs the SoapUI loadtests as specified by the plugin properties. Internally invokes the SoapUILoadTestRunner class as described there.</td>
    </tr>
    <tr>
        <td>soaptool</td>
        <td>ToolTask</td>
        <td>Runs the specified and configured code-generation tool. Internally invokes the SoapUIToolRunner class as described there.</td>
    </tr>
    <tr>
        <td>soapmock</td>
        <td>MockServiceTask</td>
        <td>Runs the specified and configured code-generation tool. Internally invokes the SoapUIMockServiceRunner class as described there.</td>
    </tr>
</table>


## Task properties
### soaptest properties

To configure the SoapUI test task you can choose to set the following properties within the `test` closure of the
`soapui` extension:

* `projectFile` : Specified the name of the SoapUI project file to use
* `testSuite` : Specifies the name of the TestSuite to run
* `testCase` : Specifies the name of the TestCase to run
* `endpoint` : Overrides the service endpoint to be invoked by any TestRequests
* `host` : Overrides the target host:port to be invoked by any TestRequests
* `username` : Overrides the username used by any TestRequests run
* `password` : Overrides the password used by any TestRequests run
* `domain` : Overrides the domain used by any TestRequests run
* `printReport` : Controls if a small test report should be printed to the console (true/false)
* `outputFolder` : Set which folder results/reports are saved to
* `junitReport` : Turns on creation of JUnit-reports, (true/false)
* `exportAll` : Controls if all test requests should be exported (default only exports errors), (true/false)
* `settingsFile` : Specifies SoapUI settings file to use
* `wssPasswordType` : Specifies WSS password type
* `projectPassword` : Specifies password for encrypted project
* `settingsFilePassword` : Specifies password for encrypted settings file
* `globalProperties` : Sets global properties
* `projectProperties` : Sets project properties
* `saveAfterRun` : Saves project file after run
* `testFailIgnore` : Ignore failed tests.

### loadtest properties

To configure the SoapUI load test task you can choose to set the following properties within the `load` closure of the
`soapui` extension:

* `projectFile` : Specified the name of the SoapUI project file to use
* `testSuite` : Specifies the name of the TestSuite to run
* `loadTest` : Specifies the name of the LoadTest to run
* `limit` : Overrides the limit of executed LoadTests
* `endpoint` : Overrides the service endpoint to be invoked by any TestRequests
* `host` : Overrides the target host:port to be invoked by any TestRequests
* `username` : Overrides the username used by any TestRequests run
* `password` : Overrides the password used by any TestRequests run
* `domain` : Overrides the domain used by any TestRequests run
* `printReport` : Controls if a small test report should be printed to the console (true/false)
* `outputFolder` : Set which folder results/reports are saved to
* `settingsFile` : Specifies SoapUI settings file to use
* `wssPasswordType` : Specifies WSS password type
* `projectPassword` : Specifies password for encrypted project
* `settingsFilePassword` : Specifies password for encrypted settings file
* `saveAfterRun` : Saves project file after run
* `threadcount` : Number of threads in loadtest.

### tool properties

* `projectFile` : Specified the name of the SoapUI project file to use
* `iface` : Specifies the interface to generate for
* `tool` : Specifies the tool(s) to run, a comma-separated list of axis1, axis2, dotnet, gsoap, jaxb, wstools, wsconsume, ora, wscompile, wsi, wsimport, xfire or xmlbeans
* `settingsFile` : Specifies SoapUI settings file to use
* `projectPassword` : Specifies password for encrypted project
* `settingsFilePassword` : Specifies password for encrypted settings file
* `outputFolder` : Set which folder results/reports are saved to

### mock properties
* `projectFile` : Specified the name of the SoapUI project file to use
* `mockService` : Specified the MockService to run
* `port` : The local port to listen on, overrides the port configured for the MockService
* `path` : The local path to listen on, overrides the path configured for the MockService
* `noBlock` : Turns off blocking when MockRunner has started
* `settingsFile` : Specifies SoapUI settings file to use
* `projectPassword` : Specifies password for encrypted project
* `settingsFilePassword` : Specifies password for encrypted settings file
* `saveAfterRun` : Saves project file after run


## Full Example

```groovy
soapui {
    test {
        projectFile = 'sample-soapui-project.xml'
        testSuite = ['TestSuite1', 'TestSuite2']
        printReport = true
        junitReport = true
    }
    load {
        projectFile = 'sample-soapui-load-project.xml'
        printReport = true
    }
    tool {
        projectFile = 'sample-soapui-tool-project.xml'
        iface = 'IOrderService'
        tool = 'wsi,axis1,axis2'
    }
}
```

## Contribute

- Issue Tracker: [github.com/byte-shifter-ltd/soapui-gradle-plugin/issues](https://github.com/byte-shifter-ltd/soapui-gradle-plugin/issues)
- Source Code: [github.com/byte-shifter-ltd/soapui-gradle-plugin](https://github.com/byte-shifter-ltd/soapui-gradle-plugin)


## License

The project is licensed under the MIT license.


