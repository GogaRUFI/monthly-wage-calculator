package models

import calculators.time.DailyTimeCalculator._
import org.joda.time.{DurationFieldType, LocalTime}

case class Shift(startTime: LocalTime, endTime: LocalTime)

object Shift {
	val OffsetMinutes = 15
	val Offset = millisByHourAndMinutes(0,OffsetMinutes)
	val Duration = DurationFieldType.millis()
}