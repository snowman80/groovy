//Создает 10 файлов из массива names с расширением txt, заполняет их случайными целыми числами
//от 1 до 200 в каждой строке

//папка, куда будут сохранены файлы
def path = "D:\\EPAM\\groovy_projects\\files_scripts\\"
//генератор чисел и запись их в файл по одному в каждой строке
def fileWriter = {filename ->
        def sb = new StringBuilder()
        (1..99999).each {
        sb << (int)(Math.random()*200)+1
        sb  << "\n"
        }
        //дописываем  последнюю строку без перевода каретки
        sb << (int)(Math.random()*200)+1
        filename << sb.toString()
 }
//создаем файлы и сразу их заполняем
def fileCreator = {name -> fileWriter(new File("$path", "$name"))}
//имена файлов
def names = ["file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt", "file6.txt", "file7.txt", "file8.txt", "file9.txt", "file10.txt"]

names.each {fileCreator(it)}