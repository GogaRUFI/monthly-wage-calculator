package calculators.time

import calculators.time.DailyTimeCalculator._
import calculators.time.EveningTimeCalculator._
import models.Shift._

trait EveningTimeCalculator {
	self: DailyTimeCalculator =>
	
	protected def eveningTime(time: Long) = {
		if (Midnight < time && time <= AfterMidLimit || BeforeMidLimit < time && time < MidnightFull || time == Midnight)
			Offset
		else 0
	}
}

private object EveningTimeCalculator {
	val BeforeMidLimit = millisByHourAndMinutes(18, 0)
	val Midnight = millisByHourAndMinutes(0, 0)
	val MidnightFull = millisByHourAndMinutes(23, 59) + millisByHourAndMinutes(0, 1)
	val AfterMidLimit = millisByHourAndMinutes(6, 0)
}
