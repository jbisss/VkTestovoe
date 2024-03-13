# Тестовое задание на Java-разработчик

## Общая информация

Запросы выполняются по следующему паттерну **http://localhost:8080/api/\***, например: **http://localhost:8080/api/posts**

Авторизация для ендпоинтов:

- Логины: admin, posts, albums, users
- Пароль: password

## Реализованные возможности

- Проксирование запросов к <https://jsonplaceholder.typicode.com/>

Реализованы обработчики (GET, POST, PUT, DELETE) для albums, posts, users, которые проксируют соответствующие запросы на ресурс указанный выше. Контроллеры представлены в [соответствующем](https://github.com/jbisss/VkTestovoe/tree/master/src/main/java/ru/vktestovoe/jbisss/controller) пакете. Контроллеры очень тонкие - реализация запросов представлена в пакете с [сервисами](https://github.com/jbisss/VkTestovoe/tree/master/src/main/java/ru/vktestovoe/jbisss/service), так как запросы однотипны - сервисы объединены параметризованным BaseService'ом.

- Базовая авторизация
- Ролевая модель доступа

В классе, конфигурирующем [Security](https://github.com/jbisss/VkTestovoe/blob/master/src/main/java/ru/vktestovoe/jbisss/config/SecurityConfig.java) описаны пользователи и роли, которые им соотвествуют, также описаны доступы к ендпоинтам в зависимости от ролей пользователей.

- Аудит действий

Аудит действий реализован с помощью [AOP-аспекта](https://github.com/jbisss/VkTestovoe/blob/master/src/main/java/ru/vktestovoe/jbisss/aspect/RequestHandlerAspect.java), в котором происходит логирование при использовании сервисов, выполняющих запросы. Ведётся аудит <u>времени выполнения запроса</u>, <u>тип запроса (GET, POST, PUT, DELETE)</u>, <u>URL запроса</u>, <u>тело запроса</u>, <u>пользователя, соверщающего запрос вместе с его ролью</u>.

- Inmemory cash

Реализован простой inmemory-cash с помощью HashMap'ы, который кэширует запросы, реализация представлена [тут](https://github.com/jbisss/VkTestovoe/tree/master/src/main/java/ru/vktestovoe/jbisss/service/api). По ссылке можно найти сервис, который делает http-запрос к ресурсу, и сервис кеширования, а также сервис, который определеяет, вид получения ответа по запросу - из кеша или по http-запросу.

- Unit-тесты

[Здесь](https://github.com/jbisss/VkTestovoe/tree/master/src/test/java/ru/vktestovoe/jbisss/service) описаны простые unit-тесты, покрывающие сервисы.
