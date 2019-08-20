# Сервис ms-translator

## Установка, настройка и запуск

### Зависимости

* Java OpenJDK 11
* SpringBoot 2.1.6.RELEASE
* Maven
* Lombok 1.18.6


### Параметры приложения

Все конфигурации проекта лежат в файле
```
application.yaml
```

### Сборка приложения

Проект собирается maven’ом из корневой папки командой:

```
mvn clean install
```

### Запуск приложения

Запустить приложение можно последовательно выполнив команды:
```
cd docker
docker-compose -f docker-compose-test.yaml up -d
```
### Остановка приложения

Остановить приложение можно последовательно выполнив команды:
```
cd docker
docker-compose -f docker-compose-test.yaml down 
```