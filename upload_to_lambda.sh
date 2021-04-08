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
      "sandboxRegister"
      "sandboxLogin"
      "sandboxLogout"
    ) ;;
esac

jarFile="./out/artifacts/SandboxHackathonServer_jar/SandboxHackathonServer.jar"
outputFile="output.txt"

for func in "${lamdaFunctions[@]}";
do
  aws lambda update-function-code --function-name "$func" --zip-file fileb://"$jarFile" >> "$outputFile"
done


if grep -q "Successful" "$outputFile";
then
  echo
  echo "SUCCESSFUL UPLOADS:"
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
