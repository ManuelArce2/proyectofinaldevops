pipeline {
    agent any
    environment{
        IMAGE_NAME = 'proyectofinal'
        IMAGE_NAME_TEST = 'proyectofinaltesting'
        DOCKER_USERNAME = 'man2101'
        DOCKER_CREDENTIALS = 'docker-hub-credentials'
        DOCKER_IMAGE = "${DOCKER_USERNAME}/${IMAGE_NAME}"

    }

    triggers {
        githubPush()
    }
    
    stages {
        stage("Checkout") {
            steps {
                git branch: 'main', url: 'https://github.com/ManuelArce2/proyectofinaldevops.git'
            }
        }
        stage("Test") {
            steps {
                script {
                    docker.build('IMAGE_NAME_TEST', '--file=Dockerfile.test .')
                }
            }
    
        }
        stage("Build") {
            steps {
                script {
                    docker.build('DOCKER_IMAGE', '.')
                }
            }
        }
    }
}