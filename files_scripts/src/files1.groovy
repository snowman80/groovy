//Создает 10 файлов из массива names с расшиирением txt, заполняет их случайными целыми числами
//от 1 до 200 в каждой строке

//папка, куда будут сохранены файлы
def path = "D:\\EPAM\\groovy_projects\\files_scripts\\"
//генератор чисел и запись их в файл по одному в каждой строке
def fileWriter = {filename ->
        def sb = new StringBuilder()
        for (i=0; i<100000; i++) {
        sb << Math.round(Math.random()*200)
        sb  << "\n"
        }
        filename << sb.toString()
 }
//создаем файлы и сразу их заполняем
def fileCreator = {name -> fileWriter(new File("$path$name"+".txt"))}
//имена файлов
def names = ["file1", "file2", "file3", "file4", "file5", "file6", "file7", "file8", "file9", "file10"]

names.each {fileCreator(it)}
