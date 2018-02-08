#!/bin/sh
# chkconfig 2345 on

WORKSPACE_DIR=`pwd`
CATALINA_DIR="/opt/zhuoerhang/server/apache-tomcat-8.5.27"
APP_NAME="wms"
BUILD_MODE=$2
catalina_run_start_exec="$CATALINA_DIR/bin/catalina.sh start"
catalina_run_stop_exec="$CATALINA_DIR/bin/shutdown.sh"

start() {
	echo ----------------------------------------------------------------------------------------------------------------
	echo - Prepare to build WMS ...
	echo ----------------------------------------------------------------------------------------------------------------
	cd $WORKSPACE_DIR
	echo Building [$APP_NAME] application as $BUILD_MODE...
	mvn clean install -Dmaven.test.skip=true -P$BUILD_MODE

	echo ----------------------------------------------------------------------------------------------------------------
	echo - copy application [$APP_NAME] to [$CATALINA_DIR/webapps] ...
	echo ----------------------------------------------------------------------------------------------------------------
	echo Removing application [$APP_NAME] from [$CATALINA_DIR/webapps] ...
	rm -rf $CATALINA_DIR/webapps/$APP_NAME.war
	rm -rf $CATALINA_DIR/webapps/$APP_NAME

	echo Copy application [$APP_NAME] to [$CATALINA_DIR/webapps] ...
	cp $WORKSPACE_DIR/target/$APP_NAME.war $CATALINA_DIR/webapps/$APP_NAME.war

	echo ----------------------------------------------------------------------------------------------------------------
	echo - Deploy application [$APP_NAME]
	echo ----------------------------------------------------------------------------------------------------------------
	echo Stoping catalina container ...
	$catalina_run_stop_exec

	echo Starting catalina container ...
	$catalina_run_start_exec
	echo Application [$APP_NAME] has stated
}

build() {
	cd $WORKSPACE_DIR
	echo Building [$APP_NAME] application as $BUILD_MODE...
	mvn clean install -Dmaven.test.skip=true -P$BUILD_MODE
	echo application [$APP_NAME] has built
}

copy() {
	echo Removing [$APP_NAME] from [$CATALINA_DIR/webapps] ...
	rm -rf $CATALINA_DIR/webapps/$APP_NAME.war
	rm -rf $CATALINA_DIR/webapps/$APP_NAME
	echo copy [$APP_NAME] application to [$CATALINA_DIR/webapps] ...
	cp $WORKSPACE_DIR/target/$APP_NAME.war $CATALINA_DIR/webapps/$APP_NAME.war
	echo [$APP_NAME.war] has been copied to [$CATALINA_DIR/webapps]
}

# See how we were called.
case "$1" in
start)
start
;;
build)
build
;;
copy)
copy
;;
*)
echo $"Usage: deploy.sh {start|build|copy} {dev|test|prod}"
RETVAL=1
esac
exit $RETVAL