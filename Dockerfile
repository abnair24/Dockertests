FROM yegor256/java-maven

RUN apt-get update 2>&1 > /dev/null 
RUN apt-get install -y git 2>&1 > /dev/null
RUN apt-get install -y nano 2>&1 > /dev/null

ADD ./pom.xml ./home

RUN cd home && mvn install clean -DskipTests=true 2>&1 > /dev/null
