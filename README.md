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

The ultimate DevOps Framework based
on ["Jenkins Shared Library"](https://www.jenkins.io/doc/book/pipeline/shared-libraries/)

# Requirements

#### System: Any (Unix for local testing)

#### Software:

- `Git`
- `Docker`
- `Docker Compose`

#### Jenkins Plugins:

- [Remote Jenkinsfile Provider](https://plugins.jenkins.io/remote-file/) **(required)**
- [Pipeline Utility Steps](https://plugins.jenkins.io/pipeline-utility-steps/) **(required)**
- [Workspace Cleanup](https://plugins.jenkins.io/ws-cleanup/) **(required)**
- [Ansi Color](https://plugins.jenkins.io/ansicolor/) **(required)**
- [Mask Passwords](https://plugins.jenkins.io/mask-passwords/) **(required)**
- [Docker Workflow](https://plugins.jenkins.io/docker-workflow/) _(local testing)_
- [Jenkins Swarm Agent](https://plugins.jenkins.io/swarm/) _(local testing)_
- And all the suggested plugins from
  official [Jenkins repository](https://github.com/jenkinsci/jenkins/blob/master/core/src/main/resources/jenkins/install/platform-plugins.json)

#### Docker Images (Local Testing):

- [Jenkins Master](https://hub.docker.com/r/jenkins/jenkins)
- [Jenkins Agent](https://hub.docker.com/r/jenkins/agent)
- [SCM-Manager](https://scm-manager.org)

# How to Use

#### Add global pipeline library

This project can be imported as any other pipeline library

# Developing and Testing

There is a [workspace](workspace) folder with the necessary files,
such as Docker Compose file and Dockerfiles for jenkins master and agent
to easily prepare a simple infrastructure for local testing.

#### Startup

The following script starts 3 containers:

1. `Jenkins Master` for hosting Jenkins
2. `Jenkins Agent` for executing pipelines
3. `SCM-Manager` for storing source code of this project

```shell
cd workspace && docker compose up -d --build
```

#### Using internal SCM

If you do not have explicit repository on any SCM platform (such as GitHub, GitLab, BitBucket)^
You can use opensource [SCM-Manager](https://scm-manager.org) as docker container.

1. Register a dummy account (for example, `admin:admin`) on `http://localhost:8081/scm`
2. Add new repository called `sapsan`
3. Push this project to the repository

The SCM available in docker internal network with the address you find out using the following command.
**Use this address to configure Jenkins instead**

```shell
USER=admin && \
REPO=sapsan && \
echo "http://$(docker network inspect sapsan_default | grep Gateway | awk '{ print $2 }' | cut -d '"' -f 2):8081/scm/repo/$USER/$REPO/"
```

Follow the instructions in `How to Use` section
