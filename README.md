# BSF Service
it is all about bank accounts with details and how to manage money transfer between accounts


# Run the application
    mvn clean install
    mvn spring-boot:run
    
# OpenAPI file Directory

    https://github.com/EvraamHany/bsf/blob/main/src/main/resources/static/bsf.yaml
    
    
# DockerFile directory 

    https://github.com/EvraamHany/bsf/blob/main/Dockerfile
    
 ### Run Docker file
    mvn clean backage
    then run dokerFile
    

# API Requests
all endpoints examples as below
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
