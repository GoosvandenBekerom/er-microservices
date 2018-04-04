node {
    def server
    def buildInfo
    def rtGradle

    stage ('Clone') {
        checkout scm
        sh 'chmod +x ./gradlew'
    }
/*
    stage('SonarQube analysis') {
        withSonarQubeEnv('er-microservices') {
            sh './gradlew --info sonarqube'
        }
    }

    stage ('Artifactory configuration') {
        server = Artifactory.server 'arti'

        rtGradle = Artifactory.newGradleBuild()
        rtGradle.useWrapper = true
        rtGradle.deployer repo: 'sop6-local', server: server
        rtGradle.resolver repo: 'sop6-virt', server: server
        rtGradle.deployer.deployArtifacts = false

        buildInfo = Artifactory.newBuildInfo()
    }
*/
    stage ('Tests') {
        //rtGradle.run rootDir: './', buildFile: 'build.gradle', tasks: 'clean test'
        sh './gradlew clean test'
    }

    stage ('Remote Deploy') {
        sh './gradlew deploy'
    }
/*
    stage ('Deploy') {
        rtGradle.run rootDir: './', buildFile: 'build.gradle', tasks: 'artifactoryPublish', buildInfo: buildInfo
        rtGradle.deployer.deployArtifacts buildInfo
    }

    stage ('Publish build info') {
        server.publishBuildInfo buildInfo
    }
*/
}
