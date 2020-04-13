//замыкание возвращает число, у которого последняя цифра больше
def comparison = {num1, num2 ->
    if (num1%10 > num2%10)
        return num1
    else return num2
}
println(comparison(11, 100))