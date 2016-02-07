# BigPandaInterview
My interview at BigPanda

Goal:
Your exercise is to implement a simple stream processing service that also exposes an HTTP interface.

You are provided with a blackbox executable that spits out an infinite stream of lines of event data encoded in JSON. You can download it here:
* Linux - https://s3-us-west-1.amazonaws.com/bp-interview-artifacts/generator-linux-amd64
* Mac OS X - https://s3-us-west-1.amazonaws.com/bp-interview-artifacts/generator-macosx-amd64
* Windows - https://s3-us-west-1.amazonaws.com/bp-interview-artifacts/generator-windows-amd64.exe

Please note that the stream might sometimes encounter errors and output corrupt JSON lines.

These are the requirements for your service:
- it should consume the output of the generator and gather the following stats: a count of events by event type and a count of words encountered in the data field of the events.
- it should expose these stats in an HTTP interface
- the processing of the generator data and the handling of the HTTP requests should not block each other

The architecture of your service should obviously decouple the data processing, HTTP handling, be testable, etc. You can implement this exercise in either Java or Scala.

Installing the solution:
This is a maven project, so a maven install command would create an executable JAR (with all dependecies inside).

Running:
java -jar json.stream-1.0-SNAPSHOT.jar event.parsing.Application

Using:
This program launches an embeded tomcat web server listening on port 8080, and exposes a RESTful API (which supports only GET commands)
To get a list of all the events:
http://localhost:8080/events/
To get the event count for a specific event type (for example, foo)
http://localhost:8080/events/foo/eventCount
To get the unique word count for a specific event type (for example, foo)
http://localhost:8080/events/foo/wordCount
