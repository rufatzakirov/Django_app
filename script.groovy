def buildApp(){
    echo "---building the docker image---"
    withCredentials([usernamePassword(credentialsId: 'nexus-repo', passwordVariable: 'password', usernameVariable: 'username')]) {
    sh "echo $password | docker login 192.168.101.132:8088 -u $username --password-stdin"
    sh "docker build -t 192.168.101.132:8088/django_app:$app$BUILD_NUMBER ."
    sh "docker push 192.168.101.132:8088/django_app:$app$BUILD_NUMBER"
    }
}

def deployApp(){
    echo "---deploy application---"
    sh "docker-compose up -d"  
}

return this
