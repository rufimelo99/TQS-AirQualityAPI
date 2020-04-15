# TQS-AirQualityAPI
Midterm assignment for TQS (2019-20 UA) Air Quality API

## Run App:
-Clone the Project and enter the directory. Then use the command:
##### sudo mvn spring-boot:run

-Run the Tests:
##### sudo mvn clean package

It is a Rest API with a simple interface. It is connected to an external api:
##### https://aqicn.org/api
##### Token: 72efba96064c6901c4cb6d7330ec55f94b553947
It stores temporarly the information received, creating CityInfo objects. Each of this objects has information regarding Ozone, Sulphur Dioxide, Nitrogen Dioxide, Wind and even Precipitation.

##### Main Page: localhost:8084/

##### All Info Received:localhost:8084/CityInfo

##### Info about station nº 32: localhost:8084/CityInfo/32

##### Info about stations which name matches "Lisbon": localhost:8084/CityInfo/ByName/Lisboa
