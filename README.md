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

## Dependencies
- System: `Linux` or `MacOS`
- Software:
  - `Git v2.39.2`
  - `Docker v20.10.23`
- Jenkins plugins:
  - [Remote Jenkinsfile Provider](https://plugins.jenkins.io/remote-file/)
- Local Workspace:
  - [Docker Jenkins Master](https://hub.docker.com/r/jenkins/jenkins)
  - [Docker Jenkins Agent](https://hub.docker.com/r/jenkins/agent)
  - [Docker SCM-Manager](https://hub.docker.com/r/scmmanager/scm-manager)

## How to Use
[TBC]

## Local Testing
There is a [testing](workspace) folder with the necessary files to easily prepare simple infrastructure for local testing.

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
There is no need to start agent manually. The agent container will be started by the master container when you register agent in Jenkins.

### Configure master
Go to `Manage Jenkins > Manage Nodes > New Node` and set up the following properties:
- Set `Remote root directory` to `/home/jenkins/agent`
- Set `Launch method` to `Launch agent via execution of command`
- Set `Launch command` with the following command

```shell
docker run -i --rm --init --name agent jenkins-agent java -jar /usr/share/jenkins/agent.jar
```

[DEPRECATED] Go to `Manage Jenkins >  Configure System > Global Pipeline Libraries` and setup the following properties
- Press `Add`
- Set `Name` to `sapsan`
- Set `Default version` to `main`
- Setup `Retrival method` for your desire

### SCM
We are going to use opensource [SCM Manager](https://scm-manager.org)

```shell
docker run -d --rm --name scm \
  -p 8081:8080 \
  -v $(pwd)/workspace/scm-manager_home:/var/lib/scm \
  scmmanager/scm-manager:latest
```