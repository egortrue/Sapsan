# Sapsan

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
    <a><img src="https://github.com/egotrue/Sapsan/actions/workflows/linter.yml/badge.svg"></a>
</p>

## Description

The ultimate DevOps Framework based
on ["Jenkins Shared Library"](https://www.jenkins.io/doc/book/pipeline/shared-libraries/)

## Requirements

### System

- `Any`
- `Unix for local testing`

### Software

- `Git`
- `Docker`
- `Docker Compose`

### Jenkins Plugins

- [Remote Jenkinsfile Provider](https://plugins.jenkins.io/remote-file/) **(required)**
- [Pipeline Utility Steps](https://plugins.jenkins.io/pipeline-utility-steps/) **(required)**
- [Workspace Cleanup](https://plugins.jenkins.io/ws-cleanup/) **(required)**
- [Ansi Color](https://plugins.jenkins.io/ansicolor/) **(required)**
- [Mask Passwords](https://plugins.jenkins.io/mask-passwords/) **(required)**
- [Docker Workflow](https://plugins.jenkins.io/docker-workflow/) _(local testing)_
- [Jenkins Swarm Agent](https://plugins.jenkins.io/swarm/) _(local testing)_
- And all the suggested plugins from
  official [Jenkins repository](https://github.com/jenkinsci/jenkins/blob/master/core/src/main/resources/jenkins/install/platform-plugins.json)

### Docker Images (Local Testing)

- [Jenkins Master](https://hub.docker.com/r/jenkins/jenkins)
- [Jenkins Agent](https://hub.docker.com/r/jenkins/agent)
- [SCM-Manager](https://scm-manager.org)

## How to Use

### Add Global Pipeline Library

1. Go to `Manage Jenkins >  Configure System > Global Pipeline Libraries`
2. Press `Add`
3. Set `Name` to `sapsan`
4. Set `Default version` to `master` or `develop` for your desire
5. Set `Retrival method` to `Modern SCM`
6. Set `Project Repository` to `https://github.com/egortrue/Sapsan`

### Add new Job

1. Press `Add new Item` on the main page
2. Set `Name` for project (it should be the same for folders in `/resources/configurations/project/`)
3. For **classic** pipeline
    - Choose `Pipeline`
    - Press `Create`
    - Set `Pipeline > Definition` to `Pipeline script from SCM`
    - Set `SCM` to `Git`
    - Set `Repository URL` to `https://github.com/egortrue/Sapsan`
    - Set `Script path` to one of the [pipelines](/resources/pipelines/) -> `resources/pipelines/*.pipeline.groovy`
4. For **multibranch** pipeline
    - Setup `Branch Sources` for project to build
    - Set `Build Confguration > Mode` to `By Remote Jenkinsfile Provider`
    - Set `Script path` to one of the [pipelines](/resources/pipelines/) -> `resources/pipelines/*.pipeline.groovy`
    - Set `Jenkinsfile SCM` to `Git`
    - Set `Repository URL` to `https://github.com/egortrue/Sapsan`
5. Run Job

# Developing and Testing

There is a [workspace](workspace) folder with the necessary files,
such as Docker Compose file and Dockerfiles for jenkins master and agent
to easily prepare a simple infrastructure for local developing and testing.

### Startup

The following [docker compose](/workspace/docker-compose.yaml) command starts 3 containers in `workspace` directory:

1. `jenkins-master` for hosting Jenkins on `http://localhost:8080`
2. `jenkins-agent` for executing pipelines _(have no open ports to connect from host)_
3. `scm-manager` for storing source code on `http://localhost:8081/scm`

```shell
cd workspace && docker compose up -d --build
```

### Using internal SCM

If you do not have explicit repository on any SCM platform (such as GitHub, GitLab, BitBucket)
for developing and testing this project, you can use opensource
[SCM-Manager](https://scm-manager.org) as docker container.

1. Register a dummy account (for example, `admin:admin`) on `http://localhost:8081/scm`
2. Add new repository called `sapsan`
3. Push this project to the repository

**Use the following address to configure Jenkins instead of this GitHub repository**.
The SCM available in docker internal network with the address you find out using the following command.

```shell
USER=admin && \
REPO=sapsan && \
echo "http://$(docker network inspect sapsan_default | grep Gateway | awk '{ print $2 }' | cut -d '"' -f 2):8081/scm/repo/$USER/$REPO/"
```

Next, follow the instructions in `How to Use` section
