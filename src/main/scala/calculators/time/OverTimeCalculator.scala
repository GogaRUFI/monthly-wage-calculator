package calculators.time

import calculators.time.DailyTimeCalculator._
import calculators.time.OverTimeCalculator._
import models.Shift._

trait OverTimeCalculator {
	self: DailyTimeCalculator =>
	
	protected def overTime(totalTime: Long) = {
		if (totalTime >= RegularLimit)
			Offset
		else 0
	}
}

private object OverTimeCalculator {
	val RegularLimit = millisByHourAndMinutes(8, 0)
}
