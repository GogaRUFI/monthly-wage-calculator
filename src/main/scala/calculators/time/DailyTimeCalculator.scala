package calculators.time

import models.Shift
import models.Shift._
import org.joda.time.LocalTime

import scala.annotation.tailrec

class DailyTimeCalculator extends OverTimeCalculator with EveningTimeCalculator {

	def calculate(shifts: Seq[Shift]) = {
		calculateTime(shifts)
	}

	private[time] def calculateTime(shifts: Seq[Shift]): TimeResult = {
		shifts.foldLeft(TimeResult.Zero)((result, shift) => calculateTime(shift, result))
	}

	private[time] def calculateTime(shift: Shift, result: TimeResult): TimeResult = {
		if (shift.startTime isEqual shift.endTime)
			TimeResult.Zero
		else
			calculateTimeBySteps(getWithOffset(shift.startTime), shift.endTime, result)
	}

	private[time] def getWithOffset(time: LocalTime) = {
		time.withFieldAdded(Duration, Offset)
	}

	@tailrec
	private def calculateTimeBySteps(currentTime: LocalTime, endTime: LocalTime, currentResult: TimeResult): TimeResult = {
		val result = nextTimeResult(currentTime, currentResult)
		if (currentTime isEqual endTime)
			result
		else {
			val nextTime = getWithOffset(currentTime)
			calculateTimeBySteps(nextTime, endTime, result)
		}
	}

	protected def nextTimeResult(time: LocalTime, result: TimeResult) = {
		result + TimeResult(Offset, eveningTime(time.getMillisOfDay), overTime(result.total))
	}

}

object DailyTimeCalculator {
	def millisByHourAndMinutes(hoursOfDay: Int, minutesOfDay: Int) = {
		new LocalTime(hoursOfDay, minutesOfDay).getMillisOfDay
	}
}
