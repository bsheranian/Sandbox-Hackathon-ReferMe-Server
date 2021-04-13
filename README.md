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
      "username": "",
      "password": "",
      "userType": 0
   }
   
   HTTP 200 RESPONSE JSON:
   {
      "success": true,
      "authToken": {
         "token": "",
         "username": ""
      }
   }
   ```
* /logout

   ```
   REQUEST JSON:
   {
      "username": "",
      "password": "",
      "userType": 0
   }
   
   HTTP 200 RESPONSE JSON:
   {
      "success": true,
      "message": ""
   }
   ```
* /register/student

   ```
   REQUEST JSON:
   {
      "newStudent": {
         "email": "",
         "password": "",
         "name": "",
         "imageUrl": "",
         "industry": "",
         "school": "",
         "gpa": 0,
         "major": ""
      }
   }
   
   HTTP 200 RESPONSE JSON:
   {
      "success": true,
      "authToken": {
         "token": "",
         "username": ""
      }
   }
   ```
* /register/mentor

   ```
   REQUEST JSON:
   {
      "newMentor": {
         "email": "",
         "password": "",
         "name": "",
         "imageUrl": "",
         "industry": "",
         "school": "",
         "rating": "",
         "moneyMade": 0,
         "yearsExperience": 0
      }
   }
   
   HTTP 200 RESPONSE JSON:
   {
      "success": true,
      "authToken": {
         "token": "",
         "username": ""
      }
   }
   ```
* /register/company

   ```
   REQUEST JSON:
   {
      "newCompany":{
         "email": "",
         "password": "",
         "name": "",
         "imageUrl": "",
         "websiteUrl": "",
         "description": ""
      }
   }
   
   HTTP 200 RESPONSE JSON:
   {
      "success": true,
      "authToken": {
         "token": "",
         "username": ""
      }
   }
   ```
* /requestmatch/{user-id-to-match-with}

   ```
   REQUEST JSON:
   {
      "requestedUserId": ""
   }
   
   HTTP 200 RESPONSE JSON:
   {
      "success": true,
      "message": ""
   }
   ```
* /acceptmatch/{user-id-to-match-with}

   ```
   REQUEST JSON:
   {
      "username": "text",
      "password": "text",
      "userType": 0
   }
   
   HTTP 200 RESPONSE JSON:
   {
      "success": true,
      "message": ""
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
   
   HTTP 200 RESPONSE JSON:
   {
      "success": true,
      "message": ""
   }
   ```

* /postopening

   ```
   REQUEST JSON:
   {
      "jobOpening":{
         "industry": "",
         "companyId": "",
         "jobDescription": ""
      }
   }
   
   HTTP 200 RESPONSE JSON:
   {
      "message": "",
      "success": true
   }
   ```
* /recommendstudent
   ```
   REQUEST JSON:
   { 
      "studentEmail": "",
      "mentorEmail": "",
      "message":  "",
      "jobOpeningId": ""
   }
   
   HTTP 200 RESPONSE JSON:
   {
      "message": "",
      "success": true
   }
   ```
* /getmentor/{industry}/{mentor-id}

   ```
   HTTP 200 RESPONSE JSON:
   {
      "success": true,
      "message": ""
   }
   ```
* /getmentors/{industry}?limit=10&last=null

   ```
   HTTP 200 RESPONSE JSON:
   {
      "success": true,
      "message": ""
   }
   ```
* /getmymentors/{industry}?limit=10&last=null

   ```
   HTTP 200 RESPONSE JSON:
   {
      "success": true,
      "message": ""
   }
   ```
* /getstudent/{industry}/{student-id}

   ```
   HTTP 200 RESPONSE JSON:
   {
      "success": true,
      "message": ""
   }
   ```
* /getstudents/{industry}?limit=10&last=null

   ```
   HTTP 200 RESPONSE JSON:
   {
      "success": true,
      "message": ""
   }
   ```
* /getmystudents/{industry}?limit=10&last=null

   ```
   HTTP 200 RESPONSE JSON:
   {
      "success": true,
      "message": ""
   }
   ```
* /getopening/{industry}/{opening-id}

   ```
   HTTP 200 RESPONSE JSON:
   {
      "success": true,
      "message": ""
   }
   ```
* /getopenings/{industry}?limit=10&last=null

   ```
   HTTP 200 RESPONSE JSON:
   {
      "success": true,
      "message": ""
   }
   ```
* /getpendingmatches/{industry}?limit=10&last=null

   ```
   HTTP 200 RESPONSE JSON:
   {
      "success": true,
      "message": ""
   }
   ```
* /getrecommendation/{recommendation-id}/{job-opening-id}

   ```
   HTTP 200 RESPONSE JSON:
   {
      "success": true,
      "message": ""
   }
   ```
* /ismatched/{other-user-id}

   ```
   HTTP 200 RESPONSE JSON:
   {
      "success": true,
      "message": ""
   }
   ```
