import java.text.SimpleDateFormat

//папка, куда будет сохранен файл
def path = "D:\\EPAM\\groovy_projects\\files_scripts\\"
//итоговый файл
File file = new File(path, '1000dates.txt')

def sb = new StringBuilder()
def separator = System.getProperty("line.separator")
//функция генерирует дату в заданном диапазоне
def start = Date.parse('yyyy-MM-dd', '0000-01-01')
def end   = Date.parse('yyyy-MM-dd', '2020-01-01')

static Date randomDate(Range<Date> range) {
        range.from + new Random().nextInt(range.to - range.from + 1)
}

//задаем формат даты
def sdf = new SimpleDateFormat("yyyy-MM-dd")

//создаем даты и записываем в файл
1000.times {sb << sdf.format(randomDate(start..end)) + separator}
file << sb