package calculators.wage

import calculators.time.TimeResult
import calculators.wage.DailyWageCalculator._
import org.joda.time.LocalTime

class DailyWageCalculator extends EveningCompensationCalculator with OverCompensationCalculator {

	def calculate(time: TimeResult) = {
		val result = total(time.total) + evening(time.evening) + over(time.over)
		result.setScale(2, BigDecimal.RoundingMode.HALF_UP)
	}

	private def total(time: Long) = {
		millisToBigDecimal(time) * HourlyWage
	}

	protected def millisToBigDecimal(time: Long): BigDecimal = {
		val local = LocalTime.fromMillisOfDay(time)
		BigDecimal(local.getHourOfDay) + BigDecimal(local.getMinuteOfHour) / 60
	}

}

private object DailyWageCalculator {
	val HourlyWage = BigDecimal(3.75)
}


