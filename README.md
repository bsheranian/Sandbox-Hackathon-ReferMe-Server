# Sandbox Hackathon ReferMe Server
An AWS server for an app called ReferMe that solves the internship/job recruiting problem for both students and employers.

Server link: https://pji3ct6u5g.execute-api.us-west-2.amazonaws.com/hackathon

Example path to try: https://pji3ct6u5g.execute-api.us-west-2.amazonaws.com/hackathon/getmentors/demo?limit=10&last=null


## API Gateway Endpoints:


POST:
* /login
* /register/student
* /register/mentor
* /register/company
* /requestmatch
* /acceptmatch
* /declinematch


GET:

## HTTP Responses

* `200`: HTTP OK
* `400`: INCORRECT USERNAME
* `410`: INCORRECT PASSWORD
* `420`: SESSION EXPIRED
* `430`: USERNAME ALREADY TAKEN 
* `500`: SERVER ERROR


## JSON Request and Response Models


* /login

   ```
   REQUEST JSON:
   {
      "username":"",
      "password":"",
      "userType":0
   }
   HTTP 200 RESPONSE JSON:
   {
      "isSuccess":true,
      "message":""
   }
   ```
* /logout

   ```
   REQUEST JSON:
   {
      "username":"text",
      "password":"text",
      "userType":0
   }
   RESPONSE JSON:
   {
      "isSuccess":true,
      "message":"Login successful"
   }
   ```
* /register/student

   ```
   REQUEST JSON:
   {
      "username":"text",
      "password":"text",
      "userType":0
   }
   RESPONSE JSON:
   {
      "isSuccess":true,
      "message":"Login successful"
   }
   ```
* /register/mentor

   ```
   REQUEST JSON:
   {
      "username":"text",
      "password":"text",
      "userType":0
   }
   RESPONSE JSON:
   {
      "isSuccess":true,
      "message":"Login successful"
   }
   ```
* /register/company

   ```
   REQUEST JSON:
   {
      "username":"text",
      "password":"text",
      "userType":0
   }
   RESPONSE JSON:
   {
      "isSuccess":true,
      "message":"Login successful"
   }
   ```
* /requestmatch/{user-id-to-match-with}

   ```
   REQUEST JSON:
   {
      "username":"text",
      "password":"text",
      "userType":0
   }
   RESPONSE JSON:
   {
      "isSuccess":true,
      "message":"Login successful"
   }
   ```
* /acceptmatch/{user-id-to-match-with}

   ```
   REQUEST JSON:
   {
      "username":"text",
      "password":"text",
      "userType":0
   }
   RESPONSE JSON:
   {
      "isSuccess":true,
      "message":"Login successful"
   }
   ```
* /declinematch/{user-id-to-match-with}

   ```
   REQUEST JSON:
   {
      "username":"text",
      "password":"text",
      "userType":0
   }
   RESPONSE JSON:
   {
      "isSuccess":true,
      "message":"Login successful"
   }
   ```

