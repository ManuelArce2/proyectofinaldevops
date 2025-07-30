pipeline {
    agent any

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
                    docker.build('proyectofinaltesting', '--file=Dockerfile.test .')
                }
            }
    
        }
    }
}