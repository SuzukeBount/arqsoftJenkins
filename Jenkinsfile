pipeline {
    agent any

    environment {
        // Define DockerHub credentials
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-rodrigo1230207')
        REPOSITORY = 'rodrigo1230207' // Replace with your actual repository

    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Build and Push Images') {
            steps {
                script {
                    // Assuming you have a list of directories for microservices
                    def services = [
                        'Product_command_microservice',
                        'Products_query_microservice',
                        'Review_command_microservice',
                        'Review_query_microservice'
                        // 'votes_command',
                        // 'votes_query'
                    ]

                    // Login to DockerHub
                    bat 'echo %DOCKERHUB_CREDENTIALS_PSW% | docker login -u %DOCKERHUB_CREDENTIALS_USR% -p %DOCKERHUB_CREDENTIALS_PSW%'

                    // Iterate over each service, build and push
                    for (service in services) {

                        def image = "${REPOSITORY}/${service.toLowerCase()}:latest"
                        // Build the Docker image using Windows batch command
                        bat "docker build -t ${image} ./${service}"
                        // Push the image to Docker Hub
                        bat "docker push ${image}"
                    }
                }
            }
        }
    }
}
