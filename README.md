### PeopleGPT Backend

📍 빌드 방법
```
./gradlew build
```

📍 실행 방법
```
java -jar ./app/build/libs/app.jar
```

📍 폴더 구조
```
📂 src  
 ┣ 📂 main  
 ┃ ┣ 📂 java  
 ┃ ┃ ┣ 📂 peoplegpt  
 ┃ ┃ ┃ ┣ 📂 console  
 ┃ ┃ ┃ ┃ ┗ 📄 GPTConsole  
 ┃ ┃ ┃ ┣ 📂 domain  
 ┃ ┃ ┃ ┃ ┣ 📂 comment  
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 controller  
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 model  
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 repository  
 ┃ ┃ ┃ ┃ ┃ ┗ 📂 service  
 ┃ ┃ ┃ ┃ ┣ 📂 global.model.entity  
 ┃ ┃ ┃ ┃ ┣ 📂 post  
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 controller  
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 model  
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 repository  
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 service  
 ┃ ┃ ┃ ┃ ┃ ┗ 📄 PostFactory  
 ┃ ┃ ┃ ┃ ┣ 📂 user  
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 controller  
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 model  
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 repository  
 ┃ ┃ ┃ ┃ ┃ ┗ 📂 service  
 ┃ ┃ ┃ ┃ ┗ 📄 UserFactory  
 ┃ ┃ ┃ ┣ 📂 global  
 ┃ ┃ ┃ ┃ ┣ 📄 temp.java  
 ┃ ┃ ┃ ┃ ┗ 📄 App  
 ┣ 📂 resources  
 ┃ ┣ 📄 comment_data.txt  
 ┃ ┣ 📄 log4j2.xml  
 ┃ ┣ 📄 post_data.txt  
 ┃ ┗ 📄 user_data.txt  
```

