<p align="center"><img src="img/logo.svg" height="250"></p>
<p align="center">
    <a href="https://groovy-lang.org">
        <img src="https://img.shields.io/badge/runs%20on-Groovy-ffa">
    </a>
    <a href="https://www.jenkins.io">
        <img src="https://img.shields.io/badge/runs%20by-Jenkins-fef">
    </a>
    <a><img src="https://img.shields.io/badge/platform-Unix-aff"></a>
    <a><img src="https://img.shields.io/tokei/lines/github/egortrue/Sapsan"></a>
    <a><img src="https://img.shields.io/github/repo-size/egortrue/Sapsan"></a>
</p>

## Description
The ultimate DevOps Framework based on 'Jenkins Shared Library'

## Requirements
- System: `Linux` or `MacOS`
- Software:
  - `Git`
  - `Docker`

## Dependencies
- Docker Images:
  - [Jenkins Master](https://hub.docker.com/r/jenkins/jenkins)
  - [Jenkins Agent](https://hub.docker.com/r/jenkins/agent)
  - [SCM Manager](https://hub.docker.com/r/scmmanager/scm-manager)
- Jenkins Plugins:
  - [Remote Jenkinsfile Provider](https://plugins.jenkins.io/remote-file/)

## How to Use
[TBC]

## Local Testing
There is a [workspace](workspace) folder with the necessary files to easily prepare simple infrastructure for local testing.
Use the following commands. 
All the commands **should be executed in root** of this project. 
Otherwise, you can edit them manually.

### Build master container
```shell
docker build -f workspace/Dockerfile.master -t jenkins-master .
```

### Run master container
```shell
docker run -d --name master \
  -p 8080:8080 \
  -p 50000:50000 \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v $(pwd)/workspace/jenkins_home:/var/jenkins_home \
  jenkins-master
```

### Build agent container
```shell
docker build -f workspace/Dockerfile.agent -t jenkins-agent .
```

### Run agent container
You should not start agent manually.
The agent container will be started by the master container when you register agent in Jenkins.
See the following step `Configure master`

### Configure master
Go to `Manage Jenkins > Manage Nodes > New Node` and set up the following properties:
- Set `Remote root directory` to `/home/jenkins/agent`
- Set `Launch method` to `Launch agent via execution of command`
- Set `Launch command` with the following command

```shell
docker run -i --rm --init --name agent jenkins-agent java -jar /usr/share/jenkins/agent.jar
```

### SCM
We are going to use opensource [SCM Manager](https://scm-manager.org)

```shell
docker run -d --rm --name scm \
  -p 8081:8080 \
  -v $(pwd)/workspace/scm-manager_home:/var/lib/scm \
  scmmanager/scm-manager:latest
```