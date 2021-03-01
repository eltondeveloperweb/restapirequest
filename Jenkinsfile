pipeline {
    agent any
    stages {
        stage ('Build Backend') {
            steps {
                sh 'mvn clean package -DskipTests=true'
            }
        }
        stage ('Unit Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage ('Sonar analysis') {
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps {
                withSonarQubeEnv('SONAR_LOCAL') {
                    sh "${SONAR_SCANNER}/bin/sonar-scanner -e -Dsonar.projectKey=RestAPIRequest -Dsonar.host.url=http://localhost:9000 -Dsonar.login=e50fc1222f8fe228a89b209107e4e44227b8f393 -Dsonar.java.binaries=target"
                }
            }
        }
    }
} 


