# Bookshelf Java Sample Code

This folder contains the sample code from [GCP Getting Started Java][getting-started-java] and ready for Opsta Introduction to Kubernetes Workshop

[getting-started-java]: https://github.com/GoogleCloudPlatform/getting-started-java

## How to run on local

```bash
make
```

This will do ```mvn clean install``` to build a war file at ```war/ROOT.war``` and ```docker-compose up``` to run jboss wildfly on local

You can develop the code and see the change by just leave the ```make``` terminal. Then open another terminal and run ```make install```. It will do ```mvn clean install``` and update ```war/ROOT.war```. jboss wildfly will detect this automatically and do hot-reload application.
