**Task and Requirements:**

- A set of images is given  
- You need to write a Java program that recognizes which cards are on the table (using only the center of each image). For example, in the file `10c3s2d.png` the cards on the table are `10c 3s 2d`  
- The program will be tested on images randomly selected from the original set and removed from it  
- You must **not** use any ready-made text-recognition libraries. You must implement your own card-recognition algorithm  
- The source code of your solution must not exceed 500 lines, properly formatted  

- The program must be delivered in a form ready to run on a Windows desktop. The file `run.bat` should accept as a parameter the path to the folder with images. It should print to the console, for each file in the folder:  

- You must include **all** source files used in your solution  
- The program must be provided as a link to a ZIP file. Links to repositories (e.g. GitHub) will **not** be accepted  

**Recommendations:**

- The author of this task solved it in 100 lines of code. The best candidate so far used 160 lines. It’s okay if your solution takes up to 500 lines—but if it’s more (and not just comments), you should reconsider  

**Built-in Java functions that may be useful:**

```java
BufferedImage img = ImageIO.read(f);            // read an image from a file
ImageIO.write(img, "png", f);                   // write an image to a file
int w = img.getWidth(); int h = img.getHeight(); // get image dimensions
BufferedImage sub = img.getSubimage(x, y, w, h);// extract a region from the image
int rgb = img.getRGB(x, y);                     // get the color of a pixel at (x, y)
Color c = new Color(rgb);
int red   = c.getRed();
int green = c.getGreen();
int blue  = c.getBlue();
c.equals(c1);                                   // compare two colors


Russian:
Это тестовое задание от компании https://www.brainshells.io/, по которому не было получено никакого фидбека:
Код распознает весь набор данных, предоставленный компанией с точностью 100%

Задача и требования:

- Дано множество картинок
- Необходимо написать программу на Java, которая распознает, какие карты лежат на столе (только по центру картинки). Например, на картинке 10c3s2d.png на столе лежат карты 10c 3s 2d
- Тестирование программы будет осуществляться на картинках, случайно выбранных из исходного множества и удаленных из него
- Нельзя использовать готовые библиотеки для распознавания текста. Необходимо написать свой алгоритм распознавания карт
- Исходный код решения задачи не должен быть длиннее 500 строк с нормальным форматированием

- Программу нужно предоставить в виде, готовом к запуску на Windows десктопе. Файл run.bat параметром принимает путь до папки с картинками. В консоль распечатывается результат в виде "имя файла – карты" для всех файлов папки
- Программу нужно предоставить с исходными файлами
- В исходных файлах должен быть ВЕСЬ код, который был использован для решения задачи
- Программу нужно предоставить в виде ссылки на ZIP-файл. Ссылки на репозитории, например на github, не принимаются

Рекомендации:

- У автора этой задачи решение заняло 100 строк кода. У лучшего на данный момент кандидата – 160 строк. Ничего страшного, если ваше решение занимает 500 строк. Однако, если больше и это – не комментарии, то стоит задуматься

Для решения задачи могут быть полезны следующие функции, встроенные в Java:

- BufferedImage img = ImageIO.read(f); – зачтка картинки из файла
- ImageIO.write(img, "png", f); – запись картинки в файл
- img.getWidth(); img.getHeight(); – размеры картинки
- BufferedImage img1 = img.getSubimage(x, y, w, h); – взятие области в картинке
- img.getRGB(x, y); – взятие цвета точки по координате
- Color c = new Color(img.getRGB(x, y)); c.getRed(); c.getGreen(); c.getBlue(); c.equals(c1) – работа с цветом точки
