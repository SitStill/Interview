# 使用基础镜像
FROM openjdk:11-jre-slim

# 设置工作目录
WORKDIR /app

# 复制应用的 JAR 文件到容器中
COPY target/my-survey-app-1.0-SNAPSHOT.jar /app/backend.jar

# 复制 Javalin JAR 到容器中
COPY target/lib/javalin-*.jar /app/lib/
COPY target/lib/slf4j-*.jar /app/lib/
COPY target/lib/kotlin-stdlib-*.jar /app/lib/
COPY target/lib/javax.servlet-api-*.jar /app/lib/
COPY target/lib/websocket-*.jar /app/lib/

# 复制 Jetty 相关的 JAR 文件到容器中
COPY target/lib/jetty-*.jar /app/lib/

# 暴露应用运行的端口
EXPOSE 8080

RUN mkdir -p /app/uploads
RUN chmod -R 755 /app/uploads

# 启动应用
CMD ["java", "-cp", "backend.jar:lib/*", "com.example.backend.Main"]