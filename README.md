# BSF Service
A service to handle bank accounts and bank transaction between accounts

## the used technologies 
    java 11
    spring-boot
    Open-API
    docker
    H2 database


# Run the application
    mvn clean install
    mvn spring-boot:run
    
    
# OpenAPI 
### use this url to get the swagger UI after running the application on local machine
        http://localhost:8080/swagger-ui.html
### you can find the swagger file here
    https://github.com/EvraamHany/bsf/blob/main/src/main/resources/static/bsf.yaml
    
    
# DockerFile 
 ### Run Docker file
    mvn clean backage
    docker build -f "Dockerfile" .
    docker images   #(to show the created image ID)
    docker run <ImageID>
### you can find the docker file here
    https://github.com/EvraamHany/bsf/blob/main/Dockerfile
    
# DataBase 
## DataBase file
    https://github.com/EvraamHany/bsf/blob/main/src/main/resources/data.sql
    
    will be automatically apply to h2 database when application starts
## Database Console 
    http://localhost:8080/h2-console
    
    url: jdbc:h2:mem:bsf
    userName: as
    password: password
    

    
    

# API Requests
end point to get account and another to make transfer
## get account details
### Request
    'GET /account/{accountNumber}'
    
            curl --location --request GET 'http://localhost:8080/api/account/1234' \
               --header 'Accept: application/json'

### Response
    {"accountNumber":"1234","name":"evram","balance":193}
    
    
## Make Money Transfer
### Request

    'POST /transfer'
          
          curl --location --request POST 'http://localhost:8080/api/transfer' \
           --header 'Accept: application/json' \
           --header 'Content-Type: application/json' \
           --data-raw '{
           "creditAccount": "1234",
           "debitAccount": "345",
           "amount": 20.99
          }'
          
### Response

      {"creditAccount":{"id":1,"accountNumber":"1234","name":"evram","balance":179.26},"debitAccount":         {"id":2,"accountNumber":"345","name":"hany","balance":221.33},"amount":20.99}
