pipeline {
  agent any
  
  stages {
    stage('Clone repository') {
      steps {
        git branch: 'main', url: 'https://github.com/ArtemMantulo/rms-taf.git'
      }
    }
    
    stage('Build') {
      steps {
        sh 'mvn clean install'
      }
    }
    
    stage('Run tests') {
      steps {
        sh 'mvn test -Dtest=NewTests'
        office365ConnectorSend (
        status: "Pipeline Status",
        webhookUrl: "${MSTEAMS_HOOK}",
        color: '00ff00',
        message: "Test Successful: ${JOB_NAME} - ${BUILD_DISPLAY_NAME}<br>Pipeline duration: ${currentBuild.durationString}"
  )
      }
      
      post {
        always {
          
          steps {
                office365ConnectorSend webhookUrl: "${URL_WEBHOOK}",
                message: 'Code is deployed',
                status: 'Success'            
            }
          
          publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'report', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: '', useWrapperFileDirectly: true])
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
