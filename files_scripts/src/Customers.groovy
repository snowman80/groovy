import groovy.json.JsonSlurper

        String url = "https://services.odata.org/V2/Northwind/Northwind.svc/Customers"

        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection()
        httpClient.setRequestMethod("GET")
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0")
        httpClient.setRequestProperty('Accept','application/json')
        //work path
        def path = "D:\\EPAM\\groovy_projects\\files_scripts\\"
        def separator = System.getProperty("line.separator")
        try (BufferedReader inn = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {
            //parsing
            def jsonSlurper = new JsonSlurper().parse(inn)
            def sb = new StringBuilder()
            jsonSlurper.d.results.CustomerID.each {sb << it.toString() + separator}
            File output = new File(path,"customers.txt")
            //writing into file
            output << sb
        }