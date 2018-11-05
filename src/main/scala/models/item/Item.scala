package models.item

import models.{Employee, Shift}
import org.joda.time.format.DateTimeFormat
import org.joda.time.{LocalDate, LocalTime}
import org.slf4j.LoggerFactory
import models.Shift._

case class Item(employee: Employee, date: LocalDate, shift: Shift)

object Item {

	import ItemField._

	val DateFormat = DateTimeFormat.forPattern("dd.MM.yyyy")
	val TimeFormat = DateTimeFormat.forPattern("HH:mm")

	val log = LoggerFactory.getLogger(getClass)

	def get(row: Map[String, String]): Option[Item] = try {
		Option(parseItem(row))
	} catch {
		case e: Throwable => log info s"Can't parse row $row: $e}"
			None
	}

	private def parseItem(row: Map[String, String]) = {
		Item(
			parseEmployee(row(Id), row(Name)),
			parseDate(row(Date)),
			parseShift(row(StartTime), row(EndTime))
		)
	}

	private def parseEmployee(id: String, name: String) = {
		Employee(id.toLong, name)
	}

	private def parseDate(date: String) = {
		LocalDate.parse(date, DateFormat)
	}

	private def parseShift(startTime: String, endTime: String) = {
		Shift(parseTime(startTime), parseTime(endTime))
	}

	private def parseTime(time: String) = {
		val local = LocalTime.parse(time, TimeFormat)
		checkAndReturn(local)
	}

	private def checkAndReturn(time: LocalTime) = {
		if(time.getMinuteOfHour % OffsetMinutes != 0)
			throw new Exception(s"Shift time $time can't be incremented with offset: $Offset")
		else time
	}
}
