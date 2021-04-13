# Sandbox Hackathon ReferMe Server
An AWS server for an app called ReferMe that solves the internship/job recruiting problem for both students and employers.

https://pji3ct6u5g.execute-api.us-west-2.amazonaws.com/hackathon

TRY THIS:

https://pji3ct6u5g.execute-api.us-west-2.amazonaws.com/hackathon/getmentors/demo/limit=10


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

## JSON Request and Response Models


* /login

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

