pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                checkout scm
                sh 'chmod +x ./gradlew && ./gradlew build'
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