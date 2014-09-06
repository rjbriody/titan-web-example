titan-web-example
=================
This is an example project that shows one way to build a RESTful Java web app around Titan, Cassandra, and Elasticsearch.

The application is built using Spring (using annotations), JAX-RS, and Jersey. A Servlet Filter is wired up to show how a Fitler can be used to manage TitanGraph transactions.

The "graph of the gods" example data set is used.

Getting Started
===============

If you prefer to install Cassandra and ES manually then skip the first 3 steps.

- Install [Virtual Box](https://www.virtualbox.org/wiki/Downloads) (because you need it for Vagrant).

- Install [Vagrant](https://docs.vagrantup.com/v2/installation/).

- Vagrant up! This will start a VM w/ IP 10.10.10.10 that has compatible versions of Cassandra and Elasticsearch running. There is also a Titan distribution with a pre-configured titan-cassandra-es.properties file that you can use for local gremlin shell access.
```bash
cd titan-web-example/vagrant; vagrant up
```
- Load the project in your favorite IDE (or compile and run via mvn).

- Run  classesthatrunthings.PopulateDB.java. This will load the "graph of the gods" example data set.

- Run classesthatrunthings.RunApp.java. This will launch the web app in an embedded Jetty container.

- Visit http://localhost:9091

If the links works then it's time to dig in to the code and see what's goin on! Start with the TitanWebService class.
