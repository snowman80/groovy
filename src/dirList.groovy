//выводит подпапки  в папке
def path = 'C:\\Windows\\'
dir = new File(path)
dir.eachDir{println it}