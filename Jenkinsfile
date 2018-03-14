pipeline {
    agent any

    def server = Artifactory.server 'arti'
    def rtGradle = Artifactory.newGradleBuild()
    rtGradle.resolver server: server, repo: 'sop6-virt'
    rtGradle.deployer server: server, repo: 'sop6-local'
    rtGradle.deployer.deployArtifacts = false
    rtGradle.useWrapper = true
    def buildInfo = rtGradle.run rootDir: "/", tasks: 'clean build'


    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
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
                rtGradle.deployer.deployArtifacts buildInfo
            }
        }
    }
}