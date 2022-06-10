FROM kshivaprasad/java:1.8
ARG JAVA_OPTS=-Dwebdriver.chrome.whitelistedIps=
#Step 1 : Install the pre-requisite
RUN apt-get update
RUN apt-get install -y curl
RUN apt-get install -y p7zip \
    p7zip-full \
    unace \
    zip \
    unzip \
    bzip2

#Step 2: Install Maven
# 1- Define Maven version
ARG MAVEN_VERSION=3.8.5
ARG USER_HOME_DIR="/root"

# 2- Define the URL where maven can be downloaded from
ARG BASE_URL=https://downloads.apache.org/maven/maven-3/${MAVEN_VERSION}/binaries

# 3- Create the directories, download maven, validate the download, install it, remove downloaded file and set links
RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && echo "Downlaoding maven" \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  \
  && echo "Unziping maven" \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  \
  && echo "Cleaning and setting links" \
  && rm -f /tmp/apache-maven.tar.gz \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
# 4- Define environmental variables required by Maven, like Maven_Home directory and where the maven repo is located
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

#Step 3 : Remove packages to shring docker image
RUN apt-get autoremove && apt-get clean



# Allure
ARG ALLURE_VERSION=2.17.2
RUN curl -s -o /tmp/allure.zip https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/$ALLURE_VERSION/allure-commandline-$ALLURE_VERSION.zip \
	&& unzip /tmp/allure.zip -d /opt \
	&& rm /tmp/allure.zip \
	&& chmod 777 /opt/allure-$ALLURE_VERSION \
	&& ln -s /opt/allure-$ALLURE_VERSION/bin/allure /usr/bin/allure




#Step 4: Copy our project
COPY . /usr/src

WORKDIR /usr/src

RUN mvn clean install -Dmaven.test.skip=true
