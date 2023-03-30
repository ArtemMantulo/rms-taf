pipeline {
  agent any
  
  stages {
    stage('Clone repository') {
      steps {
        git branch: 'main', url: 'https://github.com/ArtemMantulo/rms-taf.git'
      }
    }
    
    stage('Build and run tests') {
      steps {
        sh 'mvn clean test -Dtest=NewTests'
      }
      
      post {
        always {
          allure([
            includeProperties: false,
            jdk: '',
            properties: [],
            reportBuildPolicy: 'ALWAYS',
            results: [[path: 'target/allure-results']]
          ])
          
          script {
            if(currentBuild.result == 'SUCCESS') {
              teams(
                color: '#00FF00',
                message: 'Allure report for the latest build',
                summary: 'Allure report',
                connector: [$class: 'TeamsStandardConnector', webhookUrl: 'https://outlook.office.com/webhook/...'],
                attachments: [[
                  contentType: 'application/html',
                  contentUrl: "${env.BUILD_URL}allure",
                  fallback: 'Allure report',
                  name: 'Allure report.html'
                ]]
              )
            }
          }
        }
      }
    }
  }
}
