import groovy.xml.XmlSlurper

def path = "D:\\EPAM\\groovy_projects\\files_scripts\\"
File source = new File(path, "usa_employers.xml")
def separator = System.getProperty("line.separator")
def list = new XmlSlurper().parse(source)
//решаем одновременно 2 и 3 задачи, StringBuilder для каждого типа
def sbFN = new StringBuilder()
def sbLN = new StringBuilder()
//обращаемся к ноде по ее пути и записываем в sb
list.employee.personal_info.first_name.each {sbFN << it.toString() + separator}
list.employee.personal_info.last_name.each {sbLN << it.toString() + separator}
//для подчета и записи кол-ва используем Мар
Map<String, Integer> mapFN = new HashMap<String, Integer>()
sbFN.eachLine  {
    Integer count = mapFN.get(it)
    if ( count == null ) {
        count = 0
    }
    mapFN.put(it, count + 1)
}
Map<String, Integer> mapLN = new HashMap<String, Integer>()
sbLN.eachLine  {
    Integer count = mapFN.get(it)
    if ( count == null ) {
        count = 0
    }
    mapLN.put(it, count + 1)
}
//читаем Мар в sb
def resultFN = new StringBuilder()
mapFN.each {resultFN << it.key << "," << it.value << separator}

//запись sb в файл
File output = new File(path,"countOfFirstNames.csv")
output << resultFN
//повторяем для last_names
def resultLN = new StringBuilder()
mapLN.each {resultLN << it.key << "," << it.value << separator}

//запись sb в файл
File output2 = new File(path,"countOfLastNames.csv")
output2 << resultFN