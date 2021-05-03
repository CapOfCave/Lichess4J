[![Build Status](https://github.com/CapOfCave/Lichess4J/actions/workflows/ci.yml/badge.svg?branch=master)](https://github.com/CapOfCave/Lichess4J/actions?query=workflow%3A"Java+CI"+branch%3Amaster)
[![Codecov Coverage Status](https://codecov.io/gh/CapOfCave/Lichess4J/branch/master/graph/badge.svg)](https://codecov.io/gh/CapOfCave/Lichess4J)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/CapOfCave/Lichess4J.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/CapOfCave/Lichess4J/context:java)

# Lichess4J
> A java wrapper for the [Lichess API](https://lichess.org/api)

This library aims to create a general purpase java wrapper for the Lichess API.

## Installing / Getting started

> :warning: **This project is still in the early stages of developement.**: All aspects of the api are subject to change. Please use this library for experimental purposes only.

### Maven

This package is not part of the maven central repository (yet). It can however be imported through github packages, which requires authorization.

1. Generate a new github personal access token as described [here](https://docs.github.com/en/github/authenticating-to-github/creating-a-personal-access-token)
2. Update your `home~.m2/settings.xml` file to include your github username and the generated access token. It may look similar to this:

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd">
    <localRepository/>
    <interactiveMode/>
    <offline/>
    <pluginGroups/>
    <servers>
        <server>
            <id>github</id>
            <username>YOUR_USERNAME</username>
            <password>YOUR_GITHUB_ACTION_TOKEN</password>
        </server>
    </servers>
    <mirrors/>
    <proxies/>
    <profiles/>
    <activeProfiles/>
</settings>
```
3. Include the following in your `pom.xml` file (please replace `VERSION_NUMBER` with the latest release version):
```xml
<project>
    ...    
    <repositories>
		<repository>
			<id>github</id>
			<name>GitHub Packages CapOfCave/Lichess4J</name>
			<url>https://maven.pkg.github.com/CapOfCave/Lichess4J/</url>
		</repository>
	</repositories>
    ...
    <dependencies>
		<dependency>
			<groupId>me.kecker</groupId>
			<artifactId>lichess4j</artifactId>
			<version>VERSION_NUMBER</version>
		</dependency>
	</dependencies>
</project>
```
4. Run
```bash
mvn install
```
to ensure everything was set up correctly.


## Features

For a full list of currently implemented features, please have a look at the [endpoint coverage documentation](https://github.com/CapOfCave/Lichess4J/blob/master/docs/endpoint-coverage.md) .

## Contributing

Do you want to contribute to this library? We'd love your help. Please refer to our [contribution guideline](https://github.com/CapOfCave/Lichess4J/blob/master/CONTRIBUTING.md)

### Building locally

If you want to run this project locally, please go through the following steps:

```shell
git clone https://github.com/CapOfCave/Lichess4J.git
cd Lichess4J/
mvn clean install
```

This will clone this repository, perform tests and build it.

## Licensing
The code in this project is licensed under MIT license. See the [LICENSE](https://github.com/CapOfCave/Lichess4J/blob/master/LICENSE) file for a detailed description of rights and limitations when using this project.