import models.Employee
import org.joda.time.LocalDate

object PrintHelper {
	val MonthFormat = "MM/yyyy"

	def printResult(date: LocalDate, wages: Map[Employee, BigDecimal]) {
		printHeader(date)
		printBody(wages)
		println()
	}

	def printHeader(date: LocalDate) {
		println(s"Monthly Wages ${date.toString(MonthFormat)}")
	}

	def printBody(wages: Map[Employee, BigDecimal]) {
		wages.foreach { case (employee, wage) => printRow(employee.name, wage) }
	}

	def printRow(name: String, wage: BigDecimal) {
		println(s"$name, $$$wage")
	}
}
