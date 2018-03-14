pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                checkout scm
                sh 'chmod +x ./gradlew'
                sh './gradlew build -x'
                archiveArtifacts artifacts: '**/build/libs/*.war', fingerprint: true
            }
        }
        stage('Test') {
            steps {
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