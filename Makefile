PWD = $(shell pwd)
MAVEN_HOME = /usr/src/maven
MAVEN_DOCKER_IMAGE = maven:3.5.3-jdk-8-alpine
APP_DOCKER_IMAGE = bookshelf-java:dev
JBOSS_UID = 1000

all: install run

install:
	docker run --rm -w $(MAVEN_HOME) -u $(JBOSS_UID) \
		-e MAVEN_CONFIG=$(MAVEN_HOME)/.m2 \
		-v "$(PWD):$(MAVEN_HOME)" \
		$(MAVEN_DOCKER_IMAGE) \
		mvn clean install -Duser.home=$(MAVEN_HOME)

run:
	docker-compose up

build:
	docker build -t $(APP_DOCKER_IMAGE) .

test:
	docker run --rm \
		-v "$(PWD):$(MAVEN_HOME)" \
		$(MAVEN_DOCKER_IMAGE) mvn test

clean:
	docker-compose down
	docker run --rm -w $(MAVEN_HOME) \
		-v "$(PWD):$(MAVEN_HOME)" \
		$(MAVEN_DOCKER_IMAGE) rm -rf ./target ./m2
