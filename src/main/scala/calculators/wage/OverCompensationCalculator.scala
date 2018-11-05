package calculators.wage

import calculators.wage.DailyWageCalculator._
import calculators.wage.OverCompensationCalculator._

trait OverCompensationCalculator {
	self: DailyWageCalculator =>

	protected def over(time: Long) = {
		calculateOverTime(millisToBigDecimal(time))
	}

	private def calculateOverTime(over: BigDecimal) = over match {
		case _ if over >= SecondHourLimit => calcOverSecondLimit(over - SecondHourLimit)
		case _ if over >= FirstHourLimit => calcOverFirstLimit(over - FirstHourLimit)
		case _ => calcOverLimit(over)
	}
	
	private def calcOverSecondLimit(over: BigDecimal) = {
		over * HourlyWage + calcOverFirstLimit(FirstHourLimit)
	}

	private def calcOverFirstLimit(over: BigDecimal) = {
		over * SecondLimitPercent + calcOverLimit(FirstHourLimit)
	}

	private def calcOverLimit(over: BigDecimal) = {
		over * FirstLimitPercent
	}

}

private object OverCompensationCalculator {
	val FirstLimitPercent = HourlyWage * 0.25
	val SecondLimitPercent = HourlyWage * 0.5
	val FirstHourLimit = 2
	val SecondHourLimit = 4
}
