pipeline {
    agent any

    environment {
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
                    docker.build("${IMAGE_NAME_TEST}", "--file=Dockerfile.test .")
                }
            }
        }

        stage("Build") {
            steps {
                script {
                    image = docker.build("${DOCKER_IMAGE}", ".")
                }
            }
        }

        stage("Push") {
            steps {
                script {
                    docker.withRegistry("https://index.docker.io/v1/", "${DOCKER_CREDENTIALS}") {
                        image.push()
                    }
                }
            }
        }

        stage("Deploy") {
            steps {
                script {
                    bat 'kubectl cluster-info'
                    bat 'kubectl apply -f k8s/namespace.yaml'
                    bat 'kubectl apply -f k8s/deployment.yaml'
                    bat 'kubectl apply -f k8s/service.yaml'
                }
            }
        }
    }
}
