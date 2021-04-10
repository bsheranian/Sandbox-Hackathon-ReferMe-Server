#!/bin/bash

#   IMPORTANT:
#     - run shadowJar to update the server-all.jar
#       before updating lambda functions.
#     - run 'aws configure' before executing to make
#       sure you are using the correct credentials.
#   USAGES:
#       ./upload_to_aws_lambda.sh [function1] [function2] ...
#       ./upload_to_aws_lambda.sh -all
#       ./upload_to_aws_lambda.sh -tyla
#       ./upload_to_aws_lambda.sh -melissa
#       ./upload_to_aws_lambda.sh -bryan
#       ./upload_to_aws_lambda.sh (hard code desired functions below)


lamdaFunctions=()

for func in "$@"
do
    lamdaFunctions+=($func)
done

case "$1" in
  -all)
    lamdaFunctions=(
      "sandboxRegisterStudent"
      "sandboxRegisterMentor"
      "sandboxRegisterCompany"
      "sandboxLogin"
      "sandboxLogout"
      "sandboxAcceptMatch"
      "sandboxGetRecommendations"
      "sandboxGetOpening"
      "sandboxRequestMatch"
      "sandboxGetMentor"
      "sandboxIsMatched"
      "sandboxGetStudent"
      "sandboxGetPendingMatches"
      "sandboxDeclineMatch"
      "sandboxGetMyStudents"
      "sandboxGetStudents"
      "sandboxGetMyMentors"
      "sandboxGetOpenings"
      "sandboxGetMentors"
      "sandboxPostOpening"
      "sandboxRecommendStudent"
      "sandboxGetRecommendation"
    ) ;;
esac

jarFile="./out/artifacts/SandboxHackathonServer_jar/SandboxHackathonServer.jar"
outputFile="output.txt"

for func in "${lamdaFunctions[@]}";
do
  aws lambda update-function-code --function-name "$func" --zip-file fileb://"$jarFile" >> "$outputFile"
done

declare -i numSuccessfulUploads=0

if grep -q "Successful" "$outputFile";
then
  for func in "${lamdaFunctions[@]}";
  do
    if grep -q $func $outputFile;
    then
      numSuccessfulUploads+=1
    fi
  done
fi


if grep -q "Successful" "$outputFile";
then
  echo
  echo "SUCCESSFUL UPLOADS: ($numSuccessfulUploads)"
  for func in "${lamdaFunctions[@]}";
  do
    if grep -q $func $outputFile;
    then
      echo "  - $func"
    fi
  done
fi

echo

rm "$outputFile"
