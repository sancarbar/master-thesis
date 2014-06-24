#!/bin/sh
adb shell pm uninstall -k com.tomdroid
export PROJECT_DIR_NAME=tomdroid
export TEST_PROJECT_DIR_NAME=TomdroidTest
export CURRENT_PATH=$(pwd)
#Delete previous generated Files 
cd $PROJECT_DIR_NAME
ant clean
if [ -d "/bin" ]; then
   rm -rf bin
fi
#Execute command in main Android project folder to generate build.xml file
android update project -p . -t android-19
cd ..
# #Delete previous generated Files
ant clean
cd $TEST_PROJECT_DIR_NAME
if [ -d "/bin" ]; then
   rm -rf bin
fi
# generate build.xml for test project based on the sources
android update test-project -m  $CURRENT_PATH/$PROJECT_DIR_NAME -p . 
#run test using emma coverage tool
ant emma debug install test
echo "Execution finished succesfully"
echo "Coverage Report generated at: "
echo "file:///${CURRENT_PATH}/${TEST_PROJECT_DIR_NAME}/bin/coverage.html"