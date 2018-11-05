package calculators.wage

import calculators.wage.EveningCompensationCalculator._

trait EveningCompensationCalculator {
	self: DailyWageCalculator =>

	protected def evening(time: Long) = {
		millisToBigDecimal(time) * EveningComp
	}
}

private object EveningCompensationCalculator {
	val EveningComp = BigDecimal(1.15)
}
