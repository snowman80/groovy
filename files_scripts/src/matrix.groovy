//папка в которой будем работать, и в которой лежат исходные файлы
def path = "D:\\EPAM\\groovy_projects\\files_scripts\\"

//определяем системный разделитель строк
def separator = System.getProperty("line.separator")

//считываем файлы в list построчно
def list1 = new File(path, 'file1.txt').collect {it}
def list2 = new File(path, 'file2.txt').collect {it}
def list3 = new File(path, 'file3.txt').collect {it}
def list4 = new File(path, 'file4.txt').collect {it}
def list5 = new File(path, 'file5.txt').collect {it}
def list6 = new File(path, 'file6.txt').collect {it}
def list7 = new File(path, 'file7.txt').collect {it}
def list8 = new File(path, 'file8.txt').collect {it}
def list9 = new File(path, 'file9.txt').collect {it}
def list10 = new File(path, 'file10.txt').collect {it}

//результирующая строка
def sb = new StringBuilder()

(0..99999).each {
    sb << (list1[it]+ "," + list2[it] + "," + list3[it]+ "," + list4[it]+ "," + list5[it]+ "," + list6[it]+ "," + list7[it]+ "," + list8[it]+ "," + list9[it]+ "," + list10[it] + separator)
}

//записываем результат в файл
File resultFile = new File(path, "result.csv")
resultFile << sb.toString()