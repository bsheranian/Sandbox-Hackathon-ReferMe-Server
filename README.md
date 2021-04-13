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
* /postopening
* /recommendstudent

GET:
* /getmentor/{industry}/{mentor-id}
* /getmentors/{industry}?limit=10&last=null
* /getmymentors/{industry}?limit=10&last=null
* /getstudent/{industry}/{student-id}
* /getstudents/{industry}?limit=10&last=null
* /getmystudents/{industry}?limit=10&last=null
* /getopening/{industry}/{opening-id}
* /getopenings/{industry}?limit=10&last=null
* /getpendingmatches/{industry}?limit=10&last=null
* /getrecommendation/{recommendation-id}/{job-opening-id}
* /ismatched/{other-user-id}

TODO:
* /getrecommendations/{industry}/{job-opening-id}?limit=10&last=null
* /getcompanies/{industry}?limit=10&last=null
* /getcompany/{company-id}
* /getmyopenings/{company-id}?limit=10&last=null
* /isaccepted/{job-opening-id}/{recommendation-id}
* /getmyrecommendations?limit=10&last=null


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

* /postopening

   ```
   RESPONSE JSON:
   {
      "isSuccess":true,
      "message":"Login successful"
   }
   ```
* /recommendstudent
   ```
   RESPONSE JSON:
   {
      "isSuccess":true,
      "message":"Login successful"
   }
   ```
* /getmentor/{industry}/{mentor-id}

   ```
   RESPONSE JSON:
   {
      "isSuccess":true,
      "message":"Login successful"
   }
   ```
* /getmentors/{industry}?limit=10&last=null

   ```
   RESPONSE JSON:
   {
      "isSuccess":true,
      "message":"Login successful"
   }
   ```
* /getmymentors/{industry}?limit=10&last=null

   ```
   RESPONSE JSON:
   {
      "isSuccess":true,
      "message":"Login successful"
   }
   ```
* /getstudent/{industry}/{student-id}

   ```
   RESPONSE JSON:
   {
      "isSuccess":true,
      "message":"Login successful"
   }
   ```
* /getstudents/{industry}?limit=10&last=null

   ```
   RESPONSE JSON:
   {
      "isSuccess":true,
      "message":"Login successful"
   }
   ```
* /getmystudents/{industry}?limit=10&last=null

   ```
   RESPONSE JSON:
   {
      "isSuccess":true,
      "message":"Login successful"
   }
   ```
* /getopening/{industry}/{opening-id}

   ```
   RESPONSE JSON:
   {
      "isSuccess":true,
      "message":"Login successful"
   }
   ```
* /getopenings/{industry}?limit=10&last=null

   ```
   RESPONSE JSON:
   {
      "isSuccess":true,
      "message":"Login successful"
   }
   ```
* /getpendingmatches/{industry}?limit=10&last=null

   ```
   RESPONSE JSON:
   {
      "isSuccess":true,
      "message":"Login successful"
   }
   ```
* /getrecommendation/{recommendation-id}/{job-opening-id}

   ```
   RESPONSE JSON:
   {
      "isSuccess":true,
      "message":"Login successful"
   }
   ```
* /ismatched/{other-user-id}

   ```
   RESPONSE JSON:
   {
      "isSuccess":true,
      "message":"Login successful"
   }
   ```
