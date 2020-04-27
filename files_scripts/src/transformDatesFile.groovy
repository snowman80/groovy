import java.text.SimpleDateFormat

//папка, в которой лежит файл и исходными датами
def path = "D:\\EPAM\\groovy_projects\\files_scripts\\"
//исходный файл
File file = new File(path, '1000dates.txt')
//промежуточный файл
File file_tmp = new File(path, '1000dates_tmp.txt')

def sb = new StringBuilder()
def separator = System.getProperty("line.separator")
def sdf = new SimpleDateFormat('dd.MM.yyyy')

file.eachLine {sb << sdf.format(Date.parse('yyyy-MM-dd', it)) + separator}
file_tmp << sb
//удаляем исходный файл
file.delete()
//переименовываем выходной файл в имя исходного
file_tmp.renameTo(path + "10000dates.txt")