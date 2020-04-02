## Camunda Cinema
Camunda Cinema is a showcase for the different concepts in Camunda BPM:

- Error Handling
- Compensation
- Messages
- Timer
- Pools/Lanes
- Gateways
- Event Subprocess

## How to run it
- start the application 
    - run `./gradlew bootRun` from your terminal
    - use your IDE to start the application
- go to `localhost:8080` to get to the camunda webapps
- use a REST client to make a reservation:
    - endpoint `localhost:8080/reservation`
    - POST request 
        - body: `{ "id":"775f77ba-8cda-4052-a5c1-8c55110890b4",
      "userid":"max.mustermann",
      "seats": [ "A1", "A2" ] }`
        - content-type: `application/json`
        - no auth necessary
- check the log output