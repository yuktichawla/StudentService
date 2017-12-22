#!groovy

node {
   def mvnHome

   environment {
       CREDENTIALS_ID = "44bb31cd-b259-4160-a31c-64403b8982d3"
   }

   stage('Preparation') {
      properties([
         pipelineTriggers([[$class: 'GitHubPushTrigger'], 
         pollSCM('')])
      ])
      checkout([
         $class: 'GitSCM',
         branches: [[name: '*/master']], 
         doGenerateSubmoduleConfigurations: false, 
         extensions: [], 
         submoduleCfg: [], 
         userRemoteConfigs: [[
            credentialsId: '$CREDENTIALS_ID',
            url: 'https://github.com/deveshchanchlani/StudentService.git'
         ]]
      ])         
      mvnHome = tool 'maven3'
   }

   stage('Test') {
      // Run the maven build
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean test"
      } else {
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean test/)
      }
   }

   stage('IntegrationTest') {
      if(isUnix()) {
         sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean verify -P integration-test"
      } else {
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean verify -P integration-test/)
      }
   }
}
