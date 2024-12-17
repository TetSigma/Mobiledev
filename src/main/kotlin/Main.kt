open class KontoBankowe(val numerKonta: String, var saldo: Double) {

    fun wplata(kwota: Double) {
        if (kwota > 0) {
            saldo += kwota
            println("Wpłacono $kwota Aktualne saldo: $saldo")
        } else {
            println("Kwota wpłaty musi być większa niż zero")
        }
    }

    fun wyplata(kwota: Double) {
        if (kwota > 0 && kwota <= saldo) {
            saldo -= kwota
            println("Wypłacono $kwota Aktualne saldo: $saldo")
        } else if (kwota <= 0) {
            println("Kwota wypłaty musi być większa niż zero")
        } else {
            println("Niewystarczające środki na koncie")
        }
    }

    fun sprawdzSaldo() {
        println("Aktualne saldo: $saldo")
    }
}

class KontoOszczędnościowe(numerKonta: String, saldo: Double, var oprocentowanieLokaty: Double) : KontoBankowe(numerKonta, saldo) {

    fun dodajLokate(miesiace: Int) {
        if (miesiace > 0) {
            val odsetki = saldo * oprocentowanieLokaty * (miesiace / 12.0)
            saldo += odsetki
            println("Po dodaniu lokaty na $miesiace miesiące, saldo wzrosło o $odsetki Aktualne saldo: $saldo")
        } else {
            println("Liczba miesięcy musi być większa niż zero")
        }
    }
}

fun main() {
    val konto = KontoOszczędnościowe("123456789", 1000.0, 0.05)

    while (true) {
        println("\nWybierz operację:")
        println("1. Wpłata środków")
        println("2. Wypłata środków")
        println("3. Sprawdzenie salda")
        println("4. Dodaj lokatę")
        println("5. Wyjście")

        print("Wybór: ")
        val wybor = readLine()?.toIntOrNull()

        when (wybor) {
            1 -> {
                print("Podaj kwotę do wpłaty: ")
                val kwota = readLine()?.toDoubleOrNull()
                if (kwota != null) {
                    konto.wplata(kwota)
                } else {
                    println("Nieprawidłowa kwota.")
                }
            }
            2 -> {
                print("Podaj kwotę do wypłaty: ")
                val kwota = readLine()?.toDoubleOrNull()
                if (kwota != null) {
                    konto.wyplata(kwota)
                } else {
                    println("Nieprawidłowa kwota.")
                }
            }
            3 -> {
                konto.sprawdzSaldo()
            }
            4 -> {
                print("Podaj liczbę miesięcy lokaty: ")
                val miesiace = readLine()?.toIntOrNull()
                if (miesiace != null) {
                    konto.dodajLokate(miesiace)
                } else {
                    println("Nieprawidłowa liczba miesięcy")
                }
            }
            5 -> {
                println("Koniec programu")
                break
            }
            else -> {
                println("Nieprawidłowy wybór, spróbuj ponownie.")
            }
        }
    }
}