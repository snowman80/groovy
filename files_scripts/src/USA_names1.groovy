import groovy.xml.MarkupBuilder
import java.text.SimpleDateFormat

//папка в которой будем работать
def path = "D:\\EPAM\\groovy_projects\\files_scripts\\"
//функция генерирует дату в заданном диапазоне, возьмем 100 лет
def start = Date.parse('yyyy-MM-dd', '1920-01-01')
def end   = Date.parse('yyyy-MM-dd', '2020-01-01')
def sdf = new SimpleDateFormat("yyyy-MM-dd")

static Date randomDate(Range<Date> range) {
    range.from + new Random().nextInt(range.to - range.from + 1)
}
//функция генерирует случайный пол
static String randGender() {new Random().nextBoolean() ? "male":"female"}
//файлы с именами и фамилиями
def namesBoysFile = new File(path, "USA_Names_Boys.txt")
def namesGirlsFile = new File(path, "USA_Names_Girls.txt")
def surnamesFile = new File(path, "surnames.txt")
//находим первое слово после табуляции в каждой строке кроме первой и пишем в List
def arrNamesBoys = new ArrayList()
namesBoysFile.eachLine {
    it, line -> if (line>1) arrNamesBoys.add((it =~/(?<=\t)[A-Z]\w+/)[0])}
def arrNamesGirls = new ArrayList()
namesGirlsFile.eachLine {
    it, line -> if (line>1) arrNamesGirls.add((it =~/(?<=\t)[A-Z]\w+/)[0])}
//тут просто фамилии с маленькой буквы
def surnames = surnamesFile.collect{it.capitalize()}
//создаем xml
def builder = new MarkupBuilder(new FileWriter(new File(path, "usa_employees.xml")))

builder.root {1000.times {
    //для учета пола сохраним его в отдельную переменную
    def gend = randGender()
    employee {
        employment_info {
            start_date(sdf.format(randomDate(start..end)))
        }
        personal_info {
            gender(gend)
            //учитывая пол берем случайное имя из List
            if (gend == "male")
                first_name(arrNamesBoys.get(new Random().nextInt(arrNamesBoys.size())))
                    else first_name(arrNamesGirls.get(new Random().nextInt(arrNamesGirls.size())))
            last_name(surnames.get(new Random().nextInt(surnames.size())))
        }
    }
    }
}