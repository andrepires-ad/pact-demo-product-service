#!/usr/bin/env groovy

@Library('jenkins-shared-library') _

prBranchRegex = /(pr|PR)-\d+$/
repositoryOwner = "andrepires-ad"
repositoryName = "pact-demo-product-service"

pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out from repository...'
                checkout scm: [
                        $class: 'GitSCM',
                        branches: scm.branches,
                        userRemoteConfigs: scm.userRemoteConfigs,
                        extensions: [
                                [$class: 'CloneOption', noTags: false],
                                [$class: 'LocalBranch', localBranch: "**"]
                        ]
                ]
                script {
                    echo sh(returnStdout: true, script: 'env')
                    withPullRequestBranch {
                        sh "echo \"PR_NUMBER=$env.BRANCH_NAME\" >> gradle.properties"
                    }
                }
            }
        }

        stage('Build') {
            steps {
                echo 'Building project without tests.'
                sh "./gradlew clean build -x test"
            }
        }

        stage('Tests') {
            steps {
                echo 'Performing unit tests.'
            }
        }

        stage('Static Analysis') {
            steps {
                echo 'Performing static analysis.'
            }
        }

        stage('Code Coverage') {
            steps {
                echo "Running code coverage."
            }
        }

        stage('Artifacts') {
            steps {
                echo "Publish artifacts."
            }
        }
    }
}