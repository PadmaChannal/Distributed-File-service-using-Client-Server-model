# Distributed-File-service-using-Client-Server-model

Introduction:

What is the Project about?
To augment our theoretical knowledge acquired during lecture and gain some hands on experience we build a simple Distributed system. We implemented a file upload and download service based on client-server model.

Operations supported:
Upload, Download, Delete, Rename, Auto-Synchronize 

Assignments completed:
1.	Single-threaded Fileserver
2.	Multi-threaded Fileserver
3.	Drop-box like synchronized storage service.


Approach:  
Remote procedure call (RMI in Java)

Implementation:
We implemented the program in following steps:
•	Define remote interface
•	Implement Server
•	Implement Client
•	Compile the source files
•	Start the Java RMI registry, server & client

The code snippet for the explanation of this section can be found in DriverClient.java and DriverServer.java of the Source code attached. 

Drawing parallels to the code we have written in our program we explain:

Defining the remote Interface:
A remote object is an instance of a class that implements a remote interface. A remote interface extends the interface java.rmi.Remote and declares a set of remote methods. 

Server Implementation:
A "server" class, in this context, is the class which has a main method that creates an instance of the remote object implementation, exports the remote object, and then binds that instance to a name in a Java RMI registry. In program: DriverServer.java

Client Implementation:
The client program obtains a stub for the registry on the server's host, looks up the remote object's stub by name in the registry, and then invokes the method on the remote object using the stub. This client first obtains the stub for the registry by invoking the static LocateRegistry.getRegistry method with the hostname. Finally, the client invokes the method on the remote object's stub, which causes the following actions to happen:
•	The client-side runtime opens a connection to the server using the host and port information in the remote object's stub and then serializes the call data.
•	The server-side runtime accepts the incoming call, dispatches the call to the remote object, and serializes the result to the client.
•	The client-side runtime receives, de-serializes, and returns the result to the caller.
The response message returned from the remote invocation on the remote object is then printed to System.out.
In program: DriverClient.java
We then compile the source file, start the Java RMI Registry, Server and client.
Our learning:

As an outcome of this project we learnt 
•	RMI
•	Architecture, stub & skeleton
•	Steps for developing the RMI system

Distributed system should locate the remote objects, communicate with them & load class byte codes for objects that are passed as parameters or return values.

Interfaces and classes in Java RMI Package:

RMI Architecture:

•	Server must first bind its name to registry
•	The client lookup the server name in the registry to establish remote interfaces 
•	The stub serializing the parameters to skeleton, the skeleton invoking the remote method and serializing the result back to the stub.


Stub and skeleton:
•	A client invokes remote method; the call is first forwarded to stub.
•	The stub is responsible for sending the remote call over to the server-side skeleton
•	The stub opening a socket to remote server, marshalling the object parameters and forwarding the data stream to the skeleton.
•	The stub opening a socket to the remote server, marshalling the object parameters and forwarding the data stream to the skeleton 
•	A skeleton contains a method that receives the remote calls, un-marshals the parameters and invokes the actual remote object implementation


Developing an RMI System:
•	Define the remote interface
•	Develop remote object by implementing the remote interface
•	Develop the client program
•	Generate client stubs and server skeletons 
•	Start the remote server objects
•	Run the client

What Issues we encountered?

We couldn’t successfully bind the server to the lab(testing environment) IP address. We could set the property System.setProperty("java.rmi.server.hostname",ipAddressOftheServer); 


Challenges in converting the single-threaded server to a multi-threaded version:

Since we understood what is the purpose of the project, we carefully analyzed Message-oriented client-server communication VS Remote procedure call (RPC) based communication. 
RMI in Java supports multi-threading and hence we did not face any challenges.

References:
•	Lecture Notes
•	Internet:
¬	https://docs.oracle.com/javase/tutorial/rmi/
¬	https://en.wikipedia.org/wiki/Java_remote_method_invocation
¬	https://www.cs.ucsb.edu/~cappello/lectures/rmi/helloworld.shtml
¬	http://www.javacoffeebreak.com/articles/javarmi/javarmi.html
¬	http://stackoverflow.com/questions/37569680/why-we-need-distributed-lock-in-the-redis
¬	https://docs.oracle.com/javase/8/docs/technotes/guides/rmi/hello/hello-world.html#define
¬	http://www.slideshare.net/junyuo/a-short-java-rmi-tutorial


	
