import groovy.xml.MarkupBuilder

//папка в которой будем работать
def path = "D:\\EPAM\\groovy_projects\\files_scripts\\"

//определяем системный разделитель строк
def separator = System.getProperty("line.separator")

//функция принимает максимальное число, возвращает случайное от 1 до этого числа включительно
static int rand(int x) {(int)(1 + Math.random()*x)}

//Функция генерирует случайный UUID
def uuidGen = {UUID.randomUUID().toString()}

def map = new HashMap()
//создаем 1000 прямоугольников и добавляем в HashMap
1000.times {
    def uuid = uuidGen()
    map.put(uuid, new Square(length: rand(1000), width: rand(1000), uuid: uuid))
}
//6.1 используем StringBuilder
sb = new StringBuilder()
sb << "<root>" + separator
map.each {
    sb << "     <element id=\'$it.key\'>" + separator
    sb << "         <width>$it.value.width</width>" + separator
    sb << "         <length>$it.value.length</length>" + separator
    sb << "     </element>" + separator
}
sb << "</root>"
//записываем в файл итоговую строку
def xmlFile = new File(path, "square.xml")
xmlFile << sb.toString()

//6.2 используем MarkupBuilder

def builder = new MarkupBuilder(new FileWriter(new File(path, "square2.xml")))

    builder.root {
map.each {elem -> element(id: elem.key) {
            width(elem.value.width)
            length(elem.value.length)
        }
    }
}