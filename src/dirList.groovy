import groovy.io.FileType

//выводит подпапки  в папке
def path = 'D:\\EPAM\\'
def dir = new File(path)

dir.eachFileRecurse(FileType.DIRECTORIES){println it}