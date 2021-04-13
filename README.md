# Sandbox Hackathon ReferMe Server
An AWS server for an app called ReferMe that solves the internship/job recruiting problem for both students and employers.

# API Gateway Endpoints:

POST:
* /login
REQUEST JSON:
```
{
   "username":"text",
   "password":"text",
   "userType":0
}
```
RESPONSE JSON:
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

