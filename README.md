## Camunda Cinema
Camunda Cinema is a showcase for the different concepts in Camunda BPM:

- Error Handling
- Compensation
- Messages
- Timer
- Pools/Lanes
- Gateways
- Event Subprocess

### Versionss
- SpringBoot 2.3.1.RELEASE
- Camunda 7.14
- JDK 11
- ZXing (Zebra Crossing) 3.4 for QRCode generation

## Process Model
<img src="./src/main/resources/bpmn/ticket-reservation.png" width="900">

## How to run it
- start the application 
    - run `./gradlew bootRun` from your terminal
    - use your IDE to start the application
- go to `localhost:8087` to get to the camunda webapps
- use a REST client to make a reservation:
    - endpoint `localhost:8087/reservation`
    - POST request 
        - body: `{ "userId":"max.mustermann", "seats": [ "A1", "A2" ] }`
        - content-type: `application/json`
        - no auth necessary
- check the log output
