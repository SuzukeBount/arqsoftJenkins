pipeline {
    agent any

    environment {
        // Define DockerHub credentials
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-rodrigo1230207')
    }

    stages {
        stage('Build and Push Images') {
            steps {
                script {
                    // Assuming you have a list of directories for microservices
                    def services = [
                        'Product_command_microservice',
                        'Products_query_microservice',
                        'Review_command_microservice',
                        'Review_query_microservice',
                        'votes_command',
                        'votes_query'
                    ]

                    // Login to DockerHub
                    bat 'echo %DOCKERHUB_CREDENTIALS_PSW% | docker login -u %DOCKERHUB_CREDENTIALS_USR% --password-stdin'

                    // Iterate over each service, build and push
                    for (service in services) {
                        def imageTag = "${REPOSITORY}/${service.toLowerCase()}:latest"

                        // Build the Docker image
                        sh "docker build -t ${imageTag} ./${service}"

                        // Push the image to DockerHub
                        sh "docker push ${imageTag}"
                    }
                }
            }
        }
    }

    post {
        always {
            // Logout from DockerHub
            sh "docker logout ${REGISTRY}"

            // Optionally clean up after build
            sh "docker system prune -af"
        }
    }
}
