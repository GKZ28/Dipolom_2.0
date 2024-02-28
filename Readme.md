# Дипломный проект. Гиоргадзе Константин.

- [**Задание**](https://github.com/netology-code/qa-diploma)
- [**План тестирования**](https://github.com/GKZ28/Dipolom_2.0/blob/main/documents/Plan.md)
- [**Отчет**](https://github.com/GKZ28/Dipolom_2.0/blob/main/documents/Report.md)

## Начало работы и запуск проекта

1. Клонировать репозиторий проекта командой `git clone`
2. Запустить Docker
3. Запустить проект в Intellij Idea

### Запуск контейнеров Docker в терминале Intellij Idea

1. Вводим команду:
    * `docker-compose up -d`
2. Вводим команду по запуску SUT, в зависимости от БД, для:
    * MySQL:
        * `java  -jar ./artifacts/aqa-shop.jar  -Dspring.datasource.url=jdbc:mysql://localhost:3306/app`
    * PostgreSQL:
        * `java  -jar ./artifacts/aqa-shop.jar  -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app`
3. Проверить открывается ли приложение по адресу: `http://localhost:8080/`

### Запуск тестов

1. При необходимости открыть новый терминал и выполнить команду:
    * MySQL:
        * `gradlew test -Durlbd=jdbc:mysql://localhost:3306/app --info`
    * PostgreSQL:
        * `gradlew test -Durlbd=jdbc:postgresql://localhost:5432/app --info`
2. Для открытия отчета выполнить следующие команды последовательно:
    * `gradlew allureReport`
    * `gradlew allureServe`
3. Для завершения работы выполнить следующие команды последовательно:
    * `ctrl + C` (в терминале где запущен SUT)
    * `docker-compose down`