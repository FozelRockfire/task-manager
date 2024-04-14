# task-manager
Простая система управления задачами с использованием Spring Boot и Spring Data JPA. 

Реализованы эндпоинты на создание, получение, модфикацию и удаление задач, описание приведено при помощи sprindoc аннотаций. Для просмотра необходимо запустить приложение и перейти по ссылке: _http://localhost:8081/swagger-ui/index.html_

Контроллер и сервис покрыты unit-тестами.

Для старта приложения необходимо запустить postgreSQL через docker.compose или изменить настройки подключений к бд в application.yml

Стек технологий:
`Java 17` `Maven` `Spring` `PostgreSQL` `Flyway`

## Автор:<br>
Попов Илья ([FozelRockfire](https://github.com/FozelRockfire))<br>
