[![Build Status](https://travis-ci.org/zagamaza/sub-learn-translator.svg?branch=master)](https://travis-ci.org/zagamaza/sub-learn-translator)
# Сервис sub-learn-translator

## Установка, настройка и запуск

### Зависимости

* Java OpenJDK 11
* SpringBoot 2.1.6.RELEASE
* Spring Cloud 2.1.2.RELEASE
* Gradle 5.4.1
* Lombok 1.18.6


### Параметры приложения

Все конфигурации проекта лежат в файле
```
application.yaml
```

### Сборка приложения

Проект собирается gradle’ом из корневой папки командой:

```
build.sh  - Linux
build.bat - Windows
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
