## 1. Запуск проекта

1. Первым делом нам надо собрать jar архивы с нашими spring boot приложениями. Для этого в терминале в корне нашего проект выполните команду:

Для gradle: `./gradlew clean build` (если пишет Permission denied тогда сначала выполните `chmod +x ./gradlew`)

- После успешной сборки в папке будет находиться jar файл:`RZD-Interview-0.0.1-SNAPSHOT.jar`;
- В терминале выполнить команду по сборке images и containers: ```docker-compose up```;


### Завершение работы
- Выход из приложения: в терминале нажать "Ctrl+C"
- Удаление Docker контейнера: ```docker-compose down```

## 2. Авторизация

```text
Логин       Пароль      Доступ

User   -    User1234     USER
Guest   -   Guest1234    GUEST
Admin   -   Admin1234    ADMIN


```

## 3. Настройка OpenAPI definition

- Для доступа требуется перейти по одной из следующих ссылок и авторизоваться в системе *(см. пункт 2)*.

http://localhost:8085/swagger-ui/index.html

http://localhost:8085/swagger-ui.html

