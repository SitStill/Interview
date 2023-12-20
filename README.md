## Survey App
这个项目是一个简单的调查问卷应用，包括前端（React）、后端（Java，Spark Framework）和数据库（SQLite）。

# 前端
*技术栈：*
- React
- CSS
- Fetch API

*项目结构*
'''
frontend/
|-- src/
|   |-- components/
|   |   |-- SurveyForm.js
|   |   |-- SearchBar.js
|   |   |-- SurveyList.js
|   |   |-- ...
|   |   |-- SubmitSurveyPage.js
|   |   |-- SearchSurveyPage.js
|   |   |-- ...
|   |-- App.js
|   |-- index.js
|-- public/
|-- package.json
|-- ...
'''

*运行*
‘’‘
cd frontend
npm install
npm start
’‘’


#后端
*技术栈*
- Java 1.8
- Spark Framework
- SQLite
- Javalin

*项目结构*
'''
backend/
|-- src/
|   |-- main/
|   |   |-- java/
|   |   |   |-- com.example.backend.controllers/
|   |   |   |   |-- SurveyController.java
|   |   |   |   |-- AuthenticationController.java
|   |   |   |-- com.example.backend.models/
|   |   |   |   |-- Survey.java
|   |   |   |   |-- User.java
|   |   |   |-- com.example.backend.repositories/
|   |   |   |   |-- SurveyRepository.java
|   |   |   |   |-- SurveyRepositoryImpl.java
|   |   |   |   |-- UserRepository.java
|   |   |   |   |-- UserRepositoryImpl.java
|   |   |   |-- com.example.backend.services/
|   |   |   |   |-- SurveyService.java
|   |   |   |   |-- SurveyServiceImpl.java
|   |   |   |   |-- SurveyService.java
|   |   |   |   |-- SurveyServiceImpl.java
|   |   |   |-- Main.java
|   |   |   |-- MySQLiteDataSource.java
|-- pom.xml
|-- ...

‘’‘

*运行*
‘’‘
cd backend
mvn clean install
java -jar target/backend-<version>.jar
’‘’

# 数据库
- SQLite
数据库文件: survey.db

*数据表*
- survey
- - id INTEGER PRIMARY KEY AUTOINCREMENT
- - user_id TEXT
- - question TEXT
- - answer TEXT
- - attachment TEXT

# 注意事项
- 在运行前端和后端之前，请确保你的系统已经安装了相应的运行环境（如 Node.js、Java、Maven）。
- 在项目根目录下可能包含其他配置文件和依赖，具体取决于项目结构和工具使用。





