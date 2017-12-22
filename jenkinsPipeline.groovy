#!groovy

node {
    def mvnHome

    environment {
        GIT_CREDENTIALS_ID = "44bb31cd-b259-4160-a31c-64403b8982d3"
    }

    stage('Preparation') {
        git changelog: false,
            credentialsId: '$GIT_CREDENTIALS_ID',
            poll: false,
            url: 'https://github.com/deveshchanchlani/StudentService.git'
        mvnHome = tool 'maven3'
    }

    stage('Test') {
        if (isUnix()) {
            sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean test"
        } else {
            bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean test/)
        }
    }

    stage('IntegrationTest') {
        if (isUnix()) {
            sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean verify -P integration-test"
        } else {
            bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean verify -P integration-test/)
        }
    }
}
