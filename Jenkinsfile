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
                withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]) {
                    bat 'kubectl apply -f k8s/namespace.yaml --kubeconfig=%KUBECONFIG%'
                    bat 'kubectl apply -f k8s/deployment.yaml --kubeconfig=%KUBECONFIG%'
                    bat 'kubectl apply -f k8s/service.yaml --kubeconfig=%KUBECONFIG%'
                }
            }
        }
    }
post {
        failure {
            echo '⚠️ El pipeline falló. Enviando notificación por correo...'

            emailext subject: "❌ Fallo en el pipeline de proyectofinal",
                     body: """Hola Manuel,

El pipeline del proyecto *proyectofinal* ha fallado durante su ejecución en Jenkins.

Por favor revisa los logs del pipeline para más detalles:
- Proyecto: proyectofinal
- Repositorio: https://github.com/ManuelArce2/proyectofinaldevops

Saludos,
Tu Jenkins Bot
""",
                     to: 'jmanuel2101@gmail.com'
}
}
}