//папка в которой будем работать, и в которой лежат исходные файлы
def path = "D:\\EPAM\\groovy_projects\\files_scripts\\"

//определяем системный разделитель строк
def separator = System.getProperty("line.separator")
//файл источник
def source = new File(path, "result.csv")
def sb = new StringBuilder()
//выходной файл
def result = new File(path, "result_tmp.csv")
String[] val = ""
int summa = 0
//проходим по файлу построчно, записывая значения, разделенные ',' во временный массив
//после этого суммируем значения в переменную summa
//записываем в sb первоначальную строку + сумму и сепаратор, обнуляем временный массив и сумму
source.eachLine {val = it.split(',')
    (0..val.size()-1).each {
        summa = summa + Integer.parseInt(val[it])
    }
        sb << it + "," + summa + separator
        summa = 0
        val = ""
    }
//записываем в выходной  файл итоговую строку
result << sb
//удаляем исходный файл
source.delete()
//переименовываем выходной файл в имя исходного
result.renameTo(path + "result.csv")