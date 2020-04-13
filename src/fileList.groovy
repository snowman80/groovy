import groovy.io.FileType
//выводит все файлы в папке
def path = 'C:\\Windows\\'
dir = new File(path)
dir.eachFile(FileType.FILES){println it}
