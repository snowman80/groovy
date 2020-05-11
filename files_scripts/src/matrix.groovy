//папка в которой будем работать, и в которой лежат исходные файлы
def path = "D:\\EPAM\\groovy_projects\\files_scripts\\"

//определяем системный разделитель строк
def separator = System.getProperty("line.separator")

//создаем List файлов
def names = ["file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt", "file6.txt", "file7.txt", "file8.txt", "file9.txt", "file10.txt"]
ArrayList listOfFiles = new ArrayList<>()
names.each {listOfFiles.add(new File(path, it))}

//создаем массив reader`ов
ArrayList readerlist = new ArrayList()
listOfFiles.each {readerlist.add(new BufferedReader(new FileReader(it)))}

//записываем в sb построчно из каждого Bufferedreader
def sb = new StringBuilder()
100000.times{readerlist.each {sb << it.readLine() + ","}
//удаляем последнюю запятую
                sb.deleteCharAt(sb.size()-1)
                sb << separator
                }

//записываем результат в файл
File resultFile = new File(path, "result.csv")
resultFile << sb.toString()