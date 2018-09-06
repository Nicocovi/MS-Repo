@Grab('io.github.http-builder-ng:http-builder-ng-okhttp:0.14.2')
import static groovy.json.JsonOutput.toJson
import static groovyx.net.http.HttpBuilder.configure

node {
    /*
    deleteDir()
    
    stage('Sources') {
        checkout([
                $class           : 'GitSCM',
                branches         : [[name: "refs/heads/master"]],
                extensions       : [[$class: 'CleanBeforeCheckout', localBranch: "master"]],
                userRemoteConfigs: [[
                                            credentialsId: 'cbf178fa-56ee-4394-b782-36eb8932ac64',
                                            url          : "https://github.com/Nicocovi/MS-Repo"
                                    ]]
                ])
    }
    */

    dir("") {
        /*
        stage("Build"){
            sh "gradle build"
        }
        
        stage('Deploy') {
            def branch = ['master']
            def name = "spring-microservice-demo"
            def path = "build/libs/gs-spring-boot-0.1.0.jar"
            def manifest = "manifest.yml"
            
               if (manifest == null) {
                throw new RuntimeException('Could not map branch ' + master + ' to a manifest file')
               }
               withCredentials([[
                                     $class          : 'UsernamePasswordMultiBinding',
                                     credentialsId   : '98c5d653-dbdc-4b52-81ba-50c2ac04e4f1',
                                     usernameVariable: 'CF_USERNAME',
                                     passwordVariable: 'CF_PASSWORD'
                             ]]) {
                sh 'cf login -a https://api.run.pivotal.io -u $CF_USERNAME -p $CF_PASSWORD --skip-ssl-validation'
                sh 'cf target -o ga72hib-org -s masterarbeit'
                sh 'cf push spring-microservice-demo -f '+manifest+' --hostname '+name+' -p '+path
            }
        }
        */
        
        stage("Get Jira Information"){
            //write get call
        }
        
        stage("Push Documentation"){
            /*http.request(POST) {
                uri.path = 'http://192.168.99.100:9123'
                body = [id: 'bob', name: 'spring-microservice-demo', type: 'service',owner: 'Nicolas',description: 'Simple microservice',domain: 'Finance']
                requestContentType = ContentType.JSON

                response.success = { resp ->
                    println "Success! ${resp.status}"
                }

                response.failure = { resp ->
                    println "Request failed with status ${resp.status}"
                }
            }*/
            //def cmd = "curl -H \"Accept: application/json\" -H \"Content-Type: application/json;charset=UTF-8\" -X POST http://192.168.99.100:9123/document -d \"{\"id\": \"25280205\",\"name\": \"demo-kick\",\"type\": \"service\",\"owner\": \"Miriam\",\"description\": \"Simple-microservice\",\"domain\": \"Finance\"}\" "
            //sh 'curl -H "Accept: application/json" -H "Content-Type: application/json;charset=UTF-8" -X POST http://192.168.99.100:9123/document -d "{"id": "25280205","name": "demo-kick","type": "service","owner": "Miriam","description": "Simple-microservice","domain": "Finance"}"'
            //sh 'documentation'
            def posts = configure {
                request.uri = 'http://192.168.99.100:9123'
                request.uri.path = '/document' 
                request.contentType = 'application/json'
                request.body = toJson(id: 'bob', name: 'spring-microservice-demo', type: 'service',owner: 'Nicolas',description: 'Simple microservice',domain: 'Finance')
            }.post()
        }//stage
        
    }

}
