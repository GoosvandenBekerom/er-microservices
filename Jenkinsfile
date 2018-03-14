pipeline {
    agent any

    stages {
        stage('Checkout') {
            checkout scm
        }
        stage('Build') {
            steps {
                sh 'chmod +x ./gradlew && ./gradlew clean build'
                archiveArtifacts artifacts: '**/build/libs/*.war', fingerprint: true
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}