mkdir -p /tmp/pizzashop/spring-server-stub
rm -rf /tmp/pizzashop/spring-server-stub/*
rm -rf /tmp/pizzashop/spring-server-stub/.*

unzip /tmp/spring-server-generated.zip -d /tmp/pizzashop/spring-server-stub

rm src/main/java/com/fattazzo/pizzashop/controller/api/*

cp /tmp/pizzashop/spring-server-stub/src/main/java/com/fattazzo/pizzashop/controller/api/* src/main/java/com/fattazzo/pizzashop/controller/api

cp /tmp/pizzashop/spring-server-stub/src/main/java/com/fattazzo/pizzashop/model/api/* src/main/java/com/fattazzo/pizzashop/model/api
