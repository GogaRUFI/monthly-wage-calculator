package calculators.time

import base.BaseTest
import models.Shift
import org.joda.time.LocalTime

class DailyTimeCalculatorTest extends BaseTest {

	test("Calculates time result for a set of shifts") {
		val calc = new DailyTimeCalculator
		def ltime(h: Int, m: Int) = new LocalTime(h, m)
		def ltimeMs(h: Int, m: Int) = ltime(h, m).getMillisOfDay

		val shifts = Seq(
			Shift(ltime(14, 0), ltime(20, 0)),
			Shift(ltime(0, 0), ltime(7, 0))
		)

		val expected = TimeResult(
			ltimeMs(13, 0),
			ltimeMs(8, 0),
			ltimeMs(5, 0)
		)

		val result = calc.calculate(shifts)
		result shouldEqual expected
	}

	test("Calculates time result for a overtime shift") {
		val calc = new DailyTimeCalculator
		def ltime(h: Int, m: Int) = new LocalTime(h, m)
		def ltimeMs(h: Int, m: Int) = ltime(h, m).getMillisOfDay

		val shifts = Seq(
			Shift(ltime(0, 0), ltime(9, 0))
		)

		val expected = TimeResult(
			ltimeMs(9, 0),
			ltimeMs(6, 0),
			ltimeMs(1, 0)
		)

		val result = calc.calculate(shifts)
		result shouldEqual expected
	}

	test("Calculates time result for a regular shift") {
		val calc = new DailyTimeCalculator
		def ltime(h: Int, m: Int) = new LocalTime(h, m)
		def ltimeMs(h: Int, m: Int) = ltime(h, m).getMillisOfDay

		val shifts = Seq(
			Shift(ltime(23, 0), ltime(5, 0))
		)

		val expected = TimeResult(
			ltimeMs(6, 0),
			ltimeMs(6, 0),
			ltimeMs(0, 0)
		)

		val result = calc.calculate(shifts)
		result shouldEqual expected
	}

}


