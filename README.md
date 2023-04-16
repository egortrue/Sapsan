<p align="center"><img src="img/logo.svg" height="200"></p>

> The ultimate DevOpsLess framework based on 'Jenkins Shared Library'

## Dependencies
- System: `Linux` or `MacOS`
- Software:
  - `Git v2.39.2`
  - `Docker v20.10.23`

## How to Use

## Local Testing
There is a [testing](test) folder with the necessary files to easily prepare simple infrastructure for local testing.

### Build master container
```shell
docker build -f Dockerfile.master -t jenkins-master .
```

### Run master container
```shell
docker run -d --name master \
  -p 8080:8080 \
  -p 50000:50000 \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v $(pwd)/jenkins_home:/var/jenkins_home \
  jenkins-master
```

### Build agent container
```shell
docker build -f Dockerfile.agent -t jenkins-agent .
```

### Run agent container
There is no need to start agent manually. The agent container will be started by the master container when you register agent in Jenkins.

### Configure master
Go to `Manage Jenkins > Manage Nodes > New Node` and set up the following properties:
- Set `Remote root directory` to `/home/jenkins/agent`
- Set `Launch method` to `Launch agent via execution of command`
- Set `Launch command` with the following command

```shell
docker run -i --rm --init --name agent jenkins-agent java -jar /usr/share/jenkins/agent.jar
```

[DEPRECATED] Go to `Manage Jenkins > Configure System > Global Pipeline Libraries` and setup the following properties
- Press `Add`
- Set `Name` to `sapsan`
- Set `Default version` to `main`
- Setup `Retrival method` for your desire

### SCM
We are going to use opensource [SCM Manager](https://scm-manager.org)

```shell
docker run -d --rm --name scm \
  -p 8081:8080 \
  -v $(pwd)/scm-manager_home:/var/lib/scm \
  scmmanager/scm-manager:latest
```