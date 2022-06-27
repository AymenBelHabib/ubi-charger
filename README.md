# Ubitricity
![](https://www.ubitricity.com/wp-content/uploads/2021/02/cropped-ubitricity-a-member-of-the-shell-group-g.png)
## Test : ubi car charging station Api
 
Rest Api using spring boot, used port :8081

- java version 17  
- gradle version 5.6.2
- thymeleaf version 2.7.0

## available services
- Ubitricity charging point :  
````sh
/ubi/cp/{charging point number} # HTTP - Method POST : to plug a car 
/ubi/cp/{charging point number}  HTTP - Method DELETE : to unplug a car from the CP
````
- Ubitricity charging station :
````sh
/ubi/station   # HTTP - Method GET: get floor 
````  
 
## tests
> I added tests for entity (UbiStation). I added integration tests (ChargingPointController) as an example. I also added /error to communicate the api paths and rules 
 
  
## solution logic 
* solution:
 I used observer pattern to comunicate any update to all CPs, to comunicat to each CP what Amp should provide to the car.
whenever a car is plugged the sheet is updated respectvly to the task rules and notify CPs. so an update of the setting is applied of the concerned CP which has new changes to make (lower the Amp to 10 or increase it to 20).      

* thread safety: 
the solution is using a thread safe dataStructure to hold a sheet of CPs configuration which was comunicated between CPs. 
also the station plug unlpug fonctions are sync to prevent any collision. 




the Api is available in  http://localhost:8081/ 
 
 
 
 
