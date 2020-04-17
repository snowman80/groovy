//замыкание считает длину файла в байтах
def fileLength = {name ->
    def file = new File(name)
    if(!file.exists()) {
        println "file not found"
    }
     else println(file.length())}
//передаем в замыкание имя файла
fileLength("D:\\EPAM\\Xpath\\sample.xml")
