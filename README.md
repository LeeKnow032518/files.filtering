# files.filtering
## Утилита фильтраци содержимого файла.

Используемые инструменты:
<ul>
  <li> Java 17</li>
  <li> Maven 3.9.10</li>
  <li> commons-cli 1.9.0 (https://commons.apache.org/proper/commons-cli/)</li>
</ul>

### Инструкция по запуску

После клонирования проекта, зайти в папку проекта, перенести туда входные файлы и ввести команду
<pre>
  <code>mvn clean package</code>
</pre>

В папке <code>/target</code>  появится jar файл для запуска приложения
<pre>
  <code>cd target</code>
</pre>

Для запуска приложения ввести команду
<pre>
  <code> java -jar files.filtering-1.jar</code> <требуемые параметры>.
</pre>

### Альтернатива запуска

В папке <code>/jar</code> лежит уже собранный jar файл, поместить входные файлы в эту папку
<pre>
  <code>cd jar</code>
</pre>

Для запуска аналогичная команда
<pre>
  <code> java -jar files.filtering-1.jar</code> <требуемые параметры>.
</pre>
