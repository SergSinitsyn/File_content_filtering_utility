# Утилита Фильтрации Содержимого Файлов

Это тестовое задание для курса Java от ШИФТ, которое представляет собой утилиту для фильтрации
содержимого
файлов.

## Описание

- Утилита принимает на вход несколько файлов, содержащих в перемешку целые числа, строки и
  вещественные числа. В качестве разделителя используется перевод строки.
- Утилита записывает разные типы данных в разные файлы: целые числа в один выходной файл,
  вещественные в другой, строки в третий.v
- По умолчанию файлы с результатами располагаются в текущей папке с
  именами `integers.txt`, `floats.txt`, `strings.txt`.
- Файлы с результатами создаются по мере необходимости. Если какого-то типа данных во входящих
  файлах нет, то файл для этого типа данных не будет создаваться.
- В процессе фильтрации данных собирается статистика по каждому типу данных.
- Поддерживаетмся статистика двух видов: краткая и полная.
- Выбор статистики производится опциями -s и -f соответственно.
- Краткая статистика содержит только количество элементов записанных в исходящие файлы.
- Полная статистика для чисел дополнительно содержит минимальное и максимальное значения, сумму и
  среднее.
- Полная статистика для строк, помимо их количества, содержит также размер самой короткой строки и
  самой длинной.
- Статистику по каждому типу отфильтрованных данных утилита выводит в консоль.

## Требования к реализации

- Все возможные виды ошибок должны быть обработаны.
- Программа не должна «падать».
- Если после ошибки продолжить выполнение невозможно, программа должна сообщить об этом пользователю
  с указанием причины неудачи.
- Частичная обработка при наличии ошибок более предпочтительна чем останов программы.
- Код программы должен быть «чистым».
- Для реализации необходимо использовать язык программирования Java, допустимо использовать
  стандартные системы сборки проекта (Maven, Gradle)

## Установка

- Версия java - `JDK 18`.
- Система сборки - `Apache Maven 3.8.8`.
- Использованные библиотеки
    - [JCommander](https://mvnrepository.com/artifact/com.beust/jcommander): анализ командной
      строки.
    -

## Использование

Для запуска утилиты используйте команду:

`java -jar <имя_проекта>.jar <имена_файлов>`

Дополнительные опции:

`-o <путь>`: задает путь для результатов.\
`-p <префикс>`: задает префикс имен выходных файлов.\
`-a`: задает режим добавления в существующие файлы.\
`-s`: выводит краткую статистику по каждому типу данных.\
`-f`: выводит полную статистику по каждому типу данных.\

## Лицензия

Этот проект лицензирован под лицензией MIT.