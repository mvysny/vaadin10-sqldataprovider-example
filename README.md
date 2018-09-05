[![Build Status](https://travis-ci.org/mvysny/vaadin10-sqldataprovider-example.svg?branch=master)](https://travis-ci.org/mvysny/vaadin10-sqldataprovider-example)

Demonstrates the use of a full-blown SQLDataProvider in a Vaadin 10 Grid.

Only requires a Servlet 3.0 container to run. Developed in a pure Java. Also demoes an auto-generated
Grid filter bar, therefore this example serves as a full replacement for Teppo Kurki's
[FilteringTable](https://vaadin.com/directory/component/filteringtable).

The project uses code from [Vaadin-on-Kotlin](http://vaadinonkotlin.eu). The Kotlin stdlib is
only included as a run-time dependency - this project contains no Kotlin code and doesn't even
run the Kotlin compiler.

The live demo of this project [runs on Heroku](https://vaadin10-sqldataprovider.herokuapp.com/).

# Skeleton Starter for Vaadin Flow

This project can be used as a starting point to create your own Vaadin Flow application.
It has the necessary dependencies and files to help you get started.

The best way to use it by via [vaadin.com/start](https://vaadin.com/start) - you can get only the necessary parts and choose the package naming you want to use.
There is also a [getting started tutorial](https://vaadin.com/docs/v10/flow/introduction/tutorial-get-started.html) based on this project.

To access it directly from github, clone the repository and import the project to the IDE of your choice as a Maven project. You need to have Java 8 installed.

Run using `mvn jetty:run` and open [http://localhost:8080](http://localhost:8080) in browser.

For a full Vaadin Flow application example, there is the Beverage Buddy App Starter for Flow available also from [vaadin.com/start](https://vaadin.com/start) page.

Branching information:
* `master` the latest version of the starter, using latest platform snapshot
* `V10` the version for Vaadin 10
* `V11` the version for Vaadin 11