//папка в которой будем работать, и в которой лежат исходные файлы
def path = "D:\\EPAM\\groovy_projects\\files_scripts\\"

//определяем системный разделитель строк
def separator = System.getProperty("line.separator")
//файл источник
def source = new File(path, "result.csv")
//выходной файл
def result = new File(path, "result_tmp.csv")
def sb = new StringBuilder()

//проходим по файлу построчно, записывая значения, разделенные ',' во временный массив
//записываем в sb первоначальную строку + сумму и сепаратор
source.eachLine {
    def val = it.split(',')
        sb << it + val.sum() {Integer.parseInt(it)} + separator
    }
//записываем в выходной  файл итоговую строку
result << sb.toString()
//удаляем исходный файл
source.delete()
//переименовываем выходной файл в имя исходного
result.renameTo(path + "result.csv")