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

# Description

The ultimate DevOps Framework based on 'Jenkins Shared Library'

# Requirements

#### System: Any (for local testing: Unix-like)

#### Software:

- `Git`
- `Docker`
- `Docker Compose v3`

#### Jenkins Plugins:

- [Remote Jenkinsfile Provider](https://plugins.jenkins.io/remote-file/) **(required)**
- [Ansi Color](https://plugins.jenkins.io/ansicolor/) **(required)**
- [Docker Workflow](https://plugins.jenkins.io/docker-workflow/) _(local testing)_
- [Jenkins Swarm Agent](https://plugins.jenkins.io/swarm/) _(local testing)_
- And all the suggested plugins from
  official [Jenkins repository](https://github.com/jenkinsci/jenkins/blob/master/core/src/main/resources/jenkins/install/platform-plugins.json)

#### Docker Images (Local Testing):

- [Jenkins Master](https://hub.docker.com/r/jenkins/jenkins)
- [Jenkins Agent](https://hub.docker.com/r/jenkins/agent)
- [SCM Manager](https://hub.docker.com/r/scmmanager/scm-manager)
- [Docker Registry](https://hub.docker.com/_/registry)

# How to Use

# Local Testing

There is a [workspace](workspace) folder with the necessary files,
such as Docker Compose file and Dockerfiles for master and agent
to easily prepare a simple infrastructure for local testing.

### Run Docker

```shell
cd workpace && \
docker compose up -d --build
```

### Configure SCM Manager

### Add the library in Jenkins

### Add job in Jenkins

The SCM Manager available in docker internal network with the address you find using the following command.

```shell
USER=admin && \
REPO=sapsan && \
echo "http://$(docker network inspect sapsan_default | grep Gateway | awk '{ print $2 }' | cut -d '"' -f 2):8081/scm/repo/$USER/$REPO/"
```