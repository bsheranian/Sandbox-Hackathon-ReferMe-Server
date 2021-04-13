# Sandbox Hackathon ReferMe Server
An AWS server for an app called ReferMe that solves the internship/job recruiting problem for both students and employers.

# API Gateway Endpoints:

POST:
* /login
 * request:
          ```
          {
            "username":"text",
            "password":"text",
            "userType":0
          }
          ```
    * response:
       ```
       {
         "isSuccess":true,
         "message":"Login successful"
       }
       ```
* /logout
* /register/student
* /register/mentor
* /register/company
* /requestmatch/{user-id-to-match-with}
* /acceptmatch/{user-id-to-match-with}
* /declinematch/{user-id-to-match-with}

GET:

