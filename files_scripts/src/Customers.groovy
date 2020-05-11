import groovy.xml.XmlSlurper

class HttpURLConnectionExample {
    static void sendGet() throws Exception {

        String url = "https://services.odata.org/V2/Northwind/Northwind.svc/Customers"

        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection()

        httpClient.setRequestMethod("GET")
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0")
        //строка параметра, влияющая на возврат xml
        httpClient.setRequestProperty('Accept','application/atom+xml')
        //папка, в которой будет сохранен файл
        def path = "D:\\EPAM\\groovy_projects\\files_scripts\\"
        //разделитель строк
        def separator = System.getProperty("line.separator")
        //из примера, через буфер
        try (BufferedReader inn = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {
            //парсим входной поток
            def list = new XmlSlurper().parse(inn)
            def sb = new StringBuilder()
            //обращаемся к ноде по ее пути и записываем в sb
            list.entry.content.properties.CustomerID.each {sb << it.toString() + separator}
            File output = new File(path,"customers.txt")
            //запись sb в файл
            output << sb
        }

    }

}
def obj1 = new HttpURLConnectionExample()

obj1.sendGet()