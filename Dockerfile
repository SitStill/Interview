# 使用基础镜像
FROM openjdk:11-jre-slim

# 设置工作目录
WORKDIR /app

# 复制应用的 JAR 文件到容器中
COPY /Users/dzh/Documents/Interview/target/my-survey-app-1.0-SNAPSHOT.jar /app/backend.jar

# 暴露应用运行的端口
EXPOSE 7000

# 启动应用
CMD ["java", "-jar", "backend.jar"]

