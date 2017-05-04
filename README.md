## Synopsis

Command line program that takes a JSON file of Leads and deduplicates it.

## Installation

This procedure assumes you have Maven installed.
[Maven](https://maven.apache.org/download.cgi) [installed](https://maven.apache.org/install.html)

To download:
```
git clone git@github.com:scottfabini/dedupJSON.git
cd dedupJSON
```

To run test-cases:
```
mvn test
```

To build:
```
mvn package
```

To run:
```
java -jar target/App-1.0-SNAPSHOT-jar-with-dependencies.jar ./leads.json >> log.txt
```

Output results can be found in result.txt.
A log of changes can be found in log.txt.
Fields that changed during deduplication are denoted by " -> ".


