pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'echo "Cleaning target dir and packaging JAR file!! :)"'
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Unit Testing') {
            steps {
                echo "Running unit tests..."
                sh 'mvn -Dtest=BodyMassIndexTest,InputValidationTest,MainMenuTest,RetirementTest,ShortestDistanceTest,SqlDatabaseTest,TipCalculatorTest test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Integration Testing') {
            steps {
                echo "Now testing Mocks and Database usage..."
                sh 'mvn -Dtest=MockMainMenuTest,MockShortestDistanceTest,MockSqlDatabaseTest test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Server Testing') {
            steps {
                echo "Now testing HTTP Server and GET/POST Requests..."
                sh 'mvn -Dtest=ServerTest test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
    }
}