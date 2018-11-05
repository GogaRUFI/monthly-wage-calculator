import PrintHelper._
import calculators.time.{DailyTimeCalculator, TimeResult}
import calculators.wage.DailyWageCalculator

import com.github.tototoshi.csv._
import java.io.File

import models.item.Item
import org.joda.time.LocalDate
import org.slf4j.LoggerFactory

object MonthlyWageCalculator {

	val DailyWageCalc = new DailyWageCalculator
	val DailyTimeCalc = new DailyTimeCalculator

	val log = LoggerFactory.getLogger(getClass)

	def main(args: Array[String]) = args match {
		case Array(path, _*) =>
			val items = readFile(path) flatMap Item.get
			val wages = this calcWagesByMonthAndEmployee items
			wages foreach (printResult _).tupled
		case _ =>
			println("Please set the full path to a CSV file as the first argument")
	}

	private def readFile(path: String) = try {
		val file = new File(path)
		val reader = CSVReader open file
		reader allWithHeaders()
	} catch {
		case e: Throwable =>
			log error s"Can't read CSV file $path: $e"
			List.empty
	}

	private def calcWagesByMonthAndEmployee(items: Seq[Item]) =
		items groupBy (_.date.getMonthOfYear) map {
			case (_, monthItems) =>
				monthItems.head.date -> calcWagesByEmployee(monthItems)
		}

	private def calcWagesByEmployee(items: Seq[Item]) =
		items groupBy (_.employee) map {
			case (employee, employeeItems) => employee -> calculateMonthlyWages(employeeItems)
		}

	private def calculateMonthlyWages(items: Seq[Item]): BigDecimal = {
		calculateMonthlyWages(items groupBy (_.date))
	}

	private def calculateMonthlyWages(items: Map[LocalDate, Seq[Item]]): BigDecimal =
		items.foldLeft(BigDecimal(0)) {
			(wage, itemsByDate) => calculateDailyWages(itemsByDate) + wage
		}

	private def calculateDailyWages(items: (LocalDate, Seq[Item])): BigDecimal =
		items match {
			case (_, dailyItems) => calculate(dailyItems)
		}

	private def calculate(items: Seq[Item]): BigDecimal = {
		val time = DailyTimeCalc.calculate(items.map(_.shift))
		calculate(time)
	}

	private def calculate(time: TimeResult): BigDecimal = {
		DailyWageCalc.calculate(time)
	}

}